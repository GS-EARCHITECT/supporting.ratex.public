package document_mgmt.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import document_mgmt.model.dto.RatexDocument_DTO;
import document_mgmt.services.cud.IRatexDocumentsCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexDocumentsCUDMgmt")
public class RatexDocumentsCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexDocumentsCUD_Service ratexDocumentsCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexDocument_DTO> newForum(@RequestBody RatexDocument_DTO rDTO) {
		CompletableFuture<RatexDocument_DTO> completableFuture = ratexDocumentsCUDServ.newDocument(rDTO);
		RatexDocument_DTO ratexDocument_DTO = null;
		try {
			ratexDocument_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(ratexDocument_DTO, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updDocument")
	public void updDocument(@RequestBody RatexDocument_DTO rDTO) {
		ratexDocumentsCUDServ.updDocument(rDTO);
	}

	@DeleteMapping("/delSelectDocuments")
	public void delSelectDocuments(@RequestBody CopyOnWriteArrayList<Long> iList) {
		ratexDocumentsCUDServ.delSelectDocuments(iList);
	}

	@DeleteMapping("/delAllDocuments")
	public void delAllDocuments() {
		ratexDocumentsCUDServ.delAllDocuments();
	}
}