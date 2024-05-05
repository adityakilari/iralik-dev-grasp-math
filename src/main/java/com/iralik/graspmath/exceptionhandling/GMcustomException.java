package com.iralik.graspmath.exceptionhandling;

import java.util.List;

public class GMcustomException extends Exception {

    private List<String> registrationErrorMessages;
    public GMcustomException(List<String> registrationErrorMessages){
        this.registrationErrorMessages = registrationErrorMessages;
    }
    public GMcustomException(String message){
        super(message);
    }
    public List<String> getRegistrationErrorMessages() {
        return registrationErrorMessages;
    }



}
