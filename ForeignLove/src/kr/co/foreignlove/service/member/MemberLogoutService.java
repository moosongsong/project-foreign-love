package kr.co.foreignlove.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;


public class MemberLogoutService implements Service{

	@SuppressWarnings("unused")
	private MemberDAO dao = null;
	
	public MemberLogoutService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
	}
}
