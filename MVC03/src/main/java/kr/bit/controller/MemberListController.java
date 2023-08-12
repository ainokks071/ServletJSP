package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

//http://localhost:8080/MVC03/memberList.do
@WebServlet("/memberList.do")
public class MemberListController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response)
												throws ServletException, IOException {

		
		//1. memberList.do 로 요청을 받는다.
		//2. Model(DAO)과 연동 : 회원리스트를 DB에서 가져온다.
		//조회이므로, 파라메터 수집은 X
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberVO> list = dao.memberList();
//		System.out.println(list);
		
		
		
		
		//Model2의 핵심 : controller와 view 사이의 연동(forward 기법)!!
		
		//1. 객체 바인딩 : View에게 list데이터 전달
		//request객체(메모리 공간)에 list객체(데이터)를 저장! -> view에서 getAttribute로 추출할 것이다.
		request.setAttribute("list", list);
		
		//2. forward : 특정 JSP에게 View페이지 요청 의뢰(RequestDispatcher)
		//포워딩하기 : Controller가 갖고있는 request, response객체를 jsp에게 넘겨준다.
		RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
		//따라서, 동일한 클라이언트에 대한 request, response객체를 Controller, jsp가 동일하게 소유한다.
		//request객체에는 list데이터가 저장되어있으므로, jsp에서 그 데이터를 추출할 수 있다.
		rd.forward(request, response);
		
		
		
		
		
		
		
		//3. HTML로 응답하기
		// 주의사항 : 응답 데이터에 한글 있는경우
		// - PrintWriter "out"(브라우저로 내려보내는 빨대, 스트림)생성 전에 encoding!
		// - MIME TYPE : 브라우저로 내려보내기 전에, 어떤 데이터를 보낼지 미리 알려줌
		
		
			
		
		
		
		
//		response.setContentType("text/html;charset=UTF-8"); //MIME TYPE 지정 !
		
		
//		// view
//		PrintWriter out = response.getWriter();
//		out.println("<html>");
//
//		out.println("<head>");
//		out.println("<meta charset='utf-8'>");
//		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
//		out.println("<title>회원 리스트 화면</title>");
//		//회원리스트화면에서, 가입하기 버튼링크 -> 회원가입화면에서, 가입버튼 -> 회원리스트화면
//		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>");
//		out.println("</head>");
//		
//		
//		out.println("<body>");
//
//		out.println("<h1>회원 전체 리스트</h1>");
//		
//		out.println("<table class = 'table' 'table-borderd'>");
//		
//		out.println("<thead>");
//		
//		out.println("<tr>");
//		out.println("<th>번호</th>");
//		out.println("<th>아이디</th>");
//		out.println("<th>비밀번호</th>");
//		out.println("<th>이름</th>");
//		out.println("<th>나이</th>");
//		out.println("<th>이메일</th>");
//		out.println("<th>전화번호</th>");
//		out.println("<th>삭제하기</th>");
//		out.println("</tr>");
//		
//		out.println("</thead>");
//		
//		out.println("<tbody>");
//		
////		// 1. 고전 반복문
////		for(int i = 0; i < list.size(); i++) {
////		out.println("<tr>");
////		out.println("<td>" + list.get(i).getNum() + "</td>");
////		out.println("<td>" + list.get(i).getId() + "</td>");
////		out.println("<td>" + list.get(i).getPass() + "</td>");
////		out.println("<td>" + list.get(i).getName() + "</td>");
////		out.println("<td>" + list.get(i).getAge() + "</td>");
////		out.println("<td>" + list.get(i).getEmail() + "</td>");
////		out.println("<td>" + list.get(i).getPhone() + "</td>");
////		out.println("</tr>");
////		}
//
//		//DAO의 memberList() 호출해서 DB에 저장되어있는 테이블 list를 조회해왔음.
//		// 2. 향상된 for문 : list에서 vo하나씩 뽑아온다. 
//		for(MemberVO vo : list) {
//			out.println("<tr>");
//			out.println("<td>" + vo.getNum() + "</td>");
//			out.println("<td> <a href='memberContent.do?num=" + vo.getNum() + "'>" + vo.getId() + "</a> </td>");
//			// http://localhost:8080/MVC01/memberContent.do?num=1 요청!
//			// url ? 변수 = 값 & 변수 = 값 : 클라이언트 -> 서버 Get방식으로 요청 시, parameter 넘겨주는 방식.
//			// 이때의 값 = QueryString이라고 한다. 요청받은 서블릿에서 getParameter()로 받는다.
//			// MemberContentController를 Get방식으로 요청하면서, num = 1   전달.(마치 form 태그에서, Get방식)
//			// <a> : only Get방식, <form> : Get + Post 방식
//			out.println("<td>" + vo.getPass() + "</td>");
//			out.println("<td>" + vo.getName() + "</td>");
//			out.println("<td>" + vo.getAge() + "</td>");
//			out.println("<td>" + vo.getEmail() + "</td>");
//			out.println("<td>" + vo.getPhone() + "</td>");
//			out.println("<td><a href='memberDelete.do?num=" + vo.getNum() + "'>삭제하기</a> </td>");
//			// http://localhost:8080/MVC01/memberDelete.do?num=1
//			// MemberDeleteController를 Get방식으로 요청하면서, num = 1 전달.
//			out.println("</tr>");
//		}
//
//		out.println("<tr>");
//		out.println("<td colspan='7' align='center'>");
//		//현재위치 : http://localhost:8080/MVC01/memberList.do
//		//목적지 : http://localhost:8080/MVC01/member/memberRegister.html
//		out.println("<a href = 'member/memberRegister.html'>가입하기</a>");
//		out.println("</td>");
//
//		out.println("</tr>");
//
//		out.println("</tbody>");
//
//		out.println("</table>");
//
//		out.println("</body>");
//		out.println("</html>");
//		
//		
//		
//	}
		
		

	}
}
