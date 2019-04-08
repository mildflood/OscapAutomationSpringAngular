package org.hcqis.ventech.cloud.automation.util.mailutility;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailCommunication {

	public static void sendMail(String dest, String from, String subject, String body, boolean bodyIsHTML)
			throws MessagingException {

		// Get a Mail session
		// =================================
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "mailrelay.hcqis.org");
		props.put("mail.smtp.port", 25);
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);

		// Create a Message
		// ==================================
		Message message = new MimeMessage(session);
		message.setSubject(subject);
		if (bodyIsHTML) {
			message.setContent(body, "text/html");
		} else {
			message.setText(body);
		}

		// Address The Message
		// ==================================

		Address sourceAddress = new InternetAddress(from);
		Address destinationAddress = new InternetAddress(dest);
		message.setFrom(sourceAddress);
		message.setRecipient(Message.RecipientType.TO, destinationAddress);

		// Send The Message
		// ================================

		Transport.send(message);

	}

	

}
