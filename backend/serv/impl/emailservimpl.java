package com.example.cafe.serv.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.example.cafe.serv.emailserv;

@Service
public class emailservimpl implements emailserv{

	@Override
	public boolean sendEmail(String to, String sub, String mess) {
		// TODO Auto-generated method stub
		String host="smtp.gmail.com";
		String from="kamatiffins@gmail.com";
		
		Properties properties=System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		
		Authenticator auth=new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(from, "bwepvgwiarrhepwl");
			}
		};
		Session session=Session.getInstance(properties, auth);
		
		MimeMessage msg=new MimeMessage(session);
		try {
			msg.setFrom(from);
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(sub);
			msg.setText(mess);
			
			Transport.send(msg);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}

}
