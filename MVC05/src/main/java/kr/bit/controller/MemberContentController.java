package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberContentController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));

		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num);

		//객체바인딩 
		request.setAttribute("vo", vo);
		
//		String nextPage = "/WEB-INF/member/memberContent.jsp"; ViewResolver 활용할 것(논리적 이름)
		String nextPage = "memberContent";
		
		return nextPage;
	}
	


}
