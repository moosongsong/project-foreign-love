package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.exception.member.MemberLoginFailException;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;


public class MemberLoginService implements Service{
	private MemberDAO dao = null;
	
	public MemberLoginService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("utf-8");
		
		String m_email = request.getParameter("m_email");
		String m_pass = request.getParameter("m_pass");

		MemberVO member = dao.findMember(m_email, m_pass);

		System.out.println("sd");
		if (member == null || (!(member.getM_remDate()==null))) {
			System.out.println("null");
			throw new MemberLoginFailException();
		}
		else {
			System.out.println("yes");
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(60*60);
			session.setAttribute("loginUserInfo", member);
			request.setAttribute("member", member);
		}
		
	}
}
