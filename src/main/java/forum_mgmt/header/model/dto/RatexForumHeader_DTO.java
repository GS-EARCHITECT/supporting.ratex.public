package forum_mgmt.header.model.dto;

import java.io.Serializable;

public class RatexForumHeader_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5188448552286448466L;
	private Long rootItemSeqNo;
	private Long contextSeqNo;
	private Long targetSeqNo;
	private Long sourceSeqNo;
	private String onDttm;

	public Long getRootItemSeqNo() {
		return rootItemSeqNo;
	}

	public void setRootItemSeqNo(Long rootItemSeqNo) {
		this.rootItemSeqNo = rootItemSeqNo;
	}

	public Long getContextSeqNo() {
		return contextSeqNo;
	}

	public void setContextSeqNo(Long contextSeqNo) {
		this.contextSeqNo = contextSeqNo;
	}

	public Long getTargetSeqNo() {
		return targetSeqNo;
	}

	public void setTargetSeqNo(Long targetSeqNo) {
		this.targetSeqNo = targetSeqNo;
	}

	public Long getSourceSeqNo() {
		return sourceSeqNo;
	}

	public void setSourceSeqNo(Long sourceSeqNo) {
		this.sourceSeqNo = sourceSeqNo;
	}

	public String getOnDttm() {
		return onDttm;
	}

	public void setOnDttm(String onDttm) {
		this.onDttm = onDttm;
	}

	public RatexForumHeader_DTO(Long rootItemSeqNo, Long contextSeqNo, Long targetSeqNo, Long sourceSeqNo,
			String onDttm) {
		super();
		this.rootItemSeqNo = rootItemSeqNo;
		this.contextSeqNo = contextSeqNo;
		this.targetSeqNo = targetSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
	}

	public RatexForumHeader_DTO() {
		super();
	}

}