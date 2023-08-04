package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC 01 : Servlet(Controller) + Model(DTO, DAO, Util class)만으로 회원관리

//클라이언트의 요청 : http://localhost:8080/MVC01/h.do

//Servlet 만들 때, 미리 mapping -> annotation
@WebServlet("/h.do")
public class HelloStart extends HttpServlet {

	//doGet + doPost = service()
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		// ** Controller(Servlet)의 역할
		
		//1. Controller(Servlet) : 클라이언트의 요청을 받는 작업. -> parameter 수집.
		//   요청 시, parameter(전달값) 넘어올 수 있다. ex) 회원가입, 글 작성 ..
		//   요청을 받는 것 까지는 servlet이 한다.
				
		//2. 처리하는 작업(비즈니스로직) -> Model(Java class) 추출할 것.
		int sum = 0;
		   for(int i = 1; i <= 10; i++) {
			   sum += i;
		   }
		   
		//3. 응답하는 작업(프리젠테이션로직) : 요청한 클라이언트에게 응답
		//   servlet은 view로써 번거롭기  , 추후에 View(JSP)로 대체할 것.   
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println(sum);
		out.println("</body>");
		out.println("</html>");
		

   } 
	
}