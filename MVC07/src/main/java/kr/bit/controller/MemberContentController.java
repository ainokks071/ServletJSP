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
		
		//get방식으로 넘어온 num추출
		int num = Integer.parseInt(request.getParameter("num"));

		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.memberContent(num);

		//set 객체바인딩 -> JSP에서 EL태그($기호)로 추출할 것!(getAttribute효과) 
		request.setAttribute("vo", vo);
		
//		String nextPage = "/WEB-INF/member/memberContent.jsp"; ViewResolver 활용할 것(논리적 이름)
		String nextPage = "memberContent";
		
		return nextPage;
	}
	


}
