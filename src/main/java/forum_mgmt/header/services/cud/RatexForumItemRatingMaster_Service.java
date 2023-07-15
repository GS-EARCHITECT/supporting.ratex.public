package forum_mgmt.header.services.cud;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rating_mgmt.model.dto.*;
import rating_mgmt.model.repo.*;
import rating_mgmt.model.repo.master.RatexForumItemRatingMaster_Repo;
import rating_mgmt.model.master.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumItemRatingServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class RatexForumItemRatingMaster_Service implements IRatexForumItemRatingMaster_Service 
{	
	//private static final Logger logger = LoggerFactory.getLogger(RatexRating_Service.class);
	
	@Autowired
    private RatexForumItemRatingMaster_Repo ratexRatingRepo;
	
	@Autowired
	private Executor asyncExecutor;
		
    @Override
	public CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> getAllRatings()
    {
    	CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
    	ArrayList<RatexForumItemRatingMaster> rcReqList = null;    	
    	rcReqList =  (ArrayList<RatexForumItemRatingMaster>) ratexRatingRepo.findAll();
    	ArrayList<RatexForumItemRatingMaster_DTO> lMasters = new ArrayList<RatexForumItemRatingMaster_DTO>();
    	lMasters = rcReqList != null ? this.getRatexRatingDtos(rcReqList) : null;        
   		return lMasters;
   		},asyncExecutor);
        return future;
    }
    
    @Override
	public CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> getSelectRatings(ArrayList<Long> itemSeqNos)
    {
    	CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
    	ArrayList<RatexForumItemRatingMaster> lMasters = ratexRatingRepo.getSelectItemRatings(itemSeqNos);
    	ArrayList<RatexForumItemRatingMaster_DTO> rcMasterDTOs = lMasters != null ? this.getRatexRatingDtos(lMasters): null;
    	return rcMasterDTOs;
   		},asyncExecutor);
        return future;
    }
            
    @Override
	public CompletableFuture<RatexForumItemRatingMaster_DTO> newRating(RatexForumItemRatingMaster_DTO rcDTO) 
    {    
		CompletableFuture<RatexForumItemRatingMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
		RatexForumItemRatingMaster_DTO ratexRatingDTO = new RatexForumItemRatingMaster_DTO();  		
  		if(!ratexRatingRepo.existsById(rcDTO.getItemSeqNo()))
  		{	
  		RatexForumItemRatingMaster ratexRatingMaster = ratexRatingRepo.save(this.setRatexForumItemRatingMaster(rcDTO));    
  		ratexRatingDTO = this.getRatexForumItemRatingMaster_DTO(ratexRatingMaster);
  		}
  		return ratexRatingDTO;
     	},asyncExecutor);
      	
        return future;

    }

    @Override
	public CompletableFuture<Void> updRating(RatexForumItemRatingMaster_DTO rcReqDTO) 
    {

    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	if(ratexRatingRepo.existsById(rcReqDTO.getItemSeqNo()))
    	{
        RatexForumItemRatingMaster ratexRatingMaster = this.setRatexForumItemRatingMaster(rcReqDTO);
        ratexRatingMaster.setItemSeqNo(rcReqDTO.getItemSeqNo());
    	ratexRatingRepo.save(ratexRatingMaster);
    	}	
     	},asyncExecutor);      	
        return future;    
    }
    	
    public CompletableFuture<Void> delSelectRatings(ArrayList<Long> cList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	ratexRatingRepo.delSelectItemRatings(cList);
  		},asyncExecutor);      	
        return future;
    }
    
	@Override
	public CompletableFuture<Void> delAllRatings() 
    {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		ratexRatingRepo.deleteAll();      	
  		},asyncExecutor);      	
        return future;
    }
    
    private ArrayList<RatexForumItemRatingMaster_DTO> getRatexRatingDtos(ArrayList<RatexForumItemRatingMaster> rcReqMasters) 
	{
		ArrayList<RatexForumItemRatingMaster_DTO> ratexRatingDTOs = new ArrayList<RatexForumItemRatingMaster_DTO>(); 
		for(int i=0; i < rcReqMasters.size();i++)
		{		
		ratexRatingDTOs.add(this.getRatexForumItemRatingMaster_DTO(rcReqMasters.get(i)));
		}		
		return ratexRatingDTOs;
	}
	
	private synchronized RatexForumItemRatingMaster_DTO getRatexForumItemRatingMaster_DTO(RatexForumItemRatingMaster rcReqMaster) 
	{
		RatexForumItemRatingMaster_DTO ratexRatingDTO = new RatexForumItemRatingMaster_DTO();
		ratexRatingDTO.setItemSeqNo(rcReqMaster.getItemSeqNo());
		ratexRatingDTO.setRateContinuous(rcReqMaster.getRateContinuous());
		ratexRatingDTO.setRateDiscreet(rcReqMaster.getRateDiscreet());
		return ratexRatingDTO;
		}
		
	private synchronized RatexForumItemRatingMaster setRatexForumItemRatingMaster(RatexForumItemRatingMaster_DTO ratexRatingDTO) 
	{
		RatexForumItemRatingMaster ratexRatingMaster		=	new	RatexForumItemRatingMaster();				
		ratexRatingMaster.setRateContinuous(ratexRatingDTO.getRateContinuous());
		ratexRatingMaster.setRateDiscreet(ratexRatingDTO.getRateDiscreet());		
		return ratexRatingMaster;
	}
	
}