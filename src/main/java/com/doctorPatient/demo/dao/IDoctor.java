package com.doctorPatient.demo.dao;

import com.doctorPatient.demo.model.doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IDoctor extends JpaRepository<doctor,Long> {
    @Query(value = "select * from doctor_table where city = :City && speciality = :specialisation", nativeQuery = true)
    public List<doctor> findBySymptoms(String City, String specialisation);
}
