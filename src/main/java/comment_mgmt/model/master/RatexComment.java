package comment_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_COMMENTS_DETAILS database table.
 * 
 */
@Entity
@Table(name = "RATEX_COMMENTS")
public class RatexComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexCommentPK id;

	@Column(name = "REMARK")
	private String remark;

	public RatexComment() {
	}

	public RatexCommentPK getId() {
		return this.id;
	}

	public void setId(RatexCommentPK id) {
		this.id = id;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public RatexComment(RatexCommentPK id, String remark) {
		super();
		this.id = id;
		this.remark = remark;
	}

}