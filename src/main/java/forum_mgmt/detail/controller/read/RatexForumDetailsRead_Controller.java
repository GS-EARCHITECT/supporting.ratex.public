package forum_mgmt.detail.controller.read;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import forum_mgmt.detail.model.dto.RatexForumDetail_DTO;
import forum_mgmt.detail.services.read.IRatexForumDetailsRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexForumDetailsReadMgmt")
public class RatexForumDetailsRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexForumDetailsRead_Service ratexForumDetailsReadServ;

	@GetMapping(value = "/getAllForumDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumDetail_DTO>> getAllForumDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> completableFuture = ratexForumDetailsReadServ.getAllForumDetails();
		CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDetail_DTOs2=null;
		try {
			ratexForumDetail_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexForumDetail_DTOs2, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getSelectForumDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetails(@RequestBody CopyOnWriteArrayList<Long> iList, @RequestBody CopyOnWriteArrayList<Long> pList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> completableFuture = ratexForumDetailsReadServ.getSelectForumDetails(iList, pList);	
	CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDetail_DTOs=null;
		try {
			ratexForumDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumDetailsByParents", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsByParents(@RequestBody CopyOnWriteArrayList<Long> pList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> completableFuture = ratexForumDetailsReadServ.getSelectForumDetailsBySource(pList);	
	CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDetail_DTOs=null;
		try {
			ratexForumDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumDetailsBetweenTimes/{frDtTm}/{toDtTm}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetails(@RequestBody CopyOnWriteArrayList<Long> iList, @PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> completableFuture = ratexForumDetailsReadServ.getSelectForumDetailsBetweenTimes(iList, frDtTm, toDtTm);	
	CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDetail_DTOs=null;
		try {
			ratexForumDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectForumDetailsBySource", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumDetail_DTO>> getSelectForumDetailsBySource(@RequestBody CopyOnWriteArrayList<Long> sList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumDetail_DTO>> completableFuture = ratexForumDetailsReadServ.getSelectForumDetailsBySource(sList);	
	CopyOnWriteArrayList<RatexForumDetail_DTO> ratexForumDetail_DTOs=null;
		try {
			ratexForumDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexForumDetail_DTOs, HttpStatus.OK);
	}
}