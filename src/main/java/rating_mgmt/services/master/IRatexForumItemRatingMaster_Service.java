package rating_mgmt.services.master;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import rating_mgmt.model.dto.RatexForumItemRatingMaster_DTO;

public interface IRatexForumItemRatingMaster_Service 
{
	public CompletableFuture<RatexForumItemRatingMaster_DTO> newRating(RatexForumItemRatingMaster_DTO cDTO);
	public CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> getAllRatings();
	public CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> getSelectRatings(ArrayList<Long> cList);
	public CompletableFuture<Void> updRating(RatexForumItemRatingMaster_DTO cDTO);
	public CompletableFuture<Void> delSelectRatings(ArrayList<Long> cList);
	public CompletableFuture<Void> delAllRatings();		
	
}