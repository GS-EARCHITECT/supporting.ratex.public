package forum_mgmt.header.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import forum_mgmt.header.model.dto.RatexForumHeader_DTO;
import forum_mgmt.header.services.cud.IRatexForumHeaderCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/ratexForumHeaderCUDMgmt")
public class RatexForumHeaderCUD_Controller {

	// private static final Logger logger =
	// LoggerFactory.getLogger(Customer_ForumHeader_Controller.class);

	@Autowired
	private IRatexForumHeaderCUD_Service ratexForumHeaderCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexForumHeader_DTO> newForum(@RequestBody RatexForumHeader_DTO ratexForumDTO) {
		CompletableFuture<RatexForumHeader_DTO> completableFuture = ratexForumHeaderCUDServ.newForum(ratexForumDTO);
		RatexForumHeader_DTO ratexForumMaster_DTO = null;
		try {
			ratexForumMaster_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(ratexForumMaster_DTO, httpHeaders, HttpStatus.CREATED);
	}

	@PutMapping("/updForum")
	public void updForum(@RequestBody RatexForumHeader_DTO ratexForumDTO) {
		ratexForumHeaderCUDServ.updForum(ratexForumDTO);
	}

	@DeleteMapping("/delSelectForumsForContexts")
	public void delSelectForums(@RequestBody CopyOnWriteArrayList<Long> cList) {
		ratexForumHeaderCUDServ.delSelectForumsForContexts(cList);
	}

	@DeleteMapping("/delSelectForumsForTargets")
	public void delSelectForumsForTargets(@RequestBody CopyOnWriteArrayList<Long> tList) {
		ratexForumHeaderCUDServ.delSelectForumsForTargets(tList);
	}

	@DeleteMapping("/delSelectForumsForSources")
	public void delSelectForumsForSources(@RequestBody CopyOnWriteArrayList<Long> sList) {
		ratexForumHeaderCUDServ.delSelectForumsForSources(sList);
	}

	@DeleteMapping("/delSelectForumsBetweenTimes/{frDtTm}/{toDtTm}")
	public void delSelectForumsBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> cList, @PathVariable String frDtTm,
			@PathVariable String toDtTm) {
		ratexForumHeaderCUDServ.delSelectForumsBetweenTimes(cList, frDtTm, toDtTm);
	}

	@DeleteMapping("/delAllForums")
	public void deleteAllForums() {
		ratexForumHeaderCUDServ.delAllForums();
	}

}