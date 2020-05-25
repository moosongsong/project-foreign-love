package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MemberListService;
import kr.co.foreignlove.vo.MemberVO;

public class MemberListController implements BackController {
	
	private MemberListService service;
	
	public MemberListController() {
		service = new MemberListService();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");	
		response.setContentType("application/json");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		JSONArray jAry = new JSONArray();
		
		try
		{
			
			out = response.getWriter();
			service.doService(request, response);
			ArrayList<MemberVO> members = (ArrayList<MemberVO>) request.getAttribute("members");
			
			for (MemberVO memberVO : members) {
				JSONObject jTemp = new JSONObject(memberVO.convertMap());
				jAry.add(jTemp);
			}
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "member");
			jObj.put("members", jAry);
			
			
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "member fail");
			jObj.put("members", null);
		}

		
		out.println(jObj);
	}

}
