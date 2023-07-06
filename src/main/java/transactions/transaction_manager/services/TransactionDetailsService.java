package transactions.transaction_manager.services;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*
import transaction_manager.model.TransactionDetails;
import transaction_manager.model.TransactionDetailsDTO;
import transaction_manager.model.TransactionDetailsPK;
import transaction_manager.model.TransactionDetailsRepo;
*/

@Service("txnDetailsServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class TransactionDetailsService implements I_TransactionDetailsService 
{
/*
	@Autowired
    private TransactionDetailsRepo txnDetailsRepo;

    public ArrayList<TransactionDetailsDTO> getAllTransactionDetails() 
    {
    	ArrayList<TransactionDetailsDTO> transactionDetailDTOs = new ArrayList<TransactionDetailsDTO>();
    	ArrayList<TransactionDetails> transactionOpts =  (ArrayList<TransactionDetails>) txnDetailsRepo.findAll();
    	
    	if(transactionOpts!=null)
    	{
    		transactionDetailDTOs = getTransactionDetailsDtos(transactionOpts);
    	}
    	else
    	{
    	transactionDetailDTOs= null;    	
    	}
            	
        return transactionDetailDTOs;
    }
    
    public ArrayList<TransactionDetailsDTO> getTransactionDetailsByNo(Integer txnSeqNo) 
    {
    	Optional<ArrayList<TransactionDetails>> transactionDetails = txnDetailsRepo.getTxnDetailsForNo(txnSeqNo);
    	ArrayList<TransactionDetailsDTO> transactionDetailDTOs = null;
    	    	
    	if(transactionDetails.isPresent())
    	{
    	transactionDetailDTOs = getTransactionDetailsDtos(transactionDetails.get());
    	}
    	else
    	{
    	transactionDetailDTOs = null;	
    	}
            	
        return transactionDetailDTOs;
    }
    
    
    public TransactionDetailsDTO newTransactionDetails(TransactionDetailsDTO transactionDetailDTO) 
    {    	
    TransactionDetails transactionDetails = txnDetailsRepo.save(setTransactionDetails(transactionDetailDTO));
    TransactionDetailsDTO transactionDetailDTO2 = getTransactionDetailsDto(transactionDetails);
	return transactionDetailDTO2;
    }

    public void updateTransactionDetails(TransactionDetailsDTO transactionDetailDTO) 
    {    	
		TransactionDetailsPK transactionDetailsPK = new TransactionDetailsPK();
		transactionDetailsPK.setItemSeqNo(transactionDetailDTO.getItemSeqNo());
		transactionDetailsPK.setTseqNo(transactionDetailDTO.getTseqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(transactionDetailDTO.getTxnOn(), formatter);
		Timestamp dateOnTS = Timestamp.valueOf(dateOn);
		transactionDetailsPK.setTxnOn(dateOnTS);

		if(txnDetailsRepo.existsById(transactionDetailsPK))
		{
		TransactionDetails transactionDetails = setTransactionDetails(transactionDetailDTO);
		txnDetailsRepo.save(transactionDetails);    
		}
    }
    
    public void deleteTransactionDetails(Integer txnSeqNo, Integer itemSeqNo, String dateOnStr) 
    {
    	TransactionDetailsPK transactionDetailsPK = new TransactionDetailsPK();
		transactionDetailsPK.setItemSeqNo(itemSeqNo);
		transactionDetailsPK.setTseqNo(txnSeqNo);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateOn = LocalDateTime.parse(dateOnStr, formatter);
		Timestamp dateOnTS = Timestamp.valueOf(dateOn);
		transactionDetailsPK.setTxnOn(dateOnTS);

		if(txnDetailsRepo.existsById(transactionDetailsPK))
		{
			txnDetailsRepo.deleteById(transactionDetailsPK);
		}
    }
	
    public void deleteAllTransactions() 
    {
    txnDetailsRepo.deleteAll();
    }
    
	private ArrayList<TransactionDetailsDTO> getTransactionDetailsDtos(ArrayList<TransactionDetails> transactionDetails) 
	{
		TransactionDetailsDTO transactionDetailDTO = null;
		ArrayList<TransactionDetailsDTO> transactionDetailDTOs = new ArrayList<TransactionDetailsDTO>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		for(int i=0; i<transactionDetails.size();i++)
		{		
		transactionDetailDTO = new TransactionDetailsDTO();
		transactionDetailDTO.setTseqNo(transactionDetails.get(i).getId().getTseqNo());
		transactionDetailDTO.setQty(transactionDetails.get(i).getQty());
		transactionDetailDTO.setDiscPerc(transactionDetails.get(i).getDiscPerc());
		transactionDetailDTO.setDiscVal(transactionDetails.get(i).getDiscVal());
		transactionDetailDTO.setTaxPerc(transactionDetails.get(i).getTaxPerc());
		transactionDetailDTO.setTaxVal(transactionDetails.get(i).getTaxVal());
		transactionDetailDTO.setStoreReqSeqNo(transactionDetails.get(i).getStoreReqSeqNo());
		transactionDetailDTO.setByStoreFlag(transactionDetails.get(i).getByStoreFlag());
		transactionDetailDTO.setBySourceFlag(transactionDetails.get(i).getBySourceFlag());
		transactionDetailDTO.setTxnOn(formatter.format(transactionDetails.get(i).getId().getTxnOn().toLocalDateTime()));		
		transactionDetailDTOs.add(transactionDetailDTO);
		}
		
		return transactionDetailDTOs;
	}


	private TransactionDetailsDTO getTransactionDetailsDto(TransactionDetails transactionDetails) 
	{
		TransactionDetailsDTO transactionDetailDTO = new TransactionDetailsDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		transactionDetailDTO = new TransactionDetailsDTO();
		transactionDetailDTO.setTseqNo(transactionDetails.getId().getTseqNo());
		transactionDetailDTO.setQty(transactionDetails.getQty());
		transactionDetailDTO.setDiscPerc(transactionDetails.getDiscPerc());
		transactionDetailDTO.setDiscVal(transactionDetails.getDiscVal());
		transactionDetailDTO.setTaxPerc(transactionDetails.getTaxPerc());
		transactionDetailDTO.setTaxVal(transactionDetails.getTaxVal());
		transactionDetailDTO.setStoreReqSeqNo(transactionDetails.getStoreReqSeqNo());
		transactionDetailDTO.setByStoreFlag(transactionDetails.getByStoreFlag());
		transactionDetailDTO.setBySourceFlag(transactionDetails.getBySourceFlag());
		transactionDetailDTO.setTxnOn(formatter.format(transactionDetails.getId().getTxnOn().toLocalDateTime()));
		return transactionDetailDTO;
		}
		
	private TransactionDetails setTransactionDetails(TransactionDetailsDTO transactionDetailDTO) 
	{
		TransactionDetailsPK transactionDetailsPK = new TransactionDetailsPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		Timestamp reqDate = new Timestamp(System.currentTimeMillis());
		String txnOn =	formatter.format(reqDate.toLocalDateTime());
		Timestamp txnOnTS = Timestamp.valueOf(txnOn);
		transactionDetailsPK.setItemSeqNo(transactionDetailDTO.getItemSeqNo());
		transactionDetailsPK.setTseqNo(transactionDetailDTO.getTseqNo());
		transactionDetailsPK.setTxnOn(txnOnTS);		
		TransactionDetails transactionDetails		=	new	TransactionDetails();								
		transactionDetails.setId(transactionDetailsPK);		
		transactionDetails.setQty(transactionDetailDTO.getQty());
		transactionDetails.setDiscPerc(transactionDetailDTO.getDiscPerc());
		transactionDetails.setDiscVal(transactionDetailDTO.getDiscVal());
		transactionDetails.setTaxPerc(transactionDetailDTO.getTaxPerc());
		transactionDetails.setTaxVal(transactionDetailDTO.getTaxVal());
		transactionDetails.setStoreReqSeqNo(transactionDetailDTO.getStoreReqSeqNo());
		transactionDetails.setByStoreFlag(transactionDetailDTO.getByStoreFlag());
		transactionDetails.setBySourceFlag(transactionDetailDTO.getBySourceFlag());		
		return transactionDetails;
	}
	*/		
}
