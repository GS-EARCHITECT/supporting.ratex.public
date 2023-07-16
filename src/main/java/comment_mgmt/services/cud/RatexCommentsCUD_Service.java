package comment_mgmt.services.cud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import comment_mgmt.model.dto.RatexComment_DTO;
import comment_mgmt.model.master.RatexComment;
import comment_mgmt.model.master.RatexCommentPK;
import comment_mgmt.model.repo.cud.RatexCommentsCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexCommentsCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexCommentsCUD_Service implements IRatexCommentsCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexComment_Service.class);
	
	@Autowired
    private RatexCommentsCUD_Repo forumCommentsCUDRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<RatexComment_DTO> newComment(RatexComment_DTO rcDTO) 
    {    
		CompletableFuture<RatexComment_DTO> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
	  	Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
		RatexCommentPK ratexCommentPK = new RatexCommentPK(); 	
		ratexCommentPK.setItemSeqNo(rcDTO.getItemSeqNo());
		ratexCommentPK.setOnDttm(onTs);
		ratexCommentPK.setSourceSeqNo(rcDTO.getSourceSeqNo());
		RatexComment_DTO ratexCommentDTO = new RatexComment_DTO();  		
  		
		if(!forumCommentsCUDRepo.existsById(ratexCommentPK))
  		{	
  		RatexComment ratexComment = forumCommentsCUDRepo.save(this.setRatexComment(rcDTO));    
  		ratexCommentDTO = this.getRatexComment_DTO(ratexComment);
  		}
        return ratexCommentDTO;
  		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<Void> updComment(RatexComment_DTO rDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime onDTTm = LocalDateTime.parse(rDTO.getOnDttm(), formatter);
  		Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
  		RatexCommentPK ratexCommentPK = new RatexCommentPK(); 	
		ratexCommentPK.setItemSeqNo(rDTO.getItemSeqNo());
		ratexCommentPK.setOnDttm(onTs);
		ratexCommentPK.setSourceSeqNo(rDTO.getSourceSeqNo());
		
  		if(forumCommentsCUDRepo.existsById(ratexCommentPK))
    	{
        RatexComment ratexComment = this.setRatexComment(rDTO);
        forumCommentsCUDRepo.save(ratexComment);
    	}
  	 	},asyncExecutor);      	
        return future;
    }
    	
    public CompletableFuture<Void> delSelectComments(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumCommentsCUDRepo.delSelectComments(iList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectCommentsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	forumCommentsCUDRepo.delSelectCommentsBetweenTimes(iList, frTs, toTs);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectCommentsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumCommentsCUDRepo.delSelectCommentsBySource(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllComments() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		forumCommentsCUDRepo.deleteAll();      	
  	 	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized RatexComment_DTO getRatexComment_DTO(RatexComment rcReqComment) 
	{
		RatexComment_DTO ratexCommentDTO = new RatexComment_DTO();
		ratexCommentDTO.setSourceSeqNo(rcReqComment.getId().getItemSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexCommentDTO.setOnDttm(formatter.format(rcReqComment.getId().getOnDttm().toLocalDateTime()));
		ratexCommentDTO.setItemSeqNo(rcReqComment.getId().getItemSeqNo());
		ratexCommentDTO.setSourceSeqNo(rcReqComment.getId().getSourceSeqNo());
		ratexCommentDTO.setRemark(rcReqComment.getRemark());		
		return ratexCommentDTO;
	}
		
	private synchronized RatexComment setRatexComment(RatexComment_DTO ratexComment_DTO) 
	{
		RatexComment ratexComment		=	new	RatexComment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(ratexComment_DTO.getOnDttm(), formatter);
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
 	  	RatexCommentPK ratexCommentPK = new RatexCommentPK(); 	
  		ratexCommentPK.setItemSeqNo(ratexComment_DTO.getItemSeqNo());
  		ratexCommentPK.setSourceSeqNo(ratexComment_DTO.getSourceSeqNo());
  		ratexCommentPK.setOnDttm(onTs);
  		ratexComment.setId(ratexCommentPK);
  		ratexComment.setRemark(ratexComment_DTO.getRemark());		
		return ratexComment;
	}

	
}