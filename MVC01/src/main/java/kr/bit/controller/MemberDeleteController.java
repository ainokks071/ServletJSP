package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

// MemberDeleteController를 Get방식으로 요청하면서, num = 1 전달.(마치 form 태그)
// request 객체 내부 header + body(data num) 저장 -> getParameter()로 추출.
@WebServlet("/memberDelete.do")
public class MemberDeleteController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		//회원의 num 문자열로 받아옴. -> int 
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int count = dao.memberDelete(num);
		
		//현재 위치 : http://localhost:8080/MVC01/memberDelete.do
		//목적지 : http://localhost:8080/MVC01/memberList.do 재요청.

		if(count > 0) {
			//응답을 할 건데, 그 응답이 서블릿 재요청이다.
			response.sendRedirect("memberList.do");
		} else {
			
			//삭제실패 시 예외객체를 만들어서 WAS에게 던진다.
			throw new ServletException("not delete");
		}
		


	}

}
