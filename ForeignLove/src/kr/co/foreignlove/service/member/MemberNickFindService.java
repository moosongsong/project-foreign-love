package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MemberNickFindService implements Service{
	private MemberDAO dao = null;
	
	public MemberNickFindService() {
		dao = (MemberDAO) DAOManager.getDAO("Member");
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String m_nick = request.getParameter("m_nick");
		MemberVO member = dao.findForNick(m_nick);
		request.setAttribute("member", member);
	}
}
