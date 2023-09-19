package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberRegisterController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		String nextPage = "/WEB-INF/member/memberRegister.jsp"; ViewResolver 활용할 것(논리적 이름)
		String nextPage = "memberRegister";
		
		return nextPage;
	}

}
