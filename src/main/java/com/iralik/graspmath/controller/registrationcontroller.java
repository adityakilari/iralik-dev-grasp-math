package com.iralik.graspmath.controller;

import com.iralik.graspmath.dto.getstudentlistdto;
import com.iralik.graspmath.dto.registrationdto;
//import com.iralik.graspmath.serviceImp.emailserviceimp;
import com.iralik.graspmath.exceptionhandling.GMcustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.iralik.graspmath.service.registrationservice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping(value = "/gm/v1/")
@RestController
public class registrationcontroller {
    @Autowired
    private registrationservice registrationservice;
    @PostMapping(value = "/program_registration", produces = "application/json")
    public ResponseEntity<String> registration(@RequestBody registrationdto  registrationdto) throws GMcustomException {
           List<String> response =  registrationservice.studentprogramregistration(registrationdto);
        if (!response.isEmpty()) {
            throw new GMcustomException (response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    @GetMapping("/getAllCourses")
    public HashMap<String,HashMap<Float,List<getstudentlistdto>>> getAllCourse(){
        return registrationservice.getAllStudents();
    }

}
