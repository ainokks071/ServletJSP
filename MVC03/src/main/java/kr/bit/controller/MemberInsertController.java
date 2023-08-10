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

// context(MVC03 프로젝트) 자동 등록 : server.xml
// servlet mapping : 고전적(web.xml) -> annotation방법으로 대체.(서블릿 생성 시 지정)

//  http://localhost:8080/MVC03(context)/memberInsert.do(servlet mapping)
@WebServlet("/memberInsert.do")
public class MemberInsertController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
									throws ServletException, IOException {
		
		/* request참조변수가 가리키고있는 HttpServletRequest타입의 요청객체에는
		패킷(요청정보, header(ip, port) + body(data))이 담겨있는데, getParameter()로 받기 전,
		한글 데이터(2byte)의 깨짐을 막기 위해 setCharacterEncoding()를 이용해 utf-8방식으로 변경.
	    따라서, 한글 데이터를 1byte가 아닌, 2byte로 정상 처리! 
		*/
		
		request.setCharacterEncoding("utf-8");
//		Overrides the name of the character encoding used in the body of this request.
//		This method must be called prior to reading request parameters or reading input using getReader().
//		Otherwise, it has no effect.
		
		
		// cf) Get방식으로 client->server 파라메터 전달해보기.
		// http://localhost:8080/MVC01/memberInsert.do?id=kks0701&pass=1234&name=홍길동&age=40&email=hong@naver.com&phone=010-1234-1234
		
		//1. 파라메터수집(VO) : Client(form태그 내 input태그의 name속성과 일치)으로부터 전달된 데이터 추출 후, VO로 묶기.
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name"); //한글데이터(2byte).
		int age = Integer.parseInt(request.getParameter("age")); //숫자데이터로 형변환.
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
	
		//#1 생성자를 이용한 초기화 : 개발자에 따라 만드는 생성자의 종류 다르므로..
//		MemberVO vo = new MemberVO(id, pass, name, age, email, phone);
		
		//#2 setter(일반적인 방법!)
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
		//파라메터 수집 완료.
		
		
		//Model(DAO : Database Access Object)과 연동  
		MemberDAO dao = new MemberDAO();
		/* DAO의 JDBC 코드 생각해보기
		 * 1. DB(MySQL 접속) : getConnect()구현 후 쿼리 전송 시 사용
		 * - db url, id, password 
		 * - driver 클래스 로딩 후 연결 
		 * 2. Insert()
		 * - 컨트롤러로부터 받은 vo 객체의 데이터 추출
		 * - 추출한 데이터를 기반으로 쿼리 작성 
		 * - */
		//count = ps.executeUpdate();  insert성공 : 1 insert실패 : 0
		int count = dao.memberInsert(vo);
		
		
		
		//응답(View) -> 회원 리스트 화면으로.(MemberListController요청.)
		//현재 위치 : http://localhost:8080/MVC01/memberInsert.do
		//redirect 후, 목적지 : http://localhost:8080/MVC01/memberList.do
		//서버(서블릿,컨트롤러)에서 또 다른 서버(서블릿,컨트롤러) 재요청필요. : redirect
		
//		PrintWriter out = response.getWriter();
		
		if(count > 0) {
			//가입 성공 -> 추후 redirect.. 회원 리스트 화면으로.
//			out.println("insert success");
			
			//응답. 재요청.
			response.sendRedirect("memberList.do");
			 
			
		} else {
			//가입실패 시 예외객체를 만들어서 WAS에게 던진다.
			throw new ServletException("not insert");
		}
		
		
		
		//확인 -> 한글깨짐발생. -> request.setCharacterEncoding("utf-8");
//		System.out.println(vo);
	
	
	
	}


}
