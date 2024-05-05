package com.iralik.graspmath.serviceImp;

import com.iralik.graspmath.dto.registrationdto;



//TODo need to restructure
public class servicevalidation {

    public String regitrationerror(registrationdto registrationdto){
        String error = "";

//        if(registrationdto.getParentname().trim() == null){
//                error = "parent name is mandatory";
//            } else if (registrationdto.getStudentname().trim() == null) {
//                error += "student name is mandatory";
//            } else if (registrationdto.getEmail().trim() == null) {
//                error += "email is mandatory";
//            }else if(registrationdto.getPhone().trim() == null || registrationdto.getPhone().trim().length() > 10){
//                error += "phone number is mandatory and should not be more than 10 digits";
//        } else if (registrationdto.getGrade().trim() == null) {
//            error += "grade is mandatory";
//
//        }


        return error;
    }



}
