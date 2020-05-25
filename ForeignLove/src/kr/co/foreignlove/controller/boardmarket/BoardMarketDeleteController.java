package kr.co.foreignlove.controller.boardmarket;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardmarket.BoardMarketDeleteService;

public class BoardMarketDeleteController implements BackController{

	Service service;
	
	public BoardMarketDeleteController() {
		service = new BoardMarketDeleteService();
	}
	
	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jObj =null;
		PrintWriter out = null;
		
		try {
			service.doService(request, response);
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "삭제 요청이 정상적으로 처리되었습니다.");
		}
		catch (Exception e) {
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "게시글 삭제에 실패하였습니다.");
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.print(jObj.toJSONString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
