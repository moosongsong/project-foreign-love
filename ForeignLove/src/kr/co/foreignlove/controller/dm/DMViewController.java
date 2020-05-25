package kr.co.foreignlove.controller.dm;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.dm.DMViewService;
import kr.co.foreignlove.vo.DMVO;

public class DMViewController implements BackController {

	private DMViewService service;
	
	public DMViewController() {
		service = new DMViewService();
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
			DMVO dm = (DMVO) request.getAttribute("dm");

			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "success");
			jObj.put("dm", dm);
			
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "fail");
			jObj.put("dm", null);
		}

		
		out.println(jObj);
	}

}
