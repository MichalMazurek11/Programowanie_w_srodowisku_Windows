package helpers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import helpers.MyEmail;

public class MyEmail {

	
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	// Adres email osby która wysy³a maila
	private static final String FROM = "mazurek041198@gmail.com"; // konto stworzone do tego programu
	// Has³o do konta osoby która wysy³a maila
	private static final String PASSWORD = "?"; // ka¿dy mo¿e tu wejœæ
	// Temat wiadomoœci
	private static final String SUBJECT = "Potwierdzenie rejestracji";
	// Treœæ wiadomoœci
	private static final String CONTENT = "Dzieñ dobry. Oznajmiamy, ¿e pomyœlnie przesz³eœ etap rejestracji.";
	
	public static void main(String[] args) {
		new MyEmail().send("mazurek041198@gmail.com");
	}
	public int send(String TO) {

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");
		
		// Inicjalizacja sesji
		Session mailSession = Session.getDefaultInstance(props);

		// ustawienie debagowania, jeœli nie chcesz ogl¹daæ logów to usuñ
		// linijkê poni¿ej lub zmieñ wartoœæ na false
		mailSession.setDebug(true);

		// Tworzenie wiadomoœci email
		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setSubject(SUBJECT);
			message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	
			Transport transport = mailSession.getTransport();
			transport.connect(HOST, PORT, FROM, PASSWORD);
	
			// wys³anie wiadomoœci
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			return 1; // powodzenie
		}
		catch (MessagingException e) {
			e.printStackTrace();
			return 0; // nie powodzenie
		}
	}
	
	
	
	
	
	
	
	
    }
	
	
	
	
	
	
	

