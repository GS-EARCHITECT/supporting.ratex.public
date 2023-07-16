package like_mgmt.controller.read;

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
import like_mgmt.model.dto.RatexLike_DTO;
import like_mgmt.services.read.IRatexLikesRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexLikesReadMgmt")
public class RatexLikesRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexLikesRead_Service ratexLikesReadServ;

	@GetMapping(value = "/getAllLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getAllLikes() 
	{
		CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getAllLikes();
		CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs2=null;
		try {
			ratexLike_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexLike_DTOs2, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getSelectLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikes(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectLikes(iList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectGoodLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectGoodLikes(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectGoodLikes(iList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getSelectBadLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectBadLikes(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectBadLikes(iList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectVisibleLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectVisibleLikes(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectVisibleLikes(iList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectInVisibleLikes", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectInVisibleLikes(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectInVisibleLikes(iList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getSelectLikesBetweenTimes/{frDtTm}/{toDtTm}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikes(@RequestBody CopyOnWriteArrayList<Long> iList, @PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectLikesBetweenTimes(iList, frDtTm, toDtTm);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectLikesBySource", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexLike_DTO>> getSelectLikesBySource(@RequestBody CopyOnWriteArrayList<Long> sList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexLike_DTO>> completableFuture = ratexLikesReadServ.getSelectLikesBySource(sList);	
	CopyOnWriteArrayList<RatexLike_DTO> ratexLike_DTOs=null;
		try {
			ratexLike_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexLike_DTOs, HttpStatus.OK);
	}
}