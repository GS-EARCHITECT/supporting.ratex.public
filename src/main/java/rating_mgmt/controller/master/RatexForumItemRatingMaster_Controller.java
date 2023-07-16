package rating_mgmt.controller.master;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rating_mgmt.model.dto.RatexForumItemRatingMaster_DTO;
import rating_mgmt.services.master.IRatexForumItemRatingMaster_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/ratexForumItemRatingMasterMgmt")
public class RatexForumItemRatingMaster_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Rating_Controller.class);

	@Autowired
	private IRatexForumItemRatingMaster_Service ratexForumItemRatingServ;

	@PostMapping("/new")
	public ResponseEntity<RatexForumItemRatingMaster_DTO> newRating(@RequestBody RatexForumItemRatingMaster_DTO ratexRatingDTO) 
	{
		CompletableFuture<RatexForumItemRatingMaster_DTO> completableFuture = ratexForumItemRatingServ.newRating(ratexRatingDTO); 
		RatexForumItemRatingMaster_DTO ratexRatingMaster_DTO=null;
		try {
			ratexRatingMaster_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(ratexRatingMaster_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllRatings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<RatexForumItemRatingMaster_DTO>> getAllRatings() {
		
		CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> completableFuture = ratexForumItemRatingServ.getAllRatings();
		ArrayList<RatexForumItemRatingMaster_DTO> ratexRatingMaster_DTOs2=null;
		try {
			ratexRatingMaster_DTOs2 = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(ratexRatingMaster_DTOs2, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectRatings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<RatexForumItemRatingMaster_DTO>> getSelectRatings(@RequestBody ArrayList<Long> cList) 
	{
	CompletableFuture<ArrayList<RatexForumItemRatingMaster_DTO>> completableFuture = ratexForumItemRatingServ.getSelectRatings(cList);	
	ArrayList<RatexForumItemRatingMaster_DTO> ratexRatingMaster_DTOs=null;
		try {
			ratexRatingMaster_DTOs = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ResponseEntity<>(ratexRatingMaster_DTOs, HttpStatus.OK);
	}

	@PutMapping("/updRating")
	public void updRating(@RequestBody RatexForumItemRatingMaster_DTO ratexRatingDTO) 
	{
		ratexForumItemRatingServ.updRating(ratexRatingDTO);		
	}

	@DeleteMapping("/delSelectRatings")
	public void delSelectRatings(@RequestBody ArrayList<Long> cList)
	{
		ratexForumItemRatingServ.delSelectRatings(cList);
	}
	
	@DeleteMapping("/delAllRatings")
	public void deleteAllRatings() 
	{
		ratexForumItemRatingServ.delAllRatings();
	}
	
	
}