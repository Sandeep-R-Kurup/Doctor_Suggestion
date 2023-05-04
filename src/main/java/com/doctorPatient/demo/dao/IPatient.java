package com.doctorPatient.demo.dao;

import com.doctorPatient.demo.model.patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatient extends JpaRepository<patient,Long> {
}
