package like_mgmt.services.cud;

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
import like_mgmt.model.dto.RatexLike_DTO;
import like_mgmt.model.master.RatexLike;
import like_mgmt.model.master.RatexLikePK;
import like_mgmt.model.repo.cud.RatexLikesCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexLikesCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexLikesCUD_Service implements IRatexLikesCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexLike_Service.class);
	
	@Autowired
    private RatexLikesCUD_Repo forumLikesCUDRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<RatexLike_DTO> newLike(RatexLike_DTO rcDTO) 
    {    
		CompletableFuture<RatexLike_DTO> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
	  	Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
		RatexLikePK ratexLikePK = new RatexLikePK(); 	
		ratexLikePK.setItemSeqNo(rcDTO.getItemSeqNo());
		ratexLikePK.setOnDttm(onTs);
		ratexLikePK.setSourceSeqNo(rcDTO.getSourceSeqNo());
		RatexLike_DTO ratexLikeDTO = new RatexLike_DTO();  		
  		
		if(!forumLikesCUDRepo.existsById(ratexLikePK))
  		{	
  		RatexLike ratexLike = forumLikesCUDRepo.save(this.setRatexLike(rcDTO));    
  		ratexLikeDTO = this.getRatexLike_DTO(ratexLike);
  		}
        return ratexLikeDTO;
  		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<Void> updLike(RatexLike_DTO rDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime onDTTm = LocalDateTime.parse(rDTO.getOnDttm(), formatter);
  		Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
  		RatexLikePK ratexLikePK = new RatexLikePK(); 	
		ratexLikePK.setItemSeqNo(rDTO.getItemSeqNo());
		ratexLikePK.setOnDttm(onTs);
		ratexLikePK.setSourceSeqNo(rDTO.getSourceSeqNo());
		RatexLike_DTO ratexLikeDTO = new RatexLike_DTO();  		
  	  	
  		if(forumLikesCUDRepo.existsById(ratexLikePK))
    	{
        RatexLike ratexLike = this.setRatexLike(rDTO);
        forumLikesCUDRepo.save(ratexLike);
    	}
  	 	},asyncExecutor);      	
        return future;
    }
    	
    public CompletableFuture<Void> delSelectLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectLikes(iList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectLikesBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	forumLikesCUDRepo.delSelectLikesBetweenTimes(iList, frTs, toTs);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectLikesBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectLikesBySource(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectGoodLikes(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectGoodLikes(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectBadLikes(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectBadLikes(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectVisibleLikes(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectVisibleLikes(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectInVisibleLikes(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumLikesCUDRepo.delSelectInVisibleLikes(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllLikes() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		forumLikesCUDRepo.deleteAll();      	
  	 	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized ArrayList<RatexLike_DTO> getRatexLikeDtos(ArrayList<RatexLike> rcReqLikes) 
	{
		ArrayList<RatexLike_DTO> ratexLikeDTOs = new ArrayList<RatexLike_DTO>(); 
		for(int i=0; i < rcReqLikes.size();i++)
		{		
		ratexLikeDTOs.add(this.getRatexLike_DTO(rcReqLikes.get(i)));
		}		
		return ratexLikeDTOs;
	}
	
    private synchronized RatexLike_DTO getRatexLike_DTO(RatexLike rcReqLike) 
	{
		RatexLike_DTO ratexLikeDTO = new RatexLike_DTO();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexLikeDTO.setOnDttm(formatter.format(rcReqLike.getId().getOnDttm().toLocalDateTime()));
		ratexLikeDTO.setItemSeqNo(rcReqLike.getId().getItemSeqNo());
		ratexLikeDTO.setSourceSeqNo(rcReqLike.getId().getSourceSeqNo());
		ratexLikeDTO.setLikeFlag(rcReqLike.getLikeFlag());
		ratexLikeDTO.setVisible(rcReqLike.getVisible());		
		return ratexLikeDTO;
	}
		
	private synchronized RatexLike setRatexLike(RatexLike_DTO ratexLike_DTO) 
	{
		RatexLike ratexLike		=	new	RatexLike();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(ratexLike_DTO.getOnDttm(), formatter);
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
 	  	RatexLikePK ratexLikePK = new RatexLikePK(); 	
  		ratexLikePK.setItemSeqNo(ratexLike_DTO.getItemSeqNo());
  		ratexLikePK.setSourceSeqNo(ratexLike_DTO.getSourceSeqNo());
  		ratexLikePK.setOnDttm(onTs);
  		ratexLike.setId(ratexLikePK);
  		ratexLike.setLikeFlag(ratexLike_DTO.getLikeFlag());
  		ratexLike.setVisible(ratexLike_DTO.getVisible());
		return ratexLike;
	}

	
}