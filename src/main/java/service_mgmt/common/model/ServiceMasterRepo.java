package service_mgmt.common.model;


import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("serviceMasterRepo")
public interface ServiceMasterRepo extends CrudRepository<ServiceMaster, Long> 
{
@Query(value = "SELECT * FROM LOGI_SERVICE_WORK_MASTER where booking_seq_no>0 and upper(status)<>'Y' ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
Optional<ArrayList<ServiceMaster>> getAllOpenBookings();

@Query(value = "SELECT * FROM LOGI_SERVICE_WORK_MASTER where (TO_BILL IS NULL or upper(TO_BILL)!='Y') and upper(status)='Y' ORDER BY SERVICE_WORK_SEQ_NO",nativeQuery = true) 
Optional<ArrayList<ServiceMaster>> getAllOpenBillings();

@Modifying
@Query(value="update LOGI_SERVICE_WORK_MASTER set BOOKING_SEQ_NO = booking_no_sequence.nextval where booking_seq_no>0 and SERVICE_WORK_SEQ_NO=:servWorkSeqNo", nativeQuery = true)
void updateBookingNumber(@Param(value = "servWorkSeqNo") Long servWorkSeqNo);

@Query(value = "delete FROM LOGI_SERVICE_WORK_MASTER where upper(status)='Y'",nativeQuery = true) 
void deleteCompleted();

@Query(value = "delete FROM LOGI_SERVICE_WORK_MASTER where booking_seq_no= :bookingSeqNo and upper(status)='Y'",nativeQuery = true) 
void deleteBooking(@Param(value = "bookingSeqNo") Long bookingSeqNo);

@Query(value = "SELECT * FROM LOGI_SERVICE_WORK_MASTER where (ROWNUM = 1 and booking_seq_no=:bookingSeqNo)",nativeQuery = true) 
Optional<ServiceMaster> getServiceForBooking(@Param(value = "bookingSeqNo") Long bookingSeqNo);

@Query(value = "SELECT * FROM LOGI_SERVICE_WORK_MASTER where (upper(status)<>'Y' and upper(res_alloc_status)<>'Y') and upper(auto_alloc_status)='Y'))",nativeQuery = true) 
Optional<ArrayList<ServiceMaster>> getServicesForResAlloc();

/*	
	@Query(value = "INSERT INTO LOGI_PRODUCT_MASTER (PRODUCT_SEQ_NO,PRODUCT,REMARKS,ASSET_ID,PARENT_PRODUCT_SEQ_NO,STATUS) \r\n" + 
			"	select LOGI_LOCATION_SEQ_NO.NEXTVAL,'new','myremarks','assetssid',2,'no' from DUAL d\r\n" + 
			"	where not exists (SELECT 1 FROM  LOGI_PRODUCT_MASTER x WHERE  x.PRODUCT_SEQ_NO = 1 )",nativeQuery = true) 
	void delProjectStatus(@Param("projectSeqNo") Integer projectSeqNo);
	*/
}