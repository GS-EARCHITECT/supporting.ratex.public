package forum_mgmt.header.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import forum_mgmt.header.model.dto.RatexForumHeader_DTO;

public interface IRatexForumHeaderCUD_Service 
{
	public CompletableFuture<RatexForumHeader_DTO> newForum(RatexForumHeader_DTO fDTO);
	public CompletableFuture<Void> updForum(RatexForumHeader_DTO fDTO);
	public CompletableFuture<Void> delSelectForumsForContexts(CopyOnWriteArrayList<Long> cList);;
	public CompletableFuture<Void> delAllForums();
	public CompletableFuture<Void> delSelectForumsForTargets(CopyOnWriteArrayList<Long> tList);
	public CompletableFuture<Void> delSelectForumsForSources(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<Void> delSelectForumsBetweenTimes(CopyOnWriteArrayList<Long> cList, String frDtTm, String toDtTm);	
}