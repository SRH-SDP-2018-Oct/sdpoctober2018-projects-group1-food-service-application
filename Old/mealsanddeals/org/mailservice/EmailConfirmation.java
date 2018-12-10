package mealsanddeals.org.mailservice;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.mail.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.*;



public class EmailConfirmation {
	
	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for TLS/STARTTLS: 587
	 */
	public static void main(String[] args) {
		EmailConfirmation emailApp = new EmailConfirmation();
		emailApp.confirmCode(emailApp.generateCode());
	}
	
	public String generateCode() {
		
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 6) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;

	}
	public void sendRegistrationCodeMail () {
		
	}
	
	public void confirmCode(String mailCode) {
		Scanner scanner = new Scanner(System.in);
		String bodyValidateMsg = null;
		String bodyConfirmMsg = null;
		String enterCode = null;
		String validSubject = null;
		String confirmSubject = null;
		bodyValidateMsg = "Enter your below verification code: "+mailCode
		+"\n\n\n\n Note: Please dont reply to this mail."; // can be any email id
		bodyConfirmMsg = "Congratulation you are verfied, Welcome to the Meals and Deals "
				+ "\n\n\n\n Note: Please dont reply to this mail.";
		validSubject = "Please enter the code for verification";
		confirmSubject = "Congratulations you are now active";
		
		        
        try {
        	emailAuthentacition(bodyValidateMsg, validSubject);
    		System.out.print("Enter the verification code: ");
            enterCode = scanner.nextLine();           
        	    		
    		if (enterCode.equals(mailCode)) {
    			emailAuthentacition(bodyConfirmMsg, confirmSubject);
    			
            }else {
        		confirmCode(generateCode());
            }
    		
		} catch (Exception ex) {
			ex.printStackTrace();
			System.err.println(ex.getMessage());
		}
        
		
		
		 scanner.close();
		 
	}
	
	public void emailAuthentacition (String bodyMsg, String subjectMsg) {
		final String fromEmail = "mealsanddeals4u@gmail.com"; //requires valid gmail id
		final String password = "Meals&Deals4u"; // correct password for gmail id
		final String toEmail = "hussainmuddassir297@gmail.com"; // can be any email id 
		final String subject = subjectMsg; // Mail subject
		final String bodyMessage = bodyMsg;
		System.out.println("TLSEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.port", "587"); //TLS Port
		props.put("mail.smtp.auth", "true"); //enable authentication
		props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
		
             //create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, auth);
		
		sendEmail(session, toEmail,subject, bodyMessage);
	}
	
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("example@gmail.com", "Meals And Deals"));

	      msg.setReplyTo(InternetAddress.parse("example@gmail.com", false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setText(body, "UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      System.out.println("Message is ready");
    	  Transport.send(msg);  

	      System.out.println("EMail Sent Successfully!!");
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}

}



