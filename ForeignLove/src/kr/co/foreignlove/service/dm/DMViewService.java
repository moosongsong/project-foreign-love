package kr.co.foreignlove.service.dm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.dao.DMDAO;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.vo.DMVO;

public class DMViewService implements Service{
	private DMDAO dao = null;
	
	public DMViewService() {
		dao = (DMDAO) DAOManager.getDAO(DMDAO.NAME);
	}
	
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int dm_id = Integer.parseInt(request.getParameter("dm_id"));
		
		DMVO dm = dao.find(dm_id);
		
		if(dm == null) {
			throw new Exception();
		}
		else {
			request.setAttribute("dm", dm);
		}
	}
}
