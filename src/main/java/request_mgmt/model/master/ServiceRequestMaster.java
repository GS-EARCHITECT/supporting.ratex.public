package request_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The persistent class for the SERVICE_REQUEST database table.
 * 
 */
@Entity
@Table(name = "SERVICE_REQUEST_MASTER")
public class ServiceRequestMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_request_seq_no")
	@SequenceGenerator(name = "customer_request_seq_no", allocationSize = 1)
	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "COMPANY_SEQ_NO")
	private Long companySeqNo;

	@Column(name = "PERSON_SEQ_NO")
	private Long personSeqNo;

	@Column(name = "REQUEST_DATE")
	private Timestamp requestDate;
	
	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "SUPPLIER_SEQ_NO")
	private Long supplierSeqNo;

	public ServiceRequestMaster() {
	}

	public Long getRequestSeqNo() {
		return this.requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Long getCompanySeqNo() {
		return this.companySeqNo;
	}

	public void setCompanySeqNo(Long companySeqNo) {
		this.companySeqNo = companySeqNo;
	}

	public Long getPersonSeqNo() {
		return this.personSeqNo;
	}

	public void setPersonSeqNo(Long personSeqNo) {
		this.personSeqNo = personSeqNo;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getRequestDate() {
		return this.requestDate;
	}

	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSupplierSeqNo() {
		return this.supplierSeqNo;
	}

	public void setSupplierSeqNo(Long supplierSeqNo) {
		this.supplierSeqNo = supplierSeqNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((requestSeqNo == null) ? 0 : requestSeqNo.hashCode());
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
		ServiceRequestMaster other = (ServiceRequestMaster) obj;
		if (requestSeqNo == null) {
			if (other.requestSeqNo != null)
				return false;
		} else if (!requestSeqNo.equals(other.requestSeqNo))
			return false;
		return true;
	}

	public ServiceRequestMaster(Long requestSeqNo, Long companySeqNo, Long personSeqNo, String remark, Timestamp requestDate,
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

}