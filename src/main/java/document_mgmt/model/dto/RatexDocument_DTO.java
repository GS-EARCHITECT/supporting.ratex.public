package document_mgmt.model.dto;

import java.io.Serializable;

public class RatexDocument_DTO implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3894474292153341789L;
	private Long itemSeqNo;
	private Long doclistSeqNo;

	public Long getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Long itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Long getDoclistSeqNo() {
		return doclistSeqNo;
	}

	public void setDoclistSeqNo(Long doclistSeqNo) {
		this.doclistSeqNo = doclistSeqNo;
	}

	public RatexDocument_DTO(Long itemSeqNo, Long doclistSeqNo) {
		super();
		this.itemSeqNo = itemSeqNo;
		this.doclistSeqNo = doclistSeqNo;
	}

	public RatexDocument_DTO() {
		super();
	}

}