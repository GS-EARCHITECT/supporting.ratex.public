package like_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import like_mgmt.model.master.RatexLike;
import like_mgmt.model.master.RatexLikePK;

@Repository("ratexLikesReadRepo")
public interface RatexLikesRead_Repo extends JpaRepository<RatexLike, RatexLikePK> 
{
	@Query(value = "select * FROM RATEX_LIKES WHERE item_seq_no in :iList order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
		
	@Query(value = "select * FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(LIKE_FLAG))='Y')) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectGoodLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);

	@Query(value = "select * FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(LIKE_FLAG))<>'Y')) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectBadLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "select * FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(VISIBLE))='Y')) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectVisibleLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "select * FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(VISIBLE))<>'Y')) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectInVisibleLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "SELECT * FROM RATEX_LIKES where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm) ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectLikesBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_LIKES where source_seq_no in :sList ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexLike> getSelectLikesBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}