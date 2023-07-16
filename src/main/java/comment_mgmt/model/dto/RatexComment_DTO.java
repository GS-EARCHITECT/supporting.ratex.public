package comment_mgmt.model.dto;

import java.io.Serializable;

public class RatexComment_DTO implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4148605579736263698L;
	private Long itemSeqNo;
	private Long sourceSeqNo;
	private String onDttm;
	private String remark;

	public Long getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public RatexComment_DTO(Long itemSeqNo, Long sourceSeqNo, String onDttm, String remark) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
		this.remark = remark;
	}

	public RatexComment_DTO() {
		super();
	}

}