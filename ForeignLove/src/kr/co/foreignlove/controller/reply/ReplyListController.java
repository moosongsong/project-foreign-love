package kr.co.foreignlove.controller.reply;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.reply.ReplyListService;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.ReplyVO;

public class ReplyListController implements BackController
{
	Service service;
	
	public ReplyListController()
	{
		service = new ReplyListService();
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
			jObj.put("message", "댓글 목록을 가져왔습니다.");
			
			ArrayList<ReplyVO> replies = (ArrayList<ReplyVO>)request.getAttribute("replies");
			
			JSONArray jAry = new JSONArray();
			for(ReplyVO reply : replies)
			{
				JSONObject jTemp = new JSONObject();
				jTemp.putAll(reply.convertMap());
				jAry.add(jTemp);
			}
			jObj.put("replies", jAry);
			jObj.put("loginUserInfo", ((MemberVO)request.getSession(false).getAttribute("loginUserInfo")).convertMap());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "댓글 목록을 가져오지 못했습니다.");
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
