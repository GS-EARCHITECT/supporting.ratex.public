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
import forum_mgmt.detail.model.dto.RatexForumDetail_DTO;
import forum_mgmt.detail.model.master.RatexForumDetail;
import forum_mgmt.detail.model.master.RatexForumDetailPK;
import forum_mgmt.detail.model.repo.cud.RatexForumDetailsCUD_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumDetailsCUDServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexForumDetailsCUD_Service implements IRatexForumDetailsCUD_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexForum_Service.class);
	
	@Autowired
    private RatexForumDetailsCUD_Repo forumDetailsCUDRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	@Override
	public CompletableFuture<RatexForumDetail_DTO> newForumDetail(RatexForumDetail_DTO rcDTO) 
    {    
		CompletableFuture<RatexForumDetail_DTO> future = CompletableFuture.supplyAsync(() -> 
		{	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
	  	Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
		RatexForumDetailPK ratexForumDetailPK = new RatexForumDetailPK(); 	
		ratexForumDetailPK.setItemSeqNo(rcDTO.getItemSeqNo());
		ratexForumDetailPK.setItemSeqNo(rcDTO.getParItemSeqNo());
		RatexForumDetail_DTO ratexForumDTO = new RatexForumDetail_DTO();  		
  		
		if(!forumDetailsCUDRepo.existsById(ratexForumDetailPK))
  		{	
  		RatexForumDetail ratexForumDetail = forumDetailsCUDRepo.save(this.setRatexForumDetail(rcDTO));    
  		ratexForumDTO = this.getRatexForumDetail_DTO(ratexForumDetail);
  		}
        return ratexForumDTO;
  		},asyncExecutor);
        return future;   		
    }

    @Override
	public CompletableFuture<Void> updForumDetail(RatexForumDetail_DTO rDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime onDTTm = LocalDateTime.parse(rDTO.getOnDttm(), formatter);
  		Timestamp onTs = Timestamp.valueOf(onDTTm);	  	  	
  		RatexForumDetailPK ratexForumDetailPK = new RatexForumDetailPK(); 	
  		ratexForumDetailPK.setItemSeqNo(rDTO.getItemSeqNo());
  		ratexForumDetailPK.setParItemSeqNo(rDTO.getParItemSeqNo());
  		RatexForumDetail_DTO ratexForumDTO = new RatexForumDetail_DTO();  		
  	  	
  		if(forumDetailsCUDRepo.existsById(ratexForumDetailPK))
    	{
        RatexForumDetail ratexForumDetail = this.setRatexForumDetail(rDTO);
        forumDetailsCUDRepo.save(ratexForumDetail);
    	}
  	 	},asyncExecutor);      	
        return future;
    }
    	
    public CompletableFuture<Void> delSelectForumDetails(CopyOnWriteArrayList<Long> iList, CopyOnWriteArrayList<Long> pList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumDetailsCUDRepo.delSelectForumDetails(iList, pList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectForumDetailsForParents(CopyOnWriteArrayList<Long> pList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumDetailsCUDRepo.delSelectForumDetailsForParents(pList);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectForumDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	forumDetailsCUDRepo.delSelectForumDetailsBetweenTimes(iList, frTs, toTs);
  	 	},asyncExecutor);      	
        return future;
    }
    
    public CompletableFuture<Void> delSelectForumDetailsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	forumDetailsCUDRepo.delSelectForumDetailsBySource(sList);
  	 	},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllForumDetails() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		forumDetailsCUDRepo.deleteAll();      	
  	 	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized ArrayList<RatexForumDetail_DTO> getRatexForumDtos(ArrayList<RatexForumDetail> rcReqDetails) 
	{
		ArrayList<RatexForumDetail_DTO> ratexForumDTOs = new ArrayList<RatexForumDetail_DTO>(); 
		for(int i=0; i < rcReqDetails.size();i++)
		{		
		ratexForumDTOs.add(this.getRatexForumDetail_DTO(rcReqDetails.get(i)));
		}		
		return ratexForumDTOs;
	}
	
    private synchronized RatexForumDetail_DTO getRatexForumDetail_DTO(RatexForumDetail rcReqDetail) 
	{
		RatexForumDetail_DTO ratexForumDTO = new RatexForumDetail_DTO();
		ratexForumDTO.setSourceSeqNo(rcReqDetail.getId().getItemSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexForumDTO.setOnDttm(formatter.format(rcReqDetail.getOnDttm().toLocalDateTime()));
		ratexForumDTO.setItemSeqNo(rcReqDetail.getId().getItemSeqNo());
		ratexForumDTO.setParItemSeqNo(rcReqDetail.getId().getParItemSeqNo());
		ratexForumDTO.setDescription(rcReqDetail.getDescription());
		ratexForumDTO.setSourceSeqNo(rcReqDetail.getSourceSeqNo());
		ratexForumDTO.setSummary(rcReqDetail.getSummary());
		return ratexForumDTO;
	}
		
	private synchronized RatexForumDetail setRatexForumDetail(RatexForumDetail_DTO ratexForumDetail_DTO) 
	{
		RatexForumDetail ratexForumDetail		=	new	RatexForumDetail();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(ratexForumDetail_DTO.getOnDttm(), formatter);
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
 	  	RatexForumDetailPK ratexForumDetailPK = new RatexForumDetailPK(); 	
  		ratexForumDetailPK.setItemSeqNo(ratexForumDetail_DTO.getItemSeqNo());
  		ratexForumDetailPK.setParItemSeqNo(ratexForumDetail_DTO.getParItemSeqNo());
  		ratexForumDetail.setId(ratexForumDetailPK);
  		ratexForumDetail.setSourceSeqNo(ratexForumDetail_DTO.getSourceSeqNo());
  		ratexForumDetail.setOnDttm(onTs);		
		ratexForumDetail.setDescription(ratexForumDetail_DTO.getDescription());
		ratexForumDetail.setSummary(ratexForumDetail_DTO.getSummary());		
		return ratexForumDetail;
	}

	
}