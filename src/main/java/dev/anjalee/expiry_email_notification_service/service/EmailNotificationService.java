package dev.anjalee.expiry_email_notification_service.service;

import dev.anjalee.expiry_email_notification_service.dtos.EmailEventDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailWithAttachment(EmailEventDTO emailEventDTO) {
        try{
            File attachmentfile= new File(emailEventDTO.getAttachmentPath());
            if(!attachmentfile.exists()) {
                throw new RuntimeException("Attachment file not found at path: " + emailEventDTO.getAttachmentPath());
            }
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailEventDTO.getTo());
            helper.setSubject(emailEventDTO.getSubject());
            helper.setText(emailEventDTO.getBody());
            FileSystemResource fileResource = new FileSystemResource(attachmentfile);
            helper.addAttachment(fileResource.getFilename(), fileResource);
            mailSender.send(message);
            System.out.println("Email sent successfully to: " + emailEventDTO.getTo());

        }catch(MessagingException e){
            throw new RuntimeException("Failed to send email: " + e);
        }

    }
}
