package comment_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the RATEX_COMMENTS_DETAILS database table.
 * 
 */
@Embeddable
public class RatexCommentPK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name = "ITEM_SEQ_NO")
	private Long itemSeqNo;

	@Column(name = "SOURCE_SEQ_NO")
	private Long sourceSeqNo;

	@Column(name = "ON_DTTM")
	private Timestamp onDttm;

	public RatexCommentPK() {
	}

	public Long getItemSeqNo() {
		return this.itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Long getSourceSeqNo() {
		return this.sourceSeqNo;
	}

	public void setSourceSeqNo(Long sourceSeqNo) {
		this.sourceSeqNo = sourceSeqNo;
	}

	public Timestamp getOnDttm() {
		return this.onDttm;
	}

	public void setOnDttm(Timestamp onDttm) {
		this.onDttm = onDttm;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatexCommentPK)) {
			return false;
		}
		RatexCommentPK castOther = (RatexCommentPK) other;
		return (this.itemSeqNo == castOther.itemSeqNo) && (this.sourceSeqNo == castOther.sourceSeqNo)
				&& this.onDttm.equals(castOther.onDttm);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.itemSeqNo ^ (this.itemSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.sourceSeqNo ^ (this.sourceSeqNo >>> 32)));
		hash = hash * prime + this.onDttm.hashCode();

		return hash;
	}

	public RatexCommentPK(Long itemSeqNo, Long sourceSeqNo, Timestamp onDttm) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
	}

}