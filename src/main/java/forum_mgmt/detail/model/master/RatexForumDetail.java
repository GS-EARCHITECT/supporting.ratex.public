package forum_mgmt.detail.model.master;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the RATEX_FORUM_DETAILS database table.
 * 
 */
@Entity
@Table(name="RATEX_FORUM_DETAILS")
public class RatexForumDetail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexForumDetailPK id;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="ON_DTTM")
	private Timestamp onDttm;

	@Column(name="SOURCE_SEQ_NO")
	private Long sourceSeqNo;

	@Column(name="SUMMARY")
	private String summary;

	public RatexForumDetail() {
	}

	public RatexForumDetailPK getId() {
		return this.id;
	}

	public void setId(RatexForumDetailPK id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getOnDttm() {
		return this.onDttm;
	}

	public void setOnDttm(Timestamp onDttm) {
		this.onDttm = onDttm;
	}

	public Long getSourceSeqNo() {
		return this.sourceSeqNo;
	}

	public void setSourceSeqNo(Long sourceSeqNo) {
		this.sourceSeqNo = sourceSeqNo;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public RatexForumDetail(RatexForumDetailPK id, String description, Timestamp onDttm, Long sourceSeqNo,
			String summary) {
		super();
		this.id = id;
		this.description = description;
		this.onDttm = onDttm;
		this.sourceSeqNo = sourceSeqNo;
		this.summary = summary;
	}
	
	

}