package service_mgmt.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name="LOGI_SERVICE_WORK_MASTER")
public class ServiceMaster implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1540141891788720411L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logi_service_sequence")
	@SequenceGenerator(name = "logi_service_sequence",sequenceName="SERVICE_WORK_SEQ_NO", allocationSize = 1)
	private Long SERVICE_WORK_SEQ_NO;
	
	@Column(name="BOOKING_SEQ_NO")
	private Long bookingSeqNo;

	@Column(name="CREATED_BY")
	private Long createdBy;

	@Column(name="BILLING_CURRENCY_SEQ_NO")
	private Integer billingUnitSeqNo;
	
	@Column(name="MEMBERSHIP_SEQ_NO")
	private Long membershipSeqNo;

	@Column(name="ON_DATE")
	private Timestamp onDate;

	@Column(name="PARENT_SERVICE_WORK_SEQ_NO")
	private Long parentServiceWorkSeqNo;

	@Column(name="PERSON_SEQ_NO")
	private Long personSeqNo;

	@Column(name="REMARKS")
	private String remarks;

	@Column(name="REQ_DATE")
	private Timestamp reqDate;

	@Column(name="REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name="SERVICE_ID")
	private String serviceId;

	@Column(name="SERVICE_SEQ_NO")
	private Long serviceSeqNo;

	@Column(name="STATUS")
	private String status;
	
	@Column(name="RES_ALLOC_STATUS")
	private Character RES_ALLOC_STATUS;
	
	@Column(name="JOB_ALLOC_STATUS")
	private Character JOB_ALLOC_STATUS;
	
	@Column(name="RES_DIRECT_INDIRECT_FLAG")
	private Character RES_DIRECT_INDIRECT_FLAG;
	
	@Column(name="AUTO_ALLOC_STATUS")
	private Character AUTO_ALLOC_STATUS;

	
	public Long getSERVICE_WORK_SEQ_NO() {
		return SERVICE_WORK_SEQ_NO;
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





	public Integer getBillingUnitSeqNo() {
		return billingUnitSeqNo;
	}





	public void setBillingUnitSeqNo(Integer billingUnitSeqNo) {
		this.billingUnitSeqNo = billingUnitSeqNo;
	}





	public Long getMembershipSeqNo() {
		return membershipSeqNo;
	}





	public void setMembershipSeqNo(Long membershipSeqNo) {
		this.membershipSeqNo = membershipSeqNo;
	}





	public Timestamp getOnDate() {
		return onDate;
	}





	public void setOnDate(Timestamp onDate) {
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





	public Timestamp getReqDate() {
		return reqDate;
	}





	public void setReqDate(Timestamp reqDate) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AUTO_ALLOC_STATUS == null) ? 0 : AUTO_ALLOC_STATUS.hashCode());
		result = prime * result + ((JOB_ALLOC_STATUS == null) ? 0 : JOB_ALLOC_STATUS.hashCode());
		result = prime * result + ((RES_ALLOC_STATUS == null) ? 0 : RES_ALLOC_STATUS.hashCode());
		result = prime * result + ((RES_DIRECT_INDIRECT_FLAG == null) ? 0 : RES_DIRECT_INDIRECT_FLAG.hashCode());
		result = prime * result + ((SERVICE_WORK_SEQ_NO == null) ? 0 : SERVICE_WORK_SEQ_NO.hashCode());
		result = prime * result + ((billingUnitSeqNo == null) ? 0 : billingUnitSeqNo.hashCode());
		result = prime * result + ((bookingSeqNo == null) ? 0 : bookingSeqNo.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((membershipSeqNo == null) ? 0 : membershipSeqNo.hashCode());
		result = prime * result + ((onDate == null) ? 0 : onDate.hashCode());
		result = prime * result + ((parentServiceWorkSeqNo == null) ? 0 : parentServiceWorkSeqNo.hashCode());
		result = prime * result + ((personSeqNo == null) ? 0 : personSeqNo.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((reqDate == null) ? 0 : reqDate.hashCode());
		result = prime * result + ((requestSeqNo == null) ? 0 : requestSeqNo.hashCode());
		result = prime * result + ((serviceId == null) ? 0 : serviceId.hashCode());
		result = prime * result + ((serviceSeqNo == null) ? 0 : serviceSeqNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		ServiceMaster other = (ServiceMaster) obj;
		if (AUTO_ALLOC_STATUS == null) {
			if (other.AUTO_ALLOC_STATUS != null)
				return false;
		} else if (!AUTO_ALLOC_STATUS.equals(other.AUTO_ALLOC_STATUS))
			return false;
		if (JOB_ALLOC_STATUS == null) {
			if (other.JOB_ALLOC_STATUS != null)
				return false;
		} else if (!JOB_ALLOC_STATUS.equals(other.JOB_ALLOC_STATUS))
			return false;
		if (RES_ALLOC_STATUS == null) {
			if (other.RES_ALLOC_STATUS != null)
				return false;
		} else if (!RES_ALLOC_STATUS.equals(other.RES_ALLOC_STATUS))
			return false;
		if (RES_DIRECT_INDIRECT_FLAG == null) {
			if (other.RES_DIRECT_INDIRECT_FLAG != null)
				return false;
		} else if (!RES_DIRECT_INDIRECT_FLAG.equals(other.RES_DIRECT_INDIRECT_FLAG))
			return false;
		if (SERVICE_WORK_SEQ_NO == null) {
			if (other.SERVICE_WORK_SEQ_NO != null)
				return false;
		} else if (!SERVICE_WORK_SEQ_NO.equals(other.SERVICE_WORK_SEQ_NO))
			return false;
		if (billingUnitSeqNo == null) {
			if (other.billingUnitSeqNo != null)
				return false;
		} else if (!billingUnitSeqNo.equals(other.billingUnitSeqNo))
			return false;
		if (bookingSeqNo == null) {
			if (other.bookingSeqNo != null)
				return false;
		} else if (!bookingSeqNo.equals(other.bookingSeqNo))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (membershipSeqNo == null) {
			if (other.membershipSeqNo != null)
				return false;
		} else if (!membershipSeqNo.equals(other.membershipSeqNo))
			return false;
		if (onDate == null) {
			if (other.onDate != null)
				return false;
		} else if (!onDate.equals(other.onDate))
			return false;
		if (parentServiceWorkSeqNo == null) {
			if (other.parentServiceWorkSeqNo != null)
				return false;
		} else if (!parentServiceWorkSeqNo.equals(other.parentServiceWorkSeqNo))
			return false;
		if (personSeqNo == null) {
			if (other.personSeqNo != null)
				return false;
		} else if (!personSeqNo.equals(other.personSeqNo))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (reqDate == null) {
			if (other.reqDate != null)
				return false;
		} else if (!reqDate.equals(other.reqDate))
			return false;
		if (requestSeqNo == null) {
			if (other.requestSeqNo != null)
				return false;
		} else if (!requestSeqNo.equals(other.requestSeqNo))
			return false;
		if (serviceId == null) {
			if (other.serviceId != null)
				return false;
		} else if (!serviceId.equals(other.serviceId))
			return false;
		if (serviceSeqNo == null) {
			if (other.serviceSeqNo != null)
				return false;
		} else if (!serviceSeqNo.equals(other.serviceSeqNo))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}





	public ServiceMaster(Long sERVICE_WORK_SEQ_NO, Long bookingSeqNo, Long createdBy, Integer billingUnitSeqNo,
			Long membershipSeqNo, Timestamp onDate, Long parentServiceWorkSeqNo, Long personSeqNo, String remarks,
			Timestamp reqDate, Long requestSeqNo, String serviceId, Long serviceSeqNo, String status,
			Character rES_ALLOC_STATUS, Character jOB_ALLOC_STATUS, Character rES_DIRECT_INDIRECT_FLAG,
			Character aUTO_ALLOC_STATUS) {
		super();
		SERVICE_WORK_SEQ_NO = sERVICE_WORK_SEQ_NO;
		this.bookingSeqNo = bookingSeqNo;
		this.createdBy = createdBy;
		this.billingUnitSeqNo = billingUnitSeqNo;
		this.membershipSeqNo = membershipSeqNo;
		this.onDate = onDate;
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





	public ServiceMaster() {
		super();
	}
	
}