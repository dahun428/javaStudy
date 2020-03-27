package com.sample.bookstore.service;

import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.Rental;
import com.sample.bookstore.vo.User;

public class BookstoreService {

	BookService bs = new BookService();
	UserService us = new UserService();
	RentalService rs = new RentalService();

	//회원등록 서비스
	public void addNewUser(String name, String tel) {
		User user = new User();
		user.setName(name);
		user.setTel(tel);
		user.setPoint(100);
		user.setDisabled(false);
		us.insertUser(user);

		System.out.println("--------------------------");
		System.out.println("회원번호를 확인하세요");
		System.out.println("나의 회원 번호 : " + user.getNo());

	}


	//회원조회 서비스
	public void retrieveUserInfo(int userNo) {

		User user = us.findUserByNo(userNo);

		if(user == null) {
			System.out.println("존재하지 않은 회원입니다.");
			return;
		}

		if(user.isDisabled()) {
			System.out.println("탈퇴한 회원입니다.");
			return;
		}

		System.out.print(user.getNo()+"\t");
		System.out.print(user.getName()+"\t");
		System.out.print(user.getTel()+"\t");
		System.out.print(user.getPoint()+"\t");
		System.out.println(user.isDisabled());

	}


	//회원정보 수정 서비스
	public void modifyUserInfo(User user) {

		User chkUser = us.findUserByNo(user.getNo());

		if(chkUser == null) {
			System.out.println("존재하지 않은 회원입니다.");
			return;
		}

		if(chkUser.isDisabled()) {
			System.out.println("탈퇴한 회원입니다.");
			return;
		}
//		this.chkUserNo(chkUser);
		

		us.updateUser(user);
		this.retrieveUserInfo(user.getNo());

		System.out.println("성공적으로 정보가 수정되었습니다.");

	}



	//회원탈퇴 서비스
	public void disabledUser(int userNo) {

		User user = us.findUserByNo(userNo);
		this.chkUserNo(user);

		user.setDisabled(true);
		user.setPoint(0);

		//일괄반납처리(rental isRent 값을 false로 만들기)
		rs.returnAllRentalByUserNo(user.getNo());

		//해당 유저가 가지고 있는 책들의 재고를 1올려준다.
		//렌탈의 책 넘버와 일치하는 책의 재고를 1 올려준다.
		Rental[] rentals = rs.findRentalByUserNoArray(user.getNo());
		for(Rental rental : rentals) {
			Book[] books = bs.getAllfindBookByNo(rental.getBookNo());
			for(Book book : books) {
				book.setStock(book.getStock()+1);
			}
		}

	}


	// 전체 회원조회 서비스
	public void retrieveAllUsers() {
		
		User[] users = us.getAllUsers();
		
		for(User user : users) {
			System.out.println(user.getNo()+"\t"+user.getName()+"\t"+user.getTel()+"\t"
					+user.getPoint()+"\t"+user.isDisabled());
		}

	}

	// 도서등록 서비스
	public void insertNewBook(String title, String writer, int price) {
		Book book = new Book();
		book.setTitle(title);
		book.setWriter(writer);
		book.setPrice(price);
		book.setStock(10000);
		bs.insertBook(book);
		
	}



	// 도서 검색 서비스
	public void searchBooks(String title) {
		
		Book[] books = bs.findBookByTitle(title);
		
		for(Book book : books) {
			System.out.print(book.getNo()+"\t");
			System.out.print(book.getTitle()+"\t");
			System.out.print(book.getWriter()+"\t");
			System.out.print(book.getPrice()+"\t");
			System.out.println(book.getStock());
		}

	}

	// 도서 정보 수정 서비스
	public void modifyBook(Book book) {
		
		Book chkBook = bs.findBookByNo(book.getNo());
//		this.chkBookNo(chkBook);
		if(chkBook == null) {
			System.out.println("존재하지 않은 도서입니다.");
			return;
		}
		
		
		bs.updateBook(book);
		//정보수정 확인 
		searchBooks(book.getTitle());

		System.out.println("성공적으로 책 정보가 수정 되었습니다.");
	}

	// 전체도서 조회 서비스
	public void retrieveAllBooks() {
		Book[] books = bs.getAllBooks();

		if(books == null) {
			System.out.println("도서 목록이 없습니다.");
			return;
		}
		
		for(int i = 0; i < books.length; i++) {
			System.out.println(books[i].getNo()+"\t     "+books[i].getTitle()+"\t"+books[i].getWriter()
					+"\t"+books[i].getPrice()+"\t"+books[i].getStock());
		}
	}

	// 대여 서비스
	public void rentBook(int userNo, int bookNo) {
		
		Rental rental = new Rental();

		User user = us.findUserByNo(userNo);
		Book book = bs.findBookByNo(bookNo);
		
		if (user == null) {
			System.out.println("등록되지 않은 회원입니다.");
			return;
		}
		
		if(user.isDisabled()) {
			System.out.println("탈퇴처리된 회원입니다.");
			return;
		}
		
		 if (book == null) {
			System.out.println("책정보가 존재하지 않습니다.");
			return;
		}
				
		rental.setUserNo(user.getNo());
		rental.setBookNo(book.getNo());
		rental.setRent(true);
		rs.insertRental(rental);

		book.setStock(book.getStock()-1);

	}

	// 반납 서비스
	public void returnBook(int bookNo) {
		
		
		Rental rental = rs.findRentalByNo(bookNo);
		
		if(rental == null) {
			System.out.println("유효하지 않은 대여번호 입니다.");
			return;
		}
		if(rental.isRent()) {
			System.out.println("이미 반납된 도서입니다.");
			return;
		}
		
		rental.setRent(false);
		
		User user = us.findUserByNo(rental.getUserNo());
		this.chkUserNo(user);
		
		Book book = bs.findBookByNo(rental.getBookNo());
		this.chkBookNo(book);
		
		user.setPoint(user.getPoint()+ (int)(book.getPrice() * 0.01));
		book.setStock(book.getStock()+1);
		
	}

	// 대여현황 조회 서비스
	public void retrieveAllRentals() {
		Rental[] rental = rs.getAllRentals();
		if(rental[0] == null) {
			System.out.println("대여 현황이 존재하지 않습니다.");
			return;
		}
		
		for(int i = 0; i < rental.length; i++) {
			System.out.println(rental[i].getNo()+"\t"+rental[i].getUserNo()+"\t"
					+rental[i].getBookNo()+"\t"+rental[i].isRent());
		}
		
	}

	//개인회원 대여조회 서비스
	public void retrieveUserRentals(int userNo) {

		User user = us.findUserByNo(userNo);
		if(user == null) {
			System.out.println("해당하는 회원이 존재하지 않습니다.");
			return;
		}
			
		Rental[] rentals = rs.findRentalByUserNoArray(user.getNo());

		for(Rental rental : rentals) {
			Book[] books = bs.getAllfindBookByNo(rental.getBookNo());
			for(Book book : books) {
				System.out.println(user.getNo()+"\t"+user.getName()+"\t"
						+rental.getBookNo()+"\t"+book.getTitle()+"\t"
						+rental.getNo()+"\t"+rental.isRent());
			}
		}
	}

	private void chkUserNo(User chkUser) {
		if(chkUser == null) {
			System.out.println("존재하지 않은 회원입니다.");
			return;
		}

		if(chkUser.isDisabled()) {
			System.out.println("탈퇴한 회원입니다.");
			return;
		}
	}
	
	private void chkBookNo(Book chkBook) {
		if(chkBook == null) {
			System.out.println("존재하지 않은 도서입니다.");
			return;
		}
	}
}
