package forum_mgmt.detail.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RATEX_FORUM_DETAILS database table.
 * 
 */
@Embeddable
public class RatexForumDetailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ITEM_SEQ_NO")
	private Long itemSeqNo;

	@Column(name="PAR_ITEM_SEQ_NO")
	private Long parItemSeqNo;

	public RatexForumDetailPK() {
	}
	public Long getItemSeqNo() {
		return this.itemSeqNo;
	}
	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}
	public Long getParItemSeqNo() {
		return this.parItemSeqNo;
	}
	public void setParItemSeqNo(Long parItemSeqNo) {
		this.parItemSeqNo = parItemSeqNo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RatexForumDetailPK)) {
			return false;
		}
		RatexForumDetailPK castOther = (RatexForumDetailPK)other;
		return 
			(this.itemSeqNo == castOther.itemSeqNo)
			&& (this.parItemSeqNo == castOther.parItemSeqNo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.itemSeqNo ^ (this.itemSeqNo >>> 32)));
		hash = hash * prime + ((int) (this.parItemSeqNo ^ (this.parItemSeqNo >>> 32)));
		
		return hash;
	}
	
	public RatexForumDetailPK(Long itemSeqNo, Long parItemSeqNo) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.parItemSeqNo = parItemSeqNo;
	}
	
	
}