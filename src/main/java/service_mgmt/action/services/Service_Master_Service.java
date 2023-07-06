package service_mgmt.action.services;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*
import job_mgmt.model.master.JobMaster;
import job_mgmt.model.repo.JobRepo;
import resource_mgmt.model.dto.LogiJobTypeResAssetMasterDTO;
import resource_mgmt.model.dto.LogiJobTypeResourceCategoryMasterDTO;
import resource_mgmt.model.dto.ResourceAllocationFailureDTO;
import resource_mgmt.model.master.LogiJobTypeResAssetMaster;
import resource_mgmt.model.master.LogiJobTypeResAssetMasterPK;
import resource_mgmt.model.master.LogiJobTypeResourceCategoryMaster;
import resource_mgmt.model.repo.LogiJobTypeResAssetMasterRepo;
import resource_mgmt.model.repo.LogiJobTypeResCateMasterRepo;
import resource_mgmt.services.ResourceAllocMgmt_Service;
import service_mgmt.common.model.ServiceMaster;
import service_mgmt.common.model.ServiceMasterDTO;
import service_mgmt.common.model.ServiceMasterRepo;
import service_mgmt.common.service.Service_Alloc_Component;
*/

@Service("serviceMasterServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class Service_Master_Service implements I_Service_Master_Service {

/*	
	private static final Logger logger = LoggerFactory.getLogger(Service_Master_Service.class);

	@Autowired
	private ServiceMasterRepo serviceMasterRepo;

	@Autowired
	private LogiJobTypeResAssetMasterRepo logiJobTypeResourceAssetMasterRepo;
	
	@Autowired
	private LogiJobTypeResCateMasterRepo logiJobTypeResourceCateMasterRepo;
	
	@Autowired
	private JobRepo jobRepo;

	@Autowired
	private Service_Alloc_Component serviceAllocServ;

	@Autowired
	private ResourceAllocMgmt_Service resourceAllocMgmtServ;

	// Service Master 
	@Override
	public ServiceMasterDTO newService() {

		// ServiceMasterDTO serviceDTO
		// ServiceMaster ServiceMaster =
		// serviceMasterRepo.save(this.setServiceMaster(serviceDTO));

		logger.info("i am in service ");
		ArrayList<ServiceMaster> sL = (ArrayList<service_mgmt.common.model.ServiceMaster>) serviceMasterRepo.findAll();
		if (!sL.isEmpty()) {
			logger.info("size :" + sL.size());
		} else {
			logger.info("size 0");
		}
		logger.info("calling comp ");

		// serviceDTO = getServiceMasterDTO(ServiceMaster);
		return null;
		// serviceDTO;
	}

	@Override
	public ArrayList<Long> getAllJobsForService(Long servWorkSeqNo, Integer jobTemplateSeqNo, Integer mgr_seq_no,
			String startDateTime, Integer opFlag) {
		ArrayList<Long> jobSeqNos = serviceAllocServ.setJobsForTemplate((long) 6, 2, 55, "12-09-2021 11:15:25", 1);
		return jobSeqNos;
	}
	
	@Override
	public ArrayList<LogiJobTypeResAssetMaster> getAllAssetsForJobType(Integer jobTypeSeqNo, Integer tTypeSeqNo, Integer modeSeqNo, Long frLocSeqNo, Long toLocSeqNo)
	{
		Optional<ArrayList<LogiJobTypeResAssetMaster>> logiJobTypeResAssetMasters = logiJobTypeResourceAssetMasterRepo.getAssetsForJobType(jobTypeSeqNo, tTypeSeqNo, modeSeqNo, frLocSeqNo, toLocSeqNo);
		ArrayList<LogiJobTypeResAssetMaster> assetlist = null; 
		
		if(logiJobTypeResAssetMasters.isPresent())
		{
		assetlist=logiJobTypeResAssetMasters.get();	
		}
		return assetlist;
	}
	

	@Override	
	public ResourceAllocationFailureDTO allocAssetForJob(Long jobSeqNo, Long assetSeqNo, Integer jobTypeSeqNo, Integer modeSeqNo, Integer targetTypeSeqNo, Long frLocSeqNo, Long toLocSeqNo) 
	{	
	ResourceAllocationFailureDTO resourceAllocationFailureDTO = null;
	Optional<JobMaster> jobMaster = jobRepo.findById(jobSeqNo);
	Long allocSeqNo =(long) 0;
	JobMaster jobMaster2= jobMaster.get();
	Timestamp fr = jobMaster2.getAct_start_date() != null ? jobMaster2.getAct_start_date() : jobMaster2.getPlan_start_date();
	Timestamp to = jobMaster2.getAct_end_date() != null ? jobMaster2.getAct_end_date() : jobMaster2.getPlan_end_date();	
	allocSeqNo=resourceAllocMgmtServ.resource_Alloc_Asset(jobSeqNo, assetSeqNo, jobTypeSeqNo, modeSeqNo, targetTypeSeqNo, frLocSeqNo, toLocSeqNo, to, fr);
	resourceAllocationFailureDTO=resourceAllocMgmtServ.res_assets_Strictprocess(allocSeqNo);
	return resourceAllocationFailureDTO;
	}

	@Override
	public ArrayList<LogiJobTypeResourceCategoryMaster> getAllResourcesForJobType(Integer jobTypeSeqNo, Integer tTypeSeqNo, Integer modeSeqNo, Long frLocSeqNo, Long toLocSeqNo)
	{
		Optional<ArrayList<LogiJobTypeResourceCategoryMaster>> logiJobTypeResCateMasters = logiJobTypeResourceCateMasterRepo.getResourcesForJobType(jobTypeSeqNo, tTypeSeqNo, modeSeqNo, frLocSeqNo, toLocSeqNo);
		ArrayList<LogiJobTypeResourceCategoryMaster> assetlist = null; 
		
		if(logiJobTypeResCateMasters.isPresent())
		{
		assetlist=logiJobTypeResCateMasters.get();	
		}
		return assetlist;
	}
	

	@Override	
	public ResourceAllocationFailureDTO allocResourceForJob(Long jobSeqNo, Long resSeqNo, Integer jobTypeSeqNo, Integer modeSeqNo, Integer targetTypeSeqNo, Long frLocSeqNo, Long toLocSeqNo) 
	{
	ResourceAllocationFailureDTO resourceAllocationFailureDTO = null;	
	Optional<JobMaster> jobMaster = jobRepo.findById(jobSeqNo);
	Long allocSeqNo =(long) 0;
	JobMaster jobMaster2= jobMaster.get();
	Timestamp fr = jobMaster2.getAct_start_date() != null ? jobMaster2.getAct_start_date() : jobMaster2.getPlan_start_date();
	Timestamp to = jobMaster2.getAct_end_date() != null ? jobMaster2.getAct_end_date() : jobMaster2.getPlan_end_date();	
	allocSeqNo=resourceAllocMgmtServ.resource_Alloc_Cate(jobSeqNo, resSeqNo, jobTypeSeqNo, modeSeqNo, targetTypeSeqNo, frLocSeqNo, toLocSeqNo, to, fr);
	resourceAllocationFailureDTO=resourceAllocMgmtServ.res_cate_Strictprocess(allocSeqNo);
	return resourceAllocationFailureDTO;
	}
	
	@Override
	public ArrayList<ServiceMasterDTO> getAllServices() {
		ArrayList<ServiceMaster> ServiceList = (ArrayList<ServiceMaster>) serviceMasterRepo.findAll();
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();
		serviceDTOs = ServiceList != null ? this.getserviceDTOs(ServiceList) : null;
		return serviceDTOs;
	}

	@Override
	public ArrayList<ServiceMasterDTO> getSelectServices(ArrayList<Long> ServiceSeqNos) {
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();
		Optional<ServiceMaster> ServiceMaster = null;
		ServiceMasterDTO ServiceMasterDTO = null;

		for (int i = 0; i < ServiceSeqNos.size(); i++) {
			ServiceMaster = serviceMasterRepo.findById(ServiceSeqNos.get(i));
			if (ServiceMaster.isPresent() && ServiceMaster != null) {
				ServiceMasterDTO = this.getServiceMasterDTO(ServiceMaster.get());
				serviceDTOs.add(ServiceMasterDTO);
			}
		}
		return serviceDTOs;
	}

	@Override
	public ServiceMasterDTO getServiceById(Long ServiceSeqNo) {
		Optional<ServiceMaster> ServiceMaster = serviceMasterRepo.findById(ServiceSeqNo);
		ServiceMasterDTO ServiceMasterDTO = null;
		if (ServiceMaster.isPresent()) {
			ServiceMasterDTO = ServiceMaster != null ? this.getServiceMasterDTO(ServiceMaster.get()) : null;
		}
		return ServiceMasterDTO;
	}

	@Override
	public void updService(ServiceMasterDTO ServiceMasterDTO) {
		if (serviceMasterRepo.existsById(ServiceMasterDTO.getSERVICE_WORK_SEQ_NO())) {
			ServiceMaster ServiceMaster = this.setServiceMaster(ServiceMasterDTO);
			ServiceMaster.setSERVICE_WORK_SEQ_NO(ServiceMasterDTO.getSERVICE_WORK_SEQ_NO());
			serviceMasterRepo.save(ServiceMaster);
		}
	}

	@Override
	public void delService(Long ServiceSeqNo) {
		if (serviceMasterRepo.existsById(ServiceSeqNo)) {
			serviceMasterRepo.deleteById(ServiceSeqNo);
		}
	}

	@Override
	public void delAllServices() {
		serviceMasterRepo.deleteAll();
	}

	@Override
	public void delSelectServices(ArrayList<Long> ServiceSeqNos) {

		for (int i = 0; i < ServiceSeqNos.size(); i++) {
			if (serviceMasterRepo.existsById(ServiceSeqNos.get(i))) {
				serviceMasterRepo.deleteById(ServiceSeqNos.get(i));
			}

		}

	}

	private ArrayList<ServiceMasterDTO> getserviceDTOs(ArrayList<ServiceMaster> serviceMasters) {
		ServiceMasterDTO serviceDTO = null;
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();

		for (int i = 0; i < serviceMasters.size(); i++) {
			serviceDTO = new ServiceMasterDTO();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			serviceDTO.setOnDate(formatter.format(serviceMasters.get(i).getOnDate().toLocalDateTime()));
			serviceDTO.setReqDate(formatter.format(serviceMasters.get(i).getReqDate().toLocalDateTime()));
			serviceDTO.setSERVICE_WORK_SEQ_NO(serviceMasters.get(i).getSERVICE_WORK_SEQ_NO());
			serviceDTO.setBookingSeqNo(serviceMasters.get(i).getBookingSeqNo());
			serviceDTO.setCreatedBy(serviceMasters.get(i).getCreatedBy());
			serviceDTO.setMembershipSeqNo(serviceMasters.get(i).getMembershipSeqNo());
			serviceDTO.setParentServiceWorkSeqNo(serviceMasters.get(i).getParentServiceWorkSeqNo());
			serviceDTO.setPersonSeqNo(serviceMasters.get(i).getPersonSeqNo());
			serviceDTO.setRemarks(serviceMasters.get(i).getRemarks());
			serviceDTO.setRequestSeqNo(serviceMasters.get(i).getRequestSeqNo());
			serviceDTO.setServiceId(serviceMasters.get(i).getServiceId());
			serviceDTO.setServiceSeqNo(serviceMasters.get(i).getServiceSeqNo());
			serviceDTO.setStatus(serviceMasters.get(i).getStatus());
			serviceDTOs.add(serviceDTO);
		}
		return serviceDTOs;
	}

	private ServiceMasterDTO getServiceMasterDTO(ServiceMaster serviceMaster) {
		ServiceMasterDTO serviceDTO = new ServiceMasterDTO();
		serviceDTO = new ServiceMasterDTO();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceDTO.setOnDate(formatter.format(serviceMaster.getOnDate().toLocalDateTime()));
		serviceDTO.setReqDate(formatter.format(serviceMaster.getReqDate().toLocalDateTime()));
		serviceDTO.setSERVICE_WORK_SEQ_NO(serviceMaster.getSERVICE_WORK_SEQ_NO());
		serviceDTO.setBookingSeqNo(serviceMaster.getBookingSeqNo());
		serviceDTO.setCreatedBy(serviceMaster.getCreatedBy());
		serviceDTO.setMembershipSeqNo(serviceMaster.getMembershipSeqNo());
		serviceDTO.setParentServiceWorkSeqNo(serviceMaster.getParentServiceWorkSeqNo());
		serviceDTO.setPersonSeqNo(serviceMaster.getPersonSeqNo());
		serviceDTO.setRemarks(serviceMaster.getRemarks());
		serviceDTO.setRequestSeqNo(serviceMaster.getRequestSeqNo());
		serviceDTO.setServiceId(serviceMaster.getServiceId());
		serviceDTO.setServiceSeqNo(serviceMaster.getServiceSeqNo());
		serviceDTO.setStatus(serviceMaster.getStatus());
		return serviceDTO;
	}

	private ServiceMaster setServiceMaster(ServiceMasterDTO serviceDTO) {
		ServiceMaster serviceMaster = new ServiceMaster();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime onDateTime = LocalDateTime.parse(serviceDTO.getOnDate(), formatter);
		LocalDateTime reqdateTime = LocalDateTime.parse(serviceDTO.getReqDate(), formatter);
		serviceMaster.setOnDate(Timestamp.valueOf(onDateTime));
		serviceMaster.setReqDate(Timestamp.valueOf(reqdateTime));
		serviceMaster.setSERVICE_WORK_SEQ_NO(serviceDTO.getSERVICE_WORK_SEQ_NO());
		serviceMaster.setBookingSeqNo(serviceDTO.getBookingSeqNo());
		serviceMaster.setCreatedBy(serviceDTO.getCreatedBy());
		serviceMaster.setMembershipSeqNo(serviceDTO.getMembershipSeqNo());
		serviceMaster.setParentServiceWorkSeqNo(serviceDTO.getParentServiceWorkSeqNo());
		serviceMaster.setPersonSeqNo(serviceDTO.getPersonSeqNo());
		serviceMaster.setRemarks(serviceDTO.getRemarks());
		serviceMaster.setRequestSeqNo(serviceDTO.getRequestSeqNo());
		serviceMaster.setServiceId(serviceDTO.getServiceId());
		serviceMaster.setServiceSeqNo(serviceDTO.getServiceSeqNo());
		serviceMaster.setStatus(serviceDTO.getStatus());
		return serviceMaster;
	}
	*/
}