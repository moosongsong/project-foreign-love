package kr.co.foreignlove.service.attached;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.foreignlove.controller.DAOManager;
import kr.co.foreignlove.controller.attached.FileUploader;
import kr.co.foreignlove.dao.AttachedDAO;
import kr.co.foreignlove.service.Service;

public class AttachedInsertService implements Service{

	AttachedDAO dao;
	public AttachedInsertService() {
		dao = (AttachedDAO)DAOManager.getDAO(AttachedDAO.NAME);
	}
	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unused")
		FileUploader fu = new FileUploader();
		
	}
	
}
