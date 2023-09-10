package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //   /MVC06

		//앞서, 로그인 성공으로 session객체가 생성되었고, 그곳에 set객체바인딩까지 되어있는 것을 끊자.

//		<session객체를 제거하는 방법>
		
//		1. 강제로 session 제거
		request.getSession().invalidate();
		
//		2. 브라우저 종료 : 세션ID는 브라우저 캐쉬 메모리에 쿠키로 저장되어있기 때문.
//		3. 세션이 종료될때까지 기다린다. (Servers폴더 -> web.xml -> session-timeout : 30분기본설정 )
		
		return "redirect:"+ ctx + "/memberList.do";
	}

}
