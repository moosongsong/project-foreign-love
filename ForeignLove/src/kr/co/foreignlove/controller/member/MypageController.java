package kr.co.foreignlove.controller.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import kr.co.foreignlove.controller.BackController;
import kr.co.foreignlove.service.member.MypageService;
import kr.co.foreignlove.vo.MemberTypeVO;
import kr.co.foreignlove.vo.MemberVO;
import kr.co.foreignlove.vo.SchoolVO;

public class MypageController implements BackController{
	private MypageService service;
	
	public MypageController() {
		service = new MypageService();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		
		JSONObject jObj = null;
		PrintWriter out = null;
		
		try
		{
			out = response.getWriter();
			service.doService(request, response);
			MemberVO member = (MemberVO) request.getAttribute("member");
			
			String m_email = member.getM_email();
			String m_name = member.getM_name();
			String m_phone = member.getM_phone();
			String m_birth = member.getM_birth();
			m_birth = m_birth.substring(0, 10);
			
			String m_nick = member.getM_nick();
			SchoolVO s_id = member.getS_id();
			MemberTypeVO m_type = member.getM_type();
			
			String m_addr = member.getM_addr();
			String m_startDate = member.getM_startDate();
			m_startDate = m_startDate.substring(0, 10);
			
			String m_regDate = member.getM_regDate();
			
			jObj = new JSONObject();
			
			jObj.put("m_email", m_email);
			jObj.put("m_name", m_name);
			jObj.put("m_phone", m_phone);
			jObj.put("m_birth", m_birth);
			jObj.put("m_nick", m_nick);
			jObj.put("schoolName", s_id.getS_name());
			jObj.put("schoolNation", s_id.getS_nation());
			jObj.put("schoolState", s_id.getS_state());
			jObj.put("schoolNum", s_id.getS_id());
			jObj.put("m_type", m_type.getM_typeName());
			jObj.put("m_addr", m_addr);
			jObj.put("m_startDate", m_startDate);
			jObj.put("m_regDate", m_regDate);
			
			jObj.put("result",true);
		} catch (Exception e) {
			jObj = new JSONObject();
			jObj.put("result",false);
		}
		
		out.println(jObj.toJSONString());
	}
	
}
