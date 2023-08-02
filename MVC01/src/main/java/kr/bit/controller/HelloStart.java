package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet 만들 때, 미리 mapping 해줬음.
//http://localhost:8080/MVC01/h.do 
@WebServlet("/h.do")
public class HelloStart extends HttpServlet {

	//doGet + doPost = service()
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int sum = 0;
		   for(int i = 1; i <= 10; i++) {
			   sum += i;
		   }
		   
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(sum);
		out.println("</body>");
		out.println("</html>");

   }
	
}