package comment_mgmt.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import comment_mgmt.model.master.RatexComment;
import comment_mgmt.model.master.RatexCommentPK;

@Repository("forumCommentsCUDRepo")
public interface RatexCommentsCUD_Repo extends JpaRepository<RatexComment, RatexCommentPK>
{
	@Query(value = "delete FROM RATEX_COMMENTS WHERE item_seq_no in :iList",nativeQuery = true)
	void delSelectComments(@Param("iList") CopyOnWriteArrayList<Long> iList);
		
	@Query(value = "SELECT * FROM RATEX_COMMENTS where (item_seq_no in :iList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm)",nativeQuery = true)
	void delSelectCommentsBetweenTimes(@Param("iList") CopyOnWriteArrayList<Long> iList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
	
	@Query(value = "SELECT * FROM RATEX_COMMENTS where source_seq_no in :sList",nativeQuery = true)
	void delSelectCommentsBySource(@Param("sList") CopyOnWriteArrayList<Long> sList);
		
}