package request_mgmt.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import request_mgmt.model.dto.ServiceRequestMaster_DTO;
import request_mgmt.model.dto.ServiceRequestStatusDetails_DTO;
import request_mgmt.model.master.ServiceRequestMaster;
import request_mgmt.model.master.ServiceRequestStatusDetails;
import request_mgmt.model.master.ServiceRequestStatusDetailsPK;
import request_mgmt.model.repo.ServiceRequestMaster_Repo;
import request_mgmt.model.repo.ServiceRequestStatusDetails_Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("serviceRequestServ")
@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED)
public class ServiceRequest_Service implements IServiceRequest_Service 
{	
	private static final Logger logger = LoggerFactory.getLogger(ServiceRequest_Service.class);
	
	@Autowired
    private ServiceRequestMaster_Repo serviceRequestRepo;
	
	@Autowired
    private Executor asyncExecutor;
	
	@Autowired
	private ServiceRequestStatusDetails_Repo serviceRequestStatusRepo;

/* SERVICE REQUEST MASTER */	
	
    @Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getAllServiceRequests()
    {
    	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{		
		ArrayList<ServiceRequestMaster> servReqList = null;    	
    	servReqList =  (ArrayList<ServiceRequestMaster>) serviceRequestRepo.findAll();
    	ArrayList<ServiceRequestMaster_DTO> lMasters = new ArrayList<ServiceRequestMaster_DTO>();
    	lMasters = servReqList != null ? this.getServRequestDtos(servReqList) : null;        
   		return lMasters;
   		},asyncExecutor);
    	
