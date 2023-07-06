package service_mgmt.common.model;

import java.io.Serializable;
public class ServiceMasterDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2084912027022289826L;
	private Long SERVICE_WORK_SEQ_NO;
	private Long bookingSeqNo;
	private Long createdBy;
	private Long membershipSeqNo;
	private String onDate;	
	private Long billingUnitSeqNo;	
	private Long parentServiceWorkSeqNo;
	private Long personSeqNo;
	private String remarks;
	private String reqDate;
	private Long requestSeqNo;
	private String serviceId;
	private Long serviceSeqNo;
	private String status;
	private Character RES_ALLOC_STATUS;
	private Character JOB_ALLOC_STATUS;
	private Character RES_DIRECT_INDIRECT_FLAG;
	private Character AUTO_ALLOC_STATUS;
	public Long getSERVICE_WORK_SEQ_NO() {
		return SERVICE_WORK_SEQ_NO;
	}
	
	
	public Long getBillingUnitSeqNo() {
		return billingUnitSeqNo;
	}

	public void setBillingUnitSeqNo(Long billingUnitSeqNo) {
		this.billingUnitSeqNo = billingUnitSeqNo;
	}


	public void setSERVICE_WORK_SEQ_NO(Long sERVICE_WORK_SEQ_NO) {
		SERVICE_WORK_SEQ_NO = sERVICE_WORK_SEQ_NO;
	}
	public Long getBookingSeqNo() {
		return bookingSeqNo;
	}
	public void setBookingSeqNo(Long bookingSeqNo) {
		this.bookingSeqNo = bookingSeqNo;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Long getMembershipSeqNo() {
		return membershipSeqNo;
	}
	public void setMembershipSeqNo(Long membershipSeqNo) {
		this.membershipSeqNo = membershipSeqNo;
	}
	public String getOnDate() {
		return onDate;
	}
	public void setOnDate(String onDate) {
		this.onDate = onDate;
	}
	public Long getParentServiceWorkSeqNo() {
		return parentServiceWorkSeqNo;
	}
	public void setParentServiceWorkSeqNo(Long parentServiceWorkSeqNo) {
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
	}
	public Long getPersonSeqNo() {
		return personSeqNo;
	}
	public void setPersonSeqNo(Long personSeqNo) {
		this.personSeqNo = personSeqNo;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getReqDate() {
		return reqDate;
	}
	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}
	public Long getRequestSeqNo() {
		return requestSeqNo;
	}
	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public Long getServiceSeqNo() {
		return serviceSeqNo;
	}
	public void setServiceSeqNo(Long serviceSeqNo) {
		this.serviceSeqNo = serviceSeqNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Character getRES_ALLOC_STATUS() {
		return RES_ALLOC_STATUS;
	}
	public void setRES_ALLOC_STATUS(Character rES_ALLOC_STATUS) {
		RES_ALLOC_STATUS = rES_ALLOC_STATUS;
	}
	public Character getJOB_ALLOC_STATUS() {
		return JOB_ALLOC_STATUS;
	}
	public void setJOB_ALLOC_STATUS(Character jOB_ALLOC_STATUS) {
		JOB_ALLOC_STATUS = jOB_ALLOC_STATUS;
	}
	public Character getRES_DIRECT_INDIRECT_FLAG() {
		return RES_DIRECT_INDIRECT_FLAG;
	}
	public void setRES_DIRECT_INDIRECT_FLAG(Character rES_DIRECT_INDIRECT_FLAG) {
		RES_DIRECT_INDIRECT_FLAG = rES_DIRECT_INDIRECT_FLAG;
	}
	public Character getAUTO_ALLOC_STATUS() {
		return AUTO_ALLOC_STATUS;
	}
	public void setAUTO_ALLOC_STATUS(Character aUTO_ALLOC_STATUS) {
		AUTO_ALLOC_STATUS = aUTO_ALLOC_STATUS;
	}
	public ServiceMasterDTO() {
		super();
	}


	public ServiceMasterDTO(Long sERVICE_WORK_SEQ_NO, Long bookingSeqNo, Long createdBy, Long membershipSeqNo,
			String onDate, Long billingUnitSeqNo, Long parentServiceWorkSeqNo, Long personSeqNo, String remarks,
			String reqDate, Long requestSeqNo, String serviceId, Long serviceSeqNo, String status,
			Character rES_ALLOC_STATUS, Character jOB_ALLOC_STATUS, Character rES_DIRECT_INDIRECT_FLAG,
			Character aUTO_ALLOC_STATUS) {
		super();
		SERVICE_WORK_SEQ_NO = sERVICE_WORK_SEQ_NO;
		this.bookingSeqNo = bookingSeqNo;
		this.createdBy = createdBy;
		this.membershipSeqNo = membershipSeqNo;
		this.onDate = onDate;
		this.billingUnitSeqNo = billingUnitSeqNo;
		this.parentServiceWorkSeqNo = parentServiceWorkSeqNo;
		this.personSeqNo = personSeqNo;
		this.remarks = remarks;
		this.reqDate = reqDate;
		this.requestSeqNo = requestSeqNo;
		this.serviceId = serviceId;
		this.serviceSeqNo = serviceSeqNo;
		this.status = status;
		RES_ALLOC_STATUS = rES_ALLOC_STATUS;
		JOB_ALLOC_STATUS = jOB_ALLOC_STATUS;
		RES_DIRECT_INDIRECT_FLAG = rES_DIRECT_INDIRECT_FLAG;
		AUTO_ALLOC_STATUS = aUTO_ALLOC_STATUS;
	}
	}
