package kr.co.foreignlove.controller.reply;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.reply.ReplyUpdateService;

public class ReplyUpdateController implements BackController
{
	Service service;
	
	public ReplyUpdateController()
	{
		service = new ReplyUpdateService();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", e.getMessage());
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		try
		{
			out = response.getWriter();
			out.print(jObj.toJSONString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
