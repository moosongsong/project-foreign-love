package kr.co.foreignlove.controller.dm;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.dm.DMSendedListService;
import kr.co.foreignlove.vo.DMVO;

public class DMSendedListController implements BackController {
	
	private DMSendedListService service;
	
	public DMSendedListController() {
		service = new DMSendedListService();
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
			ArrayList<DMVO> dms = (ArrayList<DMVO>) request.getAttribute("dms");
			
			for (DMVO dmvo : dms) {
				JSONObject jTemp = new JSONObject(dmvo.convertMap());
				jAry.add(jTemp);
			}
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "success");
			jObj.put("dms", jAry);
			
			
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "fail");
			jObj.put("dms", null);
		}

		
		out.println(jObj);
	}
	
}
