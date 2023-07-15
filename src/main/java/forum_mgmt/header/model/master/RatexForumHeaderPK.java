package forum_mgmt.header.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The primary key class for the RATEX_FORUM_HEADER database table.
 * 
 */
@Embeddable
public class RatexForumHeaderPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "CONTEXT_SEQ_NO")
	private Long contextSeqNo;

	@Column(name = "TARGET_SEQ_NO")
	private Long targetSeqNo;

	@Column(name = "SOURCE_SEQ_NO")
	private Long sourceSeqNo;

	@Column(name = "ON_DTTM")
	private Timestamp onDttm;

	public RatexForumHeaderPK() {
	}

	public Long getContextSeqNo() {
		return this.contextSeqNo;
	}

	public void setContextSeqNo(Long contextSeqNo) {
		this.contextSeqNo = contextSeqNo;
	}

	public Long getTargetSeqNo() {
		return this.targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contextSeqNo == null) ? 0 : contextSeqNo.hashCode());
		result = prime * result + ((onDttm == null) ? 0 : onDttm.hashCode());
		result = prime * result + ((sourceSeqNo == null) ? 0 : sourceSeqNo.hashCode());
		result = prime * result + ((targetSeqNo == null) ? 0 : targetSeqNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatexForumHeaderPK other = (RatexForumHeaderPK) obj;
		if (contextSeqNo == null) {
			if (other.contextSeqNo != null)
				return false;
		} else if (!contextSeqNo.equals(other.contextSeqNo))
			return false;
		if (onDttm == null) {
			if (other.onDttm != null)
				return false;
		} else if (!onDttm.equals(other.onDttm))
			return false;
		if (sourceSeqNo == null) {
			if (other.sourceSeqNo != null)
				return false;
		} else if (!sourceSeqNo.equals(other.sourceSeqNo))
			return false;
		if (targetSeqNo == null) {
			if (other.targetSeqNo != null)
				return false;
		} else if (!targetSeqNo.equals(other.targetSeqNo))
			return false;
		return true;
	}

	public RatexForumHeaderPK(Long contextSeqNo, Long targetSeqNo, Long sourceSeqNo, Timestamp onDttm) {
		super();
		this.contextSeqNo = contextSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
	}

}