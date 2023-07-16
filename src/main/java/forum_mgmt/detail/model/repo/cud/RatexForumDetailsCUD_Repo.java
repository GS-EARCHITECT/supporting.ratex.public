package forum_mgmt.detail.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import forum_mgmt.detail.model.master.RatexForumDetail;
import forum_mgmt.detail.model.master.RatexForumDetailPK;

@Repository("forumDetailsCUDRepo")
public interface RatexForumDetailsCUD_Repo extends JpaRepository<RatexForumDetail, RatexForumDetailPK>
{
	@Query(value = "delete FROM RATEX_FORUM_DETAILS WHERE (item_seq_no in :iList and par_item_seq_no in :pList)",nativeQuery = true)
	void delSelectForumDetails(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("pList") CopyOnWriteArrayList<Long> pList);
		
	@Query(value = "delete FROM RATEX_FORUM_DETAILS WHERE par_item_seq_no in :pList",nativeQuery = true)
	void delSelectForumDetailsForParents(@Param("pList") CopyOnWriteArrayList<Long> pList);

	@Query(value = "SELECT * FROM RATEX_FORUM_DETAILS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm)",nativeQuery = true)
	void delSelectForumDetailsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_FORUM_DETAILS where source_seq_no in :sList",nativeQuery = true)
	void delSelectForumDetailsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}