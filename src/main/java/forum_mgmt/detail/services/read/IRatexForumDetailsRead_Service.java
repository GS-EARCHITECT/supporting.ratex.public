package forum_mgmt.detail.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import forum_mgmt.detail.model.dto.RatexForumDetail_DTO;

public interface IRatexForumDetailsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getAllForumDetails();
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetails(CopyOnWriteArrayList<Long> iList, CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsForParents(CopyOnWriteArrayList<Long> pList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsBetweenTimes(CopyOnWriteArrayList<Long> iList, String frDtTm, String toDtTm);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsBySource(CopyOnWriteArrayList<Long> sList);
}