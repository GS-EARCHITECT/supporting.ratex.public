package comment_mgmt.model.repo.read;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import comment_mgmt.model.master.RatexComment;
import comment_mgmt.model.master.RatexCommentPK;

@Repository("ratexCommentsReadRepo")
public interface RatexCommentsRead_Repo extends JpaRepository<RatexComment, RatexCommentPK> 
{
	@Query(value = "select * FROM RATEX_COMMENTS WHERE item_seq_no in :iList order by item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexComment> getSelectComments(@Param("iList") CopyOnWriteArrayList<Long> iList);
		
	@Query(value = "SELECT * FROM RATEX_COMMENTS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm) ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexComment> getSelectCommentsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_COMMENTS where source_seq_no in :sList ORDER BY item_seq_no, on_dttm",nativeQuery = true)
	CopyOnWriteArrayList<RatexComment> getSelectCommentsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}