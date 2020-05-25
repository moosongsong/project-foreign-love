package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.member.MemberFindService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberFindController implements BackController {
	private Service service;
	
	public MemberFindController() {
		service = new MemberFindService();
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
			MemberVO member = (MemberVO) request.getAttribute("member");
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", member.getM_nick()+"님, 확인되었습니다.");

		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "회원 확인에 실패하였습니다.");
		}
		
		out.println(jObj.toJSONString());
	}
}
