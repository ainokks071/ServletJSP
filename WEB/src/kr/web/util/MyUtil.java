package kr.web.util;

//JSP 페이지에서 비즈니스로직 분리
//Model(DTO, DAO, Utility 클래스)
//만들어놓은 Model은 JSP나 Servlet에서 필요할 때 사용 가능.
public class MyUtil {
	
	public int hap() {
		int sum = 0;
		for(int i = 1; i <= 10000; i++) {
			sum += i;
		}
		return sum;
		
	}

}
