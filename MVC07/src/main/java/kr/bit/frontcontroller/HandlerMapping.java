package kr.bit.frontcontroller;

import java.util.HashMap;

import kr.bit.controller.*;

public class HandlerMapping {

	//인스턴스 변수.
	private HashMap<String, Controller> mappings;
	
	Controller controller = null;
	String nextPage = null;

	
	//기본 생성자. 호출 시 -> HashMap객체 생성 + key/value쌍 저장(put)
	public HandlerMapping() {
		
		mappings = new HashMap<String, Controller>();
//		(key = 요청 url,  value = 해당하는 controller객체)
		mappings.put("/memberRegister.do", new MemberRegisterController());
		mappings.put("/memberContent.do", new MemberContentController());
		mappings.put("/memberDelete.do", new MemberDeleteController());
		mappings.put("/memberInsert.do", new MemberInsertController());
		mappings.put("/memberList.do", new MemberListController());
		mappings.put("/memberUpdate.do", new MemberUpdateController());
		mappings.put("/memberLogin.do", new MemberLoginController());
		mappings.put("/memberLogout.do", new MemberLogoutController());
		mappings.put("/memberDbcheck.do", new MemberDbcheckController());
//		ajax 컨트롤러
		mappings.put("/memberAjaxList.do", new MemberAjaxListController());
		mappings.put("/memberAjaxDelete.do", new MemberAjaxDeleteController());


	}

	//인스턴스메서드.
	public Controller getPOJO(String key) {
		
		return mappings.get(key);
	}
	
}
