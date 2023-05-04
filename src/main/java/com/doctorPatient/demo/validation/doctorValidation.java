package com.doctorPatient.demo.validation;

import com.doctorPatient.demo.utiils.commonUtills;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

public class doctorValidation {

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

        if(!json.has("doctor_name")){
            error.add("Please Add Your Name");
            return error;
        }
        if(!json.has("specialization")){
            String spec = json.getString("specialization");
            if(!commonUtills.isValidSpec(spec))
              error.add("unneeded Specialization");
        }
        return error;
    }
}
