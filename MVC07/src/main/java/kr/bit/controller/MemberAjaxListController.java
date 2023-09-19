package kr.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.bit.model.MemberDAO;
import kr.bit.model.MemberVO;

public class MemberAjaxListController implements Controller {

	@Override
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberVO> list = (ArrayList<MemberVO>)dao.memberList();
		
//		GSON API : mvnrepository에서 다운로드. -> lib폴더에 붙여넣기.
//		list -> JSON 으로 변환.
		Gson g = new Gson();
		String json = g.toJson(list);  //list->JSON으로 변환.
		
//list형태 : [MemberVO [num=2, id=user01, pass=1234, name=김경수, age=33, email=kimks071@naver.com, phone=010-3014-4462]]
//json형태 : [{"num":2,"id":"user01","pass":"1234","name":"김경수","age":33,"email":"kimks071@naver.com","phone":"010-3014-4462"}]
		response.setContentType("text/json;charset=utf-8"); //콜백함수로 응답을 json형태로 하겠다.
		PrintWriter out = response.getWriter();
		out.print(json);
		
		return null;
	}

}
