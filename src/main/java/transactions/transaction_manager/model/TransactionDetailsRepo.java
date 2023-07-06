package transactions.transaction_manager.model;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("txnDetailsRepo")
public interface TransactionDetailsRepo extends CrudRepository<TransactionDetails, TransactionDetailsPK> 
{	    
	@Query(value = "SELECT * FROM TRANSACTION_ITEM_DETAILS where TRANSACTION_SEQ_NO= :txnTypeSeqNo ORDER BY TRANSACTION_SEQ_NO",nativeQuery = true) 
	Optional<ArrayList<TransactionDetails>> getTxnDetailsForNo(@Param("txnseqNo") Integer txnSeqNo);
} 

