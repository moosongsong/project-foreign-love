package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MemberFindPasswordService;

public class MemberFindPasswordController implements BackController{
	private MemberFindPasswordService service;
	
	public MemberFindPasswordController() {
		service = new MemberFindPasswordService();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try
		{
			out = response.getWriter();
			service.doService(request, response);
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", request.getAttribute("sendResult"));
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", request.getAttribute("sendResult"));
		}
		
		out.println(jObj.toJSONString());
	}
	
}
