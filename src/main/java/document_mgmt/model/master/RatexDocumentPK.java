package document_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RATEX_DOCUMENTS database table.
 * 
 */
@Embeddable
public class RatexDocumentPK implements Serializable 
{
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ITEM_SEQ_NO")
	private Long itemSeqNo;

	@Column(name = "DOCLIST_SEQ_NO")
	private Long doclistSeqNo;

	public RatexDocumentPK() {
	}

	public Long getItemSeqNo() {
		return this.itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Long getDoclistSeqNo() {
		return this.doclistSeqNo;
	}

	public void setDoclistSeqNo(Long doclistSeqNo) {
		this.doclistSeqNo = doclistSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatexDocumentPK)) {
			return false;
		}
		RatexDocumentPK castOther = (RatexDocumentPK) other;
		return (this.itemSeqNo == castOther.itemSeqNo) && (this.doclistSeqNo == castOther.doclistSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.itemSeqNo ^ (this.itemSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.doclistSeqNo ^ (this.doclistSeqNo >>> 32)));

		return hash;
	}

	public RatexDocumentPK(Long itemSeqNo, Long doclistSeqNo) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.doclistSeqNo = doclistSeqNo;
	}

}