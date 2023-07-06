package service_mgmt.booking.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service_mgmt.common.model.ServiceMaster;
import service_mgmt.common.model.ServiceMasterDTO;
import service_mgmt.common.model.ServiceMasterRepo;

@Service("serviceBookingServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class Service_Booking_Service implements I_Service_Booking_Service {
	 
	@Autowired
	private ServiceMasterRepo serviceMasterRepo;

	
/* Service Master */
	
	public ArrayList<ServiceMasterDTO> getAllBookings() {
		Optional<ArrayList<ServiceMaster>> serviceList = serviceMasterRepo.getAllOpenBookings();
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();
		
		if(serviceList.isPresent())
		{	
		serviceDTOs = serviceList != null ? this.getserviceDTOs(serviceList.get()) : null;
		}
		return serviceDTOs;
	}
	
	public ArrayList<ServiceMasterDTO> getSelectBookings(ArrayList<Long> bookingSeqNos) {
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();
		Optional<ServiceMaster> serviceMaster = null;
		ServiceMasterDTO ServiceMasterDTO = null;

		for (int i = 0; i < bookingSeqNos.size(); i++) {
			serviceMaster = serviceMasterRepo.getServiceForBooking(bookingSeqNos.get(i));
			if (serviceMaster.isPresent() && serviceMaster != null) {
				ServiceMasterDTO = this.getServiceMasterDTO(serviceMaster.get());
				serviceDTOs.add(ServiceMasterDTO);
			}
		}
		return serviceDTOs;
	}

	public ServiceMasterDTO getBookingForId(Long bookingSeqNo) {		
		Optional<ServiceMaster> serviceMaster = null;
		ServiceMasterDTO serviceMasterDTO = null;
		
			serviceMaster = serviceMasterRepo.getServiceForBooking(bookingSeqNo);
			if (serviceMaster.isPresent() && serviceMaster != null) {
				serviceMasterDTO = this.getServiceMasterDTO(serviceMaster.get());
							}		
		return serviceMasterDTO;
	}


	public void updBooking(ServiceMasterDTO serviceMasterDTO) {		
		if (serviceMasterRepo.existsById(serviceMasterDTO.getSERVICE_WORK_SEQ_NO())) 
		{			
		serviceMasterRepo.updateBookingNumber(serviceMasterDTO.getSERVICE_WORK_SEQ_NO());
		}
	}
	
	public void delBooking(Long bookingSeqNo) {
		
		Optional<ServiceMaster> sMaster = serviceMasterRepo.getServiceForBooking(bookingSeqNo);
		
		if(sMaster.isPresent())
		{			
		serviceMasterRepo.deleteBooking(bookingSeqNo);
		}
	}

	public void delAllBookings() {
		serviceMasterRepo.deleteCompleted();
	}

	@Override
	public void delSelectBookings(ArrayList<Long> bookingSeqNos) {
		Optional<ServiceMaster> sMaster = null;
		
		for (int i = 0; i < bookingSeqNos.size(); i++) {
			sMaster = serviceMasterRepo.getServiceForBooking(bookingSeqNos.get(i));
			if (sMaster.isPresent()) {
				serviceMasterRepo.deleteBooking(bookingSeqNos.get(i));
			}

		}

	}

	private ArrayList<ServiceMasterDTO> getserviceDTOs(ArrayList<ServiceMaster> serviceMasters) {
		ServiceMasterDTO serviceDTO = null;
		ArrayList<ServiceMasterDTO> serviceDTOs = new ArrayList<ServiceMasterDTO>();

		for (int i = 0; i < serviceMasters.size(); i++) {
			serviceDTO = new ServiceMasterDTO();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			serviceDTO.setOnDate(
					formatter.format(serviceMasters.get(i).getOnDate().toLocalDateTime()));
			serviceDTO.setReqDate(
					formatter.format(serviceMasters.get(i).getReqDate().toLocalDateTime()));
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
		serviceDTO.setOnDate(
				formatter.format(serviceMaster.getOnDate().toLocalDateTime()));
		serviceDTO.setReqDate(
				formatter.format(serviceMaster.getReqDate().toLocalDateTime()));
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
}