package com.generateToken.generateToken.dto;

import com.generateToken.generateToken.entities.Doctor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clinicId;
    private String location;
    private String incharge;
    private int fees;
    private LocalTime startTime;
    private LocalTime endTime;
    private Doctor doctor;

}
