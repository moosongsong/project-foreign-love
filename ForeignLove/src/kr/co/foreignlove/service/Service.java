package kr.co.foreignlove.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Service
{
	public abstract void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
