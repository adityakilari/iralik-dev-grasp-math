package com.iralik.graspmath.service;

import com.iralik.graspmath.dto.getstudentlistdto;
import com.iralik.graspmath.dto.registrationdto;
import com.iralik.graspmath.exceptionhandling.GMcustomException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface registrationservice {
    public List<String> studentprogramregistration(registrationdto registrationdto) throws GMcustomException;
    public HashMap<String, HashMap<Float,List<getstudentlistdto>>> getAllStudents();
}
