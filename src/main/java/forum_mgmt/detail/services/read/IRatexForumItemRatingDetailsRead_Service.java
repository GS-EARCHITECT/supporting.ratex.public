package forum_mgmt.detail.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;

public interface IRatexForumItemRatingDetailsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getAllRatingDetails();
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatings(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBetweenRatings(CopyOnWriteArrayList<Long> iList, Float fr, Float to);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBySource(CopyOnWriteArrayList<Long> sList);
}