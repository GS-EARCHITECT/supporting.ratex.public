package request_mgmt.controller;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.services.IServiceRequest_Service;

@RestController
@RequestMapping("/serviceRequestMgmt")
public class ServiceRequest_Controller 
{

//	private static final Logger logger = LoggerFactory.getLogger(Customer_Request_Controller.class);

	@Autowired
	private IServiceRequest_Service serviceRequestServ;

	/* CUSTOMER REQUEST */	
	@PostMapping("/new")
	public ResponseEntity<ServiceRequestMaster_DTO> newRequest(@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		CompletableFuture<ServiceRequestMaster_DTO> future = serviceRequestServ.newCustServiceRequest(serviceRequestDTO);
		ServiceRequestMaster_DTO srvRequestDTO=null;
		try {
			srvRequestDTO = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		HttpHeaders httpHeaders = new HttpHeaders();		
		return new ResponseEntity<>(srvRequestDTO, httpHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getAllServiceRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests() {
		
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getAllServiceRequests();
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectRequests", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(@RequestBody ArrayList<Long> cList) {
		
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getSelectRequests(cList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);
	}

	@GetMapping(value = "/getSelectRequestsByCompanies", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(@RequestBody ArrayList<Long> cList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getSelectRequestsByCompanies(cList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	}
	
	@GetMapping(value = "/getSelectRequestsByPeople", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(@RequestBody ArrayList<Long> pList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getSelectRequestsByPeople(pList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}

	@GetMapping(value = "/getSelectRequestsBySuppliers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(@RequestBody ArrayList<Long> sList)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getSelectRequestsBySuppliers(sList);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}
	
	@GetMapping(value = "/getSelectRequestsBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = serviceRequestServ.getSelectRequestsBetweenTimes(fr, to);
		ArrayList<ServiceRequestMaster_DTO> serviceRequestMaster_DTOs=null;
		try {
			serviceRequestMaster_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestMaster_DTOs, HttpStatus.OK);	
		}
	
	@PutMapping("/updCustServiceRequest")
	public void updCustServiceRequest(@RequestBody ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		serviceRequestServ.updCustServiceRequest(serviceRequestDTO);		
	}

	@DeleteMapping("/delSelectRequests")
	public void delSelectRequests(@RequestBody ArrayList<Long> cList)
	{
		serviceRequestServ.delSelectRequests(cList);
	}

	@DeleteMapping("/delSelectRequestsByCompanies")
	public void delSelectRequestsByCompanies(@RequestBody ArrayList<Long> cList)
	{
	serviceRequestServ.delSelectRequestsByCompanies(cList);
	}
	
	@DeleteMapping("/delSelectRequestsByPeople")
	public void delSelectRequestsByPeople(@RequestBody ArrayList<Long> pList)
	{
	serviceRequestServ.delSelectRequestsByPeople(pList);
	}

	@DeleteMapping("/delSelectRequestsBySuppliers")
	public void delSelectRequestsBySuppliers(@RequestBody ArrayList<Long> sList)
	{
	serviceRequestServ.delSelectRequestsBySuppliers(sList);
	}
	
	@DeleteMapping("/delSelectRequestsBetweenTimes/{fr}/{to}")
	public void delSelectRequestsBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
	serviceRequestServ.delSelectRequestsBetweenTimes(fr, to);
	}
	
	@DeleteMapping("/delAllRequests")
	public void deleteAllRequests() 
	{
		serviceRequestServ.delAllRequests();
	}
	
	/* CUSTOMER REQUEST STATUS */
	
	@PostMapping("/")
	public ResponseEntity<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(
			@RequestBody ServiceRequestStatusDetails_DTO serviceRequestStatusRequestDTO) {
		
		CompletableFuture<ServiceRequestStatusDetails_DTO> future = serviceRequestServ
				.newCustServiceRequestStatus(serviceRequestStatusRequestDTO);
		ServiceRequestStatusDetails_DTO serviceRequestDTO2=null;
		try {
			serviceRequestDTO2 = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(serviceRequestDTO2, httpHeaders, HttpStatus.CREATED);
	}

	@GetMapping(value = "/getSelectRequestStatus", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(@RequestBody ArrayList<Long> rList)
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestServ.getSelectRequestStatus(rList);
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}

	@GetMapping(value = "/getSelectRequestStatusBetweenTimes/{fr}/{to}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestServ.getSelectRequestStatusBetweenTimes(fr, to);
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}
	
	@GetMapping(value = "/getAllServiceStatusDetails", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails()
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = serviceRequestServ.getAllServiceStatusDetails();
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDetails_DTOs=null;
		try {
			serviceRequestStatusDetails_DTOs = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return new ResponseEntity<>(serviceRequestStatusDetails_DTOs, HttpStatus.OK);	
		}
	
	@PutMapping("/updCustServiceRequestStatus")
	public void updCustServiceRequestStatus(@RequestBody ServiceRequestStatusDetails_DTO serviceRequestStatusDTO)
	{
		serviceRequestServ.updCustServiceRequestStatus(serviceRequestStatusDTO);
	}
	
	@DeleteMapping("/delSelectRequestStatus")
	public void delSelectRequestStatus(@RequestBody ArrayList<Long> rList) 
	{
		serviceRequestServ.delSelectRequests(rList);
	}
	
	@DeleteMapping("/delSelectRequestStatusBetweenTimes/{fr}/{to}")
	public void delSelectRequestStatusBetweenTimes(@PathVariable String fr, @PathVariable String to)
	{		
	serviceRequestServ.delSelectRequestsBetweenTimes(fr, to);
	}
	
	@DeleteMapping("/delAllRequestStatus()")
	public void delAllRequestStatus()
	{
		serviceRequestServ.delAllRequestStatus();
	}
}