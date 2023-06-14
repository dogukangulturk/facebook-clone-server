package com.orcrist.facebookcloneserver.service.email;

import com.orcrist.facebookcloneserver.util.EmailDetails;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;


    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public String sendEmail(EmailDetails details){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("emailsender87@gmail.com");
        mailMessage.setTo(details.getRecipient());
        mailMessage.setText(details.getMsgBody());
        mailMessage.setSubject(details.getSubject());

        javaMailSender.send(mailMessage);
        return "Email successfully sent!";
    }

    public String sendEmailWithAttachment(EmailDetails details) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setFrom("emailsender1@gmail.com");
        helper.setTo(details.getRecipient());
        helper.setSubject(details.getSubject());
        helper.setText(details.getMsgBody(), true);
        javaMailSender.send(message);
        return "Email successfully sent. Please register your account first!";
    }
}
