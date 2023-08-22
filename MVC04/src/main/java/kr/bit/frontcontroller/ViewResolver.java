package kr.bit.frontcontroller;

//View의 경로를 완성시켜주는 클래스. -> Spring에도 존재.(Internal Resource ViewResolver)
public class ViewResolver {
	
	public static String makeView(String nextPage) {
		
		//prefix + nextPage + subfix로 구성.
		return "/WEB-INF/member/" + nextPage + ".jsp";
	}

}
