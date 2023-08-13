package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;


@WebServlet("/memberContent.do")
public class MemberContentController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		request.setCharacterEncoding("utf-8"); 한글데이터 넘어오지 않음.
		
		//회원 num 받아 정수로 형변환.
		int num = Integer.parseInt(request.getParameter("num"));

		MemberDAO dao = new MemberDAO();
		//회원 1명 가져오기. select
		MemberVO vo = dao.memberContent(num);

		
		//view : 포워드, 객체바인딩.		
		request.setAttribute("vo", vo);
		
		RequestDispatcher rd = request.getRequestDispatcher("member/memberContent.jsp");
		rd.forward(request, response);
		

//		//view : MIME TYPE 지정 
//		response.setContentType("text/html;charset=UTF-8");
//
//		PrintWriter out = response.getWriter();
//
//		out.println("<html>");
//
//		out.println("<head>");
//		out.println("<meta charset='utf-8'>");
//		out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
//		out.println("<title>회원 상세 화면</title>");
//		out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9' crossorigin='anonymous'>");
//		out.println("</head>");
//
//		out.println("<body>");
//		out.println("<h1> 회원 상세 페이지 <h1>");
//
//		//form은 주로 post !!
//		out.println("<form action='memberUpdate.do' method='post'>");
//		
//		//hidden속성 : 수정x -> 식별자 파라메터 !
//		out.println("<input type='hidden' name='num' value='" + vo.getNum() + "'/>");
//		
//		out.println("<table class = 'table' 'table-borderd'>");
//
//		if(vo != null) {
//		out.println("<tr colspan='2'>");
//		out.println("<td>"+ vo.getName() + "회원님의 상세페이지입니다.</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>번호</th>");
//		out.println("<td>"+ vo.getNum() +"</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>아이디</th>");
//		out.println("<td>"+ vo.getId() +"</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>비밀번호</th>");
//		out.println("<td>"+ vo.getPass() +"</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>이름</th>");
//		out.println("<td>"+ vo.getName() +"</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>나이</th>");
//		//사용자가 vo.getAge() 부분 수정해서 넘길 것.
//		out.println("<td> <input type='text' name='age' value='" + vo.getAge() + "'/>" + "</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>이메일</th>");
//		out.println("<td> <input type='text' name='email' value='" + vo.getEmail() + "'/>" + "</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<th>전화번호</th>");
//		out.println("<td> <input type='text' name='phone' value='" + vo.getPhone() + "'/>" + "</td>");
//		out.println("</tr>");
//		
//		out.println("<tr>");
//		out.println("<td>");
//		out.println("</td>");		
//		out.println("<td colspan='2'>");
//		//수정하려면, 
//		out.println("<input type='submit' class='btn btn-primary' value='수정하기'/>");
//		
//		out.println("<input type='reset' class='btn btn-primary' value='취소'/>");
//		out.println("<a href='memberList.do'> 리스트로 <a/>");
//		out.println("</td>");
//		out.println("</tr>");
////		} else {
////			//vo=null
//////			http://localhost:8080/MVC01/memberContent.do?num=999999
////			out.println("존재하지 않는 회원입니다.");
////		}
//		out.println("</table>");
//		out.println("</form>");
//		out.println("</body>");
//		out.println("</html>");
//
//
//		}

	}
}