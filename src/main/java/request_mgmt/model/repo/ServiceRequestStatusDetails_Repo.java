package request_mgmt.model.repo;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;

@Repository("serviceRequestStatusRepo")
public interface ServiceRequestStatusDetails_Repo extends JpaRepository<ServiceRequestStatusDetails, ServiceRequestStatusDetailsPK> 
{
	@Query(value = "select * FROM SERVICE_REQUEST_STATUS WHERE request_seq_no in :rList order by request_seq_no",nativeQuery = true)
	ArrayList<ServiceRequestStatusDetails> getSelectRequestStatus(@Param("rList") ArrayList<Long> rList);

	@Query(value = "SELECT * FROM SERVICE_REQUEST_STATUS where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm) ORDER BY request_sEQ_NO",nativeQuery = true)
	ArrayList<ServiceRequestStatusDetails> getSelectRequestStatusBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

	@Modifying
	@Query(value = "delete FROM SERVICE_REQUEST_STATUS WHERE request_seq_no in :rList",nativeQuery = true)
	void delSelectRequestStatus(@Param("rList") ArrayList<Long> rList);

	@Modifying
	@Query(value = "DELETE FROM SERVICE_REQUEST_STATUS where (ON_DATE >= :frDtTm and ON_DATE <= :toDtTm)",nativeQuery = true)
	void delSelectRequestStatusBetweenTimes(@Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

}