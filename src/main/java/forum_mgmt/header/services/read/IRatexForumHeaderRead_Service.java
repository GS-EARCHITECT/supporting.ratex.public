package forum_mgmt.header.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import forum_mgmt.header.model.dto.RatexForumHeader_DTO;

public interface IRatexForumHeaderRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getAllForums();
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForContexts(CopyOnWriteArrayList<Long> cList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForTargets(CopyOnWriteArrayList<Long> tList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForSources(CopyOnWriteArrayList<Long> sList);
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsBetweenTimes(CopyOnWriteArrayList<Long> cList, String frDtTm, String toDtTm);
}