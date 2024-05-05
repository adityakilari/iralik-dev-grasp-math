package com.iralik.graspmath.serviceImp;

import com.iralik.graspmath.dto.getstudentlistdto;
import com.iralik.graspmath.dto.registrationdto;
import com.iralik.graspmath.exceptionhandling.GMcustomException;
import com.iralik.graspmath.model.t_course;
import com.iralik.graspmath.model.t_registration;
import com.iralik.graspmath.service.registrationservice;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.iralik.graspmath.repository.registrationrepo;
import com.iralik.graspmath.repository.courserepo;

import java.util.*;

@Service
public class registrationserviceimp implements registrationservice {

    @Autowired
    private courserepo courserepo;
    @Autowired
    private registrationrepo registrationrepo;

//    @Autowired
//    private emailserviceimp emailService;

    @Autowired
    private emailproducerimp emailproducerimp;

    @Override
    public List<String> studentprogramregistration(registrationdto registrationdto) throws GMcustomException {
        //String message = "";
        List<String> errorMessages = new ArrayList<>();
        t_registration registration = new t_registration();

        if(registrationdto != null) {

                registration.setParentname(WordUtils.capitalizeFully(registrationdto.getParentname().trim()));
                registration.setStudentname(WordUtils.capitalizeFully(registrationdto.getStudentname().trim()));
                if(registrationdto.getEmail().trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")){
                    registration.setEmail(registrationdto.getEmail());
                }else{
                    errorMessages.add("Please provide valid and complete email");
                    //throw new GMcustomException("Please provide valid and complete email");
                }
                if(registrationdto.getPhone().trim().length() == 10){
                    if(registrationdto.getPhone().matches("^[0-9]{10}$")){
                        registration.setPhone(registrationdto.getPhone());
                    }else {
                        errorMessages.add("Please provide valid number");
                        //throw new GMcustomException("Please provide valid number");
                    }
                }else{
                    errorMessages.add("improper phone number");
                    //throw new GMcustomException("improper phone number");
                }
                registration.setSchoolname(WordUtils.capitalizeFully(registrationdto.getSchoolname().trim()));
                registration.setGrade(registrationdto.getGrade().trim());
                registration.setCourseid(registrationdto.getCourseid());
                registration.setDuration(registrationdto.getDuration());
                registration.setNeeds(registrationdto.getNeeds());
        }else{
            errorMessages.add("Please Provide all the information needed..");
        }

        if(errorMessages.size() == 0){
            try {
                registrationrepo.save(registration);
                emailproducerimp.sendEmail(registration.getEmail(), registration.getStudentname());
            }catch (Exception e){
                throw new GMcustomException(e.getMessage());
            }

            //return new ResponseEntity<>(errorMessages, HttpStatus.CREATED);

        }
        //return new ResponseEntity<>(errorMessages, HttpStatus.CREATED);
        return errorMessages;
    }

    @Override
    public HashMap<String, HashMap<Float, List<getstudentlistdto>>> getAllStudents() {
        t_course course = new t_course();
        List<t_course> allcourse = courserepo.findAll();
        List<t_registration> students = registrationrepo.findAll();

        List<getstudentlistdto> registerdStudents = registrationrepo.findAll()
                .stream()
                .map(this::getstudentinfofromTregistration)
                .toList();

        HashMap<String, HashMap<Float, List<getstudentlistdto>>> resultMap = new HashMap<>();

        for (getstudentlistdto student : registerdStudents) {
            resultMap.computeIfAbsent(student.getCoursename(),k-> new HashMap<>())
                    .computeIfAbsent(student.getDuration(), k -> new ArrayList<>())
                    .add(student);
        }

        return resultMap;
    }
    public static String findCourseNameByCourseId(List<t_course> course, Long courseId) {
        Optional<String> courseName = course.stream()
                .filter(courses-> courses.getCourseid().equals(courseId))
                .findFirst()
                .map(t_course::getCoursename);

        return courseName.orElse(null);
    }

    public getstudentlistdto getstudentinfofromTregistration(t_registration registeredStudents){
        getstudentlistdto getstudentlistdto = new getstudentlistdto();

        t_course course = new t_course();
        List<t_course> courses = courserepo.findAll();

        getstudentlistdto.setCoursename(findCourseNameByCourseId(courses, registeredStudents.getCourseid()));
        getstudentlistdto.setPhone(registeredStudents.getPhone());
        getstudentlistdto.setNeeds(registeredStudents.getNeeds());
        getstudentlistdto.setGrade(registeredStudents.getGrade());
        getstudentlistdto.setEmail(registeredStudents.getEmail());
        getstudentlistdto.setParentname(registeredStudents.getParentname());
        getstudentlistdto.setSchoolname(registeredStudents.getSchoolname());
        getstudentlistdto.setStudentname(registeredStudents.getStudentname());
        getstudentlistdto.setDuration(registeredStudents.getDuration());

        return getstudentlistdto;
    }


}
