-- test DB
-- member(회원) table

create table member (
 --primary key : 중복허용 X, null허용 X
 num int primary key auto_increment,
 id varchar(20) not null,
 pass varchar(20) not null,
 name varchar(30) not null,
 age int not null,
 email varchar(30) not null,
 phone varchar(30) not null,
 --unique key : 중복허용 X, null허용 O(id는 null허용X : JS, JQuery -> 유효성 검사하므로 문제x)
 unique key(id)
)

drop table member;

-------------------------** CRUD(create, read, update, delete)

--select(조회, 검색)
select * from member;

--insert(저장)
insert into member(id, pass, name, age, email, phone)
values('admin', 'admin', '관리자', 40, 'kim@naver.com', '010-1111-1111'); 

--update(수정)
update member set age=45, phone='010-1111-0000' where id='kimks071';

--delete(삭제)
delete from member where id='admin';
delete from member;