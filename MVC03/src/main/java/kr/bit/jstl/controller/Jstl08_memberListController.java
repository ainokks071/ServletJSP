package kr.bit.jstl.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.bit.model.MemberVO;

@WebServlet("/memList.do")
public class Jstl08_memberListController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		MemberVO vo1 = new MemberVO();
		vo1.setName("kim");
		vo1.setPass("1234");
		vo1.setAge(32);
		MemberVO vo2 = new MemberVO();
		vo2.setName("park");
		vo2.setPass("1234");
		vo2.setAge(32);
		MemberVO vo3 = new MemberVO();
		vo3.setName("lee");
		vo3.setPass("1234");
		vo3.setAge(32);
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		
		//객체 바인딩 
		request.setAttribute("list", list);
		
		//포워딩 : JSP로 데이터 보내기 
		RequestDispatcher rd = request.getRequestDispatcher("view/jstl08_memberList.jsp");
		rd.forward(request, response);
	
	}

}
