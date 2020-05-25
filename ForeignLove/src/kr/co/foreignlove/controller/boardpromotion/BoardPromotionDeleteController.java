package kr.co.foreignlove.controller.boardpromotion;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardpromotion.BoardPromotionDeleteService;

public class BoardPromotionDeleteController implements BackController
{
	Service service;
	
	public BoardPromotionDeleteController()
	{
		service = new BoardPromotionDeleteService();
	}
	
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject jObj = null;
		
		try
		{
			service.doService(request, response);
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "요청이 정상적으로 처리되었습니다.");
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "게시글 삭제에 실패하였습니다.");
		}
		
		PrintWriter out = null;
		
		try
		{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.print(jObj.toJSONString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
