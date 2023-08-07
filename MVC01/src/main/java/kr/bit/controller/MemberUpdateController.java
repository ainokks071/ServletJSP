package kr.bit.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

@WebServlet("/memberUpdate.do")
public class MemberUpdateController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		//파라메터 수집. num은 hidden속성으로 넘어왔다.(식별자 역할)
		int num = Integer.parseInt(request.getParameter("num"));
		
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		MemberVO vo = new MemberVO();
		vo.setNum(num);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		MemberDAO dao = new MemberDAO();
//		dao.memberUpdate(num, age, email, phone);
		int cnt = dao.memberUpdate(vo);
		
		if(cnt > 0) {
//			응답 redirect
			response.sendRedirect("memberList.do");
		} else {
			
			throw new ServletException("not update");

		}
	}

}
