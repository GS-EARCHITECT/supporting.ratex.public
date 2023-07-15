package forum_mgmt.detail.controller.cud;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rating_mgmt.model.dto.RatexForumItemRatingDetail_DTO;
import rating_mgmt.services.detail.cud.IRatexForumItemRatingDetailsCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexForumItemRatingDetailsCUDMgmt")
public class RatexForumItemRatingDetailsCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Rating_Controller.class);

	@Autowired
	private IRatexForumItemRatingDetailsCUD_Service ratexForumItemRatingDetailsCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexForumItemRatingDetail_DTO> newRating(@RequestBody RatexForumItemRatingDetail_DTO rDTO) 
	{
		CompletableFuture<RatexForumItemRatingDetail_DTO> completableFuture = ratexForumItemRatingDetailsCUDServ.newRatingDetail(rDTO); 
		RatexForumItemRatingDetail_DTO ratexRatingDetail_DTO=null;
		try {
			ratexRatingDetail_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(ratexRatingDetail_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updRatingDetail")
	public void updRatingDetail(@RequestBody RatexForumItemRatingDetail_DTO rDTO) 
	{
		ratexForumItemRatingDetailsCUDServ.updRatingDetail(rDTO);		
	}
	
	@DeleteMapping("/delSelectRatingDetails")
	public void delSelectRatingDetails(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexForumItemRatingDetailsCUDServ.delSelectItemRatingDetails(iList);
	}

	@DeleteMapping("/delSelectItemRatingDetailsBetweenRatings/{fr}/{to}")
	public void delSelectItemRatingDetailsBetweenRatings(@RequestBody CopyOnWriteArrayList<Long> iList,@PathVariable Float fr, @PathVariable Float to)
	{
	ratexForumItemRatingDetailsCUDServ.delSelectItemRatingDetailsBetweenRatings(iList, fr, to);
	}

	@DeleteMapping("/delSelectItemRatingDetailsBetweenTimes/{fr}/{to}")
	public void delSelectItemRatingDetailsBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> iList,@PathVariable String fr, @PathVariable String to)
	{
	ratexForumItemRatingDetailsCUDServ.delSelectItemRatingDetailsBetweenTimes(iList, fr, to);
	}
	
	@DeleteMapping("/delAllRatingDetails")
	public void delAllRatingDetails()
	{
	ratexForumItemRatingDetailsCUDServ.delAllRatingDetails();
	}
}