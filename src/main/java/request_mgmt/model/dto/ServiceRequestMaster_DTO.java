package request_mgmt.model.dto;

import java.io.Serializable;

public class ServiceRequestMaster_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2471196741790137564L;
	private Long requestSeqNo;
	private Long companySeqNo;
	private Long personSeqNo;
	private String remark;
	private String requestDate;
	private String status;
	private Long supplierSeqNo;

	public Long getRequestSeqNo() {
		return requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Long getCompanySeqNo() {
		return companySeqNo;
	}

	public void setCompanySeqNo(Long companySeqNo) {
		this.companySeqNo = companySeqNo;
	}

	public Long getPersonSeqNo() {
		return personSeqNo;
	}

	public void setPersonSeqNo(Long personSeqNo) {
		this.personSeqNo = personSeqNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSupplierSeqNo() {
		return supplierSeqNo;
	}

	public void setSupplierSeqNo(Long supplierSeqNo) {
		this.supplierSeqNo = supplierSeqNo;
	}

	public ServiceRequestMaster_DTO(Long requestSeqNo, Long companySeqNo, Long personSeqNo, String remark, String requestDate,
			String status, Long supplierSeqNo) {
		super();
		this.requestSeqNo = requestSeqNo;
		this.companySeqNo = companySeqNo;
		this.personSeqNo = personSeqNo;
		this.remark = remark;
		this.requestDate = requestDate;
		this.status = status;
		this.supplierSeqNo = supplierSeqNo;
	}

	public ServiceRequestMaster_DTO() {
		super();
	}

}