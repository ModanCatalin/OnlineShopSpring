package ro.utcn.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService
{

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String email, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("eo.rest21@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);


        mailSender.send(message);
    }

}
