package comment_mgmt.services.read;

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
import comment_mgmt.model.dto.*;
import comment_mgmt.model.master.*;
import comment_mgmt.model.repo.*;
import comment_mgmt.model.repo.read.RatexCommentsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexCommentsReadServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexCommentsRead_Service implements IRatexCommentsRead_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexComment_Service.class);
	
	@Autowired
    private RatexCommentsRead_Repo ratexCommentsReadRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getAllComments()
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexComment> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexComment>) ratexCommentsReadRepo.findAll();
    	CopyOnWriteArrayList<RatexComment_DTO> lDetails = new CopyOnWriteArrayList<RatexComment_DTO>();
    	lDetails = rcReqList != null ? this.getRatexCommentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectCommentsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexComment> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexComment>) ratexCommentsReadRepo.getSelectCommentsBySource(sList);
    	CopyOnWriteArrayList<RatexComment_DTO> lDetails = new CopyOnWriteArrayList<RatexComment_DTO>();
    	lDetails = rcReqList != null ? this.getRatexCommentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectComments(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexComment> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexComment>) ratexCommentsReadRepo.getSelectComments(iList);
    	CopyOnWriteArrayList<RatexComment_DTO> lDetails = new CopyOnWriteArrayList<RatexComment_DTO>();
    	lDetails = rcReqList != null ? this.getRatexCommentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
     
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectCommentsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
       	CopyOnWriteArrayList<RatexComment> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexComment>) ratexCommentsReadRepo.getSelectCommentsBetweenTimes(iList, frTs, toTs);
    	CopyOnWriteArrayList<RatexComment_DTO> lDetails = new CopyOnWriteArrayList<RatexComment_DTO>();
    	lDetails = rcReqList != null ? this.getRatexCommentDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
  
    private synchronized CopyOnWriteArrayList<RatexComment_DTO> getRatexCommentDtos(CopyOnWriteArrayList<RatexComment> rcReqDetails) 
	{
		CopyOnWriteArrayList<RatexComment_DTO> ratexCommentDTOs = new CopyOnWriteArrayList<RatexComment_DTO>(); 
		for(int i=0; i < rcReqDetails.size();i++)
		{		
		ratexCommentDTOs.add(this.getRatexComment_DTO(rcReqDetails.get(i)));
		}		
		return ratexCommentDTOs;
	}
	
	private synchronized RatexComment_DTO getRatexComment_DTO(RatexComment rcReqDetail) 
	{
		RatexComment_DTO ratexCommentDTO = new RatexComment_DTO();
		ratexCommentDTO.setSourceSeqNo(rcReqDetail.getId().getItemSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexCommentDTO.setOnDttm(formatter.format(rcReqDetail.getId().getOnDttm().toLocalDateTime()));
		ratexCommentDTO.setItemSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexCommentDTO.setRemark(rcReqDetail.getRemark());
		ratexCommentDTO.setSourceSeqNo(rcReqDetail.getId().getSourceSeqNo());		
		return ratexCommentDTO;
		}
	
}