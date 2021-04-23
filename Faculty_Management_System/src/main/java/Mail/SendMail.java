package Mail;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.mail.SimpleMailMessage;

import Model.User;

public class SendMail {

	public void sendmail(String contextPath, Locale locale, String token, User user) throws AddressException, MessagingException, IOException {
		System.out.println(user.getEmail());
		String path = contextPath==""?"http://localhost:8080":contextPath;
		String url = path + "/resetPassword?token=" + token;
	    String message = "<p>Hello,</p>"
	    		          + "<p>You have requested to reset your password.</p>"
	    		          + "<p>Click the link below to change your password:</p>"
	    		          + "<p><a href=\"" + url + "\">Change my password</a></p>"
	    		          + "<br>"
	                      + "<p>Ignore this email if you do remember your password, "
	    		          + "or you have not made the request.</p>";

	    //constructEmail("Reset Password", message + " \r\n" + url, user);
	    
		   Properties props = new Properties();
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");
		   
		   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("princecr7singh@gmail.com", "princesingh123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("princecr7singh@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		   msg.setSubject("Reset Password");
		   msg.setContent(message, "text/html");
		   System.out.println(msg.getContent());
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setContent("Medi-Caps University", "text/html");

		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		   /*attachPart.attachFile("https://www.google.com/imgres?imgurl=https%3A%2F%2Fimages.shiksha.com%2Fmediadata%2Fimages%2F1582874545phpgsvfWb.jpeg&imgrefurl=https%3A%2F%2Fwww.shiksha.com%2Funiversity%2Fmedi-caps-university-indore-49140&tbnid=HYh3FeowdiH3jM&vet=12ahUKEwiQmYiXtZ7tAhU_n0sFHVozDIwQMygAegUIARCwAQ..i&docid=BKsuynYX6WO0FM&w=5951&h=2379&q=medicaps%20image&ved=2ahUKEwiQmYiXtZ7tAhU_n0sFHVozDIwQMygAegUIARCwAQ");
		   multipart.addBodyPart(attachPart);
		   msg.setContent(multipart);*/
		   System.out.println("how");
		   Transport.send(msg);   
		}
	
	/*private SimpleMailMessage constructResetTokenEmail(
			  String contextPath, Locale locale, String token, User user) {
			    String url = contextPath + "/user/changePassword?token=" + token;
			    String message = "Click the below link to Reset the password";
			    return constructEmail("Reset Password", message + " \r\n" + url, user);
			}*/
			 
			private static SimpleMailMessage constructEmail(String subject, String body, 
			  User user) {
			    SimpleMailMessage email = new SimpleMailMessage();
			    email.setSubject(subject);
			    email.setText(body);
			    email.setTo(user.getEmail());
			    email.setFrom("princecr7singh@gmail.com");
			    return email;
			}
}
