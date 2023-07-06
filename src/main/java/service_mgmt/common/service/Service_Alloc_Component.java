package service_mgmt.common.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/*import common.dateUtils.DateUtil;
import job_mgmt.model.details.JobTripDetails;
import job_mgmt.model.details.JobTripDetailsPK;
import job_mgmt.model.details.JobTripTemplateDetail;
import job_mgmt.model.details.JobsTemplateDetails;
import job_mgmt.model.dto.JobDTO;
import job_mgmt.model.dto.JobsTemplateMasterDTO;
import job_mgmt.model.master.JobMaster;
import job_mgmt.model.master.JobsTemplateMaster;
import job_mgmt.model.master.ServiceMovementMaster;
import job_mgmt.model.repo.JobRepo;
import job_mgmt.model.repo.JobTripRepo;
import job_mgmt.model.repo.JobsTemplateRepo;
import job_mgmt.model.repo.JobsTripTemplateRepo;
import job_mgmt.model.repo.ServiceMovementRepo;
import service_mgmt.common.model.ServiceMasterRepo;
*/

@Service("resToServAllocDirectServ")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class Service_Alloc_Component {
	
	/*
private static final Logger logger = LoggerFactory.getLogger(Service_Alloc_Component.class);
	
	@Autowired
	private JobRepo jobRepo;

	@Autowired
	private JobsTripTemplateRepo jobTripsTemplateRepo;

	@Autowired
	private JobsTemplateRepo jobTemplateRepo;
	
	@Autowired
	private JobTripRepo jobTripRepo;
	
	@Autowired
	private	ServiceMovementRepo serviceMovementRepo;

	//* Get Job Template For Service Category 	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public JobsTemplateMasterDTO getJobTemplatesForServiceCategory(Integer servCatSeqNo) 
	{		
		ArrayList<JobsTemplateMaster> jobsTemplateMasters = jobTemplateRepo.getJobTemplatesForServiceCat(servCatSeqNo);
		ArrayList<Integer> jobTemplateSeqNoList = new ArrayList<Integer>();		
		JobsTemplateMasterDTO jobsTemplateMasterDTO = new JobsTemplateMasterDTO();
		
		if(jobsTemplateMasters.size()>0)
		{
		jobsTemplateMasterDTO.setServCatSeqNo(servCatSeqNo);
		for (int i = 0; i < jobsTemplateMasters.size(); i++) 
		{
		jobTemplateSeqNoList.add(jobsTemplateMasters.get(i).getId().getJobTemplateSeqNo());	
		}
		jobsTemplateMasterDTO.setJobTemplateSeqNoList(jobTemplateSeqNoList);
		}
		
		return jobsTemplateMasterDTO;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public ArrayList<Long> setJobsForTemplate(Long servWorkSeqNo, Integer jobTemplateSeqNo, Integer mgr_seq_no, String startDateTime, Integer opFlag) 
	{
		ArrayList<Long> jobSeqNos = new ArrayList<Long>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");		
		LocalDateTime ldt = LocalDateTime.parse(startDateTime, formatter);
		Timestamp currDate = Timestamp.valueOf(ldt);		
		Timestamp endDate = null;
		JobMaster jobMaster5 = null;
		Optional<JobTripDetails> jobTripDetails=null;				
		Long jobSeqNo;
		Integer seq_no=0;
		Integer days, hours, minutes, seconds, days_dur, hours_dur, mins_dur, seconds_dur=0;		
		ArrayList<JobsTemplateDetails> jobsTemplateDetails = null;		
		ArrayList<JobMaster> jobMasters2 = jobRepo.getJobsForServiceWork(servWorkSeqNo);
		
		if(jobMasters2!=null && jobMasters2.size()>0)
		{	
		jobRepo.deleteJobDetails(servWorkSeqNo);
		}
		
		for (int l = 0; l <jobMasters2.size(); l++) 
		{
		jobSeqNo=jobMasters2.get(l).getJob_seq_no();		
		jobTripDetails=jobTripRepo.checkJobDetails(jobSeqNo);		
		if(jobTripDetails.isPresent()) 
		{
		jobTripRepo.delJobDetails(jobSeqNo);
		}		
		}
		
		// Prepare Sequence Array from job_templates_details 		
		jobsTemplateDetails = jobTemplateRepo.getJobsForTemplate(jobTemplateSeqNo);
		jobsTemplateDetails.sort((o1, o2) -> o1.getId().getSeq_no().compareTo(o2.getId().getSeq_no()));
		Optional<ServiceMovementMaster> serviceMovementMaster2 = serviceMovementRepo.getServiceMovementDetails(servWorkSeqNo);
		Optional<ServiceMovementMaster> serviceMovementMaster =null;
		if(serviceMovementMaster2.isPresent())
		{	
		serviceMovementMaster = serviceMovementRepo.getAnyOtherMovementService(servWorkSeqNo, serviceMovementMaster2.get().getService_cat_seq_no(), serviceMovementMaster2.get().getFrom_location_seq_no(), serviceMovementMaster2.get().getTo_location_seq_no());
		}
				
		logger.info("size :"+jobsTemplateDetails.size());
		
		for (int i = 0; i < jobsTemplateDetails.size(); i++) 
		{
		logger.info("loop :"+i);	
		seq_no = jobsTemplateDetails.get(i).getId().getSeq_no();
		if(jobsTemplateDetails.get(i).getJob_Type()!=null && !jobsTemplateDetails.get(i).getJob_Type().toUpperCase().equals("MOVE"))
		{
		jobMaster5 = new JobMaster();		
		days_dur=0;
		hours_dur=0;
		mins_dur=0;
		seconds_dur=0;		
		days_dur = (jobsTemplateDetails.get(i).getDur_days() == null) ? 0 : jobsTemplateDetails.get(i).getDur_days(); 
		hours_dur=(jobsTemplateDetails.get(i).getDur_hours() == null) ? 0 : jobsTemplateDetails.get(i).getDur_hours();
		mins_dur=(jobsTemplateDetails.get(i).getDur_miinutes() == null) ? 0 : jobsTemplateDetails.get(i).getDur_miinutes();
		seconds_dur=(jobsTemplateDetails.get(i).getDur_seconds() == null) ? 0 : jobsTemplateDetails.get(i).getDur_seconds();								
		days=(jobsTemplateDetails.get(i).getDays_plus() == null) ? 0 : jobsTemplateDetails.get(i).getDays_plus();
		hours=(jobsTemplateDetails.get(i).getHours_plus() == null) ? 0 : jobsTemplateDetails.get(i).getHours_plus();
		minutes=(jobsTemplateDetails.get(i).getMinutes_plus() == null) ? 0 : jobsTemplateDetails.get(i).getMinutes_plus();
		seconds=(jobsTemplateDetails.get(i).getSeconds_plus() == null) ? 0 : jobsTemplateDetails.get(i).getMinutes_plus();
		endDate = common.dateUtils.DateUtil.addDays(currDate, days_dur, hours_dur, mins_dur, seconds_dur);
		
		if(opFlag==1)
		{
		jobMaster5.setPlan_start_date(currDate);
		jobMaster5.setPlan_end_date(endDate);
		}
		else
		{
		jobMaster5.setAct_start_date(currDate);
		jobMaster5.setAct_end_date(endDate);			
		}
				
		jobMaster5.setService_work_seq_no(servWorkSeqNo);
		jobMaster5.setJob_template_seq_no(jobTemplateSeqNo);		
		jobMaster5.setTarget_type_seq_no(jobsTemplateDetails.get(i).getTarget_type_seq_no());
		jobMaster5.setJob_type_seq_no(jobsTemplateDetails.get(i).getId().getJobTypeSeqNo());
		jobMaster5.setTemplate_seq_no(jobsTemplateDetails.get(i).getId().getSeq_no());
		jobSeqNos.add(jobRepo.save(jobMaster5).getJob_seq_no());
		currDate = common.dateUtils.DateUtil.addDays(endDate, days, hours, minutes, seconds);		
		}

		if(jobsTemplateDetails.get(i).getJob_Type()!=null && jobsTemplateDetails.get(i).getJob_Type().toUpperCase().equals("MOVE"))
		{
		Optional<JobTripTemplateDetail> jobTripTemplateDetail = jobTripsTemplateRepo.getJobTripsStatusTemplate(jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo(), jobsTemplateDetails.get(i).getId().getJobTypeSeqNo());
		JobMaster jobMaster = new JobMaster();
		jobMaster.setTarget_type_seq_no(jobsTemplateDetails.get(i).getTarget_type_seq_no());
		jobMaster.setTarget_type_seq_no(jobsTemplateDetails.get(i).getTarget_type_seq_no());
		jobMaster.setService_work_seq_no(servWorkSeqNo);
		jobMaster.setJob_template_seq_no(jobTemplateSeqNo);
		jobMaster.setTarget_type_seq_no(jobsTemplateDetails.get(i).getTarget_type_seq_no());
		jobMaster.setTarget_type_seq_no(jobsTemplateDetails.get(i).getTarget_type_seq_no());
		jobMaster.setJob_type_seq_no(jobsTemplateDetails.get(i).getId().getJobTypeSeqNo());
		jobMaster.setTemplate_seq_no(jobsTemplateDetails.get(i).getId().getSeq_no());
		JobMaster jobMaster2 = jobRepo.save(jobMaster);		
		
		if(jobTripTemplateDetail.isPresent())
		{		
		endDate = setTripDetailsFromMaster(jobMaster2.getJob_seq_no(), jobMaster2.getJob_type_seq_no(), jobsTemplateDetails.get(i).getId().getJobTemplateSeqNo(), currDate, opFlag);
		if(opFlag==1)
		{
		jobMaster2.setPlan_start_date(currDate);
		jobMaster2.setPlan_end_date(endDate);
		}
		else
		{
		jobMaster2.setAct_start_date(currDate);
		jobMaster2.setAct_end_date(endDate);
		}		
		jobRepo.save(jobMaster2);
		}
		else
		{
			if (serviceMovementMaster!=null) 
			{			
			if(opFlag==1)
			{
			jobMaster2.setPlan_start_date(currDate);
			jobMaster2.setPlan_end_date(endDate);
			endDate=getTripDetailsFromData(serviceMovementMaster.get(), jobMaster2.getJob_seq_no(), jobMaster2.getJob_type_seq_no(),jobMaster2.getPlan_start_date(),jobMaster2.getAct_start_date(), opFlag);			
			jobMaster2.setPlan_end_date(endDate);
			}
			else
			{
			jobMaster2.setAct_start_date(currDate);
			jobMaster2.setAct_end_date(endDate);
			endDate=getTripDetailsFromData(serviceMovementMaster.get(), jobMaster2.getJob_seq_no(), jobMaster2.getJob_type_seq_no(),jobMaster2.getPlan_start_date(),jobMaster2.getAct_start_date(), opFlag);
			jobMaster2.setAct_end_date(endDate);
			}			
			jobRepo.save(jobMaster2);			
			}
			}
		}		
		}
		
		return jobSeqNos;		

	}
	
	
	private Timestamp getTripDetailsFromData(ServiceMovementMaster existingserviceMovementMaster, Long jobSeqNo, Integer jobTypeSeqNo, Timestamp planStDate, Timestamp actStDate, Integer opFlag)
	{
	ArrayList<Timestamp> outList = new ArrayList<Timestamp>();
	Long jobSeqNo2 = jobRepo.getJobForServiceWork(existingserviceMovementMaster.getService_work_seq_no(), jobTypeSeqNo);
	JobTripDetails jobTripDetails2 = null;
	ArrayList<JobTripDetails> jobTripDetails = null;
	JobTripDetailsPK jobTripDetailsPK2 = null;	
	boolean stFlag = true;
	Timestamp pl_EndDate = null;
	Timestamp ac_EndDate = null;
	long milliseconds_intra = 0, milliseconds_inter  = 0;
		
	logger.info("planned strt :"+planStDate);
	logger.info("actual strt :"+actStDate);
	
	if(opFlag==1)
	{	
	jobTripDetails = jobTripRepo.getJobPlanDetails(jobSeqNo2);
	}
	else
	{
	jobTripDetails = jobTripRepo.getJobActualDetails(jobSeqNo2);
	}
	
	if(opFlag==1)
	{
	for (int i = 0; i < jobTripDetails.size(); i++) 
	{
		milliseconds_intra = 0;
		milliseconds_inter  = 0;
		
		if(stFlag)
		{
		stFlag=false;	
		}
		else
		{				
		if(jobTripDetails.get(i).getPlan_start_date()!=null)
		{	
		milliseconds_inter = jobTripDetails.get(i).getPlan_start_date().getTime() - jobTripDetails.get(i-1).getPlan_end_date().getTime();		
		}		
		planStDate = DateUtil.addMilli(pl_EndDate, milliseconds_inter);		
		}
			
		if(jobTripDetails.get(i).getPlan_start_date()!=null && jobTripDetails.get(i).getPlan_end_date()!=null)
		{
		milliseconds_intra = jobTripDetails.get(i).getPlan_end_date().getTime() - jobTripDetails.get(i).getPlan_start_date().getTime();
		}		
		pl_EndDate = DateUtil.addMilli(planStDate, milliseconds_intra);
				
		jobTripDetails2 = new JobTripDetails();
		jobTripDetailsPK2 = new JobTripDetailsPK(jobSeqNo, jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(), jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo()); 
		jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
		jobTripDetails2.setPlan_start_date(planStDate);
		jobTripDetails2.setPlan_end_date(pl_EndDate);		
		jobTripRepo.save(jobTripDetails2);
	}
	}
	
	if(opFlag==0) 
	{
		for (int i = 0; i < jobTripDetails.size(); i++) 
		{
			milliseconds_intra = 0;
			milliseconds_inter  = 0;
			
			if(stFlag)
			{
			stFlag=false;	
			}
			else
			{				
			if(jobTripDetails.get(i).getAct_start_date()!=null)
			{	
			milliseconds_inter = jobTripDetails.get(i).getAct_start_date().getTime() - jobTripDetails.get(i-1).getAct_end_date().getTime();		
			}		
			actStDate = DateUtil.addMilli(ac_EndDate, milliseconds_inter);		
			}
				
			if(jobTripDetails.get(i).getAct_start_date()!=null && jobTripDetails.get(i).getAct_end_date()!=null)
			{
			milliseconds_intra = jobTripDetails.get(i).getAct_end_date().getTime() - jobTripDetails.get(i).getAct_start_date().getTime();
			}		
			ac_EndDate = DateUtil.addMilli(actStDate, milliseconds_intra);
					
			jobTripDetails2 = new JobTripDetails();
			jobTripDetailsPK2 = new JobTripDetailsPK(jobSeqNo, jobTripDetails.get(i).getJob_Trip_pk().getModeSeqNo(),jobTripDetails.get(i).getJob_Trip_pk().getFromLocationSeqNo(), jobTripDetails.get(i).getJob_Trip_pk().getToLocationSeqNo()); 
			jobTripDetails2.setJob_Trip_pk(jobTripDetailsPK2);
			jobTripDetails2.setAct_start_date(actStDate);
			jobTripDetails2.setAct_end_date(ac_EndDate);
			outList.add(ac_EndDate);
			jobTripRepo.save(jobTripDetails2);
		}
			
	}
		
	Timestamp reTimestamp = Collections.max(outList);
	return reTimestamp;	
	}

	
	private Timestamp setTripDetailsFromMaster(Long jobSeqNo, Integer job_type_seq_no, Integer template_seq_no, Timestamp startDate, Integer opFlag)
	{			
		Timestamp endDate = null;
		ArrayList<Timestamp> endDatesList = new ArrayList<Timestamp>();		
		JobTripDetails jobTripDetails = null;
		JobTripDetailsPK jobTripDetailsPK = null;		
		Long fr_Loc_Seq_no, to_Loc_Seq_no = null; 		
		Integer mode_opt, days_dur, hours_dur, mins_dur, seconds_dur=0;		
		Integer days, hours, minutes, seconds;				
		ArrayList<JobTripTemplateDetail> jobsTripTemplateDetails = null;		
		
		// Prepare Sequence Array from job_templates_details 		
		jobsTripTemplateDetails = jobTripsTemplateRepo.getJobTripsForTemplate(template_seq_no, job_type_seq_no);
		jobsTripTemplateDetails.sort((o1, o2) -> o1.getId().getSeqNo().compareTo(o2.getId().getSeqNo()));			
		
		for (int i = 0; i < jobsTripTemplateDetails.size(); i++) 
		{
			
			days_dur=0;
			hours_dur=0;
			mins_dur=0;
			seconds_dur=0;		
			days_dur = (jobsTripTemplateDetails.get(i).getDur_days() == null) ? 0 : jobsTripTemplateDetails.get(i).getDur_days(); 
			hours_dur=(jobsTripTemplateDetails.get(i).getDur_hours() == null) ? 0 : jobsTripTemplateDetails.get(i).getDur_hours();
			mins_dur=(jobsTripTemplateDetails.get(i).getDur_miinutes() == null) ? 0 : jobsTripTemplateDetails.get(i).getDur_miinutes();
			seconds_dur=(jobsTripTemplateDetails.get(i).getDur_seconds() == null) ? 0 : jobsTripTemplateDetails.get(i).getDur_seconds();								
			days=(jobsTripTemplateDetails.get(i).getDaysPlus() == null) ? 0 : jobsTripTemplateDetails.get(i).getDaysPlus();
			hours=(jobsTripTemplateDetails.get(i).getHoursPlus() == null) ? 0 : jobsTripTemplateDetails.get(i).getHoursPlus();
			minutes=(jobsTripTemplateDetails.get(i).getMinutesPlus() == null) ? 0 : jobsTripTemplateDetails.get(i).getMinutesPlus();
			seconds=(jobsTripTemplateDetails.get(i).getSecondsPlus() == null) ? 0 : jobsTripTemplateDetails.get(i).getSecondsPlus();
			endDate = common.dateUtils.DateUtil.addDays(startDate, days_dur, hours_dur, mins_dur, seconds_dur);			
			endDatesList.add(endDate);
			fr_Loc_Seq_no= jobsTripTemplateDetails.get(i).getFromLocationSeqNo();
			to_Loc_Seq_no= jobsTripTemplateDetails.get(i).getToLocationSeqNo();
			mode_opt = jobsTripTemplateDetails.get(i).getModeSeqNo();			
			jobTripDetails = new JobTripDetails();
			jobTripDetailsPK = new JobTripDetailsPK(jobSeqNo, mode_opt, fr_Loc_Seq_no, to_Loc_Seq_no); 
			jobTripDetails.setJob_Trip_pk(jobTripDetailsPK);
			if(opFlag==1)
			{
				jobTripDetails.setPlan_start_date(startDate);
				jobTripDetails.setPlan_end_date(endDate);
			}
			else
			{
				jobTripDetails.setAct_start_date(startDate);
				jobTripDetails.setAct_end_date(endDate);
			}
				jobTripRepo.save(jobTripDetails);
	
			startDate = common.dateUtils.DateUtil.addDays(endDate, days, hours, minutes, seconds);
		}

		Timestamp retTimestamp = Collections.max(endDatesList);
	
	return retTimestamp;	
	}
	*/
	
}