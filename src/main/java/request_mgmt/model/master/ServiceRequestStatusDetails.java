package request_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SERVICE_REQUEST_STATUS database table.
 * 
 */
@Entity
@Table(name = "SERVICE_REQUEST_STATUS")
public class ServiceRequestStatusDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ServiceRequestStatusDetailsPK id;

	@Column(name = "REMARK")
	private String remark;

	@Column(name = "STATUS")
	private String status;

	public ServiceRequestStatusDetails() {
	}

	public ServiceRequestStatusDetailsPK getId() {
		return this.id;
	}

	public void setId(ServiceRequestStatusDetailsPK id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ServiceRequestStatusDetails(ServiceRequestStatusDetailsPK id, String remark, String status) {
		super();
		this.id = id;
		this.remark = remark;
		this.status = status;
	}

}