package kr.co.foreignlove.controller.dm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.dm.DMFindRemainService;

public class DMFindRemainController implements BackController{
	
	private DMFindRemainService service;
	
	public DMFindRemainController() {
		service = new DMFindRemainService();
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
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "success");
			jObj.put("num", request.getAttribute("result"));
			
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "fail");
			jObj.put("num", 0);
		}

		
		out.println(jObj);
	}
	
}
