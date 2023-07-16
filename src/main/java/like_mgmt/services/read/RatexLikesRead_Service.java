package like_mgmt.services.read;

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
import like_mgmt.model.dto.*;
import like_mgmt.model.master.*;
import like_mgmt.model.repo.*;
import like_mgmt.model.repo.read.RatexLikesRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexLikesReadServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexLikesRead_Service implements IRatexLikesRead_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexLike_Service.class);
	
	@Autowired
    private RatexLikesRead_Repo ratexLikesReadRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getAllLikes()
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.findAll();
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikesBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectLikesBySource(sList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectLikes(iList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
     
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectBadLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectBadLikes(iList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectGoodLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectGoodLikes(iList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectVisibleLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectVisibleLikes(iList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectInVisibleLikes(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectInVisibleLikes(iList);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikesBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
       	CopyOnWriteArrayList<RatexLike> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexLike>) ratexLikesReadRepo.getSelectLikesBetweenTimes(iList, frTs, toTs);
    	CopyOnWriteArrayList<RatexLike_DTO> lDetails = new CopyOnWriteArrayList<RatexLike_DTO>();
    	lDetails = rcReqList != null ? this.getRatexLikeDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
  
    private synchronized CopyOnWriteArrayList<RatexLike_DTO> getRatexLikeDtos(CopyOnWriteArrayList<RatexLike> rcReqDetails) 
	{
		CopyOnWriteArrayList<RatexLike_DTO> ratexLikeDTOs = new CopyOnWriteArrayList<RatexLike_DTO>(); 
		for(int i=0; i < rcReqDetails.size();i++)
		{		
		ratexLikeDTOs.add(this.getRatexLike_DTO(rcReqDetails.get(i)));
		}		
		return ratexLikeDTOs;
	}
	
	private synchronized RatexLike_DTO getRatexLike_DTO(RatexLike rcReqDetail) 
	{
		RatexLike_DTO ratexLikeDTO = new RatexLike_DTO();		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexLikeDTO.setOnDttm(formatter.format(rcReqDetail.getId().getOnDttm().toLocalDateTime()));
		ratexLikeDTO.setItemSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexLikeDTO.setSourceSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexLikeDTO.setVisible(rcReqDetail.getVisible());
		ratexLikeDTO.setLikeFlag(rcReqDetail.getLikeFlag());
		ratexLikeDTO.setSourceSeqNo(rcReqDetail.getId().getSourceSeqNo());		
		return ratexLikeDTO;
		}
	
}