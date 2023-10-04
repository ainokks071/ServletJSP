package kr.bit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

//POJO의 역할은 어디까지? -> DAO연동, 객체바인딩, 다음페이지 안내까지. -> 포워드는 FrontController에서 한다. 
public class MemberListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		//1. 모델연동 
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = (ArrayList<MemberVO>)dao.memberList(); //DAO -> DB -> DAO -> Con
		
		//2. request객체에 set 객체바인딩 
		request.setAttribute("list", list);
		
		response.setCharacterEncoding("utf-8");
		
		//3. 다음 페이지 안내.(포워딩)
//		return "/WEB-INF/member/memberList.jsp"; ViewResolver 활용할 것(논리적 이름)
		return "memberList";
	}

}
