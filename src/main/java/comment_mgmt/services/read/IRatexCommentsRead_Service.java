package comment_mgmt.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import comment_mgmt.model.dto.RatexComment_DTO;

public interface IRatexCommentsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getAllComments();
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectComments(CopyOnWriteArrayList<Long> iList);
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectCommentsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> getSelectCommentsBySource(CopyOnWriteArrayList<Long> sList);
	
}