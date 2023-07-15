package rating_mgmt.model.dto;

import java.io.Serializable;

public class RatexForumItemRatingDetail_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 670784974765848451L;
	private Long itemSeqNo;
	private Long sourceSeqNo;
	private String onDttm;
	private Float rating;
	private Character visible;

	public Long getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Long getSourceSeqNo() {
		return sourceSeqNo;
	}

	public void setSourceSeqNo(Long sourceSeqNo) {
		this.sourceSeqNo = sourceSeqNo;
	}

	public String getOnDttm() {
		return onDttm;
	}

	public void setOnDttm(String onDttm) {
		this.onDttm = onDttm;
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

	public RatexForumItemRatingDetail_DTO() {
		super();
	}

	public RatexForumItemRatingDetail_DTO(Long itemSeqNo, Long sourceSeqNo, String onDttm, Float rating,
			Character visible) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
		this.rating = rating;
		this.visible = visible;
	}

}