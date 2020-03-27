package com.sample.bookstore.vo;

public class Rental {

	int no;		//
	int userNo;	//고객 넘버
	int bookNo;	//책 넘버
	boolean isRent;	//빌린 상태인지 여부 true : 대여중, false : 반납완료
	
	public Rental() {
		
	}
	
	public Rental(int no, int userNo, int bookNo, boolean isRent) {
		this.no = no;
		this.userNo = userNo;
		this.bookNo = bookNo;
		this.isRent = isRent;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}
	
	
	
	
}
