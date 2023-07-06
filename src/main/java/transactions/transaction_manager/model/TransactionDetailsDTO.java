package transactions.transaction_manager.model;

import java.io.Serializable;

public class TransactionDetailsDTO implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8804523618042278450L;
	private Integer tseqNo;
	private Integer itemSeqNo;
	private String txnOn;
	private Float qty;
	private Float discPerc;
	private Float discVal;
	private Float taxPerc;
	private Float taxVal;
	private Integer storeReqSeqNo;
	private Character byStoreFlag;
	private Character bySourceFlag;
	
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
	public String getTxnOn() {
		return txnOn;
	}
	public void setTxnOn(String txnOn) {
		this.txnOn = txnOn;
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
	public TransactionDetailsDTO(Integer tseqNo, Integer itemSeqNo, String txnOn, Float qty, Float discPerc,
			Float discVal, Float taxPerc, Float taxVal, Integer storeReqSeqNo, Character byStoreFlag,
			Character bySourceFlag) {
		super();
		this.tseqNo = tseqNo;
		this.itemSeqNo = itemSeqNo;
		this.txnOn = txnOn;
		this.qty = qty;
		this.discPerc = discPerc;
		this.discVal = discVal;
		this.taxPerc = taxPerc;
		this.taxVal = taxVal;
		this.storeReqSeqNo = storeReqSeqNo;
		this.byStoreFlag = byStoreFlag;
		this.bySourceFlag = bySourceFlag;
	}
	public TransactionDetailsDTO() {
		super();
	}
}