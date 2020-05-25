package kr.co.foreignlove.service.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.exception.member.SchoolFailException;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class MemberListService implements Service {

	public static final String SCHOOL_NAME = "s_id";
	public static final String SCHOOL_STATE = "s_state";
	public static final String TOTAL = "total";
	
	private MemberDAO dao = null;
	
	public MemberListService() {
		dao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String condition = request.getParameter("condition");

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");
		
		
		ArrayList<MemberVO> members = dao.getList(member, condition);
		
		if (members == null) {
			throw new SchoolFailException();
		}
		else {
			request.setAttribute("members", members);
		}

	}
}
