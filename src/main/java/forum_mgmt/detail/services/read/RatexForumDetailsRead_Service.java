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
import forum_mgmt.detail.model.dto.*;
import forum_mgmt.detail.model.master.*;
import forum_mgmt.detail.model.repo.read.RatexForumDetailsRead_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumDetailsReadServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexForumDetailsRead_Service implements IRatexForumDetailsRead_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexForum_Service.class);
	
	@Autowired
    private RatexForumDetailsRead_Repo ratexForumDetailsReadRepo;
		
	@Autowired
	private Executor asyncExecutor;
	
	
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getAllForumDetails()
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumDetail>) ratexForumDetailsReadRepo.findAll();
    	CopyOnWriteArrayList<RatexForumDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexForumDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsBySource(CopyOnWriteArrayList<Long> sList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumDetail>) ratexForumDetailsReadRepo.getSelectForumDetailsBySource(sList);
    	CopyOnWriteArrayList<RatexForumDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexForumDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetails(CopyOnWriteArrayList<Long> iList, CopyOnWriteArrayList<Long> pList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumDetail>) ratexForumDetailsReadRepo.getSelectForumDetails(iList, pList);
    	CopyOnWriteArrayList<RatexForumDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexForumDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
     
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsForParents(CopyOnWriteArrayList<Long> pList)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
       	CopyOnWriteArrayList<RatexForumDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumDetail>) ratexForumDetailsReadRepo.getSelectForumDetailsForParents(pList);
    	CopyOnWriteArrayList<RatexForumDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexForumDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
    
    @Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm)
    {
    	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
       	CopyOnWriteArrayList<RatexForumDetail> rcReqList = null;    	
    	rcReqList =  (CopyOnWriteArrayList<RatexForumDetail>) ratexForumDetailsReadRepo.getSelectForumDetailsBetweenTimes(iList, frTs, toTs);
    	CopyOnWriteArrayList<RatexForumDetail_DTO> lDetails = new CopyOnWriteArrayList<RatexForumDetail_DTO>();
    	lDetails = rcReqList != null ? this.getRatexForumDtos(rcReqList) : null;        
    	return lDetails;
   		},asyncExecutor);
        return future;   		
    }
  
    private synchronized CopyOnWriteArrayList<RatexForumDetail_DTO> getRatexForumDtos(CopyOnWriteArrayList<RatexForumDetail> rcReqDetails) 
	{
		CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDTOs = new CopyOnWriteArrayList<RatexForumDetail_DTO>(); 
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
	
}