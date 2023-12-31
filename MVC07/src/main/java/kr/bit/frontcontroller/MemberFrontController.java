package kr.bit.frontcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.controller.Controller;
import kr.bit.controller.MemberContentController;
import kr.bit.controller.MemberDeleteController;
import kr.bit.controller.MemberInsertController;
import kr.bit.controller.MemberListController;
import kr.bit.controller.MemberRegisterController;
import kr.bit.controller.MemberUpdateController;
import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

/* FrontController : 클라이언트의 모든 요청을 받겠다.(대표 안내원 역할)
	- Spring에서의 DispatcherServlet과 동일.
	- 역할이 정해져있다.
	- 1. 요청 url 추출하기(command)
	- 2. HandlerMapping에 command를 보내서 해당하는 POJO 얻기
	- 3. POJO에게 일시키기(DAO연동, 객체바인딩, nextPage 반환)
	- 4. nextPage를 받아와서 redirect or forward    */

@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
//		request.setCharacterEncoding("euc-kr");

//		1. 요청 url 추출하기(command) : 클라이언트가 "어떤 요청을 했는지" 추출하기.
//		   cf) 요청이 들어오면 request객체에 요청 경로도 저장된다.
		String url = request.getRequestURI();
//		System.out.println(url);     /MVC05/memberList.do

		String ctx = request.getContextPath();
//		System.out.println(ctx);     /MVC05
		
//		String command = url.substring(ctx.length():6, url.length():20); //url의 6~20번째 문자열 
		String command = url.substring(ctx.length());    //url의 6번째부터 끝까지의 문자열 

//		substring(int beginindex)메서드
//		: Returns a string that is a substring of this string.
//		The substring begins with the character at the specified index
//		and extends to the end of this string.
//
//		Examples:
//
//		 "unhappy".substring(2) returns "happy"
//		 "Harbison".substring(3) returns "bison"
//		 "emptiness".substring(9) returns "" (an empty string)
//		System.out.println(command); 
		
		//다형성 
		Controller controller = null;
		String nextPage = null;
		
		
//		2. 요청에 따른 분기 작업. <초기> if ~ else if --> <수정>HandlerMapping클래스(HashMap 활용)
//		HandlerMapping : 요청 url에 해당하는 POJO 얻어오기 (mapping)
//		cf) 클라이언트의 요청이 frontcontroller에 들어올 때마다 hashmap객체 생성 + key,value쌍 미리 저장.
		HandlerMapping handlerMapping = new HandlerMapping();
//		url(key)에 해당하는 POJO(value) 얻어옴. ex) key=/memberList.do value=new MemberListController();
		controller = handlerMapping.getPOJO(command);
		
//      실질적으로 요청을 받은 Controller
//		DAO연동, 얻어온 data set객체바인딩, nextPage반환 (redirect, forward는 frontcontroller)
//		얻어온 POJO에게 일을 시킴.(DAO연동, 객체바인딩, nextPage반환 )
		nextPage = controller.requestHandler(request, response);
		
		
		
//		3. 다음페이지로(정확히는 다음 컨트롤러.) "redirect"할 것인지 "forward"할 것인지 결정.
		
//		굳이 not null 넣은 이유 : ajax로 비동기전송 시 nextPage = null -> redirect X forward X !!
		if(nextPage != null) {
//			1) nextPage에 redirect: 문자열이 포함되어 있으면 "redirect"
			if(nextPage.indexOf("redirect:") != -1) {
//				memberList.do로 재요청.
				response.sendRedirect(nextPage.split(":")[1]);
//			2) redirect: 문자열이 없으면 "forward"	
			} else {
				
//				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
//				~~.jsp 페이지로 요청의뢰.(포워딩)
				response.setCharacterEncoding("utf-8");														
				RequestDispatcher rd = request.getRequestDispatcher(ViewResolver.makeView(nextPage));
				rd.forward(request, response);
			}
		}
		
		
		
		
		
		
		
		
		
		
