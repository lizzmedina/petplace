package com.example.demo.controller;

import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/v1/mail")
public class UserMailValidator {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mail;

    @PostMapping("/send/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String emailUser) throws MessagingException {

        MimeMessage message = mail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        System.out.println(emailUser);
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <title>Bienvenido a PetPlace</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Bienvenido! 🐶🐱</h1>\n" +
                "    <p>¡Te damos la bienvenida a PetPlace, la comunidad en línea para amantes de las mascotas! Para completar tu registro y asegurarnos de que tu dirección de correo electrónico sea válida, necesitamos que verifiques tu cuenta.</p>\n" +
                "    <p>Sigue los pasos a continuación para completar el proceso de validación:</p>\n" +
                "    <a href='http://bucket-equipo2-frontend.s3-website.us-east-2.amazonaws.com/validationPage/?email=" +  emailUser + "' target='_blank' style='color: blue;'>Haz clic para verificar tu cuenta</a>\n" +
                "    <p>Una vez que hayas verificado tu dirección de correo electrónico, tendrás acceso completo a todas las funciones y características de PetPlace.</p>\n" +
                "    <p>Gracias por registrarte.</p>\n" +
                "    <p>PetPlace 🐾</p>\n" +
                "    <p>Los cuidamos como en casa.</p>\n" +
                "</body>\n" +
                "</html>";
        helper.setTo(emailUser);
        helper.setFrom("petplace.dh@gmail.com");
        helper.setSubject("Validación de correo electrónico para tu registro en la página web PetPlace 🐾");
        helper.setText(htmlContent, true); // true to set content as HTML

        mail.send(message);


        return new ResponseEntity<>(true, HttpStatus.OK);

    }
}
