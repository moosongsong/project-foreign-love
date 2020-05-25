package kr.co.foreignlove.controller.boardfree;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardfree.BoardFreeInsertService;

public class BoardFreeInsertController implements BackController{
	Service service;
	public BoardFreeInsertController() {
		service = new BoardFreeInsertService();
	}
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = null;
		try {
			service.doService(request, response);
			//BoardFreeVO board = (BoardFreeVO)request.getAttribute("board");
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message", "게시물등록이 정상적으로 처리되었습니다.");
			//obj.put("board", board.convertMap());
		}
		catch(Exception e) {
			obj = new JSONObject();
			obj.put("result", false);
			obj.put("message", e.getMessage());
		}
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json");
			out = response.getWriter();
			out.print(obj.toJSONString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
