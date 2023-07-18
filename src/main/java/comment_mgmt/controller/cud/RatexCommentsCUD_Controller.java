package comment_mgmt.controller.cud;

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
import comment_mgmt.model.dto.RatexComment_DTO;
import comment_mgmt.services.cud.IRatexCommentsCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/ratexCommentsCUDMgmt")
public class RatexCommentsCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexCommentsCUD_Service ratexCommentsCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexComment_DTO> newForum(@RequestBody RatexComment_DTO rDTO) 
	{
		CompletableFuture<RatexComment_DTO> completableFuture = ratexCommentsCUDServ.newComment(rDTO); 
		RatexComment_DTO ratexComment_DTO=null;
		try {
			ratexComment_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(ratexComment_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updComment")
	public void updComment(@RequestBody RatexComment_DTO rDTO) 
	{
		ratexCommentsCUDServ.updComment(rDTO);		
	}
	
	@DeleteMapping("/delSelectComments")
	public void delSelectComments(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexCommentsCUDServ.delSelectComments(iList);
	}

	@DeleteMapping("/delSelectDetailsBetweenTimes/{fr}/{to}")
	public void delSelectCommentsBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> iList,@PathVariable String fr, @PathVariable String to)
	{
	ratexCommentsCUDServ.delSelectCommentsBetweenTimes(iList, fr, to);
	}
	
	@DeleteMapping("/delAllComments")
	public void delAllComments()
	{
	ratexCommentsCUDServ.delAllComments();
	}
}