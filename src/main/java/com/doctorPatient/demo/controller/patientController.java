package com.doctorPatient.demo.controller;

import com.doctorPatient.demo.model.doctor;
import com.doctorPatient.demo.model.patient;
import com.doctorPatient.demo.service.patientService;
import com.doctorPatient.demo.validation.patientValidation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class patientController {
    @Autowired
    patientService  patientService;

    @DeleteMapping("deletePatient")
    public void deletePatient(@RequestParam Integer id){
        patientService.deletePatient(id);
    }

    @GetMapping("suggestDoctor")
    public ResponseEntity<String> suggestDoc(@RequestParam String city,@RequestParam String symptom){
        List<doctor> doclist = new ArrayList<>();
        doclist = patientValidation.suggestDoctor(city,symptom);
        if(doclist.isEmpty()){
            return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom",HttpStatus.BAD_REQUEST);
        }else return new ResponseEntity<>(doclist.toString(),HttpStatus.OK);
    }

    @PostMapping("createPatient")
    public ResponseEntity<String> createPatient(@RequestBody String requestBody){
        JSONObject json=new JSONObject(requestBody);
        List<String> errorList=patientValidation.validateEntity(json);
        patient Patient=setPatient(json);
        if(errorList.isEmpty()){
            patientService.savePatient(Patient);
        }
        else{
            return new ResponseEntity<>(errorList.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Patient Added",HttpStatus.CREATED);
    }

    private patient setPatient(JSONObject jsonObject) {

        patient patient=new patient();
        patient.setSymptoms(jsonObject.getString("symptoms"));
        patient.setName((jsonObject.getString("patient_name")));
        patient.setCity(jsonObject.getString("city"));
        patient.setPhone(jsonObject.getString("phone_number"));
        patient.setEmail(jsonObject.getString("email"));
        return patient;
    }
}
