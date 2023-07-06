package transactions.transaction_manager.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

/**
 * The primary key class for the SALES_MASTER database table.
 * 
 */
@Embeddable
public class TransactionDetailsPK implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5338565919221494059L;

	@Column(name="TRANSACTION_SEQ_NO")
	private Integer tseqNo;

	@Column(name="ITEM_SEQ_NO")
	private Integer itemSeqNo;
	
	@Column(name="TXN_ON")
	private Timestamp txnOn;

	public Integer getTseqNo() {
		return tseqNo;
	}

	public void setTseqNo(Integer tseqNo) {
		this.tseqNo = tseqNo;
	}

	public Integer getItemSeqNo() {
		return itemSeqNo;
	}

	public void setItemSeqNo(Integer itemSeqNo) {
		this.itemSeqNo = itemSeqNo;
	}

	public Timestamp getTxnOn() {
		return txnOn;
	}

	public void setTxnOn(Timestamp txnOn) {
		this.txnOn = txnOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemSeqNo == null) ? 0 : itemSeqNo.hashCode());
		result = prime * result + ((tseqNo == null) ? 0 : tseqNo.hashCode());
		result = prime * result + ((txnOn == null) ? 0 : txnOn.hashCode());
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
		TransactionDetailsPK other = (TransactionDetailsPK) obj;
		if (itemSeqNo == null) {
			if (other.itemSeqNo != null)
				return false;
		} else if (!itemSeqNo.equals(other.itemSeqNo))
			return false;
		if (tseqNo == null) {
			if (other.tseqNo != null)
				return false;
		} else if (!tseqNo.equals(other.tseqNo))
			return false;
		if (txnOn == null) {
			if (other.txnOn != null)
				return false;
		} else if (!txnOn.equals(other.txnOn))
			return false;
		return true;
	}

	public TransactionDetailsPK(Integer tseqNo, Integer itemSeqNo, Timestamp txnOn) {
		super();
		this.tseqNo = tseqNo;
		this.itemSeqNo = itemSeqNo;
		this.txnOn = txnOn;
	}

	public TransactionDetailsPK() {
		super();
	}

}