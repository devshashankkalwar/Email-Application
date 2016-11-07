package com.starlabs.Application.EmailSender;

import java.io.*;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class EmailManager {

	public static boolean sendMail(String to, String sub, String bodyofEmail) {
		
		Properties props = new Properties();
		try{
			props.load(new FileInputStream(new File("application.properties")));
			} catch(FileNotFoundException e){
				e.printStackTrace();
			} catch(IOException e){
				e.printStackTrace();
			}
		
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host",props.getProperty("host"));
		props.put("mail.smtp.port", props.getProperty("port"));

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(props.getProperty("username")));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			message.setSubject(sub);
			message.setText(bodyofEmail);

			Transport.send(message);

			//System.out.println("Done");
			return true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
		

}


