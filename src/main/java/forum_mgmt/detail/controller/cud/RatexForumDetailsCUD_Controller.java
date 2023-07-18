package forum_mgmt.detail.controller.cud;

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
import forum_mgmt.detail.model.dto.RatexForumDetail_DTO;
import forum_mgmt.detail.services.cud.IRatexForumDetailsCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/ratexForumDetailsCUDMgmt")
public class RatexForumDetailsCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexForumDetailsCUD_Service ratexForumDetailsCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexForumDetail_DTO> newForum(@RequestBody RatexForumDetail_DTO rDTO) 
	{
		CompletableFuture<RatexForumDetail_DTO> completableFuture = ratexForumDetailsCUDServ.newForumDetail(rDTO); 
		RatexForumDetail_DTO ratexForumDetail_DTO=null;
		try {
			ratexForumDetail_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(ratexForumDetail_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updForumDetail")
	public void updForumDetail(@RequestBody RatexForumDetail_DTO rDTO) 
	{
		ratexForumDetailsCUDServ.updForumDetail(rDTO);		
	}
	
	@DeleteMapping("/delSelectForumDetails")
	public void delSelectForumDetails(@RequestBody CopyOnWriteArrayList<Long> iList, @RequestBody CopyOnWriteArrayList<Long> pList)
	{
		ratexForumDetailsCUDServ.delSelectForumDetails(iList, pList);
	}

	@DeleteMapping("/delSelectForumDetailsForParents")
	public void delSelectDetailsForParents(@RequestBody CopyOnWriteArrayList<Long> pList)
	{
	ratexForumDetailsCUDServ.delSelectForumDetailsForParents(pList);
	}

	@DeleteMapping("/delSelectDetailsBetweenTimes/{fr}/{to}")
	public void delSelectForumDetailsBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> iList,@PathVariable String fr, @PathVariable String to)
	{
	ratexForumDetailsCUDServ.delSelectForumDetailsBetweenTimes(iList, fr, to);
	}
	
	@DeleteMapping("/delAllForumDetails")
	public void delAllForumDetails()
	{
	ratexForumDetailsCUDServ.delAllForumDetails();
	}
}