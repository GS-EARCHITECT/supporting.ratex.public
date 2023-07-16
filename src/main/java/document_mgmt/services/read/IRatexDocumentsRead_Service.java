package document_mgmt.services.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import document_mgmt.model.dto.RatexDocument_DTO;

public interface IRatexDocumentsRead_Service 
{
	public CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> getAllDocuments();	
	public CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> getSelectDocuments(CopyOnWriteArrayList<Long> iList);
	
}