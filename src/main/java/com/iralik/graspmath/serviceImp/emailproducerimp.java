package com.iralik.graspmath.serviceImp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iralik.graspmath.dto.emaildto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service
public class emailproducerimp {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${spring.mail.username}")
    private String senderemail;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;



    public void sendEmail(String email, String registrant)  {
        emaildto emaildto = new emaildto();
        Map<String, String> subjectBodyMap = Map.of(
                "Registration Conformation","Thank you for choosing us, A tutor will be contacting you shortly.",
                "New Registration." + registrant , registrant +" "+"Registered to the program,\n Click the link to find all registrations and more details "+ "http://localhost:4200/submissions"
        );

        for (Map.Entry<String, String> entry : subjectBodyMap.entrySet()) {
            if(entry.getKey().equals("Registration Conformation")){
                emaildto.setBody(entry.getValue());
                emaildto.setRecipient(email);
                emaildto.setSender(senderemail);
                emaildto.setSubject(entry.getKey());
            }else{
                emaildto.setBody(entry.getValue());
                //"adevarapu@icloud.com"
                emaildto.setRecipient("adityakilari.0601@gmail.com");
                emaildto.setSender(senderemail);
                emaildto.setSubject(entry.getKey());
            }
            try{
                String dtoToJson = objectMapper.writeValueAsString(emaildto);
                rabbitTemplate.convertAndSend(exchange, entry.getKey(), dtoToJson);
            }catch (Exception e){
                e.getMessage();
            }

        }

    }
}
