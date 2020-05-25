package kr.co.foreignlove.service.dm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.DMDAO;
import kr.co.foreignlove.dao.MemberDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.DMVO;
import kr.co.foreignlove.vo.MemberVO;

public class DMInsertService implements Service{
	
	private DMDAO dao = null;
	
	public DMInsertService() {
		dao = (DMDAO) DAOManager.getDAO(DMDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO sender = (MemberVO) session.getAttribute("loginUserInfo");
		
		String email = request.getParameter("email")+"@"+request.getParameter("emailBack");
		MemberDAO mDao = (MemberDAO) DAOManager.getDAO(MemberDAO.NAME);
		MemberVO receiver = mDao.findForEmail(email);
		
		String dm_content = request.getParameter("dm_content");
		
		DMVO dm = new DMVO(0, dm_content, null, 0, receiver, sender);
		
		boolean result = dao.insert(dm);
		
		if(result) {
			request.setAttribute("dm", dm);
			System.out.println(dm);
		}else {
			throw new Exception();
		}
	}
	
	
}
