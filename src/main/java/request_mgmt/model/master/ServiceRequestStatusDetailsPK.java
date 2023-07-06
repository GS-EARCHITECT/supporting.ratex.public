package request_mgmt.model.master;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * The primary key class for the SERVICE_REQUEST_STATUS database table.
 * 
 */
@Embeddable
public class ServiceRequestStatusDetailsPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "REQUEST_SEQ_NO")
	private Long requestSeqNo;

	@Column(name = "ON_DATE")
	private Timestamp onDate;

	public ServiceRequestStatusDetailsPK() {
	}

	public Long getRequestSeqNo() {
		return this.requestSeqNo;
	}

	public void setRequestSeqNo(Long requestSeqNo) {
		this.requestSeqNo = requestSeqNo;
	}

	public Timestamp getOnDate() {
		return this.onDate;
	}

	public void setOnDate(Timestamp onDate) {
		this.onDate = onDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((onDate == null) ? 0 : onDate.hashCode());
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
		ServiceRequestStatusDetailsPK other = (ServiceRequestStatusDetailsPK) obj;
		if (onDate == null) {
			if (other.onDate != null)
				return false;
		} else if (!onDate.equals(other.onDate))
			return false;
		if (requestSeqNo == null) {
			if (other.requestSeqNo != null)
				return false;
		} else if (!requestSeqNo.equals(other.requestSeqNo))
			return false;
		return true;
	}

	public ServiceRequestStatusDetailsPK(Long requestSeqNo, Timestamp onDate) {
		super();
		this.requestSeqNo = requestSeqNo;
		this.onDate = onDate;
	}

}