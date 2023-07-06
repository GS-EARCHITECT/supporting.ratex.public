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
import transaction_manager.model.TransactionMasterDTO;
import transaction_manager.services.I_TransactionMasterService;
*/

@RestController
@RequestMapping("/txn")
public class TransactionMasterController {

/*	@Autowired
	private I_TransactionMasterService txnMasterService;

	@PostMapping("/addTransaction")
	public ResponseEntity<TransactionMasterDTO> addToTransaction(@RequestBody TransactionMasterDTO txnMasterDTO) 
	{		
		TransactionMasterDTO txnMasterDTO2 = txnMasterService.newTransaction(txnMasterDTO);
		
	    HttpHeaders httpHeaders = new HttpHeaders();
	    return new ResponseEntity<>(txnMasterDTO2, httpHeaders, HttpStatus.CREATED);		
	}

	@GetMapping(value="/getAllTransactions", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ArrayList<TransactionMasterDTO>> getAllTransactions() {
		ArrayList<TransactionMasterDTO> txnMasterDTOs = txnMasterService.getAllTransactions();
		return new ResponseEntity<>(txnMasterDTOs, HttpStatus.OK);
			}
	
	@GetMapping(value="/getTransaction/{txnSeqNo}", produces={MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<TransactionMasterDTO> getTransactionById(@PathVariable String txnSeqNo) {
		TransactionMasterDTO txnMasterDTO = txnMasterService.getTransactionByNo(Integer.parseInt(txnSeqNo));
		return new ResponseEntity<>(txnMasterDTO, HttpStatus.OK);
	}

	@PutMapping("/txnMaster")
	public void updateTransaction(@RequestBody TransactionMasterDTO txnMasterDTO) {
		txnMasterService.updateTransaction(txnMasterDTO);
	}

	@DeleteMapping("/txnMaster/{txnSeqNo}")
	public void deleteTransaction(@PathVariable String txnSeqNo) {
	txnMasterService.deleteTransaction(Integer.parseInt(txnSeqNo));
	}

	@DeleteMapping("/txnMaster/all")
	public void deleteAllTransactions() {
	txnMasterService.deleteAllTransactions();
	}
	*/
}