/*		핸들러매핑으로 아래 코드 대체..
		
//		HandlerMapping : URL요청과 해당하는 POJO 1:1매칭  
 * 
 * 
 * 
		if(command.equals("/memberRegister.do")) {
//		url 경로가 .html or .jsp 나타나지 않도록 하기위해 redirect X forward O
//		정리하자면, html이나 jsp화면으로 넘길 때는 forward 사용하자..!
		controller = new MemberRegisterController(); //POJO
		nextPage = controller.requestHandler(request, response); //POJO에게 일시키기.

//		forward or redirect 중복되는코드 -> if ~ else로 외부로 추출하면 -> HandlerMapping 완성
 * 
//		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
//		rd.forward(request, response);
		
		} else if(command.equals("/memberInsert.do")) {

//			request.setCharacterEncoding("utf-8");
//			String id = request.getParameter("id");
//			String pass = request.getParameter("pass");
//			String name = request.getParameter("name"); 
//			int age = Integer.parseInt(request.getParameter("age"));
//			String email = request.getParameter("email");
//			String phone = request.getParameter("phone");
//			MemberVO vo = new MemberVO();
//			vo.setId(id);
//			vo.setPass(pass);
//			vo.setName(name);
//			vo.setAge(age);
//			vo.setEmail(email);
//			vo.setPhone(phone);
//			//DAO 연동 
//			MemberDAO dao = new MemberDAO();
//			int count = dao.memberInsert(vo);
//			if(count > 0) {
//				response.sendRedirect("memberList.do");
//			} else {
//				throw new ServletException("not insert");
//			}
			
			//POJO에게 떠넘기기.
			controller = new MemberInsertController();
			nextPage = controller.requestHandler(request, response);
			//FrontController에서는 forward or redirect
			//nextPage = "memberList.do"
//			response.sendRedirect(nextPage);
			
		} else if(command.equals("/memberList.do")) {
			
//			MemberDAO dao = new MemberDAO();
//			ArrayList<MemberVO> list = dao.memberList();
//			request.setAttribute("list", list);
			
//			POJO에게 떠넘기기 
			controller = new MemberListController();
			nextPage = controller.requestHandler(request, response);
			
			//list JSP페이지로 forward.	
//			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
//			rd.forward(request, response);
			
		} else if(command.equals("/memberContent.do")) {
			
//			POJO에게 떠넘기기.
//			int num = Integer.parseInt(request.getParameter("num"));
//			MemberDAO dao = new MemberDAO();
//			MemberVO vo = dao.memberContent(num);
//			request.setAttribute("vo", vo);
			
			controller = new MemberContentController();
			nextPage = controller.requestHandler(request, response);
			
//			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
//			rd.forward(request, response);

		} else if(command.equals("/memberUpdate.do")) {
			
//			int num = Integer.parseInt(request.getParameter("num"));
//			int age = Integer.parseInt(request.getParameter("age"));
//			String email = request.getParameter("email");
//			String phone = request.getParameter("phone");
//			MemberVO vo = new MemberVO();
//			vo.setNum(num);
//			vo.setAge(age);
//			vo.setEmail(email);
//			vo.setPhone(phone);
//			MemberDAO dao = new MemberDAO();
//			int cnt = dao.memberUpdate(vo);
//			if(cnt > 0) {
//				response.sendRedirect("memberList.do");
//			} else {
//				throw new ServletException("not update");
//			}

			controller = new MemberUpdateController();
			nextPage = controller.requestHandler(request, response);
			
//			response.sendRedirect(nextPage);
			
		} else if(command.equals("/memberDelete.do")) {
			
//			int num = Integer.parseInt(request.getParameter("num"));
//			MemberDAO dao = new MemberDAO();
//			int count = dao.memberDelete(num);
//			if(count > 0) {
//				response.sendRedirect("memberList.do");
//			} else {
//				throw new ServletException("not delete");
//			}

			//			POJO에게 떠넘기기.
			controller = new MemberDeleteController();
			nextPage = controller.requestHandler(request, response);
			
			
//			response.sendRedirect(nextPage);

		}
		
 */
		
		

		
	}

}
