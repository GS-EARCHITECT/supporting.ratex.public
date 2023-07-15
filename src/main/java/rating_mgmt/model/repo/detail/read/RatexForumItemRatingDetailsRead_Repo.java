package rating_mgmt.model.repo.detail.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rating_mgmt.model.master.RatexForumItemRatingDetail;
import rating_mgmt.model.master.RatexForumItemRatingDetailPK;

@Repository("ratingForumItemDetailsReadRepo")
public interface RatexForumItemRatingDetailsRead_Repo extends JpaRepository<RatexForumItemRatingDetail, RatexForumItemRatingDetailPK> 
{
	@Query(value = "select * FROM RATEX_TARGET_DETAILS WHERE item_seq_no in :iList order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumItemRatingDetail> getSelectItemRatings(@Param("tList") CopyOnWriteArrayList<Long> iList);
		
	@Query(value = "select * FROM RATEX_TARGET_DETAILS WHERE (item_seq_no in :iList and rating > :fr and rating < :to) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumItemRatingDetail> getSelectItemRatingsBetweenRatings(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("fr") Float fr, @Param("to") Float to);

	@Query(value = "SELECT * FROM SERVICE_REQUEST_STATUS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm) ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumItemRatingDetail> getSelectItemRatingsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM SERVICE_REQUEST_STATUS where source_seq_no in :sList ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumItemRatingDetail> getSelectItemRatingsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}