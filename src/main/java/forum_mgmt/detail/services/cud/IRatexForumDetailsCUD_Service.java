package forum_mgmt.detail.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import forum_mgmt.detail.model.dto.RatexForumDetail_DTO;

public interface IRatexForumDetailsCUD_Service 
{
	public CompletableFuture<RatexForumDetail_DTO> newForumDetail(RatexForumDetail_DTO rDTO);
	public CompletableFuture<Void> updForumDetail(RatexForumDetail_DTO rDTO);
	public CompletableFuture<Void> delAllForumDetails();	
	public CompletableFuture<Void> delSelectForumDetails(CopyOnWriteArrayList<Long> iList, CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<Void> delSelectForumDetailsForParents(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<Void> delSelectForumDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<Void> delSelectForumDetailsBySource(CopyOnWriteArrayList<Long> sList);

}