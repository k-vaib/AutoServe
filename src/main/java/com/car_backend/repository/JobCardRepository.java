package com.car_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car_backend.entities.Appointment;
import com.car_backend.entities.JobCard;
import com.car_backend.entities.JobCardStatus;
import com.car_backend.entities.User;


public interface JobCardRepository extends JpaRepository<JobCard, Long> {

	boolean existsByAppointmentId(Long appointmentId);
	
	JobCard findByAppointment(Appointment appointment);
	
	List<JobCard> findByManager(User manager);
	
	List<JobCard> findByMechanic(User mechanic);
	
	List<JobCard> findByJobCardStatus(JobCardStatus jobCardStatus);
	
	List<JobCard> findByManagerIdAndJobCardStatus(Long managerId, JobCardStatus status);
	
	List<JobCard> findByMechanicIdAndJobCardStatus(Long mechanicId, JobCardStatus status);
	
	

}
