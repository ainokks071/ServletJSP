package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MyCalc;



//su.html의 form에 의해 input태그의 parameter가 넘어온다.(POST방식)
@WebServlet("/calc.do")
public class CalcController extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
		
		//1. Controller : 클라이언트에서 넘어오는 폼 파라메터 수집. 
		//getParameter()의 매개변수타입=String, 반환타입=String -> int형으로 형변환.
		int su1 = Integer.parseInt(request.getParameter("su1")); 
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		
		//2. 비즈니스로직 -> Model(Util class)로 추출. (su1부터 su2까지의 합 구하기.)
//		int sum = 0;
//		for(int i = su1; i <= su2; i++ ) {
//			sum += i;
//		}
		
		MyCalc calc = new MyCalc();
		int sum = calc.hap(su1, su2);
		
		
		//3. 프리젠테이션로직(View) -> 추후에 JSP로 대체.
		PrintWriter out = response.getWriter();
//		
		out.println("<html>");
		out.println("<body>");		
		out.println("<table border = '1'>");
		out.println("<tr>");
		out.println("<td>TOTAL</td>");
		out.println("<td>" + sum + "</td>");
		out.println("</tr>"); 
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");

	}

}
