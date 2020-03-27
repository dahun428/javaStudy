package com.sample.bookstore.vo;

public class User {

	int no;
	String name;		//고객이름
	String tel;			//전화번호
	int point;
	boolean isDisabled; //탈퇴 여부 false : 사용가능 고객, true : 탈퇴 처리된 고객
	
	
	public User() {
		
	}
	
	public User(int no, String name, String tel, int point, boolean isDisabled) {
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.point = point;
		this.isDisabled = isDisabled;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public boolean isDisabled() {
		return isDisabled;
	}
	public void setDisabled(boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
