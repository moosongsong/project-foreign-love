package kr.co.foreignlove.controller.tag;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.Service;
import kr.co.foreignlove.service.tag.TagListService;
import kr.co.foreignlove.vo.TagVO;

public class TagListController implements BackController
{
	private Service service;
	
	public TagListController()
	{
		service = new TagListService();
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
			
			String word = (String)request.getAttribute("word");
			ArrayList<TagVO> tags = (ArrayList<TagVO>)request.getAttribute("tags");
			
			jObj.put("word", word);
			
			JSONArray jAry = new JSONArray();
			for(TagVO tag : tags)
			{
				System.out.println(tag);
				JSONObject jTemp = new JSONObject();
				jTemp.putAll(tag.convertMap());
				jAry.add(jTemp);
			}
			
			jObj.put("tags", jAry);
		}
		catch(Exception e)
		{
			jObj = new JSONObject();
			jObj.put("result", false);
			jObj.put("message", "태그 목록 취득에 실패하였습니다.");
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