        return future;
    }
    
    @Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequests(ArrayList<Long> serviceReqSeqNos)
    {
    	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
    	ArrayList<ServiceRequestMaster> lMasters = serviceRequestRepo.getSelectRequests(serviceReqSeqNos);
    	ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters): null;
    	return servMasterDTOs;
   		},asyncExecutor);
    	
        return future;
    }
    
    @Override
	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByCompanies(ArrayList<Long> cList)
    {
    	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
		{
    	ArrayList<ServiceRequestMaster> lMasters = serviceRequestRepo.getSelectRequestsByCompanies(cList);
    	ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters): null;
    	return servMasterDTOs;
   		},asyncExecutor);
    	
        return future;
    }
    
    @Override
  	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsByPeople(ArrayList<Long> pList)
      {
      	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
      	ArrayList<ServiceRequestMaster> lMasters = serviceRequestRepo.getSelectRequestsByPeople(pList);
      	ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters): null;
      	return servMasterDTOs;
     		},asyncExecutor);
      	
          return future;
      }
    
    @Override
  	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBySuppliers(ArrayList<Long> sList)
      {
      	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
      	ArrayList<ServiceRequestMaster> lMasters = serviceRequestRepo.getSelectRequestsBySuppliers(sList);
      	ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters): null;
      	return servMasterDTOs;
     		},asyncExecutor);
      	
          return future;
      }
    
    @Override
  	public CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> getSelectRequestsBetweenTimes(String frDtTm, String toDtTm)
      {
      	CompletableFuture<ArrayList<ServiceRequestMaster_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  		LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  		Timestamp frTs = Timestamp.valueOf(frDTTm);
  		Timestamp toTs = Timestamp.valueOf(toDTTm);
      	ArrayList<ServiceRequestMaster> lMasters = serviceRequestRepo.getSelectRequestsBetweenTimes(frTs, toTs);
      	ArrayList<ServiceRequestMaster_DTO> servMasterDTOs = lMasters != null ? this.getServRequestDtos(lMasters): null;
      	return servMasterDTOs;
     	},asyncExecutor);
      	
          return future;
      }
        
    @Override
	public CompletableFuture<ServiceRequestMaster_DTO> newCustServiceRequest(ServiceRequestMaster_DTO srvReqDTO) 
    {    
		CompletableFuture<ServiceRequestMaster_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();  		
  		if(!serviceRequestRepo.existsById(srvReqDTO.getRequestSeqNo()))
  		{	
  		ServiceRequestMaster serviceRequestMaster = serviceRequestRepo.save(this.setServiceRequestMaster(srvReqDTO));    
  		serviceRequestDTO = this.getServiceRequestMaster_DTO(serviceRequestMaster);
  		}
  		return serviceRequestDTO;
     	},asyncExecutor);
      	
        return future;
    }

    @Override
	public CompletableFuture<Void> updCustServiceRequest(ServiceRequestMaster_DTO servReqDTO) 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	if(serviceRequestRepo.existsById(servReqDTO.getRequestSeqNo()))
    	{
        ServiceRequestMaster serviceRequestMaster = this.setServiceRequestMaster(servReqDTO);
        serviceRequestMaster.setRequestSeqNo(servReqDTO.getRequestSeqNo());
    	serviceRequestRepo.save(serviceRequestMaster);
    	}	
     	},asyncExecutor);      	
        return future;    
    }

    public CompletableFuture<Void> delSelectRequests(ArrayList<Long> cList)
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestRepo.delSelectRequests(cList);
    	},asyncExecutor);      	
        return future;
    }
    
	public CompletableFuture<Void> delSelectRequestsByCompanies(ArrayList<Long> cList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestRepo.delSelectRequestsByCompanies(cList);
    	},asyncExecutor);      	
        return future;
		
	}
	
	public CompletableFuture<Void> delSelectRequestsByPeople(ArrayList<Long> pList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestRepo.delSelectRequestsByPeople(pList);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delSelectRequestsBySuppliers(ArrayList<Long> sList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
    	serviceRequestRepo.delSelectRequestsBySuppliers(sList);
    	},asyncExecutor);      	
        return future;	
	}
	
	public CompletableFuture<Void> delSelectRequestsBetweenTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
    	serviceRequestRepo.delSelectRequestsBetweenTimes(frTs, toTs);
    	},asyncExecutor);      	
        return future;	
	}
	
    
    @Override
	public CompletableFuture<Void> delAllRequests() 
    {
    	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestRepo.deleteAll();
    	},asyncExecutor);      	
        return future;    
    }
    
    private synchronized ArrayList<ServiceRequestMaster_DTO> getServRequestDtos(ArrayList<ServiceRequestMaster> servReqMasters) 
	{
		ArrayList<ServiceRequestMaster_DTO> serviceRequestDTOs = new ArrayList<ServiceRequestMaster_DTO>(); 
		for(int i=0; i < servReqMasters.size();i++)
		{		
		serviceRequestDTOs.add(this.getServiceRequestMaster_DTO(servReqMasters.get(i)));
		}		
		return serviceRequestDTOs;
	}
	
	private synchronized ServiceRequestMaster_DTO getServiceRequestMaster_DTO(ServiceRequestMaster servReqMaster) 
	{
		ServiceRequestMaster_DTO serviceRequestDTO = new ServiceRequestMaster_DTO();
		serviceRequestDTO.setRequestSeqNo(servReqMaster.getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestDTO.setRequestDate(formatter.format(servReqMaster.getRequestDate().toLocalDateTime()));
		serviceRequestDTO.setPersonSeqNo(servReqMaster.getPersonSeqNo());
		serviceRequestDTO.setCompanySeqNo(servReqMaster.getCompanySeqNo());
		serviceRequestDTO.setSupplierSeqNo(servReqMaster.getSupplierSeqNo());
		serviceRequestDTO.setStatus(servReqMaster.getStatus());
		serviceRequestDTO.setRemark(servReqMaster.getRemark());		
		return serviceRequestDTO;
		}
		
	private synchronized ServiceRequestMaster setServiceRequestMaster(ServiceRequestMaster_DTO serviceRequestDTO) 
	{
		ServiceRequestMaster serviceRequestMaster		=	new	ServiceRequestMaster();				
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(serviceRequestDTO.getRequestDate(), formatter);
		Timestamp ts = Timestamp.valueOf(dateTime);		
		serviceRequestMaster.setRequestDate(ts);		
		serviceRequestMaster.setPersonSeqNo(serviceRequestDTO.getPersonSeqNo());
		serviceRequestMaster.setCompanySeqNo(serviceRequestDTO.getCompanySeqNo());
		serviceRequestMaster.setSupplierSeqNo(serviceRequestDTO.getSupplierSeqNo());
		serviceRequestMaster.setStatus(serviceRequestDTO.getStatus());
		serviceRequestMaster.setRemark(serviceRequestDTO.getRemark());
		return serviceRequestMaster;
	}
	
	
/* SERVICE REQUEST STATUS  */
    
    @Override
	public CompletableFuture<ServiceRequestStatusDetails_DTO> newCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqStatusDTO) 
    {
    	CompletableFuture<ServiceRequestStatusDetails_DTO> future = CompletableFuture.supplyAsync(() -> 
  		{
    	ServiceRequestStatusDetails_DTO serviceRequestStatusDTO=new ServiceRequestStatusDetails_DTO();    	
    	ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    	LocalDateTime dateTime = LocalDateTime.parse(serviceRequestStatusDTO.getOnDate(), formatter);
        Timestamp ts = Timestamp.valueOf(dateTime);
        serviceRequestDetailsPK.setOnDate(ts);
        serviceRequestDetailsPK.setRequestSeqNo(srvReqStatusDTO.getRequestSeqNo());
        if(!serviceRequestStatusRepo.existsById(serviceRequestDetailsPK))
        {   
        serviceRequestStatusDTO = this.getServiceRequestStatusDTO(serviceRequestStatusRepo.save(this.setServiceRequestStatusDetails(srvReqStatusDTO)));
        }
        return serviceRequestStatusDTO;
     	},asyncExecutor);
    	
	return future;
    }    
    
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatus(ArrayList<Long> rList)
	{
	  	CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ArrayList<ServiceRequestStatusDetails> lMasters = new ArrayList<ServiceRequestStatusDetails>();  	
      	lMasters = serviceRequestStatusRepo.getSelectRequestStatus(rList);
      	ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null ? this.getServRequestStatusDtos(lMasters): null;
      	return servMasterDTOs;
     	},asyncExecutor);      	
        return future;    	
	}
	
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm)
	{
	  	CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  		LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  		LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  		Timestamp frTs = Timestamp.valueOf(frDTTm);
  		Timestamp toTs = Timestamp.valueOf(toDTTm);  		
      	ArrayList<ServiceRequestStatusDetails> lMasters = serviceRequestStatusRepo.getSelectRequestStatusBetweenTimes(frTs, toTs);
      	ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null ? this.getServRequestStatusDtos(lMasters): null;
      	return servMasterDTOs;
     	},asyncExecutor);      	
          return future;    	
	}
	
	public CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> getAllServiceStatusDetails()
	{
		CompletableFuture<ArrayList<ServiceRequestStatusDetails_DTO>> future = CompletableFuture.supplyAsync(() -> 
  		{
  		ArrayList<ServiceRequestStatusDetails> lMasters = (ArrayList<ServiceRequestStatusDetails>)serviceRequestStatusRepo.findAll();
      	ArrayList<ServiceRequestStatusDetails_DTO> servMasterDTOs = lMasters != null ? this.getServRequestStatusDtos(lMasters): null;
      	return servMasterDTOs;
     	},asyncExecutor);      	
         return future; 
	}
	
	@Override
	public CompletableFuture<Void> updCustServiceRequestStatus(ServiceRequestStatusDetails_DTO srvReqDTO) 
    {
	 	CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime onDTTm = LocalDateTime.parse(srvReqDTO.getOnDate(), formatter);
  	  	ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
  	  	Timestamp onTs = Timestamp.valueOf(onDTTm);
  	  	serviceRequestDetailsPK.setOnDate(onTs);
  	    serviceRequestDetailsPK.setRequestSeqNo(srvReqDTO.getRequestSeqNo());
  	    
    	if(serviceRequestStatusRepo.existsById(serviceRequestDetailsPK))
    	{    	
    	serviceRequestStatusRepo.save(this.setServiceRequestStatusDetails(srvReqDTO));
    	}	
     	},asyncExecutor);      	
        return future;    
    }
    
	public CompletableFuture<Void> delSelectRequestStatus(ArrayList<Long> rList)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestStatusRepo.delSelectRequestStatus(rList);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delSelectRequestStatusBetweenTimes(String frDtTm, String toDtTm)
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
  	  	LocalDateTime frDTTm = LocalDateTime.parse(frDtTm, formatter);
  	  	LocalDateTime toDTTm = LocalDateTime.parse(toDtTm, formatter);
  	  	Timestamp frTs = Timestamp.valueOf(frDTTm);
  	  	Timestamp toTs = Timestamp.valueOf(toDTTm);
  	  	serviceRequestStatusRepo.delSelectRequestStatusBetweenTimes(frTs, toTs);
    	},asyncExecutor);      	
        return future;
	}
	
	public CompletableFuture<Void> delAllRequestStatus()
	{
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> 
  		{
  		serviceRequestStatusRepo.deleteAll();
    	},asyncExecutor);      	
        return future;
	}
	
	
	private ArrayList<ServiceRequestStatusDetails_DTO> getServRequestStatusDtos(ArrayList<ServiceRequestStatusDetails> servReqStatusDetails) 
	{
		
		ServiceRequestStatusDetails_DTO serviceRequestStatusDTO = null;
		ArrayList<ServiceRequestStatusDetails_DTO> serviceRequestStatusDTOs = new ArrayList<ServiceRequestStatusDetails_DTO>(); 
		
		for(int i=0; i < servReqStatusDetails.size();i++)
		{		
		serviceRequestStatusDTOs.add(this.getServiceRequestStatusDTO(servReqStatusDetails.get(i)));
		}		
		return serviceRequestStatusDTOs;
	}

	private synchronized ServiceRequestStatusDetails_DTO getServiceRequestStatusDTO(ServiceRequestStatusDetails servReqStatusDetails) 
	{
		ServiceRequestStatusDetails_DTO serviceRequestStatusDTO = new ServiceRequestStatusDetails_DTO();
		serviceRequestStatusDTO.setRequestSeqNo(servReqStatusDetails.getId().getRequestSeqNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		serviceRequestStatusDTO.setOnDate(formatter.format(servReqStatusDetails.getId().getOnDate().toLocalDateTime()));
		serviceRequestStatusDTO.setRemark(servReqStatusDetails.getRemark());
		serviceRequestStatusDTO.setStatus(servReqStatusDetails.getStatus());
		return serviceRequestStatusDTO;
		}
	
	private synchronized ServiceRequestStatusDetails setServiceRequestStatusDetails(ServiceRequestStatusDetails_DTO  serviceRequestStatusDetailsDTO) 
	{
		ServiceRequestStatusDetails serviceRequestStatusDetails	=	new	ServiceRequestStatusDetails();		
		ServiceRequestStatusDetailsPK serviceRequestDetailsPK = new ServiceRequestStatusDetailsPK();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(serviceRequestStatusDetailsDTO.getOnDate(), formatter);
	    Timestamp timestamp = Timestamp.valueOf(dateTime);
	    serviceRequestDetailsPK.setOnDate(timestamp);
	    serviceRequestDetailsPK.setRequestSeqNo(serviceRequestStatusDetailsDTO.getRequestSeqNo());
	    serviceRequestStatusDetails.setId(serviceRequestDetailsPK);		
		serviceRequestStatusDetails.setStatus(serviceRequestStatusDetailsDTO.getStatus());
	    serviceRequestStatusDetails.setRemark(serviceRequestStatusDetailsDTO.getRemark());	
		return serviceRequestStatusDetails;
	}
	
}