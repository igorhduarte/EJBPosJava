package br.com.rp.services.proposta;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PropostaEnvioEmail {

	@Resource(name = "java:jboss/mail/gmail")
    private Session session;
 
    public void send(String addresses, String topic, String textMessage) {
 
        try {
 
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);
 
            Transport.send(message);
 
        } catch (MessagingException e) {
            Logger.getLogger(PropostaEnvioEmail.class.getName()).log(Level.WARNING, "Erro ao enviar email para " + addresses, e);
        }
    }
    
}
