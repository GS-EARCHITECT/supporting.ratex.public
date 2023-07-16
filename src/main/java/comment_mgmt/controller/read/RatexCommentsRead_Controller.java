package comment_mgmt.controller.read;

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
import comment_mgmt.model.dto.RatexComment_DTO;
import comment_mgmt.services.read.IRatexCommentsRead_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexCommentsReadMgmt")
public class RatexCommentsRead_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexCommentsRead_Service ratexCommentsReadServ;

	@GetMapping(value = "/getAllComments", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexComment_DTO>> getAllComments() 
	{
		CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> completableFuture = ratexCommentsReadServ.getAllComments();
		CopyOnWriteArrayList<RatexComment_DTO> ratexComment_DTOs2=null;
		try {
			ratexComment_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexComment_DTOs2, HttpStatus.OK);
	}

	
	@GetMapping(value = "/getSelectComments", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexComment_DTO>> getSelectComments(@RequestBody CopyOnWriteArrayList<Long> iList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> completableFuture = ratexCommentsReadServ.getSelectComments(iList);	
	CopyOnWriteArrayList<RatexComment_DTO> ratexComment_DTOs=null;
		try {
			ratexComment_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexComment_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectCommentsBetweenTimes/{frDtTm}/{toDtTm}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexComment_DTO>> getSelectComments(@RequestBody CopyOnWriteArrayList<Long> iList, @PathVariable String frDtTm, @PathVariable String toDtTm) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> completableFuture = ratexCommentsReadServ.getSelectCommentsBetweenTimes(iList, frDtTm, toDtTm);	
	CopyOnWriteArrayList<RatexComment_DTO> ratexComment_DTOs=null;
		try {
			ratexComment_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexComment_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectCommentsBySource", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<CopyOnWriteArrayList<RatexComment_DTO>> getSelectCommentsBySource(@RequestBody CopyOnWriteArrayList<Long> sList) 
	{
	CompletableFuture<CopyOnWriteArrayList<RatexComment_DTO>> completableFuture = ratexCommentsReadServ.getSelectCommentsBySource(sList);	
	CopyOnWriteArrayList<RatexComment_DTO> ratexComment_DTOs=null;
		try {
			ratexComment_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexComment_DTOs, HttpStatus.OK);
	}
}