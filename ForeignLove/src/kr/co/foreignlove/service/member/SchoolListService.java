package kr.co.foreignlove.service.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.SchoolDAO;
import kr.co.foreignlove.exception.member.SchoolFailException;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.SchoolVO;

public class SchoolListService implements Service{
	private SchoolDAO dao = null;
	
	public SchoolListService() {
		dao = (SchoolDAO) DAOManager.getDAO(SchoolDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String m_school = request.getParameter("m_school");
		ArrayList<SchoolVO> schools = dao.searchForRegister(m_school);

		if (schools == null) {
			throw new SchoolFailException();
		}
		else {
			request.setAttribute("schools", schools);
		}

	}
}
