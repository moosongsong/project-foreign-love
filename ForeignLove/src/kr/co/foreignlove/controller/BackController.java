package kr.co.foreignlove.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BackController
{
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);
}
