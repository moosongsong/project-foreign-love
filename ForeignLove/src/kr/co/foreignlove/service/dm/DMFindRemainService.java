package kr.co.foreignlove.service.dm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.DMDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.MemberVO;

public class DMFindRemainService implements Service {
	
	private DMDAO dao = null;
	
	public DMFindRemainService() {
		dao = (DMDAO) DAOManager.getDAO(DMDAO.NAME);
	}

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");
		
		int result = 0;
		
		result = dao.isAllReaded(member.getM_id());
				
		request.setAttribute("result", result);
	}

}
