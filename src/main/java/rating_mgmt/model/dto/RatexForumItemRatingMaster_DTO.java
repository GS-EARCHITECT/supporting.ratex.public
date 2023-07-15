package rating_mgmt.model.dto;

import java.io.Serializable;

public class RatexForumItemRatingMaster_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5188448552286448466L;
	private Long itemSeqNo;
	private String rateContinuous;
	private String rateDiscreet;

	public Long getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public String getRateContinuous() {
		return rateContinuous;
	}

	public void setRateContinuous(String rateContinuous) {
		this.rateContinuous = rateContinuous;
	}

	public String getRateDiscreet() {
		return rateDiscreet;
	}

	public void setRateDiscreet(String rateDiscreet) {
		this.rateDiscreet = rateDiscreet;
	}

	public RatexForumItemRatingMaster_DTO(Long itemSeqNo, String rateContinuous, String rateDiscreet) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.rateContinuous = rateContinuous;
		this.rateDiscreet = rateDiscreet;
	}

	public RatexForumItemRatingMaster_DTO() {
		super();
	}

}