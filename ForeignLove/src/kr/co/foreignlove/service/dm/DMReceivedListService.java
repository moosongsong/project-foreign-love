package kr.co.foreignlove.service.dm;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.DMDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.DMVO;
import kr.co.foreignlove.vo.MemberVO;

public class DMReceivedListService implements Service {
	
	private DMDAO dao = null;
	
	public DMReceivedListService() {
		dao = (DMDAO) DAOManager.getDAO(DMDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUserInfo");
		
		ArrayList<DMVO> dms = dao.getListReceived(member.getM_id());
		
		if(dms == null) {
			throw new Exception();
		}
		else {
			request.setAttribute("dms", dms);
		}
	}

}
