package com.moxos.uab.domain.entity.siiga;

import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.*;

/**
 * A simple email sender class.
 */
public class EnviarCorreo {

    // private String smtp_server;

    private String to;
    private String subject;
    private String body;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setEnviarCorreo() {
        try {
            String pieCorreo = "\n.\n Este mensaje es generado electronicamente, no lo responda!.";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", "localhost"); // cambiar localhost por getSmtp_server() si utiliza esa variable
            Session session = Session.getDefaultInstance(props, null);

            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress("wayka@coimata.bo"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getTo(), false));

            msg.setSubject(getSubject());
            msg.setText(getBody() + pieCorreo);

            msg.setHeader("X-Mailer", "LOTONtechEmail");

            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}