package kr.bit.model;

//현실세계의 회원(Object) -> MemberVO 데이터 타입
//class : 새로운 자료를 만드는 모델링 도구.
public class MemberVO {
	
	private int num;
	private String id;
	private String pass;
	private String name;
	private int age;
	private String email;
	private String phone; 
	private String filename; 
	
	//기본생성자
	public MemberVO() {}
	
	//회원가입 시, 입력 생성자
	public MemberVO(String id, String pass, String name, int age, String email, String phone) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	
	//DB에서 데이터 출력 후, VO 생성자
	public MemberVO(int num, String id, String pass, String name, int age, String email, String phone) {
		this.num = num;
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = phone;
	}
	
	//getter, setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "MemberVO [num=" + num + ", id=" + id + ", pass=" + pass + ", name=" + name + ", age=" + age + ", email="
				+ email + ", phone=" + phone + ", filename=" + filename + "]";
	}

	
	
	
	
	
	
	
	
}
