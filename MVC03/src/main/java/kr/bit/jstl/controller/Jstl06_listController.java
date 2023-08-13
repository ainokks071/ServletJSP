package kr.bit.jstl.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list.do")
public class Jstl06_listController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("java");
		list.add("python");
		list.add("C++");
		list.add("React");
		list.add("Node.js");
		
		//객체 바인딩 
		request.setAttribute("list", list);
		
		//JSP로 데이터 보내기 
		RequestDispatcher rd = request.getRequestDispatcher("view/jstl06_list.jsp");
		rd.forward(request, response);
	
	}

}