package forum_mgmt.header.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import forum_mgmt.header.model.dto.RatexForumHeader_DTO;
import forum_mgmt.header.services.read.IRatexForumHeaderRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/ratexForumHeaderReadMgmt")
public class RatexForumHeaderRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_ForumHeader_Controller.class);

	@Autowired
	private IRatexForumHeaderRead_Service ratexForumHeaderReadServ;

	@GetMapping(value = "/getAllForums", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumHeader_DTO>> getAllForums() {
		
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> completableFuture = ratexForumHeaderReadServ.getAllForums();
		CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderMaster_DTOs2=null;
		try {
			ratexForumHeaderMaster_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexForumHeaderMaster_DTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumsForContexts", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumHeadersForContexts(@RequestBody CopyOnWriteArrayList<Long> cList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> completableFuture = ratexForumHeaderReadServ.getSelectForumsForContexts(cList);	
	CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderMaster_DTOs=null;
		try {
			ratexForumHeaderMaster_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumHeaderMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumsForTargets", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumHeadersForTargets(@RequestBody CopyOnWriteArrayList<Long> tList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> completableFuture = ratexForumHeaderReadServ.getSelectForumsForTargets(tList);	
	CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderMaster_DTOs=null;
		try {
			ratexForumHeaderMaster_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumHeaderMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumsForSources", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForSources(@RequestBody CopyOnWriteArrayList<Long> sList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> completableFuture = ratexForumHeaderReadServ.getSelectForumsForSources(sList);	
	CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderMaster_DTOs=null;
		try {
			ratexForumHeaderMaster_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumHeaderMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumsBetweenTimes/{frDtTm}/{toDtTm}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> cList, @PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> completableFuture = ratexForumHeaderReadServ.getSelectForumsBetweenTimes(cList, frDtTm, toDtTm);	
	CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderMaster_DTOs=null;
		try {
			ratexForumHeaderMaster_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumHeaderMaster_DTOs, HttpStatus.OK);
	}

	
	
	
}