package com.generateToken.generateToken.controllers;

import com.generateToken.generateToken.dto.DoctorClinicDto;
import com.generateToken.generateToken.services.DocClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doc")
public class DocClinicController {

  @Autowired
  private DocClinicService docClinicService;

  @RequestMapping("/del")
  public String deleteRel(@RequestParam Long id){
    String mess = docClinicService.deleteRel(id);
    return mess;
  }

}
