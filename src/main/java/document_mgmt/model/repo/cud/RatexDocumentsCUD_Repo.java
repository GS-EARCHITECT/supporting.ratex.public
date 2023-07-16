package document_mgmt.model.repo.cud;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import document_mgmt.model.master.RatexDocument;
import document_mgmt.model.master.RatexDocumentPK;

@Repository("forumDocumentsCUDRepo")
public interface RatexDocumentsCUD_Repo extends JpaRepository<RatexDocument, RatexDocumentPK>
{
	@Query(value = "delete FROM RATEX_DOCUMENTS WHERE item_seq_no in :iList",nativeQuery = true)
	void delSelectDocuments(@Param("iList") CopyOnWriteArrayList<Long> iList);
		
}