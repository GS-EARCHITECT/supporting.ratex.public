package forum_mgmt.detail.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import forum_mgmt.detail.model.master.RatexForumDetail;
import forum_mgmt.detail.model.master.RatexForumDetailPK;

@Repository("ratexForumDetailsReadRepo")
public interface RatexForumDetailsRead_Repo extends JpaRepository<RatexForumDetail, RatexForumDetailPK> 
{
	@Query(value = "select * FROM RATEX_FORUM_DETAILS WHERE (item_seq_no in :iList and par_item_seq_no in :pList) order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumDetail> getSelectForumDetails(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("pList") CopyOnWriteArrayList<Long> pList);
		
	@Query(value = "select * FROM RATEX_FORUM_DETAILS WHERE par_item_seq_no in :pList order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumDetail> getSelectForumDetailsForParents(@Param("pList") CopyOnWriteArrayList<Long> pList);

	@Query(value = "SELECT * FROM RATEX_FORUM_DETAILS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm) ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumDetail> getSelectForumDetailsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_FORUM_DETAILS where source_seq_no in :sList ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexForumDetail> getSelectForumDetailsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}