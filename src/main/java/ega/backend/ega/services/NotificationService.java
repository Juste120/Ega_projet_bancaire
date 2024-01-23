package ega.backend.ega.services;

import ega.backend.ega.entites.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    JavaMailSender javaMailSender;
    public   void envoyer (Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@ega.com");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour %s ,Votre code d'activation est %s; A bientot",
                validation.getUtilisateur().getNom(),
                validation.getCode()

        );

        message.setText(texte);

        javaMailSender.send(message);





    }

}
