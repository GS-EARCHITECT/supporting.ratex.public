package forum_mgmt.detail.model.dto;

import java.io.Serializable;

public class RatexForumDetail_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7294215009226086914L;
	private String description;
	private String onDttm;
	private Long sourceSeqNo;
	private String summary;
	private Long itemSeqNo;
	private Long parItemSeqNo;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOnDttm() {
		return onDttm;
	}

	public void setOnDttm(String onDttm) {
		this.onDttm = onDttm;
	}

	public Long getSourceSeqNo() {
		return sourceSeqNo;
	}

	public void setSourceSeqNo(Long sourceSeqNo) {
		this.sourceSeqNo = sourceSeqNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Long getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Long getParItemSeqNo() {
		return parItemSeqNo;
	}

	public void setParItemSeqNo(Long parItemSeqNo) {
		this.parItemSeqNo = parItemSeqNo;
	}

	public RatexForumDetail_DTO(String description, String onDttm, Long sourceSeqNo, String summary, Long itemSeqNo,
			Long parItemSeqNo) {
		super();
		this.description = description;
		this.onDttm = onDttm;
		this.sourceSeqNo = sourceSeqNo;
		this.summary = summary;
		this.itemSeqNo = itemSeqNo;
		this.parItemSeqNo = parItemSeqNo;
	}

	public RatexForumDetail_DTO() {
		super();
	}

}