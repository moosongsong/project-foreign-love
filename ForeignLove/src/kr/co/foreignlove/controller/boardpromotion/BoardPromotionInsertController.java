package kr.co.foreignlove.controller.boardpromotion;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardpromotion.BoardPromotionInsertService;
import kr.co.foreignlove.vo.BoardPromotionVO;

public class BoardPromotionInsertController implements BackController
{
	Service service;
	
	public BoardPromotionInsertController()
	{
		service = new BoardPromotionInsertService();
	}
	
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject jObj = null;
		
		try
		{
			service.doService(request, response);
			BoardPromotionVO board = (BoardPromotionVO)request.getAttribute("board");
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "게시물 등록에 성공하였습니다.");
			JSONObject jTemp = new JSONObject();
			jTemp.putAll(board.convertMap());
			jObj.put("board", jTemp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", e.getMessage());
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
