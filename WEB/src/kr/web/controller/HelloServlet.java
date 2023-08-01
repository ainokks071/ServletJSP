package kr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.web.util.MyUtil;

/* Servlet = Server + Let(공통특징존재)
 * 클라이언트의 요청 -> 톰캣 서버가 서블릿 해석 -> 응답
 * JavaSE : Class기반, 시작메서드 : main()
 * JavaEE : Servlet기반, 시작메서드 : service()
 * 
 * Servlet의 기본 골격!!
 * 1. HttpServlet클래스를 상속받는다. (from 웹용 API servlet-api.jar)
 * 2. 부모클래스 HttpServlet의 service()메서드 재정의(오버라이딩)
 *    (상속관계 : HelloServlet -> HttpServlet -> GenericServlet -> Object)
 *    - service()메서드는 웹에서 동작. 
 *    - 시작 시 요청, 응답객체를 받는다. 
 *    - 에러 발생가능성 -> 예외처리(Servlet예외, IO예외)
 *    
 * Client -> Server 요청 시 : Client의 IP주소 + Port정보가 Server로 제공     
 * 							service()의 req, resp객체에 담긴다.(즉, 클라이언트의 정보가 담겨있다.)
 */ 

public class HelloServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// 1. 클라이언트가 1~100까지의 총합 요청 
		 MyUtil util = new MyUtil();
		 int sum = util.hap();
		 
		/* 2. 요청한 클라이언트에게 응답하기
		 - response 이용(getWriter()메서드) : resp에는 클라이언트의 소켓(IP, Port)이 담겨있어, 연결가능
		 - 요청한 클라이언트와의 연결고리(빨대) IO생성(출력스트림 out 얻어옴)
		 - HTML로 응답.
		 */
		 PrintWriter out = resp.getWriter();
		 out.println("<html>");
		 out.println("<body>");
		 out.println(sum);
		 out.println("</body>");
		 out.println("</html>");

		
	}
	
//	@Override
//	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
//
//		super.service(arg0, arg1);
//	}

	
}
