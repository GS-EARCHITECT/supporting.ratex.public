package service_mgmt.booking.services;

import java.util.ArrayList;

import service_mgmt.common.model.ServiceMasterDTO;


public interface I_Service_Booking_Service {
	
	abstract public ArrayList<ServiceMasterDTO> getAllBookings();

	abstract public ServiceMasterDTO getBookingForId(Long bookingSeqNo);
	
	abstract public ArrayList<ServiceMasterDTO> getSelectBookings(ArrayList<Long> bookingSeqNos);
	
	abstract public void updBooking(ServiceMasterDTO bookingDTO);
	
	abstract public void delBooking(Long bookingSeqNo);
	
	abstract public void delSelectBookings(ArrayList<Long> bookingSeqNos);
	
	abstract public void delAllBookings();	
	
}