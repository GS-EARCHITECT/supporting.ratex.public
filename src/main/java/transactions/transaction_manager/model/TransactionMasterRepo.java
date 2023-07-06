package transactions.transaction_manager.model;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("txnMasterRepo")
public interface TransactionMasterRepo extends CrudRepository<TransactionMaster, Integer> 
{
@Query(value = "SELECT * FROM TRANSACTION_MASTER where TRANSACTION_TYPE_SEQ_NO= :txnTypeSeqNo ORDER BY TRANSACTION_SEQ_NO",nativeQuery = true) 
Optional<ArrayList<TransactionMaster>> getTxnsForType(@Param("txnTypeseqNo") Integer txnTypeSeqNo);

@Query(value = "SELECT * FROM TRANSACTION_ITEM_DETAILS where TRANSACTION_SEQ_NO= :txnSeqNo ORDER BY TRANSACTION_SEQ_NO",nativeQuery = true) 
ArrayList<TransactionDetails> getTxnDDetails(@Param("txnSeqNo") Integer txnSeqNo);

@Modifying
@Query(value="INSERT INTO store_order_master (STORE_REQUEST_SEQ_NO, REQUESTOR_SEQ_NO, STATUS, REMARK, SERV_REQUEST_SEQ_NO, QTY_UNIT_SEQ_NO, IS_BOOKED,ITEM_SEQ_NO,QTY_ALLOCATED,QTY_REQUESTED, MODE_TXN, QTY_BOOKED, SERVICE_REQUEST_SEQ_NO VALUES (store_request_sequence.nextval, :REQUESTOR_SEQ_NO, :STATUS, :REMARK, :SERV_REQUEST_SEQ_NO, :QTY_UNIT_SEQ_NO, :IS_BOOKED, :ITEM_SEQ_NO, :QTY_ALLOCATED, :QTY_REQUESTED, :MODE_TXN, :QTY_BOOKED, :SERVICE_REQUEST_SEQ_NO)", nativeQuery = true)
void insertStoreRecord(@Param(value = "REQUESTOR_SEQ_NO") Integer REQUESTOR_SEQ_NO,
@Param(value = "STATUS") String STATUS,
@Param(value = "REMARK") String REMARK,
@Param(value = "SERV_REQUEST_SEQ_NO") Integer SERV_REQUEST_SEQ_NO,
@Param(value = "QTY_UNIT_SEQ_NO") Integer QTY_UNIT_SEQ_NO,
@Param(value = "IS_BOOKED") Character IS_BOOKED,
@Param(value = "ITEM_SEQ_NO") Integer ITEM_SEQ_NO,
@Param(value = "QTY_ALLOCATED") Float QTY_ALLOCATED,
@Param(value = "QTY_REQUESTED") Float QTY_REQUESTED,
@Param(value = "MODE_TXN") Integer MODE_TXN,
@Param(value = "QTY_BOOKED") Float QTY_BOOKED,
@Param(value = "SERVICE_REQUEST_SEQ_NO") Integer SERVICE_REQUEST_SEQ_NO);
} 

