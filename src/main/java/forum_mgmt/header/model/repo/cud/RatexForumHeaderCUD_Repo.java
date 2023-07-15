package forum_mgmt.header.model.repo.cud;

import java.sql.Timestamp;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import forum_mgmt.header.model.master.RatexForumHeader;
import forum_mgmt.header.model.master.RatexForumHeaderPK;

@Repository("forumHeaderCUDRepo")
public interface RatexForumHeaderCUD_Repo extends JpaRepository<RatexForumHeader, RatexForumHeaderPK>
{
	@Query(value = "delete FROM RATEX_FORUM_HEADER WHERE context_seq_no in :cList",nativeQuery = true)
	void delSelectForumsForContexts(@Param("cList") CopyOnWriteArrayList<Long> cList);
		
	@Query(value = "delete FROM RATEX_FORUM_HEADER WHERE target_seq_no in :tList order by target_seq_no, on_dttm",nativeQuery = true)
	void delSelectForumsForTargets(@Param("tList") CopyOnWriteArrayList<Long> tList);

	@Query(value = "delete FROM RATEX_FORUM_HEADER WHERE source_seq_no in :sList order by source_seq_no, on_dttm",nativeQuery = true)
	void delSelectForumsForSources(@Param("sList") CopyOnWriteArrayList<Long> sList);

	@Query(value = "delete FROM RATEX_FORUM_HEADER where (context_seq_no in :cList and ON_DTTM >= :frDtTm and ON_ON_DTTM <= :toDtTm) ORDER BY context_seq_no, on_dttm",nativeQuery = true)
	void delSelectForumsBetweenTimes(@Param("cList") CopyOnWriteArrayList<Long> cList, @Param("frDtTm") Timestamp frDtTm, @Param("toDtTm") Timestamp toDtTm);
		
}