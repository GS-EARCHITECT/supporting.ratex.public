package comment_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import comment_mgmt.model.dto.RatexComment_DTO;

public interface IRatexCommentsCUD_Service 
{
	public CompletableFuture<RatexComment_DTO> newComment(RatexComment_DTO rDTO);
	public CompletableFuture<Void> updComment(RatexComment_DTO rDTO);
	public CompletableFuture<Void> delAllComments();	
	public CompletableFuture<Void> delSelectComments(CopyOnWriteArrayList<Long> iList);	
	public CompletableFuture<Void> delSelectCommentsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectCommentsBySource(CopyOnWriteArrayList<Long> sList);

}