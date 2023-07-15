package forum_mgmt.header.services.read;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import forum_mgmt.header.model.dto.RatexForumHeader_DTO;
import forum_mgmt.header.model.repo.read.RatexForumHeaderRead_Repo;
import forum_mgmt.header.model.master.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumItemForumHeaderServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RatexForumHeaderRead_Service implements IRatexForumHeaderRead_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(RatexForumHeader_Service.class);

	@Autowired
	private RatexForumHeaderRead_Repo forumHeaderReadRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getAllForums() {
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<RatexForumHeader> rcReqList = null;
			rcReqList = (CopyOnWriteArrayList<RatexForumHeader>) forumHeaderReadRepo.findAll();
			CopyOnWriteArrayList<RatexForumHeader_DTO> lMasters = new CopyOnWriteArrayList<RatexForumHeader_DTO>();
			lMasters = rcReqList != null ? this.getRatexForumHeaderDtos(rcReqList) : null;
			return lMasters;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForContexts(
			CopyOnWriteArrayList<Long> cList) {
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<RatexForumHeader> lMasters = forumHeaderReadRepo.getSelectForumsForContexts(cList);
			CopyOnWriteArrayList<RatexForumHeader_DTO> rcMasterDTOs = lMasters != null
					? this.getRatexForumHeaderDtos(lMasters)
					: null;
			return rcMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForTargets(
			CopyOnWriteArrayList<Long> tList) {
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<RatexForumHeader> lMasters = forumHeaderReadRepo.getSelectForumsForTargets(tList);
			CopyOnWriteArrayList<RatexForumHeader_DTO> rcMasterDTOs = lMasters != null
					? this.getRatexForumHeaderDtos(lMasters)
					: null;
			return rcMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsForSources(
			CopyOnWriteArrayList<Long> sList) {
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> future = CompletableFuture.supplyAsync(() -> {
			CopyOnWriteArrayList<RatexForumHeader> lMasters = forumHeaderReadRepo.getSelectForumsForSources(sList);
			CopyOnWriteArrayList<RatexForumHeader_DTO> rcMasterDTOs = lMasters != null
					? this.getRatexForumHeaderDtos(lMasters)
					: null;
			return rcMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	@Override
	public CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> getSelectForumsBetweenTimes(
			CopyOnWriteArrayList<Long> cList, String frDtTm, String toDtTm) {
		CompletableFuture<CopyOnWriteArrayList<RatexForumHeader_DTO>> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(frDTTm);
			Timestamp toTs = Timestamp.valueOf(toDTTm);
			CopyOnWriteArrayList<RatexForumHeader> lMasters = forumHeaderReadRepo.getSelectForumsBetweenTimes(cList,
					frTs, toTs);
			CopyOnWriteArrayList<RatexForumHeader_DTO> rcMasterDTOs = lMasters != null
					? this.getRatexForumHeaderDtos(lMasters)
					: null;
			return rcMasterDTOs;
		}, asyncExecutor);
		return future;
	}

	private synchronized CopyOnWriteArrayList<RatexForumHeader_DTO> getRatexForumHeaderDtos(
			CopyOnWriteArrayList<RatexForumHeader> rcReqMasters) {
		CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumHeaderDTOs = new CopyOnWriteArrayList<RatexForumHeader_DTO>();
		for (int i = 0; i < rcReqMasters.size(); i++) {
			ratexForumHeaderDTOs.add(this.getRatexForumHeader_DTO(rcReqMasters.get(i)));
		}
		return ratexForumHeaderDTOs;
	}

	private synchronized RatexForumHeader_DTO getRatexForumHeader_DTO(RatexForumHeader rcReqMaster) {
		RatexForumHeader_DTO ratexForumHeaderDTO = new RatexForumHeader_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexForumHeaderDTO.setOnDttm(formatter.format(rcReqMaster.getId().getOnDttm().toLocalDateTime()));
		ratexForumHeaderDTO.setContextSeqNo(rcReqMaster.getId().getContextSeqNo());
		ratexForumHeaderDTO.setSourceSeqNo(rcReqMaster.getId().getSourceSeqNo());
		ratexForumHeaderDTO.setTargetSeqNo(rcReqMaster.getId().getTargetSeqNo());
		ratexForumHeaderDTO.setRootItemSeqNo(rcReqMaster.getRootItemSeqNo());
		return ratexForumHeaderDTO;
	}

}