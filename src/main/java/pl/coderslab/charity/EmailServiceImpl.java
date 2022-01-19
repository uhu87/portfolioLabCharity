package pl.coderslab.charity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.Donation;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.validation.constraints.Email;

@Component
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Mail sent");

    }

    public void sendSummaryMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
        System.out.println("Mail sent");

    }

  /*  public void sendMimeMessage(
            String to, String subject, String text) {

        MimeMessage message = new MimeMessage();
        message.setFrom("noreply@baeldung.com");
        message.addRecipients(Message.RecipientType.TO,to);
        message.setSubject(subject);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(text, "utf-8", "html"); //here the email template is inputted
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);

        mailSender.send(message);*/


}
