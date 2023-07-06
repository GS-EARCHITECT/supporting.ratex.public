package service_mgmt.booking.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service_mgmt.booking.services.I_Service_Booking_Service;
import service_mgmt.common.model.ServiceMasterDTO;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/serviceBooking")
public class Service_Booking_Controller {

	//private static final Logger logger = LoggerFactory.getLogger(Service_Controller.class);

	@Autowired
	private I_Service_Booking_Service serviceBookingServ;

	/* booking */

	@GetMapping(value = "/getSelectBookings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceMasterDTO>> getSelectBookings(@RequestBody ArrayList<Long> bookingSeqNos) {
		ArrayList<ServiceMasterDTO> ServiceMasterDTOs = serviceBookingServ.getSelectBookings(bookingSeqNos);
		return new ResponseEntity<>(ServiceMasterDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getService/{ServiceSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceMasterDTO> getBookingBySeqNo(@PathVariable String bookingSeqNo) {
		ServiceMasterDTO ServiceMasterDTO = serviceBookingServ.getBookingForId(Long.parseLong(bookingSeqNo));
		return new ResponseEntity<>(ServiceMasterDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllBookings", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceMasterDTO>> getAllBookings() {
		ArrayList<ServiceMasterDTO> ServiceMasterDTOs = serviceBookingServ.getAllBookings();
		return new ResponseEntity<>(ServiceMasterDTOs, HttpStatus.OK);
	}

	@PutMapping("/booking")
	public void updateBooking(@RequestBody ServiceMasterDTO serviceDTO) {
		serviceBookingServ.updBooking(serviceDTO);
	}

	@DeleteMapping("/delBooking/{ServiceSeqNo}")
	public void deleteBooking(@PathVariable String ServiceSeqNo) {
		serviceBookingServ.delBooking(Long.parseLong(ServiceSeqNo));
	}

	@DeleteMapping("/delAllBookings")
	public void deleteAllBookings() {
		serviceBookingServ.delAllBookings();
	}

	@DeleteMapping("/delSelectBookings")
	public void deleteSelectBookings(@RequestBody ArrayList<Long> bookingSeqNoList) {
		serviceBookingServ.delSelectBookings(bookingSeqNoList);
	}

	
}