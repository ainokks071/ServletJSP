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
	
	String name = "kim";
	int age = 32;
	String email = "kim@naver.com";
	
	MemberVO vo = new MemberVO();
	vo.setName(name);
	vo.setAge(age);
	vo.setEmail(email);
		
	
 	//객체 바인딩 : request객체에 vo 데이터 저장.
//	void setAttribute(String name, Object o) 
//	cf) 다형성 : Object o = (Object) vo; -> 자식 인스턴스를 부모 타입의 참조변수가 가리킬 수 있다.

	request.setAttribute("vo", vo);
	
	//JSP에게 요청의뢰 : view역할 
	RequestDispatcher rd = request.getRequestDispatcher("view/forward.jsp");
	rd.forward(request, response);
	
	}

}
