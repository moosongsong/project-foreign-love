package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.SchoolVO;

public class IndexViewService implements Service {
	@SuppressWarnings("unused")
	private MemberDAO dao = null;
	
	public IndexViewService() {
		dao = (MemberDAO) DAOManager.getDAO("Member");
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");
		
		String m_nick = member.getM_nick();
		SchoolVO s_id = member.getS_id();
		String m_sex = member.getM_sex();
		String state = s_id.getS_state();
				
		request.setAttribute("nick", m_nick);
		request.setAttribute("schoolName", s_id.getS_name());
		request.setAttribute("sex", m_sex);
		request.setAttribute("schoolState", state);
	}

}
