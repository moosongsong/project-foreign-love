package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.member.MemberNickFindService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberNickFindContoller implements BackController {

	private Service service;
	
	public MemberNickFindContoller() {
		service = new MemberNickFindService();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try
		{
			out = response.getWriter();
			service.doService(request, response);
			MemberVO member = (MemberVO) request.getAttribute("member");
			
			jObj = new JSONObject();

			if (member == null) {
				jObj.put("result", true);
				jObj.put("message", "this nick can be used");
			}
			else {
				jObj.put("result", false);
				jObj.put("message", "this nick can not be used");
			}
			
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "this nick can not be used");
		}
		
		out.println(jObj);
	}
}
