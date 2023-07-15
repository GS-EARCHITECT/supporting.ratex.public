package rating_mgmt.services.detail.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;

public interface IRatexForumItemRatingDetailsCUD_Service 
{
	public CompletableFuture<RatexForumItemRatingDetail_DTO> newRatingDetail(RatexForumItemRatingDetail_DTO rDTO);
	public CompletableFuture<Void> updRatingDetail(RatexForumItemRatingDetail_DTO rDTO);
	public CompletableFuture<Void> delSelectItemRatingDetails(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delAllRatingDetails();
	public CompletableFuture<Void> delSelectItemRatingDetailsBetweenRatings(CopyOnWriteArrayList<Long> iList, Float fr, Float to);
	public CompletableFuture<Void> delSelectItemRatingDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectItemRatingDetailsBySource(CopyOnWriteArrayList<Long> sList);
}