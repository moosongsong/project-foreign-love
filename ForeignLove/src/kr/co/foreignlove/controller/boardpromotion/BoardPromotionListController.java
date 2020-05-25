package kr.co.foreignlove.controller.boardpromotion;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardpromotion.BoardPromotionListService;
import kr.co.foreignlove.vo.BoardPromotionVO;

public class BoardPromotionListController implements BackController
{
	private Service service;

	public BoardPromotionListController()
	{
		service = new BoardPromotionListService();
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
			
			int page = (Integer)request.getAttribute("page");
			String cond = (String)request.getAttribute("cond");
			String word = (String)request.getAttribute("word");
			String type = (String)request.getAttribute("type");
			int pageCount = (Integer)request.getAttribute("pageCount");
			
			ArrayList<BoardPromotionVO> boards = (ArrayList<BoardPromotionVO>)request.getAttribute("boards");

			jObj.put("page", page);
			jObj.put("cond", cond);
			jObj.put("word", word);
			jObj.put("type", type);
			jObj.put("pageCount", pageCount);
			
			JSONArray jAry = new JSONArray();
			for(BoardPromotionVO board : boards)
			{
				JSONObject jTemp = new JSONObject();
//				for(AttachedVO attached : board.getAttachedList() ) {
//					attached.convertMap();	
//					jTemp.putAll(attached.convertMap());
//					
//				}
				if(board.getAttachedList().size() != 0)
				{
					jTemp.putAll(board.getAttachedList().get(0).convertMap());					
				}
				
				jTemp.putAll(board.convertMap());
				jAry.add(jTemp);
			}
			jObj.put("boards", jAry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "게시판 목록 취득에 실패하였습니다.");
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
