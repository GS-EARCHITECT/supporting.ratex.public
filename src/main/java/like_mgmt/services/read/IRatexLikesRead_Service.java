package like_mgmt.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import like_mgmt.model.dto.RatexLike_DTO;

public interface IRatexLikesRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getAllLikes();
	
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikes(CopyOnWriteArrayList<Long> iList);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectGoodLikes(CopyOnWriteArrayList<Long> iList);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectBadLikes(CopyOnWriteArrayList<Long> iList);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectVisibleLikes(CopyOnWriteArrayList<Long> iList);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectInVisibleLikes(CopyOnWriteArrayList<Long> iList);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikesBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikesBySource(CopyOnWriteArrayList<Long> sList);

}