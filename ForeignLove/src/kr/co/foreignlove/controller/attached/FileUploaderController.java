package kr.co.foreignlove.controller.attached;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileUploaderController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		FileUploadable uploader = new FileUploader();
		uploader.upload(request, response);
	}
}

