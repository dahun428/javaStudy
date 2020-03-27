package com.sample.bookstore.service;

import java.util.ArrayList;
import java.util.Arrays;

import com.sample.bookstore.vo.Book;

public class BookService {

	private Book[] db = new Book[100];
	private int position = 0;
	private int bookSequence = 20001;

	public BookService() {
		Book b1 = new Book(bookSequence++,"자바의 정석","남궁성",35000, 100);
		Book b2 = new Book(bookSequence++,"머신러닝 입문","안동현",32000, 100);
		Book b3 = new Book(bookSequence++,"경영의 대가","김동현",28000, 100);
		Book b4 = new Book(bookSequence++,"야자의 튐","정다훈",180000, 1000);

		db[position++] = b1;
		db[position++] = b2;
		db[position++] = b3;
		db[position++] = b4;
	}

	public void insertBook(Book book) {
		book.setNo(bookSequence);
		db[position] = book;

		position++;
		bookSequence++;
	}

	public Book findBookByNo(int bookNo) {
		Book result = null;
		for(int i = 0; i < position; i++) {
			Book book = db[i];
			if(bookNo == book.getNo()) {
				result = book;
				break;
			}	
		}
		return result;
	}


	public Book[] getAllfindBookByNo(int bookNo) {
		
		Book[] books = new Book[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(db[i].getNo() == bookNo) {
				books[count] = db[i];
				count++;
			}
		}
		
		return Arrays.copyOfRange(books, 0, count);
	}


	public Book[] findBookByTitle(String title) {
		ArrayList<Book> books = new ArrayList<Book>();
		for (int i=0; i<position; i++) {
			if (db[i].getTitle().contains(title)) {
				books.add(db[i]);
			}
		}
		Book[] result = new Book[books.size()];
		books.toArray(result);
		
		return result;
	}

	public void updateBook(Book book) {
		
		Book oldBook = findBookByNo(book.getNo());

		oldBook.setTitle(book.getTitle());
		oldBook.setWriter(book.getWriter());
		oldBook.setPrice(book.getPrice());
		oldBook.setStock(book.getStock());

	}

	public Book[] getAllBooks() {
		return Arrays.copyOfRange(db, 0, position);
	}

	public Book[] getAllBooks(int bookNo) {
		ArrayList<Book> books = new ArrayList<Book>();
		for(int i = 0; i < position; i++) {
			Book book = db[i];
			if(book.getNo() == bookNo) {
				books.add(book);
			}
		}
		Book[] result = new Book[books.size()];
		books.toArray(result);
		return result;
	}

}
