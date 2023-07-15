package rating_mgmt.model.repo.detail.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rating_mgmt.model.master.RatexForumItemRatingDetail;
import rating_mgmt.model.master.RatexForumItemRatingDetailPK;

@Repository("ratingForumItemDetailsCUDRepo")
public interface RatexForumItemRatingDetailsCUD_Repo extends JpaRepository<RatexForumItemRatingDetail, RatexForumItemRatingDetailPK> 
{
@Query(value = "delete FROM RATEX_TARGET_DETAILS WHERE item_seq_no in :iList",nativeQuery = true)
void delSelectItemRatingDetails(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
@Query(value = "delete FROM RATEX_TARGET_DETAILS WHERE (item_seq_no in :iList and rating > :fr and rating < :to)",nativeQuery = true)
void delSelectItemRatingDetailsBetweenRatings(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("fr") Float fr, @Param("to") Float to);

@Query(value = "delete FROM SERVICE_REQUEST_STATUS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm)",nativeQuery = true)
void delSelectItemRatingDetailsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);

@Query(value = "delete FROM SERVICE_REQUEST_STATUS where source_seq_no in :sList",nativeQuery = true)
void delSelectItemRatingDetailsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);

}