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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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


        String[] filePaths = {
                "C:\\Users\\Vivi\\Documents\\digitalHouse\\CertifiedTechDeveloper\\ProyectoIntegrador\\equipo-02\\Back\\IntegradorMaven\\src\\main\\resources\\mail1.html",
                "C:\\Users\\Vivi\\Documents\\digitalHouse\\CertifiedTechDeveloper\\ProyectoIntegrador\\equipo-02\\Back\\IntegradorMaven\\src\\main\\resources\\mail2.html",
                "C:\\Users\\Vivi\\Documents\\digitalHouse\\CertifiedTechDeveloper\\ProyectoIntegrador\\equipo-02\\Back\\IntegradorMaven\\src\\main\\resources\\mail3.html"
        };


         String mailData1 = "  <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
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
                 "                                                                                style=\"font-size: 38px; line-height: 53.2px;\">¡Hola\n" +
                 "                                                                                <strong> "+bookingDTO.get().getUser().getName()+" </strong>!</span>&nbsp;&nbsp;</span></span>\n" +
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
                 "                                                                        reserva para <strong> "+bookingDTO.get().getDataPet().get(0)+" </strong>en\n" +
                 "                                                                        <strong> "+bookingDTO.get().getPetDayCare().getName()+" </strong>está\n" +
                 "                                                                        confirmada.</span></p>\n" +
                 "                                                                <p\n" +
                 "                                                                    style=\"font-size: 14px; line-height: 140%; text-align: center;\">\n" +
                 "                                                                    &nbsp;</p>\n" +
                 "                                                            </div>\n" +
                 "\n" +
                 "                                                        </td>\n" +
                 "                                                    </tr>\n" +
                 "                                                </tbody>\n" +
                 "                                            </table>" ;



         String mailData2 = "\n" +
                 "\n" +
                 "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                 "    <div class=\"u-row\"\n" +
                 "         style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                 "        <div\n" +
                 "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                 "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                 "\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"background-color: #ffffff;width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-16p67\"\n" +
                 "                 style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"background-color: #ffffff;height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                 "                                           border=\"0\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr>\n" +
                 "                                            <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                 "                                                align=\"center\">\n" +
                 "\n" +
                 "                                                <img align=\"center\" border=\"0\"\n" +
                 "                                                     src=\"https://share1.cloudhq-mkt3.net/d2eca185e4a2c3.png\"\n" +
                 "                                                     alt=\"Image\" title=\"Image\"\n" +
                 "                                                     style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                 "                                                     width=\"54\">\n" +
                 "\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-33p33\"\n" +
                 "                 style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <div\n" +
                 "                                            style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                 "                                        <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                 "                                            <strong><span\n" +
                 "                                                    style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">CHECK-IN</span></strong><br><span\n" +
                 "                                                style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\"> "+bookingDTO.get().getCheckIn()+" </span>\n" +
                 "                                        </p>\n" +
                 "                                    </div>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-16p67\"\n" +
                 "                 style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                 "                                           border=\"0\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr>\n" +
                 "                                            <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                 "                                                align=\"center\">\n" +
                 "\n" +
                 "                                                <img align=\"center\" border=\"0\"\n" +
                 "                                                     src=\"https://share1.cloudhq-mkt3.net/d557ff886513c7.png\"\n" +
                 "                                                     alt=\"Image\" title=\"Image\"\n" +
                 "                                                     style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                 "                                                     width=\"54\">\n" +
                 "\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-33p33\"\n" +
                 "                 style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <div\n" +
                 "                                            style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                 "                                        <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                 "                                            <strong><span\n" +
                 "                                                    style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">CHCEK-OUT</span></strong><br><span\n" +
                 "                                                style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\"> "+bookingDTO.get().getCheckOut()+" </span>\n" +
                 "                                        </p>\n" +
                 "                                    </div>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                 "        </div>\n" +
                 "    </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "\n" +
                 "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                 "    <div class=\"u-row\"\n" +
                 "         style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                 "        <div\n" +
                 "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                 "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                 "\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-100\"\n" +
                 "                 style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table height=\"0px\" align=\"center\" border=\"0\"\n" +
                 "                                           cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                 "                                           style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 0px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr style=\"vertical-align: top\">\n" +
                 "                                            <td\n" +
                 "                                                    style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                 "                                                <span>&nbsp;</span>\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                 "        </div>\n" +
                 "    </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                 "    <div class=\"u-row\"\n" +
                 "         style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                 "        <div\n" +
                 "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                 "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                 "\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-16p67\"\n" +
                 "                 style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                 "                                           border=\"0\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr>\n" +
                 "                                            <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                 "                                                align=\"center\">\n" +
                 "\n" +
                 "                                                <img align=\"center\" border=\"0\"\n" +
                 "                                                     src=\"https://share1.cloudhq-mkt3.net/bc389e3a6dc9a7.png\"\n" +
                 "                                                     alt=\"Image\" title=\"Image\"\n" +
                 "                                                     style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                 "                                                     width=\"54\">\n" +
                 "\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-33p33\"\n" +
                 "                 style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <div\n" +
                 "                                            style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                 "                                        <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                 "                                            <strong><span\n" +
                 "                                                    style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">TOTAL\n" +
                 "                                                                            A PAGAR</span></strong><br><span\n" +
                 "                                                style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\"> "+bookingDTO.get().getTotalPrice()+" </span>\n" +
                 "                                        </p>\n" +
                 "                                    </div>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"100\" style=\"width: 100px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-16p67\"\n" +
                 "                 style=\"max-width: 320px;min-width: 100px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 0px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"\n" +
                 "                                           border=\"0\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr>\n" +
                 "                                            <td style=\"padding-right: 0px;padding-left: 0px;\"\n" +
                 "                                                align=\"center\">\n" +
                 "\n" +
                 "                                                <img align=\"center\" border=\"0\"\n" +
                 "                                                     src=\"https://share1.cloudhq-mkt3.net/9e8b81938374b5.png\"\n" +
                 "                                                     alt=\"Image\" title=\"Image\"\n" +
                 "                                                     style=\"outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;clear: both;display: inline-block !important;border: none;height: auto;float: none;width: 54%;max-width: 54px;\"\n" +
                 "                                                     width=\"54\">\n" +
                 "\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"200\" style=\"width: 200px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-33p33\"\n" +
                 "                 style=\"max-width: 320px;min-width: 200px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 10px 10px 0px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <div\n" +
                 "                                            style=\"font-size: 14px; line-height: 140%; text-align: left; word-wrap: break-word;\">\n" +
                 "                                        <p style=\"font-size: 14px; line-height: 140%;\">\n" +
                 "                                            <strong><span\n" +
                 "                                                    style=\"font-family: Lato, sans-serif; font-size: 14px; line-height: 19.6px;\">UBICACIÓN</span></strong><br><span\n" +
                 "                                                style=\"font-family: Lato, sans-serif; font-size: 12px; line-height: 16.8px; color: #7e8c8d;\"> "+bookingDTO.get().getPetDayCare().getAddress()+" </span>\n" +
                 "                                        </p>\n" +
                 "                                    </div>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                 "        </div>\n" +
                 "    </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                 "    <div class=\"u-row\"\n" +
                 "         style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                 "        <div\n" +
                 "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                 "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                 "\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-100\"\n" +
                 "                 style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <table height=\"0px\" align=\"center\" border=\"0\"\n" +
                 "                                           cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
                 "                                           style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;border-top: 1px solid #BBBBBB;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                 "                                        <tbody>\n" +
                 "                                        <tr style=\"vertical-align: top\">\n" +
                 "                                            <td\n" +
                 "                                                    style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top;font-size: 0px;line-height: 0px;mso-line-height-rule: exactly;-ms-text-size-adjust: 100%;-webkit-text-size-adjust: 100%\">\n" +
                 "                                                <span>&nbsp;</span>\n" +
                 "                                            </td>\n" +
                 "                                        </tr>\n" +
                 "                                        </tbody>\n" +
                 "                                    </table>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                 "        </div>\n" +
                 "    </div>\n" +
                 "</div>\n" +
                 "\n" +
                 "\n" +
                 "\n" +
                 "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                 "    <div class=\"u-row\"\n" +
                 "         style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #ffffff;\">\n" +
                 "        <div\n" +
                 "                style=\"border-collapse: collapse;display: table;width: 100%;height: 100%;background-color: transparent;\">\n" +
                 "            <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #ffffff;\"><![endif]-->\n" +
                 "\n" +
                 "            <!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\" valign=\"top\"><![endif]-->\n" +
                 "            <div class=\"u-col u-col-100\"\n" +
                 "                 style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                 "                <div style=\"height: 100%;width: 100% !important;\">\n" +
                 "                    <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    <div\n" +
                 "                            style=\"box-sizing: border-box; height: 100%; padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;\">\n" +
                 "                        <!--<![endif]-->\n" +
                 "\n" +
                 "                        <table style=\"font-family:'Cabin',sans-serif;\" role=\"presentation\"\n" +
                 "                               cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                 "                            <tbody>\n" +
                 "                            <tr>\n" +
                 "                                <td style=\"overflow-wrap:break-word;word-break:break-word;padding:10px;font-family:'Cabin',sans-serif;\"\n" +
                 "                                    align=\"left\">\n" +
                 "\n" +
                 "                                    <h3\n" +
                 "                                            style=\"margin: 0px; color: #a278f0; line-height: 140%; text-align: center; word-wrap: break-word; font-family: 'Montserrat',sans-serif; font-size: 18px; font-weight: 400;\">\n" +
                 "                                        Codigo de reserva: <strong> "+bookingDTO.get().getIdBooking()+" </strong>\n" +
                 "                                    </h3>\n" +
                 "\n" +
                 "                                </td>\n" +
                 "                            </tr>\n" +
                 "                            </tbody>\n" +
                 "                        </table>\n" +
                 "\n" +
                 "                        <!--[if (!mso)&(!IE)]><!-->\n" +
                 "                    </div><!--<![endif]-->\n" +
                 "                </div>\n" +
                 "            </div>\n" +
                 "            <!--[if (mso)|(IE)]></td><![endif]-->\n" +
                 "            <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                 "        </div>\n" +
                 "    </div>\n" +
                 "</div>";

         String mergedHTML = mergeHTMLFiles(filePaths, mailData1, mailData2);


//        ---------------------------------------------------
        helper.setTo(emailUser);
        helper.setFrom("petplace.dh@gmail.com");
        helper.setSubject("Validación de correo electrónico para tu registro en la página web PetPlace 🐾");
        helper.setText(mergedHTML, true); // true to set content as HTML

        mail.send(message);


        return new ResponseEntity<>(true, HttpStatus.OK);



    }

    public static String mergeHTMLFiles(String[] filePaths, String mailContent1, String mailContent2) {
        StringBuilder mergedHTMLBuilder = new StringBuilder();

        String file1Content = readHTMLFile(filePaths[0]);
        mergedHTMLBuilder.append(file1Content);

        mergedHTMLBuilder.append(mailContent1);

        String file2Content = readHTMLFile(filePaths[1]);
        mergedHTMLBuilder.append(file2Content);

        mergedHTMLBuilder.append(mailContent2);


        String file3Content = readHTMLFile(filePaths[2]);
        mergedHTMLBuilder.append(file3Content);

        return mergedHTMLBuilder.toString();
    }

    public static String readHTMLFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return stringBuilder.toString();
    }

}
