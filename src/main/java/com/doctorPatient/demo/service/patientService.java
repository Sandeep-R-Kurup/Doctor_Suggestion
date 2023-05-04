package com.doctorPatient.demo.service;


import com.doctorPatient.demo.dao.IPatient;
import com.doctorPatient.demo.model.patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class patientService {

    @Autowired
    IPatient patientRepository;
    public void savePatient(patient patient) {
        patientRepository.save(patient);
    }


    public void deletePatient(Integer id) {
        patient patient=patientRepository.findById(Long.valueOf(id)).get();
        patientRepository.delete(patient);
    }
}

