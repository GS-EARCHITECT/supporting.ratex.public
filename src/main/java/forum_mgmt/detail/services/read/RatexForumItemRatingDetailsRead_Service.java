package forum_mgmt.detail.services.read;

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
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;
import rating_mgmt.model.master.RatexForumItemRatingDetail;
import rating_mgmt.model.repo.detail.read.RatexForumItemRatingDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumItemRatingDetailsReadServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexForumItemRatingDetailsRead_Service implements IRatexForumItemRatingDetailsRead_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexRating_Service.class);
	
	@Autowired
    private RatexForumItemRatingDetailsRead_Repo ratingForumItemDetailsReadRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getAllRatingDetails()
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumItemRatingDetail>) ratingForumItemDetailsReadRepo.findAll();
    	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatings(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumItemRatingDetail>) ratingForumItemDetailsReadRepo.getSelectItemRatings(iList);
    	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
     
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBetweenRatings(CopyOnWriteArrayList<Long> iList, Float fr, Float to)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumItemRatingDetail>) ratingForumItemDetailsReadRepo.getSelectItemRatingsBetweenRatings(iList, fr, to);
    	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
       	CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumItemRatingDetail>) ratingForumItemDetailsReadRepo.getSelectItemRatingsBetweenTimes(iList, frTs, toTs);
    	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumItemRatingDetail>) ratingForumItemDetailsReadRepo.getSelectItemRatingsBySource(sList);
    	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    private synchronized CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> getRatexRatingDtos(CopyOnWriteArrayList<RatexForumItemRatingDetail> rcReqDetails) 
	{
		CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDTOs = new CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>(); 
		for(int i=0; i < rcReqDetails.size();i++)
		{		
		ratexRatingDTOs.add(this.getRatexForumItemRatingDetail_DTO(rcReqDetails.get(i)));
		}		
		return ratexRatingDTOs;
	}
	
	private synchronized RatexForumItemRatingDetail_DTO getRatexForumItemRatingDetail_DTO(RatexForumItemRatingDetail rcReqDetail) 
	{
		RatexForumItemRatingDetail_DTO ratexRatingDTO = new RatexForumItemRatingDetail_DTO();
		ratexRatingDTO.setSourceSeqNo(rcReqDetail.getId().getItemSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexRatingDTO.setOnDttm(formatter.format(rcReqDetail.getId().getOnDttm().toLocalDateTime()));
		ratexRatingDTO.setItemSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexRatingDTO.setRating(rcReqDetail.getRating());
		ratexRatingDTO.setVisible(rcReqDetail.getVisible());
		return ratexRatingDTO;
		}
	
}