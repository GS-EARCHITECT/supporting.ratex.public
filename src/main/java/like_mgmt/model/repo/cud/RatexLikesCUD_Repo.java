package like_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import like_mgmt.model.master.RatexLike;
import like_mgmt.model.master.RatexLikePK;

@Repository("forumLikesCUDRepo")
public interface RatexLikesCUD_Repo extends JpaRepository<RatexLike, RatexLikePK>
{
	@Query(value = "delete FROM RATEX_LIKES WHERE item_seq_no in :iList",nativeQuery = true)
	void delSelectLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
		
	@Query(value = "delete FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(LIKE_FLAG))='Y'))",nativeQuery = true)
	void delSelectGoodLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);

	@Query(value = "delete FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(LIKE_FLAG))<>'Y'))",nativeQuery = true)
	void delSelectBadLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "delete FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(VISIBLE))='Y'))",nativeQuery = true)
	void delSelectVisibleLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "delete FROM RATEX_LIKES WHERE ((item_seq_no in :iList) and (LIKE_FLAG IS NOT null and upper(trim(VISIBLE))<>'Y'))",nativeQuery = true)
	void delSelectInVisibleLikes(@Param("iList") CopyOnWriteArrayList<Long> iList);
	
	@Query(value = "SELECT * FROM RATEX_LIKES where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm)",nativeQuery = true)
	void delSelectLikesBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_LIKES where source_seq_no in :sList",nativeQuery = true)
	void delSelectLikesBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}