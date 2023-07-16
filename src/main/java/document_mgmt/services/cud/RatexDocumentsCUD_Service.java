package document_mgmt.services.cud;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import document_mgmt.model.dto.RatexDocument_DTO;
import document_mgmt.model.master.RatexDocument;
import document_mgmt.model.master.RatexDocumentPK;
import document_mgmt.model.repo.cud.RatexDocumentsCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexDocumentsCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexDocumentsCUD_Service implements IRatexDocumentsCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexDocument_Service.class);
	
	@Autowired
    private RatexDocumentsCUD_Repo forumDocumentsCUDRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<RatexDocument_DTO> newDocument(RatexDocument_DTO rcDTO) 
    {    
		CompletableFuture<RatexDocument_DTO> future = CompletableFuture.supplyAsync(() -> 
		{	
		RatexDocumentPK ratexDocumentPK = new RatexDocumentPK(); 	
		ratexDocumentPK.setItemSeqNo(rcDTO.getItemSeqNo());
		ratexDocumentPK.setDoclistSeqNo(rcDTO.getDoclistSeqNo());
		RatexDocument_DTO ratexDocumentDTO = new RatexDocument_DTO();  		
  		
		if(!forumDocumentsCUDRepo.existsById(ratexDocumentPK))
  		{	
  		RatexDocument ratexDocument = forumDocumentsCUDRepo.save(this.setRatexDocument(rcDTO));    
  		ratexDocumentDTO = this.getRatexDocument_DTO(ratexDocument);
  		}
        return ratexDocumentDTO;
  		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<Void> updDocument(RatexDocument_DTO rDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		RatexDocumentPK ratexDocumentPK = new RatexDocumentPK(); 	
  		ratexDocumentPK.setItemSeqNo(rDTO.getItemSeqNo());
  		ratexDocumentPK.setDoclistSeqNo(rDTO.getDoclistSeqNo());
  		RatexDocument_DTO ratexDocumentDTO = new RatexDocument_DTO();  		
  	  	
  		if(forumDocumentsCUDRepo.existsById(ratexDocumentPK))
    	{
        RatexDocument ratexDocument = this.setRatexDocument(rDTO);
        forumDocumentsCUDRepo.save(ratexDocument);
    	}
  	 	},asyncExecutor);      	
        return future;
    }
    	
    public CompletableFuture<Void> delSelectDocuments(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumDocumentsCUDRepo.delSelectDocuments(iList);
  	 	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllDocuments() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		forumDocumentsCUDRepo.deleteAll();      	
  	 	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized ArrayList<RatexDocument_DTO> getRatexDocumentDtos(ArrayList<RatexDocument> rcReqDocuments) 
	{
		ArrayList<RatexDocument_DTO> ratexDocumentDTOs = new ArrayList<RatexDocument_DTO>(); 
		for(int i=0; i < rcReqDocuments.size();i++)
		{		
		ratexDocumentDTOs.add(this.getRatexDocument_DTO(rcReqDocuments.get(i)));
		}		
		return ratexDocumentDTOs;
	}
	
    private synchronized RatexDocument_DTO getRatexDocument_DTO(RatexDocument rcReqDocument) 
	{
		RatexDocument_DTO ratexDocumentDTO = new RatexDocument_DTO();		
		ratexDocumentDTO.setItemSeqNo(rcReqDocument.getId().getItemSeqNo());
		ratexDocumentDTO.setDoclistSeqNo(rcReqDocument.getId().getDoclistSeqNo());				
		return ratexDocumentDTO;
	}
		
	private synchronized RatexDocument setRatexDocument(RatexDocument_DTO ratexDocument_DTO) 
	{
		RatexDocument ratexDocument		=	new	RatexDocument();		
 	  	RatexDocumentPK ratexDocumentPK = new RatexDocumentPK(); 	
  		ratexDocumentPK.setItemSeqNo(ratexDocument_DTO.getItemSeqNo());
  		ratexDocumentPK.setDoclistSeqNo(ratexDocument_DTO.getDoclistSeqNo());
  		ratexDocument.setId(ratexDocumentPK);
  		return ratexDocument;
	}

	
}