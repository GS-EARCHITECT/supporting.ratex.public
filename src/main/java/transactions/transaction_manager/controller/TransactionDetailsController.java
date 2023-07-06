package transactions.transaction_manager.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
import transaction_manager.model.TransactionDetailsDTO;
import transaction_manager.services.I_TransactionDetailsService;
*/

@RestController
@RequestMapping("/txnDetails")
public class TransactionDetailsController {

	/*
	@Autowired
	private I_TransactionDetailsService txnDetailsService;

	@PostMapping("/addTransaction")
	public ResponseEntity<TransactionDetailsDTO> addToTransaction(@RequestBody TransactionDetailsDTO txnDetailsDTO) 
	{		
		TransactionDetailsDTO txnDetailsDTO2 = txnDetailsService.newTransactionDetails(txnDetailsDTO);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<>(txnDetailsDTO2, httpHeaders, HttpStatus.CREATED);		
	}

	@GetMapping(value="/getAllTransactions", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<TransactionDetailsDTO>> getAllTransactions() {
		ArrayList<TransactionDetailsDTO> txnDetailsDTOs = txnDetailsService.getAllTransactionDetails();
		return new ResponseEntity<>(txnDetailsDTOs, HttpStatus.OK);
			}
	
	@GetMapping(value="/getTransaction/{txnSeqNo}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<TransactionDetailsDTO>> getTransactionById(@PathVariable String txnSeqNo) {
		ArrayList<TransactionDetailsDTO> txnDetailsDTOs = txnDetailsService.getTransactionDetailsByNo(Integer.parseInt(txnSeqNo));
		return new ResponseEntity<>(txnDetailsDTOs, HttpStatus.OK);
	}

	@PutMapping("/txnDetails")
	public void updateTransactionDetails(@RequestBody TransactionDetailsDTO txnDetailsDTO) {
		txnDetailsService.updateTransactionDetails(txnDetailsDTO);
	}

	@DeleteMapping("/txnDetails/{txnSeqNo}")
	public void deleteTransactionDetails(@PathVariable Integer txnSeqNo, @PathVariable Integer itemSeqNo, @PathVariable String dateOnStr) {
	txnDetailsService.deleteTransactionDetails(txnSeqNo, itemSeqNo, dateOnStr);
	}

	@DeleteMapping("/txnDetails/all")
	public void deleteAllTransactions() {
	txnDetailsService.deleteAllTransactions();
	}
	*/
}