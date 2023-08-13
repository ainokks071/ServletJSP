package kr.bit.jstl.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.bit.model.MemberVO;

@WebServlet("/member.do")
public class Jstl07_memberController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		MemberVO vo = new MemberVO();
		vo.setName("kim");
		vo.setPass("1234");
		vo.setAge(32);
		
		//객체 바인딩 
		request.setAttribute("vo", vo);
		
		//포워딩 : JSP로 데이터 보내기 
		RequestDispatcher rd = request.getRequestDispatcher("view/jstl07_member.jsp");
		rd.forward(request, response);
	
	}

}
