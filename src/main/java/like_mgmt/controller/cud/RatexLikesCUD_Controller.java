package like_mgmt.controller.cud;

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
import like_mgmt.model.dto.RatexLike_DTO;
import like_mgmt.services.cud.IRatexLikesCUD_Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/ratexLikesCUDMgmt")
public class RatexLikesCUD_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Forum_Controller.class);

	@Autowired
	private IRatexLikesCUD_Service ratexLikesCUDServ;

	@PostMapping("/new")
	public ResponseEntity<RatexLike_DTO> newForum(@RequestBody RatexLike_DTO rDTO) 
	{
		CompletableFuture<RatexLike_DTO> completableFuture = ratexLikesCUDServ.newLike(rDTO); 
		RatexLike_DTO ratexLike_DTO=null;
		try {
			ratexLike_DTO = completableFuture.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(ratexLike_DTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@PutMapping("/updLike")
	public void updLike(@RequestBody RatexLike_DTO rDTO) 
	{
		ratexLikesCUDServ.updLike(rDTO);		
	}
	
	@DeleteMapping("/delSelectLikes")
	public void delSelectLikes(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexLikesCUDServ.delSelectLikes(iList);
	}

	@DeleteMapping("/delSelectGoodLikes")
	public void delSelectGoodLikes(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexLikesCUDServ.delSelectGoodLikes(iList);
	}
	
	@DeleteMapping("/delSelectBadLikes")
	public void delSelectBadLikes(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexLikesCUDServ.delSelectBadLikes(iList);
	}

	@DeleteMapping("/delSelectVisibleLikes")
	public void delSelectVisibleLikes(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexLikesCUDServ.delSelectVisibleLikes(iList);
	}
	
	@DeleteMapping("/delSelectInVisibleLikes")
	public void delSelectInVisibleLikes(@RequestBody CopyOnWriteArrayList<Long> iList)
	{
		ratexLikesCUDServ.delSelectInVisibleLikes(iList);
	}
	
	@DeleteMapping("/delSelectDetailsBetweenTimes/{fr}/{to}")
	public void delSelectLikesBetweenTimes(@RequestBody CopyOnWriteArrayList<Long> iList,@PathVariable String fr, @PathVariable String to)
	{
	ratexLikesCUDServ.delSelectLikesBetweenTimes(iList, fr, to);
	}
	
	@DeleteMapping("/delAllLikes")
	public void delAllLikes()
	{
	ratexLikesCUDServ.delAllLikes();
	}
}