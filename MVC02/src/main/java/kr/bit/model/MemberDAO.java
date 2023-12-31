package kr.bit.model;

//java.sql.* : JavaAPI 인터페이스 
//mysql-connector-j-8.1.0.jar : MySQL 구현클래스 빌드패스에추가.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


// CRUD메서드 

//DAO = Data Access Object
//JDBC -> MyBatis, JPA : JDBC는 맛보기.
public class MemberDAO {
	
	//기본적으로, 커넥션 객체 필요.
	private Connection conn;
	//SQL문 전송 객체 
	private PreparedStatement ps;
	//db에서 가져온 데이터 저장 객체 
	private ResultSet rs;

	//DB연결객체(Connection객체) 생성 
	public void getConnect() {
		//DB접속 URL : DB회사마다 다르다.
		String URL = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String password = "dkssud123";
	
		//mysql에서 제공하는 구현클래스(=드라이버) 등록 방법
		//1. Buil Path -> Library -> external jar 추가!
		//2. mysql-connector-j-8.1.0.jar lib폴더에 붙여넣기!
		String driver = "com.mysql.jdbc.Driver";
		
		
		//MySQL Driver 메모리에 로딩.
		try {
			// cf) 정적로딩 : new 연산자 사용해서 객체 생성. 컴파일 시점에서 객체를 생성하는 방법.
			/* DriverManager(인터페이스) dm = new com.mysql.jdbc.Driver();(구현클래스)
			 * dm.getConnection(url, user, pass); 구현클래스의 메서드 사용.
			 * 만약, db 교체 한다면..
			 * */
			
			//동적로딩 : 실행시점에서 객체를 생성하는 방법
			/* 컴파일 할 때는 단순 문자열에 불과하다. -> 컴파일 시점에는 드라이버가 있어도 그만, 없어도 그만.
			 * 실행 시 Driver 클래스를 찾아 메모리에 로딩한다. -> 따라서, 드라이버는 실행 시점에만 있으면 된다. 
			 * db교체 시, 이 부분(문자열)만 바꿔주면 된다. */
			Class.forName(driver);
			//Driver클래스(구현클래스) 로딩.
			try {
				//DB접속 연결 시도해서 성공하면 ***연결객체(Connection) 반환, 생성.***
				//DriverManager(인터페이스)
				//내부적으로 DriverManager(인터페이스), Driver클래스(구현클래스) 연결되어있다. (다형성) 
				conn = DriverManager.getConnection(URL, user, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dbClose() {
		
		try {
			//rs or ps or conn 객체가 생성되어있을 때만 close
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// DB에 회원 저장 동작
	public int memberInsert(MemberVO vo) {
		//																? = 파라메터 1,2,3,4,5,6
		String SQL = "insert into member(id, pass, name, age, email, phone) values(?,?,?,?,?,?)";
		//연결객체(Connection conn 생성.)
		getConnect();
		int count = -1;//쿼리 성공여부
		
		try {
			//prepareStatement(SQL) 메서드 호출. 
			//1. 불완전한 SQL문장을 DB에 전송 : 미리 컴파일(precompile) 시켜놔서 속도 up
			//2. PreparedStatement ps 객체 생성 : SQL문장을 전송하는 객체, 컴파일된 SQL문 갖고 있다.
			ps = conn.prepareStatement(SQL);
			//vo로부터 get -> set
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPass());
			ps.setString(3, vo.getName());
			ps.setInt(4, vo.getAge());
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			
			//완성된 SQL문 DB에 전송(실행)!! 성공이면 1, 실패면 0
			//update() : insert는 테이블 affected row 변화 수반함.
			count = ps.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
			//try or catch 무관하게 finally는 무조건 실행.
		} finally {
			dbClose();
		}
		
		return count;
	}

	public ArrayList<MemberVO> memberList() {
		
		String SQL = "select * from member";
		getConnect();
		
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			ps = conn.prepareStatement(SQL);
			//rs(결과집합, 커서) : 쿼리수행 -> select한 결과 담겨져있다.
			rs = ps.executeQuery(); 
			
			while(rs.next()) {
				//rs(table)에서 각 컬럼에 해당하는 값 가져오기. 
				int num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				//vo로 묶고. 
				MemberVO vo = new MemberVO(num, id, pass, name, age, email, phone);
				
				//list에 담고.
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return list;
		
	}

	public int memberDelete(int num) {
	
		String SQL = "delete from member where num = ?";
		getConnect();
		int count = -1;
		
		try {
			//불완전 쿼리 precompile 
			ps = conn.prepareStatement(SQL);
			
			//쿼리 완성 
			ps.setInt(1, num);
			
			//쿼리전송! 성공 1 실패 0, row의 수 변화O -> executeUpdate!
			count = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return count;

	}

	public MemberVO memberContent(int num) {
		
		String SQL = "select * from member where num = ?";
		getConnect();
		
		MemberVO vo = null;
		
		try {
			//불완전 쿼리 precompile 
			ps = conn.prepareStatement(SQL);
			
			//쿼리 완성 
			ps.setInt(1, num);
			
			//DB로 쿼리전송! 성공 1 실패 0, row의 수 변화O -> executeUpdate!
			rs = ps.executeQuery();
			

			if(rs.next()) {
				//rs(table)에서 각 컬럼에 해당하는 값 가져오기. 
				num = rs.getInt("num");
				String id = rs.getString("id");
				String pass = rs.getString("pass");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String email = rs.getString("email");
				String phone = rs.getString("phone");
				
				//vo로 묶기 . 
				vo = new MemberVO(num, id, pass, name, age, email, phone);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return vo;
	}

//	public void memberUpdate(int num, int age, String email, String phone) {
	public int memberUpdate(MemberVO vo) {
		
		String SQL = "update member set age=?, phone=?, email=? where num= ? ";
		getConnect();
		int cnt = -1;
		try {
			ps = conn.prepareStatement(SQL);
			
			ps.setInt(1, vo.getAge());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getEmail());
			ps.setInt(4, vo.getNum());
			
//			either (1) the row count for SQL Data Manipulation Language (DML) statements
//			or (2) 0 for SQL statements that return nothing
//			즉, affected row(변화가 생긴 행의 갯수!)
//			따라서, select(Q) / insert(U), delete(U), update(U)			
			cnt = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return cnt;
		
	}
	
	
	
	
	
}
