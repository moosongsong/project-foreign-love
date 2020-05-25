package kr.co.foreignlove.service.member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.dao.MemberTypeDAO;
import kr.co.foreignlove.dao.SchoolDAO;
import kr.co.foreignlove.exception.member.MemberException;
import kr.co.foreignlove.vo.MemberTypeVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.SchoolVO;

public class MemberRegisterService implements MemberService {

	private MemberDAO dao = null;
	
	public MemberRegisterService() {
		dao  = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MemberException {
		int m_id = 0;
		
		String m_email = (String) request.getParameter("m_email")+'@'+request.getParameter("ori_email");
		String m_pass = (String) request.getParameter("m_pass");
		String m_name = (String) request.getParameter("m_name");
		String m_phone = (String) request.getParameter("m_phone");
		String m_birth = (String) request.getParameter("m_birth")+" 00:00:00";
		String m_photo = null;
		String m_nick = (String) request.getParameter("m_nick");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		String m_regDate = sdf.format(cal.getTime());
		String m_sex = (String) request.getParameter("m_sex");
		String m_startDate = ((String) request.getParameter("m_startDate"))+" 00:00:00";
		String m_addr = (String) request.getParameter("addr1")+" "+(String)request.getParameter("addr2");

		String s_id = (String) request.getParameter("m_school");
		SchoolDAO sDao = (SchoolDAO) DAOManager.getDAO("School");
		SchoolVO school = sDao.find(s_id);

		int temp = dao.dateDiff(m_startDate, m_regDate);	
		String m_type;
		
		if (temp>30) {
			m_type = "SENIOR";
		}
		else {
			m_type = "JUNIOR";
		}

		MemberTypeDAO mDao = (MemberTypeDAO) DAOManager.getDAO("MemberType");
		MemberTypeVO memberType = mDao.find(m_type);
		
		MemberVO member = new MemberVO(m_id, m_email, m_name, m_pass, m_phone, m_birth, m_photo, m_nick, m_regDate, null, m_sex, m_startDate, m_addr, school, memberType);
				
		boolean result =false;
		
		try {
			result = dao.insert(member);
			if(result) {
				request.setAttribute("member", member);
			}
			request.setAttribute("member", null);
		} catch (Exception e) {
			request.setAttribute("member", null);
		}
	}
}


