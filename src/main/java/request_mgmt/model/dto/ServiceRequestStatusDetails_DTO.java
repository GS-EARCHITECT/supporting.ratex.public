package request_mgmt.model.dto;

import java.io.Serializable;

public class ServiceRequestStatusDetails_DTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9030207590831582726L;
	private Long requestSeqNo;
	private String onDate;
	private String remark;
	private String status;

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public String getOnDate() {
		return onDate;
	}

	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remarks) {
		this.remark = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ServiceRequestStatusDetails_DTO(Long requestSeqNo, String onDate, String remark, String status) {
		super();
		this.requestSeqNo = requestSeqNo;
		this.onDate = onDate;
		this.remark = remark;
		this.status = status;
	}

	public ServiceRequestStatusDetails_DTO() {
		super();
	}

}