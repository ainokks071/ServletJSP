package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.bit.model.MemberDAO;

public class MemberDbcheckController implements Controller{

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		중복확인 할 ID 받아옴.
		String id = request.getParameter("id"); // $.ajax({"id" : id});의 "id"
		
//		위 id에 해당하는 회원 조회.
		MemberDAO dao = new MemberDAO();
		boolean isDouble = dao.memberDbcheck(id);
		
//		memberRegister.do ---(forward)---> memberRegister.jsp ---> memberDbcheck.do ---> 출력.

//		약속 : ajax함수에 callback : dbCheck 명시해놓았으므로, 그곳으로 return(응답)
		PrintWriter out = response.getWriter();
		out.print(isDouble); //스트림을 생성해서 클라이언트로 내려보낸다.
		
//		redirect or forward XX
		return null;
	}

}
