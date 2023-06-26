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


        String htmlContent = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\"\n" +
                "    xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "    <!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "  <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG/>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "  </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
                "    <title></title>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "        @media only screen and (min-width: 620px) {\n" +
                "            .u-row {\n" +
                "                width: 600px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col {\n" +
                "                vertical-align: top;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col-16p67 {\n" +
                "                width: 100.02000000000002px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col-17p33 {\n" +
                "                width: 103.97999999999998px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col-33p33 {\n" +
                "                width: 199.98px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col-82p67 {\n" +
                "                width: 496.02px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col-100 {\n" +
                "                width: 600px !important;\n" +
                "            }\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        @media (max-width: 620px) {\n" +
                "            .u-row-container {\n" +
                "                max-width: 100% !important;\n" +
                "                padding-left: 0px !important;\n" +
                "                padding-right: 0px !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row .u-col {\n" +
                "                min-width: 320px !important;\n" +
                "                max-width: 100% !important;\n" +
                "                display: block !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-row {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-col {\n" +
                "                width: 100% !important;\n" +
                "            }\n" +
                "\n" +
                "            .u-col>div {\n" +
                "                margin: 0 auto;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "\n" +
                "        table,\n" +
                "        tr,\n" +
                "        td {\n" +
                "            vertical-align: top;\n" +
                "            border-collapse: collapse;\n" +
                "        }\n" +
                "\n" +
                "        p {\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .ie-container table,\n" +
                "        .mso-container table {\n" +
                "            table-layout: fixed;\n" +
                "        }\n" +
                "\n" +
                "        * {\n" +
                "            line-height: inherit;\n" +
                "        }\n" +
                "\n" +
                "        a[x-apple-data-detectors='true'] {\n" +
                "            color: inherit !important;\n" +
                "            text-decoration: none !important;\n" +
                "        }\n" +
                "\n" +
                "        table,\n" +
                "        td {\n" +
                "            color: #000000;\n" +
                "        }\n" +
                "\n" +
                "        #u_body a {\n" +
                "            color: #0000ee;\n" +
                "            text-decoration: underline;\n" +
                "        }\n" +
                "    </style>\n" +
                "\n" +
                "\n" +
                "\n" +
                "    <!--[if !mso]><!-->\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Lato:400,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Cabin:400,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">\n" +
                "    <!--<![endif]-->\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body u_body\"\n" +
                "    style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #f9f9f9;color: #000000\">\n" +
                "    <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "    <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "    <table id=\"u_body\"\n" +
                "        style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #f9f9f9;width:100%\"\n" +
                "        cellpadding=\"0\" cellspacing=\"0\">\n" +
                "        <tbody>\n" +
                "            <tr style=\"vertical-align: top\">\n" +
                "                <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "                    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #f9f9f9;\"><![endif]-->\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"103\" style=\"background-color: #a278f0;width: 103px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-17p33\"\n" +
                "                                    style=\"max-width: 320px;min-width: 103.98px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"background-color: #a278f0;height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:9px 9px 9px 21px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                "                                                                            align=\"left\">\n" +
                "                                                                            <a href=\"http://petplace.redirectme.net\"\n" +
                "                                                                                target=\"_blank\">\n" +
                "                                                                                <img align=\"left\" border=\"0\"\n" +
                "                                                                                    src=\"https://share1.cloudhq-mkt3.net/5ab0253bd70134.png\"\n" +
                "                                                                                    alt=\"PetPlace\" title=\"PetPlace\"\n" +
                "                                                                                    style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 92%;max-width: 68.06px;\"\n" +
                "                                                                                    width=\"68.06\">\n" +
                "                                                                            </a>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"496\" style=\"background-color: #a278f0;width: 496px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-82p67\"\n" +
                "                                    style=\"max-width: 320px;min-width: 496.02px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"background-color: #a278f0;height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:34px 10px 10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <h4\n" +
                "                                                                style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: left; word-wrap: break-word; font-family: 'Montserrat',sans-serif; font-size: 18px; font-weight: 400;\">\n" +
                "                                                                Los cuidamos como en casa</h4>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: transparent;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-image: url('https://images.unsplash.com/photo-1619326229465-1942c876e17c?ixlib=rb-4.0.3&amp;ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&amp;auto=format&amp;fit=crop&amp;w=1470&amp;q=80');background-repeat: no-repeat;background-position: center center;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-image: url('https://images.unsplash.com/photo-1619326229465-1942c876e17c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80');background-repeat: no-repeat;background-position: center center;background-color: transparent;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:60px 10px 30px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p style=\"line-height: 140%; text-align: center;\"><span\n" +
                "                                                                        style=\"color: #ffffff; font-family: Lato, sans-serif; line-height: 19.6px;\"><span\n" +
                "                                                                            style=\"font-size: 48px; line-height: 67.2px;\"><span\n" +
                "                                                                                style=\"font-size: 38px; line-height: 53.2px;\">¡Hola,\n" +
                "                                                                                <strong>" + bookingDTO.get().getUser().getName() +"</strong>!</span>&nbsp;&nbsp;</span></span>\n" +
                "                                                                </p>\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                "                                                                    &nbsp;</p>\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                "                                                                    &nbsp;</p>\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                "                                                                    <span\n" +
                "                                                                        style=\"color: #ffffff; font-family: Lato, sans-serif; line-height: 25.2px; font-size: 18px;\">La\n" +
                "                                                                        reserva para <strong>"+bookingDTO.get().getDataPet().get(0)+ "</strong>en \n" +
                "                                                                        <strong>"+ bookingDTO.get().getPetDayCare().getName() + "</strong>está\n" +
                "                                                                        confirmada.</span></p>\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                "                                                                    &nbsp;</p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:30px 10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; color: #a278f0; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p\n" +
                "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                "                                                                    <span\n" +
                "                                                                        style=\"color: #a278f0; line-height: 19.6px;\"><strong><span\n" +
                "                                                                                style=\"font-family: Lato, sans-serif; font-size: 24px; line-height: 33.6px;\">¿Qué\n" +
                "                                                                                debes saber?</span></strong></span></p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"background-color: #ffffff;width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-16p67\"\n" +
                "                                    style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"background-color: #ffffff;height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                "                                                                            align=\"center\">\n" +
                "\n" +
                "                                                                            <img align=\"center\" border=\"0\"\n" +
                "                                                                                src=\"https://share1.cloudhq-mkt3.net/d2eca185e4a2c3.png\"\n" +
                "                                                                                alt=\"Image\" title=\"Image\"\n" +
                "                                                                                style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                "                                                                                width=\"54\">\n" +
                "\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-33p33\"\n" +
                "                                    style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                "                                                                    <strong><span\n" +
                "                                                                            style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">CHECK-IN</span></strong><br><span\n" +
                "                                                                        style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\">"+bookingDTO.get().getCheckIn() +"</span>\n" +
                "                                                                </p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-16p67\"\n" +
                "                                    style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                "                                                                            align=\"center\">\n" +
                "\n" +
                "                                                                            <img align=\"center\" border=\"0\"\n" +
                "                                                                                src=\"https://share1.cloudhq-mkt3.net/d557ff886513c7.png\"\n" +
                "                                                                                alt=\"Image\" title=\"Image\"\n" +
                "                                                                                style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                "                                                                                width=\"54\">\n" +
                "\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-33p33\"\n" +
                "                                    style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                "                                                                    <strong><span\n" +
                "                                                                            style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">CHECK-OUT</span></strong><br><span\n" +
                "                                                                        style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\">"+bookingDTO.get().getCheckOut() +"</span>\n" +
                "                                                                </p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table height=\"0px\" align=\"center\" border=\"0\"\n" +
                "                                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                                style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"vertical-align: top\">\n" +
                "                                                                        <td\n" +
                "                                                                            style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                            <span>&nbsp;</span>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-16p67\"\n" +
                "                                    style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                "                                                                            align=\"center\">\n" +
                "\n" +
                "                                                                            <img align=\"center\" border=\"0\"\n" +
                "                                                                                src=\"https://share1.cloudhq-mkt3.net/bc389e3a6dc9a7.png\"\n" +
                "                                                                                alt=\"Image\" title=\"Image\"\n" +
                "                                                                                style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                "                                                                                width=\"54\">\n" +
                "\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-33p33\"\n" +
                "                                    style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                "                                                                    <strong><span\n" +
                "                                                                            style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">TOTAL\n" +
                "                                                                            A PAGAR</span></strong><br><span\n" +
                "                                                                        style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\">"+bookingDTO.get().getTotalPrice()+"</span>\n" +
                "                                                                </p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-16p67\"\n" +
                "                                    style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                "                                                                border=\"0\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr>\n" +
                "                                                                        <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                "                                                                            align=\"center\">\n" +
                "\n" +
                "                                                                            <img align=\"center\" border=\"0\"\n" +
                "                                                                                src=\"https://share1.cloudhq-mkt3.net/9e8b81938374b5.png\"\n" +
                "                                                                                alt=\"Image\" title=\"Image\"\n" +
                "                                                                                style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                "                                                                                width=\"54\">\n" +
                "\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-33p33\"\n" +
                "                                    style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div\n" +
                "                                                                style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                "                                                                <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                "                                                                    <strong><span\n" +
                "                                                                            style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">UBICACIÓN</span></strong><br><span\n" +
                "                                                                        style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\">"+bookingDTO.get().getPetDayCare().getAddress()+"</span>\n" +
                "                                                                </p>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table height=\"0px\" align=\"center\" border=\"0\"\n" +
                "                                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                                style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"vertical-align: top\">\n" +
                "                                                                        <td\n" +
                "                                                                            style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                            <span>&nbsp;</span>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <h3\n" +
                "                                                                style=\"margin: 0px; color: #a278f0; line-height: 140%; text-align: center; word-wrap: break-word; font-family: 'Montserrat',sans-serif; font-size: 18px; font-weight: 400;\">\n" +
                "                                                                Codigo de reserva: <strong>"+bookingDTO.get().getIdBooking()+"</strong>\n" +
                "                                                            </h3>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <table height=\"0px\" align=\"center\" border=\"0\"\n" +
                "                                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                "                                                                style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                <tbody>\n" +
                "                                                                    <tr style=\"vertical-align: top\">\n" +
                "                                                                        <td\n" +
                "                                                                            style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                "                                                                            <span>&nbsp;</span>\n" +
                "                                                                        </td>\n" +
                "                                                                    </tr>\n" +
                "                                                                </tbody>\n" +
                "                                                            </table>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 3px 0px 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 3px 0px 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"background-color: #8ed1b9;width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"background-color: #8ed1b9;height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div align=\"center\">\n" +
                "                                                                <div style=\"display: table; max-width:170px;\">\n" +
                "                                                                    <!--[if (mso)|(IE)]><table width=\"170\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"border-collapse:collapse;\" align=\"center\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse; mso-table-lspace: 0pt;mso-table-rspace: 0pt; width:170px;\"><tr><![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\n" +
                "                                                                    <table align=\"left\" border=\"0\" cellspacing=\"0\"\n" +
                "                                                                        cellpadding=\"0\" width=\"32\" height=\"32\"\n" +
                "                                                                        style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                            <tr style=\"vertical-align: top\">\n" +
                "                                                                                <td align=\"left\" valign=\"middle\"\n" +
                "                                                                                    style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "                                                                                    <a href=\"https://www.facebook.com/PetPlaceDB\"\n" +
                "                                                                                        title=\"Facebook\"\n" +
                "                                                                                        target=\"_blank\">\n" +
                "                                                                                        <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-white/facebook.png\"\n" +
                "                                                                                            alt=\"Facebook\"\n" +
                "                                                                                            title=\"Facebook\" width=\"32\"\n" +
                "                                                                                            style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
                "                                                                                    </a>\n" +
                "                                                                                </td>\n" +
                "                                                                            </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "\n" +
                "                                                                    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 25px;\" valign=\"top\"><![endif]-->\n" +
                "                                                                    <table align=\"left\" border=\"0\" cellspacing=\"0\"\n" +
                "                                                                        cellpadding=\"0\" width=\"32\" height=\"32\"\n" +
                "                                                                        style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 25px\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                            <tr style=\"vertical-align: top\">\n" +
                "                                                                                <td align=\"left\" valign=\"middle\"\n" +
                "                                                                                    style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "                                                                                    <a href=\"https://twitter.com/PetPlacecasa\"\n" +
                "                                                                                        title=\"Twitter\" target=\"_blank\">\n" +
                "                                                                                        <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-white/twitter.png\"\n" +
                "                                                                                            alt=\"Twitter\"\n" +
                "                                                                                            title=\"Twitter\" width=\"32\"\n" +
                "                                                                                            style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
                "                                                                                    </a>\n" +
                "                                                                                </td>\n" +
                "                                                                            </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "\n" +
                "                                                                    <!--[if (mso)|(IE)]><td width=\"32\" style=\"width:32px; padding-right: 0px;\" valign=\"top\"><![endif]-->\n" +
                "                                                                    <table align=\"left\" border=\"0\" cellspacing=\"0\"\n" +
                "                                                                        cellpadding=\"0\" width=\"32\" height=\"32\"\n" +
                "                                                                        style=\"width: 32px !important;height: 32px !important;display: inline-block;border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;margin-right: 0px\">\n" +
                "                                                                        <tbody>\n" +
                "                                                                            <tr style=\"vertical-align: top\">\n" +
                "                                                                                <td align=\"left\" valign=\"middle\"\n" +
                "                                                                                    style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "                                                                                    <a href=\"https://www.instagram.com/petplacecomoencasa/\"\n" +
                "                                                                                        title=\"Instagram\"\n" +
                "                                                                                        target=\"_blank\">\n" +
                "                                                                                        <img src=\"https://cdn.tools.unlayer.com/social/icons/circle-white/instagram.png\"\n" +
                "                                                                                            alt=\"Instagram\"\n" +
                "                                                                                            title=\"Instagram\" width=\"32\"\n" +
                "                                                                                            style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: block !important;border: none;height: auto;float: none;max-width: 32px !important\">\n" +
                "                                                                                    </a>\n" +
                "                                                                                </td>\n" +
                "                                                                            </tr>\n" +
                "                                                                        </tbody>\n" +
                "                                                                    </table>\n" +
                "                                                                    <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "\n" +
                "\n" +
                "                                                                    <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                                                                </div>\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "                    <div class=\"u-row-container\" style=\"padding: 3px 0px 0px;background-color: transparent\">\n" +
                "                        <div class=\"u-row\"\n" +
                "                            style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                "                            <div\n" +
                "                                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                "                                <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 3px 0px 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                "\n" +
                "                                <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                "                                <div class=\"u-col u-col-100\"\n" +
                "                                    style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "                                    <div style=\"height: 100%;width: 100% !important;\">\n" +
                "                                        <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        <div\n" +
                "                                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                "                                            <!--<![endif]-->\n" +
                "\n" +
                "                                            <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                "                                                cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "                                                <tbody>\n" +
                "                                                    <tr>\n" +
                "                                                        <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                "                                                            align=\"left\">\n" +
                "\n" +
                "                                                            <div>\n" +
                "                                                                <div style=\"text-align: center;\">\n" +
                "                                                                    <a href=\"*|UNSUB|*\" style=\"color:#000\"><span\n" +
                "                                                                            style=\"color:#000000\">Unsubscribe</span></a><span\n" +
                "                                                                        style=\"color:#000\"> </span>\n" +
                "                                                                </div>\n" +
                "\n" +
                "                                                            </div>\n" +
                "\n" +
                "                                                        </td>\n" +
                "                                                    </tr>\n" +
                "                                                </tbody>\n" +
                "                                            </table>\n" +
                "\n" +
                "                                            <!--[if (!mso)&(!IE)]><!-->\n" +
                "                                        </div><!--<![endif]-->\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "                                <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "\n" +
                "\n" +
                "                    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "    <!--[if mso]></div><![endif]-->\n" +
                "    <!--[if IE]></div><![endif]-->\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";
        helper.setTo(emailUser);
        helper.setFrom("petplace.dh@gmail.com");
        helper.setSubject("Validación de correo electrónico para tu registro en la página web PetPlace 🐾");
        helper.setText(htmlContent, true); // true to set content as HTML

        mail.send(message);


        return new ResponseEntity<>(true, HttpStatus.OK);



    }
}
