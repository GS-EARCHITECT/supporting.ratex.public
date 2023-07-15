package rating_mgmt.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_RATING_MASTER database table.
 * 
 */
@Entity
@Table(name = "RATEX_FORUMITEMRATING_MASTER")
public class RatexForumItemRatingMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratex_rating_sequence")
	@SequenceGenerator(name = "ratex_rating_sequence", sequenceName = "ratex_rating_sequence", allocationSize = 1)
	@Column(name = "ITEM_SEQ_NO")
	private Long itemSeqNo;

	@Column(name = "RATE_CONTINUOUS")
	private String rateContinuous;

	@Column(name = "RATE_DISCREET")
	private String rateDiscreet;

	public RatexForumItemRatingMaster() {
	}

	public Long getItemSeqNo() {
		return this.itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public String getRateContinuous() {
		return this.rateContinuous;
	}

	public void setRateContinuous(String rateContinuous) {
		this.rateContinuous = rateContinuous;
	}

	public String getRateDiscreet() {
		return this.rateDiscreet;
	}

	public void setRateDiscreet(String rateDiscreet) {
		this.rateDiscreet = rateDiscreet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemSeqNo == null) ? 0 : itemSeqNo.hashCode());
		result = prime * result + ((rateContinuous == null) ? 0 : rateContinuous.hashCode());
		result = prime * result + ((rateDiscreet == null) ? 0 : rateDiscreet.hashCode());
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
		RatexForumItemRatingMaster other = (RatexForumItemRatingMaster) obj;
		if (itemSeqNo == null) {
			if (other.itemSeqNo != null)
				return false;
		} else if (!itemSeqNo.equals(other.itemSeqNo))
			return false;
		if (rateContinuous == null) {
			if (other.rateContinuous != null)
				return false;
		} else if (!rateContinuous.equals(other.rateContinuous))
			return false;
		if (rateDiscreet == null) {
			if (other.rateDiscreet != null)
				return false;
		} else if (!rateDiscreet.equals(other.rateDiscreet))
			return false;
		return true;
	}

	public RatexForumItemRatingMaster(Long itemSeqNo, String rateContinuous, String rateDiscreet) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.rateContinuous = rateContinuous;
		this.rateDiscreet = rateDiscreet;
	}

}