package kr.bit.jstl.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/array.do")
public class Jstl05_arrayController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String[] array = {"사과", "배", "포도", "바나나", "파인애플"};
		
		request.setAttribute("array", array);
		
//	 	JSP로 포워딩 : 데이터 넘기기 
		RequestDispatcher rd = request.getRequestDispatcher("view/jstl05_array.jsp");
		rd.forward(request, response);
	
	}

}
