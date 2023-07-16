package document_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_DOCUMENTS database table.
 * 
 */
@Entity
@Table(name = "RATEX_DOCUMENTS")
public class RatexDocument implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexDocumentPK id;

	public RatexDocument() {
	}

	public RatexDocumentPK getId() {
		return this.id;
	}

	public void setId(RatexDocumentPK id) {
		this.id = id;
	}

	public RatexDocument(RatexDocumentPK id) {
		super();
		this.id = id;
	}

}