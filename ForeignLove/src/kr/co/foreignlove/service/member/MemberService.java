package kr.co.foreignlove.service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.co.foreignlove.exception.member.MemberException;

public interface MemberService {
	void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException , MemberException;
//	, MemberException
}
