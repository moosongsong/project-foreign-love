package kr.co.foreignlove.controller.likey;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.likey.LikeyCountService;
import kr.co.foreignlove.vo.LikeyCountVO;

public class LikeyCountController implements BackController
{
	Service service;
	
	public LikeyCountController()
	{
		service = new LikeyCountService();
	}
	
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try
		{
			service.doService(request, response);
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "요청이 정상적으로 처리되었습니다.");
			
			LikeyCountVO likeyCount = (LikeyCountVO)request.getAttribute("likeyCount");
			jObj.put("likeyCount", likeyCount.convertMap());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", e.getMessage());
		}
		
		try
		{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.print(jObj);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
