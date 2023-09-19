package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberLoginController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //   /MVC06

//		1. 파라메터수집, vo로 묶기
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		
		MemberVO vo = new MemberVO();
		vo.setId(user_id);
		vo.setPass(password);
		
//		2. DAO연동 
		MemberDAO dao = new MemberDAO();
		String user_name = dao.memberLogin(vo);
		
//		user_name에 값이 있으면 로그인 성공, 없으면 실패(일치하는 회원이 없다.)
		
		HttpSession session = null;
		
		if(user_name != null && !"".equals(user_name)) {
			/*로그인 성공 시, 그 회원의 정보(id, name)가 session객체에 바인딩 된다.
			  브라우저에서 사용할 HttpSession 객체(메모리공간) 생성, 세션ID 생성 -> 클라이언트로 세션ID전달(Cookie)
			  session객체에 객체바인딩 
			  다른 jsp페이지에서 객체바인딩된 정보를 얻을 수 있음. -> 상태유지기법 
			*/
			session = request.getSession();
			session.setAttribute("userId", user_id);
			session.setAttribute("userName", user_name);
			
			/* 로그인 실패 시(일치하는 회원이 없을 시), user_name = null
			 * Session객체 생성, "msg = 에러 메세지" : session객체 바인딩! */
		} else {
			session = request.getSession();
			session.setAttribute("userId", null);
			session.setAttribute("userName", null);
			
			session.setAttribute("msg", "사용자 정보가 올바르지 않습니다.");
		}
//		session객체바인딩 + redirect는 한 쌍으로 생각.
//		cf) request객체바인딩 + forwarding
		return "redirect:"+ ctx + "/memberList.do";
	}

}
