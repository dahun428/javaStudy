package com.sample.bookstore;

import java.util.Scanner;


import com.sample.bookstore.service.BookstoreService;
import com.sample.bookstore.vo.Book;
import com.sample.bookstore.vo.User;

public class BookstoreAPP {
	public static void main(String[] args) {

		BookstoreService service = new BookstoreService();
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println();
			System.out.println("--------------------------------------------");
			System.out.println("	[도서 대여점 관리 프로그램]");
			System.out.println("============================================");
			System.out.println("	1.회원관리	2.도서관리	3.대여관리	0.종료");
			System.out.println("============================================");
			System.out.print("	메뉴를 선택하세요 :  ");
			int menuNum = sc.nextInt();

			//회원관리
			if(menuNum == 1) {
				System.out.println();
				System.out.println("--------------------------------------------");
				System.out.println("	[회원관리]");
				System.out.println("====================================================");
				System.out.println("	1.등록	2.조회	3.변경	4.탈퇴	5.전체조회");
				System.out.println("====================================================");
				System.out.print("	회원관련 메뉴를 선택하세요 :  ");
				int userMenuNum = sc.nextInt();

				//등록
				if(userMenuNum == 1) {
					System.out.println();
					System.out.println("	[신규 회원 등록]");
					System.out.print("이름을 입력하세요 :  ");
					String name = sc.next();
					System.out.print("전화번호를 입력하세요 : ");
					String tel = sc.next();
					System.out.println("### 회원등록이 완료되었습니다 ###");
					service.addNewUser(name, tel);
					System.out.println();

					//조회
				}else if(userMenuNum == 2) {
					System.out.println();
					System.out.println("	[회원 조회]");
					System.out.print("회원 번호를 입력하세요 : ");
					int userNo = sc.nextInt();
					System.out.println();
					System.out.println("회원번호	회원명	전화번호		적립포인트	탈퇴여부");
					System.out.println("===================================================");

					service.retrieveUserInfo(userNo);

				}else if(userMenuNum == 3) {
					System.out.println();
					System.out.println("	[회원 정보 변경]");
					System.out.println();
					System.out.print("회원 번호를 입력하세요 : ");
					int userNo = sc.nextInt();
					System.out.print("수정할 이름을 입력하세요 : ");
					String name = sc.next();
					System.out.print("수정할 전화번호를 입력하세요 : ");
					String tel = sc.next();

					User user = new User();
					user.setNo(userNo);
					user.setName(name);
					user.setTel(tel);

					service.modifyUserInfo(user);

				}else if(userMenuNum == 4) {

					System.out.println();
					System.out.println("	[회원 탈퇴]");
					System.out.println();
					System.out.print("회원 번호를 입력하세요 : ");
					int userNo = sc.nextInt();
					service.disabledUser(userNo);

				}else if(userMenuNum == 5) {

					System.out.println();
					System.out.println("	[회원 전체 조회]");
					System.out.println();
					System.out.println("회원번호	회원명	전화번호		적립포인트	탈퇴여부");
					System.out.println("=====================================================");
					service.retrieveAllUsers();

				}else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}

				//도서관리
			}else if(menuNum == 2) {
				System.out.println();
				System.out.println("[도서 관리]");
				System.out.println("================================================");
				System.out.println(" 1.검색  2.등록  3.수정  4.전체조회");
				System.out.println("================================================");
				System.out.print("메뉴를 입력해주세요 : ");
				int bookMenuNum = sc.nextInt();

				if(bookMenuNum == 1) {
					System.out.println();
					System.out.println("	[도서 검색]");
					System.out.print("검색할 도서 제목을 입력해주세요 : ");
					String title = sc.next();
					System.out.println("책번호	책제목	저자	가격	재고");
					System.out.println("===================================================");
					
					service.searchBooks(title);

				}else if(bookMenuNum == 2) {
					System.out.println();
					System.out.println("	[도서 등록]");
					System.out.print("도서 제목을 입력해주세요 : ");
					String title = sc.next();
					System.out.print("도서 저자를 입력해주세요 : ");
					String writer = sc.next();
					System.out.print("도서 가격을 입력해주세요 : ");
					int price = sc.nextInt();

					service.insertNewBook(title, writer, price);
					System.out.println("도서가 성공적으로 등록되었습니다.");

				}else if(bookMenuNum == 3) {
					System.out.println();
					System.out.println("	[도서 수정]");
					System.out.print("수정할 책 번호를 입력해주세요 : ");
					int no = sc.nextInt();
					System.out.print("수정할 제목을 입력해주세요 : ");
					String title = sc.next();
					System.out.print("수정할 저자를 입력해주세요 : ");
					String writer = sc.next();
					System.out.print("수정할 가격을 입력해주세요 : ");
					int price = sc.nextInt();
					System.out.print("수정할 재고를 입력해주세요 : ");
					int stock = sc.nextInt();
					
					Book book = new Book();
					book.setNo(no);
					book.setTitle(title);
					book.setWriter(writer);
					book.setPrice(price);
					book.setStock(stock);
					service.modifyBook(book);

				}else if(bookMenuNum == 4){

					System.out.println("[도서 전체 조회]");
					System.out.println();
					System.out.println("================================================");
					System.out.println("도서번호	도서제목		저자	가격	재고");
					System.out.println("================================================");

					service.retrieveAllBooks();

				}else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}

			}else if(menuNum == 3) {
				System.out.println();
				System.out.println("--------------------------------------------");
				System.out.println("	[대여/반납 관리]");
				System.out.println("=======================================================");
				System.out.println("	1.대여	2.반납	3.대여현황조회	4.개인대여현황 조회");
				System.out.println("=======================================================");
				System.out.print("메뉴를 입력해주세요  : ");
				int RentalManageNum = sc.nextInt();
				
				if(RentalManageNum == 1) {
					System.out.println();
					System.out.println("	[도서 대여]");
					System.out.print("회원 번호를 입력해주세요 : ");
					int userNo = sc.nextInt();
					System.out.print("대여할 도서번호를 입력해주세요 : ");
					int bookNo = sc.nextInt();
					service.rentBook(userNo, bookNo);

					System.out.println("대여가 완료되었습니다.");
				}else if(RentalManageNum == 2) {
					System.out.println();
					System.out.println("	[도서 반납]");
					System.out.print("반납할 대여 번호를 입력해주세요 : ");
					int no = sc.nextInt();

					service.returnBook(no);
					System.out.println("반납이 완료되었습니다.");

				}else if(RentalManageNum == 3) {
					System.out.println();
					System.out.println("	[대여 현황 조회]");
					System.out.println();
					System.out.println("================================================");
					System.out.println("대여번호	회원번호	도서번호	대여현황	");
					System.out.println("================================================");

					service.retrieveAllRentals();

				}else if(RentalManageNum == 4){

					System.out.println();
					System.out.println("	[개인 대여 현황 조회]");

					System.out.print("회원 번호를 입력해주세요 : ");
					int userNo = sc.nextInt();
					System.out.println();
					System.out.println("====================================================");
					System.out.println("회원번호	회원이름	도서번호	도서이름	대여번호	대여현황");
					System.out.println("====================================================");

					service.retrieveUserRentals(userNo);

				}else {
					System.out.println("잘못된 입력입니다. 다시 입력해주세요");
				}

			}else if(menuNum == 0) {
				System.out.println();
				System.out.println("--------------------------------------------");
				System.out.println("		[프로그램 종료]");
				System.exit(0);

			}else {
				System.out.println("잘못된 입력입니다. 다시 입력해주세요");
			}
		}
	}
}
