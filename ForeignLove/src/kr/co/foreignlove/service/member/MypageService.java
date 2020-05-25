package kr.co.foreignlove.service.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MypageService implements Service {
	@SuppressWarnings("unused")
	private MemberDAO dao = null;
	
	public MypageService() {
		dao = (MemberDAO) DAOManager.getDAO("Member");
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");

		request.setAttribute("member", member);
	}
}
