package kr.co.foreignlove.controller.member;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FindPass {
//	static final String FROM = "foreign.love.2020@gmail.com";
//    static final String FROMNAME = "foreign.love";
//    static final String TO = "songe08@gmail.com";
// 
//    static final String SMTP_USERNAME = "foreign.love.2020@gmail.com";
//    static final String SMTP_PASSWORD = "****";
//    
//    static final String HOST = "smtp.live.com";
//    static final int PORT = 25;
//    
//    static final String SUBJECT = "메일 제목";
//    
//    static final String BODY = String.join(
//        System.getProperty("line.separator"),
//        "<h1>메일 내용</h1>",
//        "<p>이 메일은 아름다운 사람이 보낸 아름다운 메일입니다!</p>."
//    );
 
    public static void main(String[] args) throws Exception {
    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    	//이메일 객체생성하기
    	Properties props = System.getProperties();
    	props.setProperty("mail.smtp.host", "smtp.gmail.com");
    	props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    	props.setProperty("mail.smtp.socketFactory.fallback", "false");
    	props.setProperty("mail.smtp.port", "465");
    	props.setProperty("mail.smtp.socketFactory.port", "465");
    	props.put("mail.smtp.auth", "true");
    	props.put("mail.debug", "true");
    	props.put("mail.store.protocol", "pop3");
    	props.put("mail.transport.protocol", "smtp");
    	final String username = "foreign.love.2020@gmail.com";//
    	final String password = "moosong980208";

    	try{
    	 Session session = Session.getDefaultInstance(props, 
    	 new Authenticator(){
    	 protected PasswordAuthentication getPasswordAuthentication() {
    	 return new PasswordAuthentication(username, password);
    	}});

    	//메세지 설정
    	Message msg = new MimeMessage(session);

    	//보내는사람 받는사람 설정
    	msg.setFrom(new InternetAddress("foreign.love.2020@gmail.com"));
    	msg.setRecipients(Message.RecipientType.TO, 
    						InternetAddress.parse("songe08@gmail.com",false));
    	msg.setSubject("제목입니다");
    	msg.setText("굈내용입니다");
    	msg.setSentDate(new Date());
    	Transport.send(msg);
    	System.out.println("발신성공!");

    	}catch (MessagingException error){ 
    		System.out.println("에러가 발생했습니다: " + error);
    	}

    }
}
