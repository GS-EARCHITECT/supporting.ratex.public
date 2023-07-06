package transactions.transaction_manager.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the SALES_MASTER database table.
 * 
 */
@Entity
@Table(name="TRANSACTION_ITEM_DETAILS")
public class TransactionDetails implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1229642171381812612L;

	@EmbeddedId
	private TransactionDetailsPK id;
	
	@Column(name="QTY")
	private Float qty;
	
	@Column(name="DISC_PERC")
	private Float discPerc;
	
	@Column(name="DISC_VALUE")
	private Float discVal;
	
	@Column(name="TAX_PERC")
	private Float taxPerc;
	
	@Column(name="TAX_VALUE")
	private Float taxVal;
	
	@Column(name="STORE_REQUEST_SEQ_NO")
	private Integer storeReqSeqNo;
		
	@Column(name="BY_STORE_VERIFICATION_FLAG")
	private Character byStoreFlag;
	
	@Column(name="BY_SOURCE_VERIFICATION_FLAG	")
	private Character bySourceFlag;

	public TransactionDetailsPK getId() {
		return id;
	}

	public void setId(TransactionDetailsPK id) {
		this.id = id;
	}

	public Float getQty() {
		return qty;
	}

	public void setQty(Float qty) {
		this.qty = qty;
	}

	public Float getDiscPerc() {
		return discPerc;
	}

	public void setDiscPerc(Float discPerc) {
		this.discPerc = discPerc;
	}

	public Float getDiscVal() {
		return discVal;
	}

	public void setDiscVal(Float discVal) {
		this.discVal = discVal;
	}

	public Float getTaxPerc() {
		return taxPerc;
	}

	public void setTaxPerc(Float taxPerc) {
		this.taxPerc = taxPerc;
	}

	public Float getTaxVal() {
		return taxVal;
	}

	public void setTaxVal(Float taxVal) {
		this.taxVal = taxVal;
	}

	public Integer getStoreReqSeqNo() {
		return storeReqSeqNo;
	}

	public void setStoreReqSeqNo(Integer storeReqSeqNo) {
		this.storeReqSeqNo = storeReqSeqNo;
	}

	public Character getByStoreFlag() {
		return byStoreFlag;
	}

	public void setByStoreFlag(Character byStoreFlag) {
		this.byStoreFlag = byStoreFlag;
	}

	public Character getBySourceFlag() {
		return bySourceFlag;
	}

	public void setBySourceFlag(Character bySourceFlag) {
		this.bySourceFlag = bySourceFlag;
	}

	public TransactionDetails(TransactionDetailsPK id, Float qty, Float discPerc, Float discVal, Float taxPerc,
			Float taxVal, Integer storeReqSeqNo, Character byStoreFlag, Character bySourceFlag) {
		super();
		this.id = id;
		this.qty = qty;
		this.discPerc = discPerc;
		this.discVal = discVal;
		this.taxPerc = taxPerc;
		this.taxVal = taxVal;
		this.storeReqSeqNo = storeReqSeqNo;
		this.byStoreFlag = byStoreFlag;
		this.bySourceFlag = bySourceFlag;
	}

	public TransactionDetails() {
		super();
	}
	
}