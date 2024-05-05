package com.iralik.graspmath.serviceImp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iralik.graspmath.dto.emaildto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
public class emailconsumerimp {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JavaMailSender mailsender;

    @RabbitListener(queues ={"${rabbitmq.student-queue.name}" })
    public void processEmailToStudent(String emailDtoJson) throws IOException, MessagingException {

        emaildto emaildto = objectMapper.readValue(emailDtoJson, emaildto.class);

        Resource resource = resourceLoader.getResource("classpath:templates/email_template.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        // Replace placeholders in the HTML content
        String modifiedContent = htmlContent.replace("{{messagebody_content_2}}", emaildto.getBody())
                .replace("{{heading_content}}", emaildto.getSubject());

        MimeMessage mimeMessage = mailsender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setTo(emaildto.getRecipient());
        messageHelper.setSubject(emaildto.getSubject());
        messageHelper.setText(modifiedContent, true);

        mailsender.send(mimeMessage);

    }

    @RabbitListener(queues = {"${rabbitmq.tutor-queue.name}"})
    public void processEmailToTutor(String emailDtoJson) throws IOException, MessagingException {

        emaildto emaildto = objectMapper.readValue(emailDtoJson, emaildto.class);

        Resource resource = resourceLoader.getResource("classpath:templates/email_template.html");
        InputStream inputStream = resource.getInputStream();
        String htmlContent = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        // Replace placeholders in the HTML content
        String modifiedContent = htmlContent.replace("{{messagebody_content_2}}", emaildto.getBody())
                .replace("{{heading_content}}", emaildto.getSubject());
        MimeMessage mimeMessage = mailsender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        messageHelper.setTo(emaildto.getRecipient());
        messageHelper.setSubject(emaildto.getSubject());

        messageHelper.setText(modifiedContent, true);

        mailsender.send(mimeMessage);
    }
}
