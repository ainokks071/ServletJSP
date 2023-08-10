package test01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//WAS에 의해, 자동으로 test01.jsp파일 -> test01.java(서블릿)으로 변환될 때의 코드.
@WebServlet("/test01")
public class test01 extends HttpServlet {

	//<%! %> 메서드 선언문
	 public int hap(int a, int b) {
		  int sum = 0;
		  for(int i = a; i <= b; i++) {
			  sum += i;
		  }
		return sum;
		}
	 
	 //내장객체 request, response 
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//지시자 <%@ %>
	    response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		
		//스크립틀릿 <% %>
		int sum = 0;
		   for(int i = 1; i <= 100; i++) {
			   sum += i;
		   }
		   
		   
		//HTML 영역. 이하 생략. 클라이언트에게 응답.   
		out.println("<!DOCTYPE html>");  
		//출력식 <%= %>
		out.println(sum);
	}

}
