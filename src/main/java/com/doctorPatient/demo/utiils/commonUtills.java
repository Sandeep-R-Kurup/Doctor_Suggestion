package com.doctorPatient.demo.utiils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class commonUtills {
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(phoneNumber);
        return (m.find() && m.group().equals(phoneNumber));
    }
    public static boolean isValidCity(String city){
        return (cities.contains(city));
    }
    public static boolean isValidSpec(String spec){
        return (specialization.contains(spec));
    }

    static List<String> cities = new ArrayList<>();
    static List<String> specialization = new ArrayList<>();

    static{
        cities.add("Dehli");
        cities.add("Noida");
        cities.add("Faridabad");
        specialization.add("Orthopedic");
        specialization.add("Gynecology");
        specialization.add("Dermatology");
        specialization.add("ENT");
    }
}
