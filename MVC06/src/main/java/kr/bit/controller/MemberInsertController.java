package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberInsertController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ctx = request.getContextPath(); //  /MVC06
		
		request.setCharacterEncoding("utf-8"); //  form태그(post방식)로 넘어온 한글데이터 깨짐 방지

//		파라메터 수집 후 VO로 묶기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name"); 
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
	
		MemberVO vo = new MemberVO();
		vo.setId(id);
		vo.setPass(pass);
		vo.setName(name);
		vo.setAge(age);
		vo.setEmail(email);
		vo.setPhone(phone);
		
//		DAO연동 후 DB에 데이터 저장.
		MemberDAO dao = new MemberDAO();
		int count = dao.memberInsert(vo);
		
		String nextPage = null;
		
		if(count > 0) {
			
			nextPage = "redirect:"+ctx+"/memberList.do";
		} else {
			
			throw new ServletException("not insert");
		}
		
		return nextPage;
	}

}
