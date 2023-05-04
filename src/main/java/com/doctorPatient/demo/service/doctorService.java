package com.doctorPatient.demo.service;

import com.doctorPatient.demo.dao.IDoctor;
import com.doctorPatient.demo.dao.IPatient;
import com.doctorPatient.demo.model.doctor;
import com.doctorPatient.demo.model.patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class doctorService {

    @Autowired
    IDoctor doctorRepository;


    public void saveDoctor(doctor doctor) {
        doctorRepository.save(doctor);
    }

    public void deletedoctor(Integer id) {
        doctor doctor=doctorRepository.findById(Long.valueOf(id)).get();
        doctorRepository.delete(doctor);
    }

    public List<doctor> getalldoctors() {
        return doctorRepository.findAll();
    }
}

