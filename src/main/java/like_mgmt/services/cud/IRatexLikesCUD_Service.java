package like_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import like_mgmt.model.dto.RatexLike_DTO;

public interface IRatexLikesCUD_Service 
{
	public CompletableFuture<RatexLike_DTO> newLike(RatexLike_DTO rDTO);
	public CompletableFuture<Void> updLike(RatexLike_DTO rDTO);
	public CompletableFuture<Void> delAllLikes();
	public CompletableFuture<Void> delSelectLikes(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delSelectGoodLikes(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delSelectBadLikes(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delSelectVisibleLikes(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delSelectInVisibleLikes(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<Void> delSelectLikesBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectLikesBySource(CopyOnWriteArrayList<Long> sList);

}