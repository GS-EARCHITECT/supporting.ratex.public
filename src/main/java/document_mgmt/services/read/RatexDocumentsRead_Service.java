package document_mgmt.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import document_mgmt.model.dto.RatexDocument_DTO;
import document_mgmt.model.repo.read.RatexDocumentsRead_Repo;
import document_mgmt.model.dto.*;
import document_mgmt.model.master.*;
import document_mgmt.model.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexDocumentsReadServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexDocumentsRead_Service implements IRatexDocumentsRead_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexDocument_Service.class);
	
	@Autowired
    private RatexDocumentsRead_Repo ratexDocumentsReadRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> getAllDocuments()
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexDocument> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexDocument>) ratexDocumentsReadRepo.findAll();
    	CopyOnWriteArrayList<RatexDocument_DTO> lDetails = new CopyOnWriteArrayList<RatexDocument_DTO>();
    	lDetails = rcReqList != null ? this.getRatexDocumentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> getSelectDocuments(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexDocument> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexDocument>) ratexDocumentsReadRepo.getSelectDocuments(iList);
    	CopyOnWriteArrayList<RatexDocument_DTO> lDetails = new CopyOnWriteArrayList<RatexDocument_DTO>();
    	lDetails = rcReqList != null ? this.getRatexDocumentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    private synchronized CopyOnWriteArrayList<RatexDocument_DTO> getRatexDocumentDtos(CopyOnWriteArrayList<RatexDocument> rcReqDetails) 
	{
		CopyOnWriteArrayList<RatexDocument_DTO> ratexDocumentDTOs = new CopyOnWriteArrayList<RatexDocument_DTO>(); 
		for(int i=0; i < rcReqDetails.size();i++)
		{		
		ratexDocumentDTOs.add(this.getRatexDocument_DTO(rcReqDetails.get(i)));
		}		
		return ratexDocumentDTOs;
	}
	
	private synchronized RatexDocument_DTO getRatexDocument_DTO(RatexDocument rcReqDetail) 
	{
		RatexDocument_DTO ratexDocumentDTO = new RatexDocument_DTO();		
		ratexDocumentDTO.setItemSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexDocumentDTO.setDoclistSeqNo(rcReqDetail.getId().getDoclistSeqNo());
		return ratexDocumentDTO;
		}
	
}