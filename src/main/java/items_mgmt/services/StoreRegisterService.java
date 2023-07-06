package items_mgmt.services;

import java.sql.Timestamp;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*import common.domain.model.StoreRegister;
import common.domain.model.StoreRegisterRepo;
import storeMovementManager.model.StoreIssueMaster;
import storeMovementManager.model.StoreIssueRepo;
import storeMovementManager.model.StoreMovementMasterPK;
*/

@Service("storeRegisterServ")
public class StoreRegisterService implements I_StoreRegisterService 
{
/*
	private static final Logger logger = LoggerFactory.getLogger(StoreRegisterService.class);
	
	@Autowired
    private StoreRegisterRepo storeRegisterRepo;
		
	@Autowired(required=true)
    private StoreIssueRepo storeIssueRepo;
	
	
	// CREDIT & COUNTER SALE, MODE =0 
	 
	@Scheduled(fixedRate=3000)
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
    public void sale_Alloc() 
    {	    	
		logger.info("Sales Job Start");
		Optional<ArrayList<StoreRegister>> storeRegisters = null;
		storeRegisters = storeRegisterRepo.getAllRowsForMode0();
    	Integer item_seq_no_curr = 0;
    	Integer store_seq_no_curr = 0;
    	float needed_qty = (float)0;
    	float allocated_qty = (float)0;
    	float req_qty = (float)0;
    	float book_qty = (float)0;
    	float eqoh = (float)0;
    	float qoh = (float)0;
    	float eff = (float)0;
    	StoreIssueMaster sMaster = null;
    	    	
    	if(storeRegisters.isPresent())
    	{
    	for (int i = 0; i < storeRegisters.get().size(); i++) 
    	{    		
    		book_qty=0;
    		item_seq_no_curr = storeRegisters.get().get(i).getItem_seq_no();
    		store_seq_no_curr = storeRegisters.get().get(i).getStore_request_seq_no();
    		needed_qty = storeRegisters.get().get(i).getQty_requested()-storeRegisters.get().get(i).getQty_allocated();
    		req_qty = storeRegisters.get().get(i).getQty_requested();
    		allocated_qty = storeRegisters.get().get(i).getQty_allocated();
    		qoh = storeRegisterRepo.getItemQty(item_seq_no_curr);
    		eff = getEffectiveQoh(store_seq_no_curr, item_seq_no_curr);
    		eqoh = qoh-eff;    		
    		logger.info("SALE Seq no :"+store_seq_no_curr);
    		logger.info("Needed qty :"+needed_qty);
    		logger.info("EQOH :"+eqoh);
    		logger.info("QOH :"+qoh);
    		    			
    		if(needed_qty>0)
    		{	
    		if(eqoh>0)
    		{
    			if(needed_qty<=eqoh)
    			{
    			logger.info("Perfect Alloc & Deducting From Items :"+needed_qty);    			
    			storeRegisterRepo.updateItemMasterDeductQty(needed_qty, item_seq_no_curr);
    			storeRegisterRepo.updateStoreAlloc(needed_qty, store_seq_no_curr);
    			sMaster = createIssueRecord(store_seq_no_curr, item_seq_no_curr, needed_qty);
    			storeIssueRepo.save(sMaster);
    			
    			//book_qty = req_qty -(allocated_qty+needed_qty);
    			}
    			else 
    			{
    			storeRegisterRepo.updateItemMasterQtyToZero(item_seq_no_curr);
        		storeRegisterRepo.updateStoreAlloc(eqoh, store_seq_no_curr);
        		sMaster = createIssueRecord(store_seq_no_curr, item_seq_no_curr, needed_qty);
    			storeIssueRepo.save(sMaster);
        		book_qty = req_qty - (allocated_qty+eqoh);
        		if(checkIfBooked(store_seq_no_curr)==false)
        		{           			
        			logger.info("INSIDE SALE book qty :"+book_qty);
        			storeRegisterRepo.updateQtyBooked(book_qty, store_seq_no_curr);
        			storeRegisterRepo.updateBookStatus(store_seq_no_curr);
        		}
        		}    								
    		}
    		else
    		{
    		if(checkIfBooked(store_seq_no_curr)==false)
    		{
    			logger.info("OUTSIDE Needed :"+needed_qty);
    			logger.info("OUTSIDE SALE book qty :"+needed_qty);
    			storeRegisterRepo.updateQtyBooked(needed_qty, store_seq_no_curr);
    			storeRegisterRepo.updateBookStatus(store_seq_no_curr);
    		}
    		}
    		}
    		}
		}
    	 
    	    	
    	logger.info("Sales Job End");
        return;
    }

    // PURCHASE, MODE=1  
	 
	@Scheduled(fixedRate=3000)		
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
    public void purchase_Alloc() 
    {	    	
		logger.info("Purchase Job Start");
    	Optional<ArrayList<StoreRegister>> p_storeRegisters = storeRegisterRepo.getAllRowsForMode1();    	
    	Integer p_store_seq_no_curr = 0;
    	Float p_needed_qty = (float)0;
    	
    	for (int i = 0; i < p_storeRegisters.get().size(); i++) 
    	{   
    		p_store_seq_no_curr = p_storeRegisters.get().get(i).getStore_request_seq_no();
    		p_needed_qty = p_storeRegisters.get().get(i).getQty_requested()-p_storeRegisters.get().get(i).getQty_allocated();    		
    		
    		if(p_needed_qty>0)
    		{
    			logger.info("PURCH seq :"+p_store_seq_no_curr);
        		logger.info("PURCH needed qty :"+p_needed_qty);
    			storeRegisterRepo.updateStoreAlloc(p_needed_qty, p_store_seq_no_curr);
    		}
		}
    	    	    	
    	logger.info("Purchase Job End");
        return;
    }


    // DELIVERY-IN, MODE=2  
	    
	@Scheduled(fixedRate=3000)	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public void deliveryIn_Alloc() 
    {	    	
		logger.info("Delivery In Job Start");
    	Optional<ArrayList<StoreRegister>> di_storeRegisters = storeRegisterRepo.getAllRowsForMode(2);
    	Integer di_item_seq_no_curr = 0;
    	Integer di_store_seq_no_curr = 0;    	
    	float di_needed_qty = 0;
    	    	    	
    	for (int di_i = 0; di_i < di_storeRegisters.get().size(); di_i++) 
    	{    	
    		di_item_seq_no_curr = di_storeRegisters.get().get(di_i).getItem_seq_no();
    		di_store_seq_no_curr = di_storeRegisters.get().get(di_i).getStore_request_seq_no();    	
    		di_needed_qty = di_storeRegisters.get().get(di_i).getQty_requested()-di_storeRegisters.get().get(di_i).getQty_allocated();    		
    		
    		if(di_needed_qty>0)
    		{
    		logger.info("DI booking for : "+di_store_seq_no_curr);
    		logger.info("DI booking qty : "+di_needed_qty);
    		storeRegisterRepo.updateQtyBooked(di_needed_qty, di_store_seq_no_curr);
        	storeRegisterRepo.updateBookStatus(di_store_seq_no_curr);
        	storeRegisterRepo.updateStoreAlloc(di_needed_qty, di_store_seq_no_curr);
    		storeRegisterRepo.updateItemMasterAddQty(di_needed_qty, di_item_seq_no_curr);    		
    		}
    		}
    	logger.info("Delivery In Job End");
        return;
    }

    
    // DELIVERY-OUT, MODE=3  
	    
	@Scheduled(fixedRate=3000)	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public void deliveryOut_Alloc() 
    {	    	
    	Optional<ArrayList<StoreRegister>> do_storeRegisters = storeRegisterRepo.getAllRowsForMode(3);
    	Integer do_store_seq_no_curr = 0;
    	float do_needed_qty = (float)0;
    	
    	for (int do_i = 0; do_i < do_storeRegisters.get().size(); do_i++) 
    	{   
    		do_store_seq_no_curr = do_storeRegisters.get().get(do_i).getStore_request_seq_no();
    		do_needed_qty = do_storeRegisters.get().get(do_i).getQty_requested()-do_storeRegisters.get().get(do_i).getQty_allocated();    		
    		
    		if(do_needed_qty>0)
    		{
    			logger.info("DO seq :"+do_store_seq_no_curr);
        		logger.info("DO needed qty :"+do_needed_qty);
    			storeRegisterRepo.updateStoreAlloc(do_needed_qty, do_store_seq_no_curr);
    		}
		}
        return;
    }

    // RETURN-IN, MODE=4  
	    
	@Scheduled(fixedRate=3000)	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public void returnIn_Alloc() 
    {	    	
    	Optional<ArrayList<StoreRegister>> ri_storeRegisters = storeRegisterRepo.getAllRowsForMode(4);
    	logger.info("Return In Job Start");
    	Integer ri_item_seq_no_curr = 0;
    	Integer ri_store_seq_no_curr = 0;
    	float ri_req_qty = 0;
    	float ri_needed_qty = 0;
    	    	    	
    	for (int ri_i = 0; ri_i < ri_storeRegisters.get().size(); ri_i++) 
    	{    	
    		ri_needed_qty = 0;
    		ri_item_seq_no_curr = ri_storeRegisters.get().get(ri_i).getItem_seq_no();
    		ri_store_seq_no_curr = ri_storeRegisters.get().get(ri_i).getStore_request_seq_no();
    		ri_req_qty = ri_storeRegisters.get().get(ri_i).getQty_requested();
    		ri_needed_qty = ri_req_qty-ri_storeRegisters.get().get(ri_i).getQty_allocated();    		
    		
    		if(ri_needed_qty>0)
    		{
    		logger.info("RI booking for : "+ri_store_seq_no_curr);
    		logger.info("RI booking qty : "+ri_needed_qty);
        	storeRegisterRepo.updateStoreAlloc(ri_needed_qty, ri_store_seq_no_curr);
    		storeRegisterRepo.updateItemMasterAddQty(ri_needed_qty, ri_item_seq_no_curr);    		
    		}
    	}    	    	    	
        return;
    }
    
    //* RETURN-OUT, MODE=6  
	    
	@Scheduled(fixedRate=3000)	
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
	public void returnOut_Alloc() 
    {	    	
		Optional<ArrayList<StoreRegister>> ro_storeRegisters = storeRegisterRepo.getAllRowsForMode(6);
    	Integer ro_item_seq_no_curr = 0;
    	Integer ro_store_seq_no_curr = 0;
    	float ro_needed_qty = (float)0;
    	    	
    	for (int ro_i = 0; ro_i < ro_storeRegisters.get().size(); ro_i++) 
    	{
    		logger.info("Return Out Loop :"+ro_i);
    		ro_item_seq_no_curr = ro_storeRegisters.get().get(ro_i).getItem_seq_no();
    		ro_store_seq_no_curr = ro_storeRegisters.get().get(ro_i).getStore_request_seq_no();
    		ro_needed_qty = ro_storeRegisters.get().get(ro_i).getQty_requested()-ro_storeRegisters.get().get(ro_i).getQty_allocated();    		
    		
    		if(!checkIfAllocated(ro_store_seq_no_curr, ro_item_seq_no_curr))
    		{
    			if(checkIfBooked(ro_store_seq_no_curr)==false)
        		{
        			storeRegisterRepo.updateQtyBooked(ro_needed_qty, ro_store_seq_no_curr);
        			storeRegisterRepo.updateBookStatus(ro_store_seq_no_curr);
        		}
    			if(ro_needed_qty>0)
    			{
    			storeRegisterRepo.updateStoreAlloc(ro_needed_qty, ro_store_seq_no_curr);
    			storeRegisterRepo.updateItemMasterDeductQty(ro_needed_qty, ro_item_seq_no_curr);
    			}        		
    		}
		}    	    	    	
        return;
    }

    //* RETURN-IN-ERR, MODE=5  
	    
	public void returnInErr_Alloc() 
    {	    	
		Optional<ArrayList<StoreRegister>> storeRegisters = storeRegisterRepo.getAllRowsForMode(5);
    	Integer item_seq_no_curr = 0;
    	Integer store_seq_no_curr = 0;
    	Float needed_qty = (float)0;
    	Float alloc_qty = (float)0;    	
    	    	
    	for (int i = 0; i < storeRegisters.get().size(); i++) 
    	{
    		item_seq_no_curr = storeRegisters.get().get(i).getItem_seq_no();
    		store_seq_no_curr = storeRegisters.get().get(i).getStore_request_seq_no();
    		needed_qty = storeRegisters.get().get(i).getQty_requested()-storeRegisters.get().get(i).getQty_allocated();    		
    		alloc_qty = storeRegisters.get().get(i).getQty_allocated();    		
    		
    		if(checkIfAllocated(store_seq_no_curr, item_seq_no_curr))
    		{
    			if(checkIfAllocated(store_seq_no_curr, item_seq_no_curr))
        		{	
        		if(checkIfBooked(store_seq_no_curr)==false)
        		{
        			storeRegisterRepo.updateQtyBooked(needed_qty, store_seq_no_curr);
        			storeRegisterRepo.updateBookStatus(store_seq_no_curr);
        		}
    			if(needed_qty>0)
    			{
    			storeRegisterRepo.updateStoreAlloc(alloc_qty+needed_qty, store_seq_no_curr);    		
    			}
        		}
    		}
		}    	    	    	
        return;
    }

    //* RETURN-IN-ERR, MODE=7  
	    
	public void returnOutErr_Alloc() 
    {	    	
		Optional<ArrayList<StoreRegister>> storeRegisters = storeRegisterRepo.getAllRowsForMode(7);
    	Integer item_seq_no_curr = 0;
    	Integer store_seq_no_curr = 0;
    	Float needed_qty = (float)0;
    	Float alloc_qty = (float)0;    	
    	    	
    	for (int i = 0; i < storeRegisters.get().size(); i++) 
    	{
    		item_seq_no_curr = storeRegisters.get().get(i).getItem_seq_no();
    		store_seq_no_curr = storeRegisters.get().get(i).getStore_request_seq_no();
    		needed_qty = storeRegisters.get().get(i).getQty_requested()-storeRegisters.get().get(i).getQty_allocated();    		
    		alloc_qty = storeRegisters.get().get(i).getQty_allocated();    		
    		
    		if(checkIfAllocated(store_seq_no_curr, item_seq_no_curr))
    		{
    			if(checkIfAllocated(store_seq_no_curr, item_seq_no_curr))
        		{	
        		if(checkIfBooked(store_seq_no_curr)==false)
        		{
        			storeRegisterRepo.updateQtyBooked(needed_qty, store_seq_no_curr);
        			storeRegisterRepo.updateBookStatus(store_seq_no_curr);
        		}
    			if(needed_qty>0)
    			{
    			storeRegisterRepo.updateStoreAlloc(alloc_qty+needed_qty, store_seq_no_curr);    		
    			}
        		}
    		}
		}    	    	    	
        return;
    }    
	
	
	private float getEffectiveQoh(Integer eff_storeSeqNo, Integer eff_itemSeqNo) 
    {
		float eff_totReqQty = 0;
		float eff_totAllocQty = 0;
		
		eff_totReqQty = storeRegisterRepo.getTotalQtyRequestedForItem(eff_storeSeqNo, eff_itemSeqNo);
		eff_totAllocQty = storeRegisterRepo.getTotalQtyAllocForItem(eff_storeSeqNo, eff_itemSeqNo);
		logger.info("SALE seq no :"+eff_storeSeqNo);
		logger.info("SALE req total :"+eff_totReqQty);
		logger.info("SALE alloc total :"+eff_totAllocQty);
	    
		return (eff_totReqQty - eff_totAllocQty);	
		}
	
	private boolean checkIfAllocated(Integer storeSeqNo, Integer itemSeqNo) 
    {
		float totReqQty = 0;
		float totAllocQty = 0;				
		boolean retStatus = false; 
				
			totReqQty = storeRegisterRepo.getTotalQtyRequestedForItem(storeSeqNo, itemSeqNo);
			totAllocQty = storeRegisterRepo.getTotalQtyAllocForItem(storeSeqNo, itemSeqNo);
			
			if(totReqQty - totAllocQty>0)
				{	
				retStatus = true;
			}	
		    	
		return retStatus;
    	}
	
	private boolean checkIfBooked(Integer storeSeqNo) 
    {
		Optional<Character> status;
		boolean retStatus=false;
		
			status = storeRegisterRepo.getIsbookedStatus(storeSeqNo);
	    	
			if(status.isPresent() && status.get()!=null)
			{	
	    	if(Character.toUpperCase(status.get())=='Y')
	    	{
	    		retStatus= true;
	    	}
	    	else 
	    	{
	    		retStatus=  false;	    	
        	}
			}    			
		
		return retStatus; 
		}

	private StoreIssueMaster createIssueRecord(Integer storeSeqNo, Integer itemseqNo, float qtyIssue)
	{
		StoreIssueMaster storeIssueMaster = new StoreIssueMaster();
		StoreMovementMasterPK smPK = new StoreMovementMasterPK();
		Timestamp reqDate = new Timestamp(System.currentTimeMillis());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String reqDate2 =	formatter.format(reqDate.toLocalDateTime());		
		Timestamp reqDate3 = Timestamp.valueOf(reqDate2);		
		smPK.setRequest_on(reqDate3);
		smPK.setStore_request_seq_no(storeSeqNo);
		smPK.setItem_seq_no(itemseqNo);
		storeIssueMaster.setId(smPK);
		storeIssueMaster.setRequest_qty(qtyIssue);
		return storeIssueMaster;
	}
*/
	
	
	
}