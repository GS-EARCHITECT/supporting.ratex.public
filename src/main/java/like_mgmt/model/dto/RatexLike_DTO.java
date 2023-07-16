package like_mgmt.model.dto;

import java.io.Serializable;

public class RatexLike_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4148605579736263698L;
	private Long itemSeqNo;
	private Long sourceSeqNo;
	private String onDttm;
	private Character likeFlag;
	private String visible;

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

	public Character getLikeFlag() {
		return likeFlag;
	}

	public void setLikeFlag(Character likeFlag) {
		this.likeFlag = likeFlag;
	}

	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public RatexLike_DTO(Long itemSeqNo, Long sourceSeqNo, String onDttm, Character likeFlag, String visible) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.sourceSeqNo = sourceSeqNo;
		this.onDttm = onDttm;
		this.likeFlag = likeFlag;
		this.visible = visible;
	}

	public RatexLike_DTO() {
		super();
	}

}