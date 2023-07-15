package forum_mgmt.header.model.master;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the RATEX_FORUM_HEADER database table.
 * 
 */
@Entity
@Table(name = "RATEX_FORUM_HEADER")
public class RatexForumHeader implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RatexForumHeaderPK id;

	@Column(name = "ROOT_ITEM_SEQ_NO")
	private Long rootItemSeqNo;

	public RatexForumHeader() {
	}

	public RatexForumHeaderPK getId() {
		return this.id;
	}

	public void setId(RatexForumHeaderPK id) {
		this.id = id;
	}

	public Long getRootItemSeqNo() {
		return this.rootItemSeqNo;
	}

	public void setRootItemSeqNo(Long rootItemSeqNo) {
		this.rootItemSeqNo = rootItemSeqNo;
	}

	public RatexForumHeader(RatexForumHeaderPK id, Long rootItemSeqNo) {
		super();
		this.id = id;
		this.rootItemSeqNo = rootItemSeqNo;
	}

}