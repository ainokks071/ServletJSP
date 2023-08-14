package kr.bit.controller;

import java.io.IOException;
import java.util.ArrayList;

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
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = dao.memberList();
		
		request.setAttribute("list", list);
		
		//다음 페이지 안내.
		return "member/memberList.jsp";
	}

}
