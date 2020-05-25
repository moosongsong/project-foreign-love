package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.exception.member.MemberLoginFailException;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MemberFindService implements Service{
	
private MemberDAO dao = null;
	
	public MemberFindService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");
		
		String m_email = member.getM_email();
		String m_pass = request.getParameter("passtemp");
		
		System.out.println("m_email"+m_email+"m_pass"+m_pass);
		
		MemberVO res = dao.findMember(m_email, m_pass);
		System.out.println(res);

		if (res == null) {
			throw new MemberLoginFailException();
		}
		else {
			request.setAttribute("member", member);
		}
		
	}

}
