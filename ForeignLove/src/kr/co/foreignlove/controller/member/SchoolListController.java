package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.member.SchoolListService;
import kr.co.foreignlove.vo.SchoolVO;

public class SchoolListController implements BackController{
	private Service service;
	
	public SchoolListController() {
		service = new SchoolListService();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		JSONArray jAry = new JSONArray();
		
		try
		{
			out = response.getWriter();
			service.doService(request, response);
			ArrayList<SchoolVO> schools = (ArrayList<SchoolVO>) request.getAttribute("schools");
			
			for (SchoolVO schoolVO : schools) {
				JSONObject jTemp = new JSONObject();
				jTemp.putAll(schoolVO.convertMap());
				jAry.add(jTemp);
			}
			
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "school");
			jObj.put("schools", jAry);
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "school fail");
			jObj.put("schools", null);
		}	
		
		response.setContentType("application/json");
		out.println(jObj);
	}
}
