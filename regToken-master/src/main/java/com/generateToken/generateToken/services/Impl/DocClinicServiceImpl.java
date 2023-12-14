package com.generateToken.generateToken.services.Impl;

import com.generateToken.generateToken.dto.DoctorClinicDto;
import com.generateToken.generateToken.entities.Clinic;
import com.generateToken.generateToken.repositories.DocClinicRepository;
import com.generateToken.generateToken.services.DocClinicService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocClinicServiceImpl implements DocClinicService {

  @Autowired
  private DocClinicRepository docClinicRepository;
  @Override
  public String deleteRel(Long clinicId) {

       docClinicRepository.deleteClinicDoc(clinicId);
       return "deleted";
  }
}
