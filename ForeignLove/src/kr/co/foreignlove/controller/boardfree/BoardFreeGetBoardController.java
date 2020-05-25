package kr.co.foreignlove.controller.boardfree;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.boardfree.BoardFreeGetBoardService;
import kr.co.foreignlove.vo.BoardFreeVO;

public class BoardFreeGetBoardController implements BackController{
	Service service;
	public BoardFreeGetBoardController() {
		service = new BoardFreeGetBoardService();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		JSONObject obj = null;
		try {
			service.doService(request, response);
			BoardFreeVO board = (BoardFreeVO)request.getAttribute("board");
			obj = new JSONObject();
			obj.put("result", true);
			obj.put("message",  "요청이정상적으로 처리되었습니다.");
			JSONObject temp = new JSONObject();
			temp.putAll(board.convertMap());
			obj.put("board", temp);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.print(obj.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
//		RequestDispatcher rd = request.getRequestDispatcher("/freeview.jsp");
//		try{
//			rd.forward(request, response);
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//		}
//		catch(ServletException e) {
//			e.printStackTrace();
//		}
		out.close();
	}
	
}
