package document_mgmt.services.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import document_mgmt.model.dto.RatexDocument_DTO;

public interface IRatexDocumentsCUD_Service 
{
	public CompletableFuture<RatexDocument_DTO> newDocument(RatexDocument_DTO rDTO);
	public CompletableFuture<Void> updDocument(RatexDocument_DTO rDTO);
	public CompletableFuture<Void> delAllDocuments();
	public CompletableFuture<Void> delSelectDocuments(CopyOnWriteArrayList<Long> iList);
}