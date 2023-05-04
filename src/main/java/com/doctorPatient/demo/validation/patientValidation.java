package com.doctorPatient.demo.validation;

import com.doctorPatient.demo.dao.IDoctor;
import com.doctorPatient.demo.model.doctor;
import com.doctorPatient.demo.utiils.commonUtills;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class patientValidation {
    @Autowired
    static
    IDoctor doctorRepository;
    public static List<String> validateEntity(JSONObject json) {
        List<String> error=new ArrayList<>();

        if(json.has("email")){
            String email= json.getString("email");
            if(!commonUtills.isValidEmail(email)){
                error.add("Invalid Email");
            }
        }
        else{
            error.add("Email is Mandatory");
            return error;
        }
        if(json.has("city")){
            String city = json.getString("city");
            if(!commonUtills.isValidCity(city))
                error.add("We are still waiting to expand to your location");

        }
        else{
            error.add("city is mandatory");
            return error;
        }

        if(json.has("phone_number")){
            String number=json.getString("phone_number");
            if(commonUtills.isValidPhoneNumber(number)){
                error.add("(should be at least 10 number");
            }
        }
        else{
            error.add("PhoneNumber is mandatory");
            return error;
        }

        if(!json.has("patient_name")){
            error.add("Please Add Your Name");
            return error;
        }
        return error;
    }
    public static List<doctor> suggestDoctor(@RequestParam String city ,@RequestParam String Symptom ){
        List<doctor> doctorList = new ArrayList<>();
          switch (Symptom) {
              case "Arthritis", "Back Pain", " Tissue Injuries" -> {
                  List<doctor> list = doctorRepository.findBySymptoms(city, "Orthopedic");
                  doctorList = list;
              }
              case "Dysmenorrhea" -> {
                  List<doctor> list = doctorRepository.findBySymptoms(city, "Gynecology");
                  doctorList = list;
              }
              case "Skin infection", "Skin Burn" -> {
                  List<doctor> list = doctorRepository.findBySymptoms(city, "Dermatology");
                  doctorList = list;
              }
              case "Ear Pain" -> {
                  List<doctor> list = doctorRepository.findBySymptoms(city, "ENT");
                  doctorList = list;
              }
          }
          return doctorList;
    }
}
