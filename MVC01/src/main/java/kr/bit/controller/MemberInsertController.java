package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;


//  http://localhost:8080/MVC01(context)/memberInsert.do(annotation)
@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
									throws ServletException, IOException {
		
		/*request참조변수가 가리키고있는 HttpServletRequest타입의 요청객체에는
		패킷(요청정보, header+body)이 담겨있는데, getParameter()로 받기 전,
		한글 데이터(2byte)의 깨짐을 막기 위해 setCharacterEncoding()를 이용해 utf-8방식으로 변경.
	 	한글 데이터를 2byte로 처리!
		*/
		request.setCharacterEncoding("utf-8");
		
		
		//1. 파라메터수집(VO) : form으로부터 전달된 데이터 추출 후, VO에 담기.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age")); 
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
	
		//#1 생성자를 이용한 초기화.
//		MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		
		//#2 setter(일반적인 방법!)
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		//Model과 연동  
		MemberDAO dao = new MemberDAO();
		int count = dao.memberInsert(vo); //insert성공 : 1 insert실패 : 0
		
		//응답
		PrintWriter out = response.getWriter();
		
		if(count > 0) {
			//가입 성공 
			out.println("insert success");
		} else {
			//가입실패 시 예외객체를 만들어서 WAS에게 던진다.
			throw new ServletException("not insert");
		}
		
		
		
		//확인 -> 한글깨짐발생. -> request.setCharacterEncoding("utf-8");
//		System.out.println(vo);
	
	
	
	}


}
