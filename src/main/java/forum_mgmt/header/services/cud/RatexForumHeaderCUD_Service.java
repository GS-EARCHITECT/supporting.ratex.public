package forum_mgmt.header.services.cud;

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
import forum_mgmt.header.model.master.RatexForumHeader;
import forum_mgmt.header.model.master.RatexForumHeaderPK;
import forum_mgmt.header.model.repo.cud.RatexForumHeaderCUD_Repo;
import rating_mgmt.model.dto.*;
import rating_mgmt.model.repo.*;
import rating_mgmt.model.master.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("ratexForumHeaderCUDServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class RatexForumHeaderCUD_Service implements IRatexForumHeaderCUD_Service {
	// private static final Logger logger =
	// LoggerFactory.getLogger(RatexForum_Service.class);

	@Autowired
	private RatexForumHeaderCUD_Repo forumHeaderCUDRepo;

	@Autowired
	private Executor asyncExecutor;

	@Override
	public CompletableFuture<RatexForumHeader_DTO> newForum(RatexForumHeader_DTO rcDTO) {
		CompletableFuture<RatexForumHeader_DTO> future = CompletableFuture.supplyAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
			Timestamp onTs = Timestamp.valueOf(onDTTm);
			RatexForumHeaderPK ratexForumHeaderPK = new RatexForumHeaderPK();
			ratexForumHeaderPK.setContextSeqNo(rcDTO.getContextSeqNo());
			ratexForumHeaderPK.setOnDttm(onTs);
			ratexForumHeaderPK.setSourceSeqNo(rcDTO.getSourceSeqNo());
			ratexForumHeaderPK.setTargetSeqNo(rcDTO.getTargetSeqNo());
			RatexForumHeader_DTO ratexForumDTO = new RatexForumHeader_DTO();

			if (!forumHeaderCUDRepo.existsById(ratexForumHeaderPK)) {
				RatexForumHeader ratexForumMaster = forumHeaderCUDRepo.save(this.setRatexForumHeader(rcDTO));
				ratexForumDTO = this.getRatexForumHeader_DTO(ratexForumMaster);
			}
			return ratexForumDTO;
		}, asyncExecutor);

		return future;

	}

	@Override
	public CompletableFuture<Void> updForum(RatexForumHeader_DTO rcDTO) {

		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime onDTTm = LocalDateTime.parse(rcDTO.getOnDttm(), formatter);
		Timestamp onTs = Timestamp.valueOf(onDTTm);
		RatexForumHeaderPK ratexForumHeaderPK = new RatexForumHeaderPK();
		ratexForumHeaderPK.setContextSeqNo(rcDTO.getContextSeqNo());
		ratexForumHeaderPK.setOnDttm(onTs);
		ratexForumHeaderPK.setSourceSeqNo(rcDTO.getSourceSeqNo());
		ratexForumHeaderPK.setTargetSeqNo(rcDTO.getTargetSeqNo());
		RatexForumHeader_DTO ratexForumDTO = new RatexForumHeader_DTO();
		
		if (forumHeaderCUDRepo.existsById(ratexForumHeaderPK)) 
		{
		RatexForumHeader ratexForumMaster = this.setRatexForumHeader(rcDTO);
		forumHeaderCUDRepo.save(ratexForumMaster);
		}
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectForumsForContexts(CopyOnWriteArrayList<Long> cList) {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			forumHeaderCUDRepo.delSelectForumsForContexts(cList);
		}, asyncExecutor);
		return future;
	}
	
	public CompletableFuture<Void> delSelectForumsForTargets(CopyOnWriteArrayList<Long> tList) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			forumHeaderCUDRepo.delSelectForumsForTargets(tList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectForumsForSources(CopyOnWriteArrayList<Long> sList) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			forumHeaderCUDRepo.delSelectForumsForSources(sList);
		}, asyncExecutor);
		return future;
	}

	public CompletableFuture<Void> delSelectForumsBetweenTimes(CopyOnWriteArrayList<Long> cList, String frDtTm, String toDtTm) 
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			LocalDateTime lfrDTTm = LocalDateTime.parse(frDtTm, formatter);
			LocalDateTime ltoDTTm = LocalDateTime.parse(toDtTm, formatter);
			Timestamp frTs = Timestamp.valueOf(lfrDTTm);
			Timestamp toTs = Timestamp.valueOf(ltoDTTm);
			forumHeaderCUDRepo.delSelectForumsBetweenTimes(cList, frTs, toTs);
		}, asyncExecutor);
		return future;
	}

	
	@Override
	public CompletableFuture<Void> delAllForums() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			forumHeaderCUDRepo.deleteAll();
		}, asyncExecutor);
		return future;
	}

	private CopyOnWriteArrayList<RatexForumHeader_DTO> getRatexForumDtos(CopyOnWriteArrayList<RatexForumHeader> rcReqMasters) {
		CopyOnWriteArrayList<RatexForumHeader_DTO> ratexForumDTOs = new CopyOnWriteArrayList<RatexForumHeader_DTO>();
		for (int i = 0; i < rcReqMasters.size(); i++) {
			ratexForumDTOs.add(this.getRatexForumHeader_DTO(rcReqMasters.get(i)));
		}
		return ratexForumDTOs;
	}

	private synchronized RatexForumHeader_DTO getRatexForumHeader_DTO(RatexForumHeader rcReqMaster) {
		RatexForumHeader_DTO ratexForumDTO = new RatexForumHeader_DTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		ratexForumDTO.setOnDttm(formatter.format(rcReqMaster.getId().getOnDttm().toLocalDateTime()));
		ratexForumDTO.setContextSeqNo(rcReqMaster.getId().getContextSeqNo());
		ratexForumDTO.setSourceSeqNo(rcReqMaster.getId().getSourceSeqNo());
		ratexForumDTO.setTargetSeqNo(rcReqMaster.getId().getTargetSeqNo());
		ratexForumDTO.setRootItemSeqNo(rcReqMaster.getRootItemSeqNo());
		return ratexForumDTO;
	}

	private synchronized RatexForumHeader setRatexForumHeader(RatexForumHeader_DTO ratexForumDTO) {
		RatexForumHeader ratexForumMaster = new RatexForumHeader();
		RatexForumHeaderPK ratexForumHeaderPK = new RatexForumHeaderPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime onDtTm = LocalDateTime.parse(ratexForumDTO.getOnDttm(), formatter);
	    Timestamp timestamp = Timestamp.valueOf(onDtTm);
	    ratexForumMaster.setRootItemSeqNo(ratexForumDTO.getRootItemSeqNo());
	    ratexForumHeaderPK.setContextSeqNo(ratexForumDTO.getContextSeqNo());
	    ratexForumHeaderPK.setOnDttm(timestamp);
	    ratexForumHeaderPK.setSourceSeqNo(ratexForumDTO.getSourceSeqNo());
	    ratexForumHeaderPK.setTargetSeqNo(ratexForumDTO.getTargetSeqNo());
	    ratexForumMaster.setId(ratexForumHeaderPK);
		return ratexForumMaster;
	}

}