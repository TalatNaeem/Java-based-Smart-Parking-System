package smartparkingsystem.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// There is room for simplification here...
public class MailSender {

	public static boolean initializeMail(String receiver, String subject, String alertDescription) {
		final Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream("resources/mail.properties");
			properties.load(fileInputStream);
			fileInputStream.close();

			FileOutputStream fileOutputStream = new FileOutputStream("resources/mail.properties");

			String messageBody = "Hello " + receiver
					+ "\n\nThis is an automated message sent by the Smart Parking System. This is because an alert has been raised against your vehicle. The details of the alert are as follows:\n\n"
					+ alertDescription + "\n\n";
			properties.setProperty("testmail.to", receiver);
			properties.setProperty("testmail.subject", subject);
			properties.setProperty("testmail.body", messageBody);
			properties.store(fileOutputStream, null);
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			System.err.println("Caught a FileNotFoundException Properties file not found");
		} catch (IOException e) {
			System.err.println("Error loading properties file");
		}

		return MailSender.send(properties);
	}

	public static boolean send(final Properties properties) {

		String from = properties.getProperty("testmail.from");
		String to = properties.getProperty("testmail.to");
		String subject = properties.getProperty("testmail.subject");
		String body = properties.getProperty("testmail.body");

		Session mailSession = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(properties.getProperty("mail.user"),
						properties.getProperty("mail.password"));
			}
		});
		Message simpleMessage = new MimeMessage(mailSession);

		InternetAddress fromInternetAddress = null;
		InternetAddress toInternetAddress = null;
		try {
			fromInternetAddress = new InternetAddress(from);
			toInternetAddress = new InternetAddress(to);
		} catch (AddressException e) {
			System.err.println(
					"Caught an AddressException when trying to convert the receiver address to InternetAddress");
		}

		try {
			simpleMessage.setFrom(fromInternetAddress);
			simpleMessage.setRecipient(RecipientType.TO, toInternetAddress);
			simpleMessage.setSubject(subject);
			simpleMessage.setText(body);

			Transport.send(simpleMessage);
			return true;
		} catch (MessagingException e) {
			System.err.println("Caught a MessagingException when trying to send email");
		}
		return false;
	}

}
