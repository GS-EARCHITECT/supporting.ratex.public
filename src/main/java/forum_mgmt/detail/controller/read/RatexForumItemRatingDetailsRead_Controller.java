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
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;
import rating_mgmt.services.detail.read.IRatexForumItemRatingDetailsRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexForumItemRatingDetailsReadMgmt")
public class RatexForumItemRatingDetailsRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Rating_Controller.class);

	@Autowired
	private IRatexForumItemRatingDetailsRead_Service ratexForumItemRatingDetailsReadServ;

	@GetMapping(value = "/getAllRatingDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getAllRatingDetails() 
	{
		CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> completableFuture = ratexForumItemRatingDetailsReadServ.getAllRatingDetails();
		CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDetail_DTOs2=null;
		try {
			ratexRatingDetail_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexRatingDetail_DTOs2, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getSelectItemRatings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatings(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> completableFuture = ratexForumItemRatingDetailsReadServ.getSelectItemRatings(iList);	
	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDetail_DTOs=null;
		try {
			ratexRatingDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexRatingDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectItemRatingsBetweenRatings/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatings(@RequestBody CopyOnWriteArrayList<Long> iList, @PathVariable Float fr, @PathVariable Float to) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> completableFuture = ratexForumItemRatingDetailsReadServ.getSelectItemRatingsBetweenRatings(iList, fr, to);	
	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDetail_DTOs=null;
		try {
			ratexRatingDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexRatingDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectItemRatingsBetweenTimes/{frDtTm}/{toDtTm}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatings(@RequestBody CopyOnWriteArrayList<Long> iList, @PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> completableFuture = ratexForumItemRatingDetailsReadServ.getSelectItemRatingsBetweenTimes(iList, frDtTm, toDtTm);	
	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDetail_DTOs=null;
		try {
			ratexRatingDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexRatingDetail_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectItemRatingsBySource", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> getSelectItemRatingsBySource(@RequestBody CopyOnWriteArrayList<Long> sList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO>> completableFuture = ratexForumItemRatingDetailsReadServ.getSelectItemRatingsBySource(sList);	
	CopyOnWriteArrayList<RatexForumItemRatingDetail_DTO> ratexRatingDetail_DTOs=null;
		try {
			ratexRatingDetail_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexRatingDetail_DTOs, HttpStatus.OK);
	}
}