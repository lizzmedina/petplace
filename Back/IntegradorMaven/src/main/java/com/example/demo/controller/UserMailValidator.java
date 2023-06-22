package com.example.demo.controller;

import com.example.demo.DTO.BookingDTO;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/mail")
public class UserMailValidator {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mail;
    private BookingService bookingService;
    @Autowired
    public UserMailValidator(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/send/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String emailUser) throws MessagingException {

        MimeMessage message = mail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

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

    @PostMapping("/send/{email}/idBooking/{id}")
    public ResponseEntity<?> sendMailBooking(@PathVariable("email") String emailUser, @PathVariable("id") Integer id) throws MessagingException {

        Optional<Booking> bookingDTO = bookingService.findById(id);

        MimeMessage message = mail.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset='UTF-8'>\n" +
                "    <title>Reserva PetPlace</title>\n" +
                "</head>\n" +
                "<body>\n" +

                "    <h1>¡Gracias, " + bookingDTO.get().getUser().getName() +"! Tu reserva en "+ bookingDTO.get().getPetDayCare().getName() +" está confirmado. 🐶🐱</h1>\n" +
                "     <img alt='Dog_vacation' src='https://bucket-equipo2-frontend-imagenes.s3.us-east-2.amazonaws.com/ImagenesPetDayCare/25.+Hairy+Paw/httpstinyurl.com3ttyapzy.jpg' style='display:block' width='144' height='144' border='0' data-image-whitelisted='' class='CToWUd' data-bit='iit'>\n" +
                "    <p> Codigo reserva: "+bookingDTO.get().getIdBooking()+"\n" +
                "    <p> Check in: "+bookingDTO.get().getCheckIn() +"\n" +
                "    <p> Check in: "+bookingDTO.get().getCheckOut() +"\n" +
                "    <p> Huesped: "+bookingDTO.get().getDataPet().get(0)+"\n" +
                "    <p> Total a pagar: "+bookingDTO.get().getTotalPrice()+"\n" +
                "    <img alt='PetPlace' src='https://bucket-equipo2-frontend-imagenes.s3.us-east-2.amazonaws.com/Logo/LogoPP.png' style='display:block' width='144' height='144' border='0' data-image-whitelisted='' class='CToWUd' data-bit='iit'>\n" +
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
