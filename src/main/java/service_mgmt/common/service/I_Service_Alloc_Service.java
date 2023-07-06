package service_mgmt.common.service;

import java.util.ArrayList;

import service_mgmt.common.model.ServiceMasterDTO;


public interface I_Service_Alloc_Service {
	
	abstract public ArrayList<ServiceMasterDTO> getAllServices();

	abstract public ServiceMasterDTO getServiceById(Long serviceSeqNo);
	
	abstract ArrayList<ServiceMasterDTO> getSelectServices(ArrayList<Long> serviceSeqNos);
	
	abstract public ServiceMasterDTO newService(ServiceMasterDTO serviceDTO);

	abstract void updService(ServiceMasterDTO serviceDTO);
	
	abstract void delService(Long serviceSeqNo);
	
	abstract void delSelectServices(ArrayList<Long> serviceSeqNos);
	
	abstract public void delAllServices();	
	
}