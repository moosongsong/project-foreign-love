package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MemberEmailFindService implements Service{
	private MemberDAO dao = null;
	
	public MemberEmailFindService() {
		dao = (MemberDAO) DAOManager.getDAO("Member");
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String m_email = request.getParameter("m_email")+"@"+request.getParameter("ori_email");
		MemberVO member = dao.findForEmail(m_email);
		request.setAttribute("member", member);
	}

}
