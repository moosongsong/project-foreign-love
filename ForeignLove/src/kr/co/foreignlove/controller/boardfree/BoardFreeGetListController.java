package kr.co.foreignlove.controller.boardfree;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardfree.BoardFreeGetListService;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeGetListController implements BackController{
	Service service;
	public BoardFreeGetListController() {
		service = new BoardFreeGetListService();
	}
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = null;
		PrintWriter out = null;
		try {
			service.doService(request, response);
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message", "요청이 정상적으로 처리되었습니다.");
			int page = (Integer)request.getAttribute("page");
			String cond = (String)request.getAttribute("cond");
			String word = (String)request.getAttribute("word");
			int pageCount = (Integer)request.getAttribute("pageCount");
			ArrayList<BoardFreeVO> boards = (ArrayList<BoardFreeVO>)request.getAttribute("boards");
			
			obj.put("page",page);
			obj.put("cond", cond);
			obj.put("word", word);
			obj.put("pageCount", pageCount);
			JSONArray ary = new JSONArray();
			for(BoardFreeVO board : boards) {
				JSONObject temp = new JSONObject();
				temp.putAll(board.convertMap());
				ary.add(temp);
			}
			obj.put("boards", ary);
		}
		catch(Exception e) {
			e.printStackTrace();
			obj = new JSONObject();
			obj.put("result", false);
			obj.put("message",  "게시판 목록취득에 실패하였습니다.");
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.print(obj.toJSONString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
