package kr.co.foreignlove.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.exception.member.MemberException;
import kr.co.foreignlove.exception.member.MemberInsertException;
import kr.co.foreignlove.service.Service;

public class MemberDeleteService implements Service{
	private MemberDAO dao = null;
	
	public MemberDeleteService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MemberException {
		String m_email = (String) request.getParameter("m_email");
		System.out.println(m_email);
		boolean result = dao.delete(m_email);
		
		if(result) {
			;
		}
		else {
			throw new MemberInsertException();
		}
	}

}
