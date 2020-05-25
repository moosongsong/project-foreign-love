package kr.co.foreignlove.controller.attached;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface FileUploadable {
	public abstract void upload(HttpServletRequest request, HttpServletResponse response);
}
