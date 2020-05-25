package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MemberUpdateService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberUpdateController implements BackController{
	
	private MemberUpdateService service;
	
	public MemberUpdateController() {
		service = new MemberUpdateService();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			service.doService(request, response);
			MemberVO member = (MemberVO) request.getAttribute("member");
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUserInfo", member);
			
			jObj = new JSONObject();
			jObj.put("result", 0);
			jObj.put("message", member.getM_email()+"update suceess");
		} catch (Exception e) {
			jObj = new JSONObject();
			jObj.put("result", 1);
			jObj.put("message", "update fail");
		}
		
		out.println(jObj.toJSONString());
	}

}
