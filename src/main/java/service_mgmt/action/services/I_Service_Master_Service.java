package service_mgmt.action.services;

import java.sql.Timestamp;

import java.util.ArrayList;
/*
import resource_mgmt.model.dto.LogiJobTypeResAssetMasterDTO;
import resource_mgmt.model.dto.LogiJobTypeResourceCategoryMasterDTO;
import resource_mgmt.model.dto.ResourceAllocationFailureDTO;
import resource_mgmt.model.master.LogiJobTypeResAssetMaster;
import resource_mgmt.model.master.LogiJobTypeResourceCategoryMaster;
import service_mgmt.common.model.ServiceMasterDTO;
*/

public interface I_Service_Master_Service {
	
	/*
	abstract public ArrayList<LogiJobTypeResAssetMaster> getAllAssetsForJobType(Integer jobTypeSeqNo, Integer tTypeSeqNo, Integer modeSeqNo, Long frLocSeqNo, Long toLocSeqNo);
	
	abstract public ArrayList<Long> getAllJobsForService(Long servWorkSeqNo, Integer jobTemplateSeqNo, Integer mgr_seq_no, String startDateTime, Integer opFlag);
	
	abstract public ResourceAllocationFailureDTO allocAssetForJob(Long jobSeqNo, Long assetSeqNo, Integer jobTypeSeqNo, Integer modeSeqNo, Integer targetTypeSeqNo, Long frLocSeqNo, Long toLocSeqNo);
	
	abstract public ArrayList<LogiJobTypeResourceCategoryMaster> getAllResourcesForJobType(Integer jobTypeSeqNo, Integer tTypeSeqNo, Integer modeSeqNo, Long frLocSeqNo, Long toLocSeqNo);
		
	abstract public ResourceAllocationFailureDTO allocResourceForJob(Long jobSeqNo, Long resSeqNo, Integer jobTypeSeqNo, Integer modeSeqNo, Integer targetTypeSeqNo, Long frLocSeqNo, Long toLocSeqNo);
	
	abstract public ArrayList<ServiceMasterDTO> getAllServices();

	abstract public ServiceMasterDTO getServiceById(Long serviceSeqNo);
	
	abstract ArrayList<ServiceMasterDTO> getSelectServices(ArrayList<Long> serviceSeqNos);
	
	abstract public ServiceMasterDTO newService();

	abstract void updService(ServiceMasterDTO serviceDTO);
	
	abstract void delService(Long serviceSeqNo);
	
	abstract void delSelectServices(ArrayList<Long> serviceSeqNos);
	
	abstract public void delAllServices();	
	*/
}