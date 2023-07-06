package transactions.transaction_manager.services;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*
import transaction_manager.model.TransactionDetails;
import transaction_manager.model.TransactionMaster;
import transaction_manager.model.TransactionMasterDTO;
import transaction_manager.model.TransactionMasterRepo;
*/

@Service("txnMasterServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class TransactionMasterService implements I_TransactionMasterService 
{

/*	@Autowired
    private TransactionMasterRepo txnMasterRepo;

    public ArrayList<TransactionMasterDTO> getAllTransactions() 
    {
    	ArrayList<TransactionMasterDTO> transactionDTOs = new ArrayList<TransactionMasterDTO>();
    	ArrayList<TransactionMaster> transactionOpts =  (ArrayList<TransactionMaster>) txnMasterRepo.findAll();
    	
    	if(transactionOpts!=null)
    	{
    	transactionDTOs = getTransactionDtos(transactionOpts);
    	}
    	else
    	{
    	transactionDTOs= null;    	
    	}
            	
        return transactionDTOs;
    }
    
    public TransactionMasterDTO getTransactionByNo(Integer txnSeqNo) 
    {
    	Optional<TransactionMaster> transactionMaster = txnMasterRepo.findById(txnSeqNo);
    	TransactionMasterDTO transactionDTO = null;
    	    	
    	if(transactionMaster.isPresent())
    	{
    	transactionDTO = getTransactionDto(transactionMaster.get());
    	}
    	else
    	{
    	transactionDTO= null;	
    	}
            	
        return transactionDTO;
    }
    
    public ArrayList<TransactionMasterDTO> getTransactionsByType(Integer txnTypeSeqNo) 
    {
    	Optional<ArrayList<TransactionMaster>> transactionMasters = txnMasterRepo.getTxnsForType(txnTypeSeqNo);
    	ArrayList<TransactionMasterDTO> transactionDTOs = null;
    	    	
    	if(transactionMasters.isPresent())
    	{
    	transactionDTOs = getTransactionDtos(transactionMasters.get());
    	}
    	else
    	{
    	transactionDTOs= null;	
    	}
            	
        return transactionDTOs;
    }
    
    public TransactionMasterDTO newTransaction(TransactionMasterDTO transactionDTO) 
    {    	
    TransactionMaster transactionMaster = txnMasterRepo.save(setTransactionMaster(transactionDTO));
    TransactionMasterDTO transactionDTO2 = getTransactionDto(transactionMaster);
	return transactionDTO2;
    }

    public void updateTransaction(TransactionMasterDTO transactionDTO) 
    {
    TransactionMaster transactionMaster = setTransactionMaster(transactionDTO);
    txnMasterRepo.save(transactionMaster);    
    }
    
    public void deleteTransaction(Integer txnSeqNo) 
    {
    txnMasterRepo.deleteById(txnSeqNo);
    }
	
    public void deleteAllTransactions() 
    {
    txnMasterRepo.deleteAll();
    }
    
    public void confirmTransaction(Integer txnSeqNo) 
    {
    ArrayList<TransactionDetails> transactionDetails = txnMasterRepo.getTxnDDetails(txnSeqNo);
    Optional<TransactionMaster> transactionMaster = txnMasterRepo.findById(txnSeqNo);
    
    if(transactionMaster.isPresent())
    {
    if(transactionDetails!=null && !transactionDetails.isEmpty())
    {
    	Integer REQUESTOR_SEQ_NO = 0;
    	String STATUS = null;
    	String REMARK = null;
    	Integer SERV_REQUEST_SEQ_NO=0;
    	Integer QTY_UNIT_SEQ_NO=0;
    	Character IS_BOOKED=' ';
    	Integer ITEM_SEQ_NO=0;
    	float QTY_ALLOCATED=0;
    	float QTY_REQUESTED=0;
    	Integer MODE_TXN=0;
    	float QTY_BOOKED=0;
    	Integer SERVICE_REQUEST_SEQ_NO=0;

    	for (int i = 0; i < transactionDetails.size(); i++) 
    	{
    		REQUESTOR_SEQ_NO = transactionDetails.get(i).getStoreReqSeqNo();
        	STATUS = null;
        	REMARK = null;
        	SERV_REQUEST_SEQ_NO=0;
        	QTY_UNIT_SEQ_NO=0;
        	IS_BOOKED='n';
        	ITEM_SEQ_NO=transactionDetails.get(i).getId().getItemSeqNo();
        	QTY_ALLOCATED=0;
        	QTY_REQUESTED=transactionDetails.get(i).getQty();
        	MODE_TXN=transactionMaster.get().gettTypeSeqNo();
        	QTY_BOOKED=0;
        	SERVICE_REQUEST_SEQ_NO=0;	    	
        	txnMasterRepo.insertStoreRecord(REQUESTOR_SEQ_NO,STATUS,REMARK,SERV_REQUEST_SEQ_NO,QTY_UNIT_SEQ_NO,IS_BOOKED,ITEM_SEQ_NO,QTY_ALLOCATED,QTY_REQUESTED,MODE_TXN,QTY_BOOKED,SERVICE_REQUEST_SEQ_NO);
    	}
    }
    }
    }
    
	private ArrayList<TransactionMasterDTO> getTransactionDtos(ArrayList<TransactionMaster> transactionMasters) 
	{
		TransactionMasterDTO transactionDTO = null;
		ArrayList<TransactionMasterDTO> transactionDTOs = new ArrayList<TransactionMasterDTO>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		
		for(int i=0; i<transactionMasters.size();i++)
		{		
		transactionDTO = new TransactionMasterDTO();
		transactionDTO.setTransaction_seq_no(transactionMasters.get(i).getTransaction_seq_no());
		transactionDTO.settTypeSeqNo(transactionMasters.get(i).gettTypeSeqNo());
		transactionDTO.setCompOn(formatter.format(transactionMasters.get(i).getCompOn().toLocalDateTime()));
		transactionDTO.setStartOn(formatter.format(transactionMasters.get(i).getStartOn().toLocalDateTime()));		
		transactionDTO.setByTxnSeqNo(transactionMasters.get(i).getByTxnSeqNo());		
		transactionDTO.setDocSeqNo(transactionMasters.get(i).getDocSeqNo());
		transactionDTO.setRefTxnSeqNo(transactionMasters.get(i).getRefTxnSeqNo());
		transactionDTO.setRemark(transactionMasters.get(i).getRemark());
		transactionDTO.setStatus(transactionMasters.get(i).getStatus());		
		transactionDTOs.add(transactionDTO);
		}
		
		return transactionDTOs;
	}


	private TransactionMasterDTO getTransactionDto(TransactionMaster transactionMaster) 
	{
		TransactionMasterDTO transactionDTO = new TransactionMasterDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		transactionDTO.setTransaction_seq_no(transactionMaster.getTransaction_seq_no());
		transactionDTO.settTypeSeqNo(transactionMaster.gettTypeSeqNo());
		transactionDTO.setCompOn(formatter.format(transactionMaster.getCompOn().toLocalDateTime()));
		transactionDTO.setStartOn(formatter.format(transactionMaster.getStartOn().toLocalDateTime()));		
		transactionDTO.setByTxnSeqNo(transactionMaster.getByTxnSeqNo());		
		transactionDTO.setDocSeqNo(transactionMaster.getDocSeqNo());
		transactionDTO.setRefTxnSeqNo(transactionMaster.getRefTxnSeqNo());
		transactionDTO.setRemark(transactionMaster.getRemark());
		transactionDTO.setStatus(transactionMaster.getStatus());
		return transactionDTO;
		}
		
	private TransactionMaster setTransactionMaster(TransactionMasterDTO transactionDTO) 
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		TransactionMaster transactionMaster		=	new	TransactionMaster();		
		transactionMaster.settTypeSeqNo(transactionMaster.gettTypeSeqNo());
		LocalDateTime compOn = LocalDateTime.parse(transactionDTO.getCompOn(), formatter);
		Timestamp compOnTS = Timestamp.valueOf(compOn);
		transactionMaster.setCompOn(compOnTS);
		LocalDateTime startOn = LocalDateTime.parse(transactionDTO.getStartOn(), formatter);
		Timestamp startOnTS = Timestamp.valueOf(startOn);		
		transactionMaster.setStartOn(startOnTS);		
		transactionMaster.setByTxnSeqNo(transactionMaster.getByTxnSeqNo());		
		transactionMaster.setDocSeqNo(transactionMaster.getDocSeqNo());
		transactionMaster.setRefTxnSeqNo(transactionMaster.getRefTxnSeqNo());
		transactionMaster.setRemark(transactionMaster.getRemark());
		transactionMaster.setStatus(transactionMaster.getStatus());
		return transactionMaster;
	}

		*/
}
