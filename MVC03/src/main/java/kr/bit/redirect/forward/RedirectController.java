package kr.bit.redirect.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//클라이언트의 요청 시, request, response객체 생성
//Redirect기법으로 페이지 전환(QueryString, Get방식 )
@WebServlet("/rc.do")
public class RedirectController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/* Client -> Controller(Servlet) 요청 시, View(JSP)로 페이지를 전환하는 방법
		 * 
		 * 1. Redirect 기법 
		 * - redirect는 클라이언트가 새롭게 재요청하는 효과를 낸다.
		 * - redirect로 요청받은 JSP에서도 request, response 새롭게 생성 될 것.
		 * - 따라서, 서블릿의 request, response객체와는 완전히 별개다. */
	
		String name = "kim";
		int age = 33;
		String email = "kim@naver.com";
		
		/* redirect기법으로 View(JSP)로 데이터 전달하는 방법 : QueryString, GET방식 이용.
		 * 단점 : 여러 데이터 전송 시 번거로움.*/
		
//		response.sendRedirect("view/result.jsp);
		
//		request.setAttribute("name",name);
//		-> redirect는 새롭게 재요청하므로, 객체바인딩(=request바인딩) 소용없다!
		response.sendRedirect("view/redirect.jsp?name=" + name + "&age=" + age + "&email=" + email);
		
		
//		// 2. Forward 기법 
//		String data = "apple";
//		
//		request.setAttribute("data", data);
//		
//		RequestDispatcher rd = request.getRequestDispatcher("view/result.jsp"); //찾아.
//		rd.forward(request, response); //보내.
		
	
	}

}
