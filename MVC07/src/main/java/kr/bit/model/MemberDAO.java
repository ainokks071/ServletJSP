package kr.bit.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//DAO = Database Access Object
//JDBC -> MyBatis, JPA : JDBC는 맛보기.
public class MemberDAO {

	//	SqlSessionFactory(= Connection Pool) : SqlSession객체(=Connection객체; DB 연결 객체)들이 존재하는 메모리 공간.
	//	Connection Pool기법 : "미리" SqlSession객체들을 만들어 놔서(5~8개) 각 객체마다 DB에 "미리" 접속을 시켜놓고,
	//	사용자들이 DB작업(CRUD)을 할 때마다 SqlSessionFactory에서 SqlSession객체들을 꺼내와서 사용하고 반납, 재활용하는 기법.
	//	-> 순수 JDBC보다 접속 시간 단축, 성능 향상
    //	순수 JDBC는 DB작업(CRUD)마다 Connection을 생성하고, 끊는 작업을 반복하는 데, 이는 부하가 많이 걸리는 작업.
	private static SqlSessionFactory sqlSessionFactory;

	//	static블럭(초기화블럭) : 프로그램 실행 시 딱 한번만 실행되는 코드 영역. 
	static {

		//Building SqlSessionFactory from XML : config.xml파일로부터 "미리" SqlSessionFactory객체를 만들자.
		
		try {
			String resource = "kr/bit/mybatis/config.xml";
			//IO, inputStream -> config.xml파일 읽기. -> inputstream으로 config.xml파일 읽기 
			//config.xml파일이 있을지 없을지 모르기때문에 예외처리.			
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//SqlSessionFactory 객체생성(build) : SqlSession(Connection객체)들이 존재하는 메모리 공간.
			//build()메서드가 커넥션 풀 객체 생성, sqlSessionFactory 참조변수가 참조 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//위에서 DB연결을 위해 "미리" SqlSessionFactory객체를 만들었다.
	
//  회원 리스트 보기.
	public List<MemberVO> memberList() {
//		1. SqlSession객체 얻기.
//		-> JDBC의 Connection(DB연결객체) + Statement(DB로 SQL문전송객체)의 두가지 기능 포함.
		SqlSession session = sqlSessionFactory.openSession();
//		2. selectList()메서드 : 회원1명의 정보들을 VO로 묶고, list에 담아준다.
//		JDBC보다 매우 편리하다! -> while, resultset 등등 수작업 필요 X
//		MemberMapper.xml 내부의 식별자인 id="memberList"를 찾아, SQL문을 수행한다.(VO로 묶고, list에 담고)
		List<MemberVO> list = session.selectList("memberList");
//		3. 사용한 session객체 반납.
		session.close();
		
		return list;
	}
	
	
	
	public int memberInsert(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
//		mapper의 id, 파라메터 vo
		int cnt = session.insert("memberInsert", vo);
//		insert, delete, update는 commit 필요.
		session.commit();
		session.close();
		
		return cnt;
	}
	
	public int memberDelete(int num) {
		SqlSession session = sqlSessionFactory.openSession();
//		mapper의 id:memberDelete, 파라메터 num
		int cnt = session.delete("memberDelete", num);
		session.commit();
		session.close();
		
		return cnt;
	}

	public MemberVO memberContent(int num) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO vo = session.selectOne("memberContent", num);
//		select는 commit 필요 X 
		session.close();
		
		return vo;
	}

	public int memberUpdate(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		int cnt = session.update("memberUpdate", vo);
		session.commit();
		session.close();
		
		return cnt;
	}

	
//	로그인 검사..
	public String memberLogin(MemberVO vo) {
		SqlSession session = sqlSessionFactory.openSession();
		String user_name = session.selectOne("memberLogin", vo);
		session.close();

		return user_name;
	}


//	회원가입 시 아이디 중복체크
	public boolean memberDbcheck(String id) {
		SqlSession session = sqlSessionFactory.openSession();
//		select id from member where id=#{id}
//		중복 O -> user_id != null
		String user_id = session.selectOne("memberDbcheck", id);

//		중복X
		boolean isDouble = false;
//		중복 O 
		if(user_id != null) {
			isDouble = true;
		}
		
		session.close();

		return isDouble;
	}
	
	
	
	
	
	
	
	
}
