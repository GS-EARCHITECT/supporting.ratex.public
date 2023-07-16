package like_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_LIKES database table.
 * 
 */
@Entity
@Table(name = "RATEX_LIKES")
public class RatexLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexLikePK id;

	@Column(name = "LIKE_FLAG")
	private Character likeFlag;

	@Column(name = "VISIBLE")
	private String visible;

	public RatexLike() {
	}

	public RatexLikePK getId() {
		return this.id;
	}

	public void setId(RatexLikePK id) {
		this.id = id;
	}

	public Character getLikeFlag() {
		return this.likeFlag;
	}

	public void setLikeFlag(Character likeFlag) {
		this.likeFlag = likeFlag;
	}

	public String getVisible() {
		return this.visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public RatexLike(RatexLikePK id, Character likeFlag, String visible) {
		super();
		this.id = id;
		this.likeFlag = likeFlag;
		this.visible = visible;
	}

}