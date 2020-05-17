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
	// Adres email osby kt�ra wysy�a maila
	private static final String FROM = "mazurek041198@gmail.com"; // konto stworzone do tego programu
	// Has�o do konta osoby kt�ra wysy�a maila
	private static final String PASSWORD = "?"; // ka�dy mo�e tu wej��
	// Temat wiadomo�ci
	private static final String SUBJECT = "Potwierdzenie rejestracji";
	// Tre�� wiadomo�ci
	private static final String CONTENT = "Dzie� dobry. Oznajmiamy, �e pomy�lnie przesz�e� etap rejestracji.";
	
	public static void main(String[] args) {
		new MyEmail().send("mazurek041198@gmail.com");
	}
	public int send(String TO) {

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");
		
		// Inicjalizacja sesji
		Session mailSession = Session.getDefaultInstance(props);

		// ustawienie debagowania, je�li nie chcesz ogl�da� log�w to usu�
		// linijk� poni�ej lub zmie� warto�� na false
		mailSession.setDebug(true);

		// Tworzenie wiadomo�ci email
		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setSubject(SUBJECT);
			message.setContent(CONTENT, "text/plain; charset=ISO-8859-2");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO));
	
			Transport transport = mailSession.getTransport();
			transport.connect(HOST, PORT, FROM, PASSWORD);
	
			// wys�anie wiadomo�ci
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
	
	
	
	
	
	
	

