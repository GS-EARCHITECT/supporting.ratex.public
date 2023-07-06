package transactions.transaction_manager.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The persistent class for the SALES_MASTER database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_MASTER")
public class TransactionMaster implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1173381731139597410L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
	@SequenceGenerator(name = "transaction_sequence", allocationSize = 1)
	private Integer transaction_seq_no;
	
	@Column(name="TRANSACTION_TYPE_SEQ_NO")
	private Integer tTypeSeqNo;

	@Column(name="REMARK")	
	private String remark;
	
	@Column(name="STATUS")
	private String status;

	@Column(name="Transaction_SEQ_NO")
	private Integer txnSeqNo;
	
	@Column(name="REF_Transaction_SEQ_NO")
	private Integer refTxnSeqNo;
	
	@Column(name="Transaction_BY_SEQ_NO")
	private Integer byTxnSeqNo;

	@Column(name="DOCUMENT_SEQ_NO")
	private Integer docSeqNo;
	
	@Column(name="STARTED_ON")
	private Timestamp startOn;
	
	@Column(name="COMPLETED_ON")
	private Timestamp compOn;
	
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

	public Timestamp getStartOn() {
		return startOn;
	}

	public void setStartOn(Timestamp startOn) {
		this.startOn = startOn;
	}

	public Timestamp getCompOn() {
		return compOn;
	}

	public void setCompOn(Timestamp compOn) {
		this.compOn = compOn;
	}

	
	
	public TransactionMaster(Integer transaction_seq_no, Integer tTypeSeqNo, String remark, String status,
			Integer txnSeqNo, Integer refTxnSeqNo, Integer byTxnSeqNo, Integer docSeqNo, Timestamp startOn,
			Timestamp compOn) {
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

	public TransactionMaster() {
		super();
	}
}