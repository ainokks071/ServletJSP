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

//클라이언트의 모든 요청을 받겠다.
//6개의 controller -> 1개의 frontcontorller
@WebServlet("*.do")
public class MemberFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException {
	
		//1. 클라이언트가 "어떤 요청을 했는지" 파악하기.(요청이 들어오면 request객체에 요청 경로도 저장된다.)
		// request를 이용하자.
		String url = request.getRequestURI();
		System.out.println(url); //    /MVC04/memberList.do
		
		String ctx = request.getContextPath();
		System.out.println(ctx); //    /MVC04
		
		//2. 실제로 요청한 명령이 무엇인지 알아내기.
		//substring();
//		String command = url.substring(ctx.length(), url.length()); //url의 6~20 문자열 
		String command = url.substring(ctx.length());    //url의 6번째부터 끝까지의 문자열 
//		/memberList.do
//		Returns a string that is a substring of this string.
//		The substring begins with the character at the specified index
//		and extends to the end of this string.
//
//		Examples:
//
//		 "unhappy".substring(2) returns "happy"
//		 "Harbison".substring(3) returns "bison"
//		 "emptiness".substring(9) returns "" (an empty string)
		System.out.println(command); //    /MVC04
		
		Controller controller = null;
		String nextPage = null;
		
		//3. 요청에 따른 분기 작업.
		
		if(command.equals("/memberRegister.do")) {
		 
//		url 경로가 ~html or ~jsp 나타나지 않도록 하기위해 redirect X forward O
//		정리하자면, html이나 jsp화면으로 넘길 때는 forward 사용하자..!
		
//		POJO에게 떠넘기기 
		controller = new MemberRegisterController();
		nextPage = controller.requestHandler(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
		
		} else if(command.equals("/memberInsert.do")) {

//			POJO에게 떠넘기기 
//			request.setCharacterEncoding("utf-8");
//
//			String id = request.getParameter("id");
//			String pass = request.getParameter("pass");
//			String name = request.getParameter("name"); 
//			int age = Integer.parseInt(request.getParameter("age"));
//			String email = request.getParameter("email");
//			String phone = request.getParameter("phone");
//		
//			MemberVO vo = new MemberVO();
//			vo.setId(id);
//			vo.setPass(pass);
//			vo.setName(name);
//			vo.setAge(age);
//			vo.setEmail(email);
//			vo.setPhone(phone);
//			
//			//DAO 연동 
//			MemberDAO dao = new MemberDAO();
//			int count = dao.memberInsert(vo);
			

//			if(count > 0) {
//				
//				response.sendRedirect("memberList.do");
				
//				RequestDispatcher rd = request.getRequestDispatcher("memberList.do");
//				rd.forward(request, response);
				
//			} else {
//				throw new ServletException("not insert");
//			}
			
			controller = new MemberInsertController();
			//POJO에게 떠넘기기.
			nextPage = controller.requestHandler(request, response);
			//FrontController에서는 forward or redirect
			response.sendRedirect(nextPage);
			
		} else if(command.equals("/memberList.do")) {
			
//			POJO에게 떠넘기기 
//			MemberDAO dao = new MemberDAO();
//			ArrayList<MemberVO> list = dao.memberList();
//			
//			request.setAttribute("list", list);
			
			//다형성.
			controller = new MemberListController();
			nextPage = controller.requestHandler(request, response);
			//forward.	
//			RequestDispatcher rd = request.getRequestDispatcher("member/memberList.jsp");
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
			
		} else if(command.equals("/memberContent.do")) {
			
//			POJO에게 떠넘기기.
//			int num = Integer.parseInt(request.getParameter("num"));
//
//			MemberDAO dao = new MemberDAO();
//			
//			MemberVO vo = dao.memberContent(num);
//
//			request.setAttribute("vo", vo);
			
			controller = new MemberContentController();
			nextPage = controller.requestHandler(request, response);
			
			RequestDispatcher rd = request.getRequestDispatcher(nextPage);
			rd.forward(request, response);

		} else if(command.equals("/memberUpdate.do")) {
			
//			POJO에게 떠넘기기.
//			int num = Integer.parseInt(request.getParameter("num"));
//			
//			int age = Integer.parseInt(request.getParameter("age"));
//			String email = request.getParameter("email");
//			String phone = request.getParameter("phone");
//			
//			MemberVO vo = new MemberVO();
//			vo.setNum(num);
//			vo.setAge(age);
//			vo.setEmail(email);
//			vo.setPhone(phone);
//			
//			MemberDAO dao = new MemberDAO();
//			int cnt = dao.memberUpdate(vo);
//			
//			if(cnt > 0) {
//
//				response.sendRedirect("memberList.do");
//			} else {
//				
//				throw new ServletException("not update");
//
//			}

			controller = new MemberUpdateController();
			nextPage = controller.requestHandler(request, response);
			
			response.sendRedirect(nextPage);
			
		} else if(command.equals("/memberDelete.do")) {
			
//			POJO에게 떠넘기기.
//			int num = Integer.parseInt(request.getParameter("num"));
//			
//			MemberDAO dao = new MemberDAO();
//			int count = dao.memberDelete(num);
//			
//			if(count > 0) {
//				response.sendRedirect("memberList.do");
//			} else {
//				throw new ServletException("not delete");
//			}
			controller = new MemberDeleteController();
			nextPage = controller.requestHandler(request, response);
			
			response.sendRedirect(nextPage);

		}
	}

}
