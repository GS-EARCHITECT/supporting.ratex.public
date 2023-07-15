package forum_mgmt.detail.services.cud;

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
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;
import rating_mgmt.model.master.RatexForumItemRatingDetail;
import rating_mgmt.model.master.RatexForumItemRatingDetailPK;
import rating_mgmt.model.repo.detail.cud.RatexForumItemRatingDetailsCUD_Repo;
import rating_mgmt.model.repo.detail.read.RatexForumItemRatingDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumItemRatingDetailsCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexForumItemRatingDetailsCUD_Service implements IRatexForumItemRatingDetailsCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexRating_Service.class);
	
	@Autowired
    private RatexForumItemRatingDetailsCUD_Repo ratingForumItemDetailsCUDRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<RatexForumItemRatingDetail_DTO> newRatingDetail(RatexForumItemRatingDetail_DTO rcDTO) 
    {    
		CompletableFuture<RatexForumItemRatingDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
	  	Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
		RatexForumItemRatingDetailPK ratexForumItemRatingDetailPK = new RatexForumItemRatingDetailPK(); 	
		ratexForumItemRatingDetailPK.setItemSeqNo(rcDTO.getItemSeqNo());
		ratexForumItemRatingDetailPK.setOnDttm(onTs);
		ratexForumItemRatingDetailPK.setSourceSeqNo(rcDTO.getSourceSeqNo());
		RatexForumItemRatingDetail_DTO ratexRatingDTO = new RatexForumItemRatingDetail_DTO();  		
  		
		if(!ratingForumItemDetailsCUDRepo.existsById(ratexForumItemRatingDetailPK))
  		{	
  		RatexForumItemRatingDetail ratexForumItemRatingDetail = ratingForumItemDetailsCUDRepo.save(this.setRatexForumItemRatingDetail(rcDTO));    
  		ratexRatingDTO = this.getRatexForumItemRatingDetail_DTO(ratexForumItemRatingDetail);
  		}
        return ratexRatingDTO;
  		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<Void> updRatingDetail(RatexForumItemRatingDetail_DTO rDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime onDTTm = LocalDateTime.parse(rDTO.getOnDttm(), formatter);
  		Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
  		RatexForumItemRatingDetailPK ratexForumItemRatingDetailPK = new RatexForumItemRatingDetailPK(); 	
  		ratexForumItemRatingDetailPK.setItemSeqNo(rDTO.getItemSeqNo());
  		ratexForumItemRatingDetailPK.setOnDttm(onTs);
  		ratexForumItemRatingDetailPK.setSourceSeqNo(rDTO.getSourceSeqNo());
  		RatexForumItemRatingDetail_DTO ratexRatingDTO = new RatexForumItemRatingDetail_DTO();  		
  	  	
  		if(ratingForumItemDetailsCUDRepo.existsById(ratexForumItemRatingDetailPK))
    	{
        RatexForumItemRatingDetail ratexRatingDetail = this.setRatexForumItemRatingDetail(rDTO);
        ratingForumItemDetailsCUDRepo.save(ratexRatingDetail);
    	}
  	 	},asyncExecutor);      	
        return future;
    }
    	
    public CompletableFuture<Void> delSelectItemRatingDetails(CopyOnWriteArrayList<Long> iList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	ratingForumItemDetailsCUDRepo.delSelectItemRatingDetails(iList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectItemRatingDetailsBetweenRatings(CopyOnWriteArrayList<Long> iList, Float fr, Float to)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	ratingForumItemDetailsCUDRepo.delSelectItemRatingDetailsBetweenRatings(iList, fr, to);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectItemRatingDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	ratingForumItemDetailsCUDRepo.delSelectItemRatingDetailsBetweenTimes(iList, frTs, toTs);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectItemRatingDetailsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	ratingForumItemDetailsCUDRepo.delSelectItemRatingDetailsBySource(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllRatingDetails() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		ratingForumItemDetailsCUDRepo.deleteAll();      	
  	 	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized ArrayList<RatexForumItemRatingDetail_DTO> getRatexRatingDtos(ArrayList<RatexForumItemRatingDetail> rcReqDetails) 
	{
		ArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDTOs = new ArrayList<RatexForumItemRatingDetail_DTO>(); 
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
		
	private synchronized RatexForumItemRatingDetail setRatexForumItemRatingDetail(RatexForumItemRatingDetail_DTO ratexRatingDetail_DTO) 
	{
		RatexForumItemRatingDetail ratexRatingDetail		=	new	RatexForumItemRatingDetail();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(ratexRatingDetail_DTO.getOnDttm(), formatter);
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
 	  	RatexForumItemRatingDetailPK ratexForumItemRatingDetailPK = new RatexForumItemRatingDetailPK(); 	
  		ratexForumItemRatingDetailPK.setItemSeqNo(ratexRatingDetail_DTO.getItemSeqNo());
  		ratexForumItemRatingDetailPK.setOnDttm(onTs);
  		ratexForumItemRatingDetailPK.setSourceSeqNo(ratexRatingDetail_DTO.getSourceSeqNo());
		ratexRatingDetail.setId(ratexForumItemRatingDetailPK);
		ratexRatingDetail.setRating(ratexRatingDetail_DTO.getRating());
		ratexRatingDetail.setVisible(ratexRatingDetail_DTO.getVisible());
		return ratexRatingDetail;
	}

	
}