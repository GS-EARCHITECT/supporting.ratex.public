package service_mgmt.action.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service_mgmt.action.services.I_Service_Master_Service;
import service_mgmt.common.model.ServiceMasterDTO;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/service")
public class Service_Controller {

	/*
	//private static final Logger logger = LoggerFactory.getLogger(Service_Controller.class);

	@Autowired
	private I_Service_Master_Service serviceMasterServ;

	// Service REQUEST 

	@PostMapping("/new")
	public ResponseEntity<ServiceMasterDTO> newService(@RequestBody ServiceMasterDTO serviceDTO) {
		ServiceMasterDTO ServiceMasterDTO2 = serviceMasterServ.newService();
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(ServiceMasterDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getSelectServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceMasterDTO>> getSelectServices(@RequestBody ArrayList<Long> ServiceSeqNos) {
		ArrayList<ServiceMasterDTO> ServiceMasterDTOs = serviceMasterServ.getSelectServices(ServiceSeqNos);
		return new ResponseEntity<>(ServiceMasterDTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getService/{ServiceSeqNo}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ServiceMasterDTO> getServiceBySeqNo(@PathVariable String ServiceSeqNo) {
		ServiceMasterDTO ServiceMasterDTO = serviceMasterServ.getServiceById(Long.parseLong(ServiceSeqNo));
		return new ResponseEntity<>(ServiceMasterDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllServices", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceMasterDTO>> getAllServices() {
		ArrayList<ServiceMasterDTO> ServiceMasterDTOs = serviceMasterServ.getAllServices();
		return new ResponseEntity<>(ServiceMasterDTOs, HttpStatus.OK);
	}

	@PutMapping("/servicetype")
	public void updateService(@RequestBody ServiceMasterDTO serviceDTO) {
		serviceMasterServ.updService(serviceDTO);
	}

	@DeleteMapping("/delService/{ServiceSeqNo}")
	public void deleteService(@PathVariable String ServiceSeqNo) {
		serviceMasterServ.delService(Long.parseLong(ServiceSeqNo));
	}

	@DeleteMapping("/delAllServices")
	public void deleteAllServices() {
		serviceMasterServ.delAllServices();
	}

	@DeleteMapping("/delSelectServices")
	public void deleteSelectServices(@RequestBody ArrayList<Long> ServiceSeqNoList) {
		serviceMasterServ.delSelectServices(ServiceSeqNoList);
	}

		*/
}