package kr.co.foreignlove.service.member;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MemberFindPasswordService implements Service{
	private MemberDAO dao = null;
	
	public MemberFindPasswordService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String m_email = request.getParameter("m_email");
		MemberVO member = dao.findForEmail(m_email);
		
		if (member == null) {
			request.setAttribute("sendResult", "없는 회원입니다.");
		}
		
		//////////////////////////////////////////////////////
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        // a-z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        // A-Z
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        // 0-9
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		String str = temp.toString();
		///////////////////////////////////////////////////
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

		try {
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

    	//메세지 설정
    	Message msg = new MimeMessage(session);

    	//보내는사람 받는사람 설정
    	msg.setFrom(new InternetAddress("foreign.love.2020@gmail.com"));
    	msg.setRecipients(Message.RecipientType.TO, 
//    						InternetAddress.parse(m_email,false));
    			InternetAddress.parse("songe08@gmail.com",false));
    	msg.setSubject("임시 비밀번호입니다.");
    	msg.setText(str);
    	msg.setSentDate(new Date());
    	Transport.send(msg);

    	}catch (MessagingException error){ 
    		System.out.println("에러가 발생했습니다: " + error);
    	}
		
//		if (dao.resetPassword(member, str)) {
//			request.setAttribute("sendResult", "발신성공");
//		}else {
//			request.setAttribute("sendResult", "에러발생");
//		}
		request.setAttribute("sendResult", "발신성공");
	}

}
