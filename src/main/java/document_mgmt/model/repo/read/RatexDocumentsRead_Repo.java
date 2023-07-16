package document_mgmt.model.repo.read;

import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import document_mgmt.model.master.RatexDocument;
import document_mgmt.model.master.RatexDocumentPK;

@Repository("ratexDocumentsReadRepo")
public interface RatexDocumentsRead_Repo extends JpaRepository<RatexDocument, RatexDocumentPK> 
{
	@Query(value = "select * FROM RATEX_DOCUMENTS WHERE item_seq_no in :iList order by item_seq_no",nativeQuery = true)
	CopyOnWriteArrayList<RatexDocument> getSelectDocuments(@Param("iList") CopyOnWriteArrayList<Long> iList);			
}