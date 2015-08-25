/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.mail;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 *
 * @author marino
 */
@Stateless
@LocalBean
public class MailSender {

    @Resource(name = "mail/dinamic")
    private Session maildinamic;

    @Asynchronous
    public void sendMail(String email, String subject, String body) {
        MimeMessage message = new MimeMessage(maildinamic);

        try {
            message.setSubject(subject);
            message.setRecipients(RecipientType.TO, InternetAddress.parse(email, false));
            message.setContent(body, "text/html");
            Transport.send(message);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "mail enviado correctamente");
        } catch (MessagingException ex) {
            Logger.getLogger(MailSender.class.getName()).log(Level.SEVERE, null, ex);

        } 

    }
}
