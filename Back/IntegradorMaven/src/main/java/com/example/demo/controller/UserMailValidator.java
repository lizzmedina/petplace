package com.example.demo.controller;

import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mail")
public class UserMailValidator {
    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mail;

    @PostMapping("/send/{email}")
    public ResponseEntity<?> sendMail(@PathVariable("email") String emailUser){

        SimpleMailMessage email = new SimpleMailMessage();

        email.setTo(emailUser);
        email.setFrom("petplace.dh@gmail.com");
        email.setSubject("Validación de correo electrónico para tu registro en la página web PetPlace \uD83D\uDC3E");
        email.setText("Bienvenido!  \uD83D\uDC36\uD83D\uDC31,\n" +
                "\n" +
                "¡Te damos la bienvenida a PetPlace, la comunidad en línea para amantes de las mascotas! Para completar tu registro y asegurarnos de que tu dirección de correo electrónico sea válida, necesitamos que verifiques tu cuenta.\n" +
                "\n" +
                "Sigue los pasos a continuación para completar el proceso de validación: \n" +
                "\n" +
                "Haz clic en el siguiente enlace de verificación: [http://127.0.0.1:5173/validation]\n" +
                "\n" +
                "Se abrirá una página de confirmación donde podrás verificar tu dirección de correo electrónico.\n" +
                "\n" +
                "Si no puedes hacer clic en el enlace de verificación, copia y pega la URL completa en la barra de direcciones de tu navegador.\n" +
                "\n" +
                "Una vez que hayas verificado tu dirección de correo electrónico, tendrás acceso completo a todas las funciones y características de PetPlace.");


        mail.send(email);


      return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
