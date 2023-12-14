package com.generateToken.generateToken.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.generateToken.generateToken.dto.AppointmentDTOs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clinic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String location;
    private String incharge;
    private int fees;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Appointment> appointmentList = new ArrayList<>();

    public void addAppointmentPatient(Appointment appointment){
        this.appointmentList.add(appointment);
    }

    public List<AppointmentDTOs> getAppointmentDto(){
        List<AppointmentDTOs> appointmentDTOs = new ArrayList<>();
        for(Appointment appointment : this.appointmentList){
            appointmentDTOs.add(appointment.getAppointmentDto());
        }
        return appointmentDTOs;
    }


}
