package kr.co.foreignlove.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.dao.MemberTypeDAO;
import kr.co.foreignlove.dao.SchoolDAO;
import kr.co.foreignlove.exception.member.MemberException;
import kr.co.foreignlove.exception.member.MemberInsertException;
import kr.co.foreignlove.vo.MemberTypeVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.SchoolVO;

public class MemberUpdateService implements MemberService{

	private MemberDAO dao = null;
	
	public MemberUpdateService() {
		dao  = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MemberException {

		String m_email = request.getParameter("m_email");
		
		String m_pass = request.getParameter("m_pass");
		String m_phone = request.getParameter("m_phone");
		String m_photo = null;
		String m_nick = request.getParameter("m_nick");
		String m_regDate = request.getParameter("m_regDate");
		String m_startDate = ((String) request.getParameter("m_startDate"))+" 00:00:00";
		String m_addr = (String) request.getParameter("addr1")+" "+(String)request.getParameter("addr2");

		String s_id = (String) request.getParameter("m_school");
		SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO(SchoolDAO.NAME);
		SchoolVO school = sDao.find(s_id);

		int temp = dao.dateDiff(m_startDate, m_regDate);
		String m_type;
		
		if (temp<31) {
			m_type = "SENIOR";
		}
		else {
			m_type = "JUNIOR";
		}

		MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
		MemberTypeVO memberType = mDao.find(m_type);
		
		MemberVO member = dao.findForEmail(m_email);
		member.setM_pass(m_pass);
		member.setM_phone(m_phone);
		member.setM_photo(m_photo);
		member.setM_nick(m_nick);
		member.setM_startDate(m_startDate);
		member.setM_addr(m_addr);
		member.setS_id(school);
		member.setM_type(memberType);
		
		boolean result = dao.update(member);
		if (result) {
			request.setAttribute("member", member);
		}
		else {
			throw new MemberInsertException();
		}
	}
}
