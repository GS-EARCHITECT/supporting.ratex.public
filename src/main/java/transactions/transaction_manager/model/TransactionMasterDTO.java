package transactions.transaction_manager.model;

import java.io.Serializable;


public class TransactionMasterDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5061758416521681558L;
	private Integer transaction_seq_no;
	private Integer tTypeSeqNo;
	private String remark;
	private String status;
	private Integer txnSeqNo;
	private Integer refTxnSeqNo;
	private Integer byTxnSeqNo;
	private Integer docSeqNo;
	private String startOn;
	private String compOn;
	
	public Integer getTransaction_seq_no() {
		return transaction_seq_no;
	}
	public void setTransaction_seq_no(Integer transaction_seq_no) {
		this.transaction_seq_no = transaction_seq_no;
	}
	public Integer gettTypeSeqNo() {
		return tTypeSeqNo;
	}
	public void settTypeSeqNo(Integer tTypeSeqNo) {
		this.tTypeSeqNo = tTypeSeqNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getTxnSeqNo() {
		return txnSeqNo;
	}
	public void setTxnSeqNo(Integer txnSeqNo) {
		this.txnSeqNo = txnSeqNo;
	}
	public Integer getRefTxnSeqNo() {
		return refTxnSeqNo;
	}
	public void setRefTxnSeqNo(Integer refTxnSeqNo) {
		this.refTxnSeqNo = refTxnSeqNo;
	}
	public Integer getByTxnSeqNo() {
		return byTxnSeqNo;
	}
	public void setByTxnSeqNo(Integer byTxnSeqNo) {
		this.byTxnSeqNo = byTxnSeqNo;
	}
	public Integer getDocSeqNo() {
		return docSeqNo;
	}
	public void setDocSeqNo(Integer docSeqNo) {
		this.docSeqNo = docSeqNo;
	}
	public String getStartOn() {
		return startOn;
	}
	public void setStartOn(String startOn) {
		this.startOn = startOn;
	}
	public String getCompOn() {
		return compOn;
	}
	public void setCompOn(String compOn) {
		this.compOn = compOn;
	}
	
	
	public TransactionMasterDTO(Integer transaction_seq_no, Integer tTypeSeqNo, String remark, String status,
			Integer txnSeqNo, Integer refTxnSeqNo, Integer byTxnSeqNo, Integer docSeqNo, String startOn,
			String compOn) {
		super();
		this.transaction_seq_no = transaction_seq_no;
		this.tTypeSeqNo = tTypeSeqNo;
		this.remark = remark;
		this.status = status;
		this.txnSeqNo = txnSeqNo;
		this.refTxnSeqNo = refTxnSeqNo;
		this.byTxnSeqNo = byTxnSeqNo;
		this.docSeqNo = docSeqNo;
		this.startOn = startOn;
		this.compOn = compOn;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public TransactionMasterDTO() {
		super();
	}

}