package kr.bit.redirect.forward;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberVO;


// forward기법으로 페이지 전환 (객체 바인딩)
@WebServlet("/fc.do")
public class ForwardController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String name = "김";
	int age = 32;
	String email = "kim@naver.com";
	
	MemberVO vo = new MemberVO();
	vo.setName(name);
	vo.setAge(age);
	vo.setEmail(email);
		
	
//  한마디로 표현하면, request에 객체를 바인딩하여 포워딩을 이용하여 객체를 view페이지로 넘긴다.
	
	
//	void setAttribute(String name, Object o) 
//	cf) 다형성 : Object o = (Object) vo; -> 자식 인스턴스를 부모 타입의 참조변수가 가리킬 수 있다.

	//객체 바인딩 : request객체에 vo 데이터 저장.
	request.setAttribute("vo", vo);
	
	//forward기법
	//JSP에게 요청의뢰 : view역할 
	RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp");
	rd.forward(request, response);
	
	}

}
