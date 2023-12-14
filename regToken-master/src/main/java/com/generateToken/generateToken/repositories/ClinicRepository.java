package com.generateToken.generateToken.repositories;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.entities.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static jakarta.ws.rs.HttpMethod.DELETE;
import static org.hibernate.sql.ast.Clause.WHERE;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic,Long> {
   // List<AppointmentDTOs> findByAppointmentDateBetween(Long clinicId, LocalDate startDate, LocalDate endDate);

//   @Query(value = "DELETE FROM users_clinics WHERE clinics_id = :clinicId",nativeQuery = true)
//   public void deleteClinicDoc(Long clinicId);

//  @Modifying
//  @Query(value = "DELETE FROM users_clinics WHERE clinics_id = :clinicId", nativeQuery = true)
//  public void deleteClinicDoc(@Param("clinicId") Long clinicId);

}
