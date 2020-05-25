package kr.co.foreignlove.controller.boardfree;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardfree.BoardFreeUpdateService;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeUpdateController implements BackController{
	Service service;
	public BoardFreeUpdateController() {
		service = new BoardFreeUpdateService();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = null;
		try {
			service.doService(request, response);
			@SuppressWarnings("unused")
			BoardFreeVO board =(BoardFreeVO)request.getAttribute("board");
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message", "게시물수정이 정상적으로 처리되었습니다.");
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
