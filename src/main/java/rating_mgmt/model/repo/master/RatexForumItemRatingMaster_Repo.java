package rating_mgmt.model.repo.master;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rating_mgmt.model.master.RatexForumItemRatingMaster;

@Repository("forumItemRatingRepo")
public interface RatexForumItemRatingMaster_Repo extends JpaRepository<RatexForumItemRatingMaster, Long> 
{
@Query(value = "select * FROM RATEX_TARGET_MASTER WHERE item_seq_no in :rList order by item_seq_no",nativeQuery = true)
ArrayList<RatexForumItemRatingMaster> getSelectItemRatings(@Param("iList") ArrayList<Long> iList);
	
@Query(value = "delete FROM RATEX_TARGET_MASTER WHERE item_seq_no in :rList",nativeQuery = true)
void delSelectItemRatings(@Param("rList") ArrayList<Long> rList);
}