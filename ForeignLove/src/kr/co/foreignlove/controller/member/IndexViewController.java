package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.IndexViewService;

public class IndexViewController implements BackController{
	private IndexViewService service;
	
	public IndexViewController() {
		service = new IndexViewService();
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
			String m_nick = (String) request.getAttribute("nick");
			String schoolName = (String) request.getAttribute("schoolName");
			String sex = (String) request.getAttribute("sex");
			String schoolState = (String) request.getAttribute("schoolState");
			
			jObj = new JSONObject();
			jObj.put("m_nick", m_nick);
			jObj.put("schoolName",schoolName);
			jObj.put("sex", sex);
			jObj.put("schoolState",schoolState);
			jObj.put("result",true);
		} catch (Exception e) {
			jObj = new JSONObject();
			jObj.put("result",false);
		}
		
		out.println(jObj.toJSONString());
	}

}
