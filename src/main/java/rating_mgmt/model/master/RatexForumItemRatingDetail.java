package rating_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_RATING_DETAILS database table.
 * 
 */
@Entity
@Table(name = "RATEX_FORUMITEMRATING_DETAILS")
public class RatexForumItemRatingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexForumItemRatingDetailPK id;

	@Column(name = "RATING")
	private Float rating;

	@Column(name = "VISIBLE")
	private Character visible;

	public RatexForumItemRatingDetail() {
	}

	public RatexForumItemRatingDetailPK getId() {
		return this.id;
	}

	public void setId(RatexForumItemRatingDetailPK id) {
		this.id = id;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Character getVisible() {
		return visible;
	}

	public void setVisible(Character visible) {
		this.visible = visible;
	}

	public RatexForumItemRatingDetail(RatexForumItemRatingDetailPK id, Float rating, Character visible) {
		super();
		this.id = id;
		this.rating = rating;
		this.visible = visible;
	}

}