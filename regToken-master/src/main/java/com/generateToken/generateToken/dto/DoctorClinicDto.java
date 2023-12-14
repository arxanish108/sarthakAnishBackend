package com.generateToken.generateToken.dto;

import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorClinicDto {

  private Doctor doctor;
  private Clinic clinic;

  //private Long id;
}
