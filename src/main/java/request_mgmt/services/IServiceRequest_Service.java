package request_mgmt.services;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;

public interface IServiceRequest_Service 
{
	public CompletableFuture<ServiceRequestMaster_DTO> newCustServiceRequest(ServiceRequestMaster_DTO srvReqDTO);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests();
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(ArrayList<Long> cList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(ArrayList<Long> cList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(ArrayList<Long> pList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(ArrayList<Long> sList);
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> updCustServiceRequest(ServiceRequestMaster_DTO servReqDTO);
	public CompletableFuture<Void> delSelectRequests(ArrayList<Long> cList);
	public CompletableFuture<Void> delSelectRequestsByCompanies(ArrayList<Long> cList);
	public CompletableFuture<Void> delSelectRequestsByPeople(ArrayList<Long> pList);
	public CompletableFuture<Void> delSelectRequestsBySuppliers(ArrayList<Long> sList);
	public CompletableFuture<Void> delSelectRequestsBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delAllRequests();
	public CompletableFuture<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqStatusDTO);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(ArrayList<Long> rList);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails();
	public CompletableFuture<Void> updCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqDTO);
	public CompletableFuture<Void> delSelectRequestStatus(ArrayList<Long> rList);
	public CompletableFuture<Void> delSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm);
	public CompletableFuture<Void> delAllRequestStatus();
		
}