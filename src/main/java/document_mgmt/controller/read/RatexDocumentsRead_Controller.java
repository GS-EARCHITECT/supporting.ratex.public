package document_mgmt.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import document_mgmt.model.dto.RatexDocument_DTO;
import document_mgmt.services.read.IRatexDocumentsRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexDocumentsReadMgmt")
public class RatexDocumentsRead_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexDocumentsRead_Service ratexDocumentsReadServ;

	@GetMapping(value = "/getAllDocuments", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexDocument_DTO>> getAllDocuments() {
		CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> completableFuture = ratexDocumentsReadServ
				.getAllDocuments();
		CopyOnWriteArrayList<RatexDocument_DTO> ratexDocument_DTOs2 = null;
		try {
			ratexDocument_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(ratexDocument_DTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectDocuments", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexDocument_DTO>> getSelectDocuments(
			@RequestBody CopyOnWriteArrayList<Long> iList) {
		CompletableFuture<CopyOnWriteArrayList<RatexDocument_DTO>> completableFuture = ratexDocumentsReadServ
				.getSelectDocuments(iList);
		CopyOnWriteArrayList<RatexDocument_DTO> ratexDocument_DTOs = null;
		try {
			ratexDocument_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(ratexDocument_DTOs, HttpStatus.OK);
	}

}