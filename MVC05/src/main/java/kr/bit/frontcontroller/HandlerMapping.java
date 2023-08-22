package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.*;

public class HandlerMapping {

	private HashMap<String, Controller> mappings;
	
	Controller controller = null;
	String nextPage = null;

	
	//생성자.
	public HandlerMapping() {
		
		mappings = new HashMap<String, Controller>();
		
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());

	}

	public Controller getPOJO(String key) {
		
		return mappings.get(key);
	}
	
}
