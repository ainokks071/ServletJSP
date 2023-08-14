package kr.bit.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//인터페이스 : 추상메서드의 집합
//          -> 이를 구현하는 여러 클래스들은 강제적으로 메서드를 완성시켜야 한다.  
//          -> 메서드 선언부는 동일, 구현부는 클래스에 맞게..
// 			-> 코드 중복 최소화.

public interface Controller {

	//모든 POJO(알바생)가 지녀야하는 메서드. 
	//POJO의 역할
	//-> 1. Model(DAO)연동 2. 객체바인딩 3. 다음 페이지정보 안내.
	public String requestHandler(HttpServletRequest request, HttpServletResponse response)
								throws ServletException, IOException;
}
