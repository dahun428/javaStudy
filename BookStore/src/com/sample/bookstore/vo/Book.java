package com.sample.bookstore.vo;

public class Book {

	int no;			//책 넘버
	String title;	//책 제목
	String writer;	//책 저자
	int price;		//책 가격
	int stock;		//재고
	
	public Book() {
		
	}
	
	public Book(int no, String title, String writer, int price, int stock) {
		super();
		this.no = no;
		this.title = title;
		this.writer = writer;
		this.price = price;
		this.stock = stock;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
}
