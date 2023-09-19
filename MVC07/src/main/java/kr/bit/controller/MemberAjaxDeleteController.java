package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberAjaxDeleteController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //   /MVC05
		
		//get방식으로 넘어온 num 추출.
		int num = Integer.parseInt(request.getParameter("num"));
		
		MemberDAO dao = new MemberDAO();
		int count = dao.memberDelete(num);
		
		String nextPage = null;
		
		if(count > 0) {

			nextPage = "redirect:"+ ctx + "/memberList.do";
//			앞서, 로그인 성공으로 생성된 session객체 삭제(바인딩 데이터도 삭제) -> 삭제와 함께 로그아웃되게끔.
//			memberList.jsp에서 로그인form 뜨겠지.
			request.getSession().invalidate(); //로그아웃 
			request.getSession().setAttribute("a", "a");
		} else {
			
			throw new ServletException("not delete");
			
		}
		
		return nextPage;
	}

}
