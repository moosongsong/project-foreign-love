package kr.co.foreignlove.controller.tag;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.tag.TagInsertService;
import kr.co.foreignlove.vo.TagVO;

public class TagInsertController implements BackController
{
	private Service service;
	
	public TagInsertController()
	{
		service = new TagInsertService();
	}

	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{
		JSONObject jObj = null;
		
		try
		{
			service.doService(request, response);
			TagVO tag = (TagVO)request.getAttribute("tag");
			jObj = new JSONObject();
			jObj.put("result", true);
			jObj.put("message", "게시물 등록이 정상적으로 처리되었습니다.");
			jObj.put("tag", tag.convertMap());
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
