package com.doctorPatient.demo.controller;

import com.doctorPatient.demo.model.doctor;
import com.doctorPatient.demo.service.doctorService;
import com.doctorPatient.demo.validation.doctorValidation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
public class doctorController {

    @Autowired
    doctorService doctorService;


    @GetMapping("getalldoctors")
    public ResponseEntity<String> getalldoctors(){
        List<doctor> doctorList=doctorService.getalldoctors();
        return new ResponseEntity<>(doctorList.toString(), HttpStatus.OK);
    }


    @DeleteMapping("deletedoctor")
    public ResponseEntity<String> deletedoctor(@RequestParam Integer id){
        doctorService.deletedoctor(id);
        return new ResponseEntity<>("This Doctor Has Been Removed", HttpStatus.NO_CONTENT);
    }
    @PostMapping("create")
    public ResponseEntity<String> createDoctor(@RequestBody String requestBody){
        JSONObject jsonObject=new JSONObject(requestBody);
        doctor doctor=setDoctor(jsonObject);
        List<String> errorList=new ArrayList<>();
        errorList=doctorValidation.validateEntity(jsonObject);
        if(errorList.isEmpty()){
            doctorService.saveDoctor(doctor);
        }
        else{
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("Doctor Added Successfully",HttpStatus.CREATED);
    }

    private doctor setDoctor(JSONObject jsonObject) {
        doctor doctor=new doctor();
        doctor.setSpeciality(jsonObject.getString("speciality"));
        doctor.setName(jsonObject.getString("doctor_name"));
        doctor.setCity(jsonObject.getString("city"));
        doctor.setPhoneNumber(jsonObject.getString("phone_number"));
        doctor.setEmail(jsonObject.getString("email"));
        return doctor;
    }
}
