package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MemberDeleteService;

public class MemberDeleteController implements BackController {
	private MemberDeleteService service;
	
	public MemberDeleteController() {
		service = new MemberDeleteService();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			service.doService(request, response);
			
			jObj = new JSONObject();
			jObj.put("result", 0);
			jObj.put("message", "Å»Åð°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
		} catch (Exception e) {
			jObj = new JSONObject();
			jObj.put("result", 1);
			jObj.put("message", "Å»Åð°¡ ½ÇÆÐµÇ¾ú½À´Ï´Ù.");
		}
		out.println(jObj.toJSONString());
	}

}
