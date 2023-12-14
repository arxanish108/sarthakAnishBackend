package com.generateToken.generateToken.services.Impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.generateToken.generateToken.Custome.ClinicNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generateToken.generateToken.dto.AppointmentDTOs;
import com.generateToken.generateToken.dto.ClinicDto;
import com.generateToken.generateToken.dto.DoctorDTO;
import com.generateToken.generateToken.entities.Appointment;
import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.entities.Doctor;
import com.generateToken.generateToken.repositories.AppointmentRepository;
import com.generateToken.generateToken.repositories.ClinicRepository;
import com.generateToken.generateToken.repositories.DoctorRepository;
import com.generateToken.generateToken.services.ClinicService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<AppointmentDTOs> getAppointments(Long clinicId) {

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        return clinic.getAppointmentDto();
    }

    @Override
    public List<AppointmentDTOs> getAppointmentBetweenDate(Long clinicId, LocalDate startDate, LocalDate endDate) {
        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        List<AppointmentDTOs> appointments = clinic.getAppointmentDto();

        return appointments.stream()
                .filter(appointment -> {
                    LocalDate appointmentDate = appointment.getAppointmentDate();
                    return !appointmentDate.isBefore(startDate) && !appointmentDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
//        List<AppointmentDTOs> appointmentDTOs1 = new ArrayList<>();
//        for(AppointmentDTOs appointmentDTOs : appointments){
//            if(!appointmentDTOs.getAppointmentDate().isBefore(startDate) && !appointmentDTOs.getAppointmentDate().isAfter(endDate)){
//                appointmentDTOs1.add(appointmentDTOs);
//            }
//        }
//        return appointmentDTOs1;
    }

    @Override
    public Double findAmountForClinicInDateRange(Long clinicId, java.util.Date startDate, java.util.Date endDate) {
        Optional<Clinic> clinicOptional = clinicRepository.findById(clinicId);
        System.out.println(appointmentRepository.findByAppointmentDateBetween(clinicId,startDate,endDate));
        if (clinicOptional.isPresent()) {
            Clinic clinic = clinicOptional.get();

            List<Appointment> clinicAppointments = clinic.getAppointmentList();
            double totalAmount = 0.0;

            for (Appointment appointment : clinicAppointments) {
                Date appointmentDate = Date.valueOf(appointment.getAppointmentDate());
                if (appointmentDate.after(startDate) && appointmentDate.before(endDate)) {
                    totalAmount += clinic.getFees();
                }
            }
            return totalAmount;
        } else {
            return null;
        }
    }

    public ClinicDto addClinic(Long doctorId, ClinicDto clinicDto) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        DoctorDTO doctorDTO = new DoctorDTO();

        Clinic clinic = new Clinic();
        clinic.setLocation(clinicDto.getLocation());
        clinic.setIncharge(clinicDto.getIncharge());
        clinic.setFees(clinicDto.getFees());
        clinic.setStartTime(clinicDto.getStartTime());
        clinic.setEndTime(clinicDto.getEndTime());
        clinic.setDoctor(doctor);
        clinic = clinicRepository.save(clinic);

        ClinicDto clinicDto1 = new ClinicDto();
        clinicDto1.setLocation(clinic.getLocation());
        clinicDto1.setIncharge(clinic.getIncharge());
        clinicDto1.setFees(clinic.getFees());
        clinicDto1.setStartTime(clinic.getStartTime());
        clinicDto1.setEndTime(clinic.getEndTime());

        doctor.addClinic(clinic);

        //  clinic = clinicRepository.save(clinic);
        doctor = doctorRepository.save(doctor);

        return clinicDto1;
    }

    public Optional<Clinic> getClinicById(Long clinicId) {
        return clinicRepository.findById(clinicId);
    }

//    @Override
//    public String deleteClinic(Long clinicId) {
//      clinicRepository.delClinic(clinicId);
////      System.out.println("hello");
////      Doctor doctor = doctorRepository.findById(doctor_id)
////        .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
//
////         Clinic clinic = clinicRepository.findById(clinicId)
////        .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));
////
////
////
////         Doctor doctor = clinic.getDoctor();
////         doctor.getClinics().remove(clinic);
////
////         clinic.setDoctor(null);
////         doctorRepository.save(doctor);
////         clinicRepository.delete(clinic);
//         return "deleted";
//    }

    @Override
    public ClinicDto updateClinic(Long id, ClinicDto updatedClinicDto) {
//      Doctor doctor = doctorRepository.findById(docId)
//        .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Clinic not found"));

        // Update the clinic properties with the values from updatedClinicDto
        clinic.setLocation(updatedClinicDto.getLocation());
        clinic.setIncharge(updatedClinicDto.getIncharge());
        clinic.setFees(updatedClinicDto.getFees());
        clinic.setStartTime(updatedClinicDto.getStartTime());
        clinic.setEndTime(updatedClinicDto.getEndTime());

        // Save the updated clinic entity
        clinic = clinicRepository.save(clinic);

        // Convert the updated clinic entity to ClinicDto
        ClinicDto updatedClinicDtoResponse = new ClinicDto();
        updatedClinicDtoResponse.setLocation(clinic.getLocation());
        updatedClinicDtoResponse.setIncharge(clinic.getIncharge());
        updatedClinicDtoResponse.setFees(clinic.getFees());
        updatedClinicDtoResponse.setStartTime(clinic.getStartTime());
        updatedClinicDtoResponse.setEndTime(clinic.getEndTime());

        return updatedClinicDtoResponse;
    }


}
