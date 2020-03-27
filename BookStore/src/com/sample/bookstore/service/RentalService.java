package com.sample.bookstore.service;

import java.util.Arrays;

import com.sample.bookstore.vo.Rental;

public class RentalService {


	private Rental[] db = new Rental[100];
	private int position = 0;
	private int rentalSequence = 50001;

	public void insertRental(Rental rental) {
		rental.setNo(rentalSequence);
		db[position] = rental;	

		position++;
		rentalSequence++;	
	}

	public Rental findRentalByNo(int rentalNo) {
		Rental result = null;
		for(int i = 0; i < position; i++) {
			Rental rental = db[i];
			if(rental.getNo() == rentalNo) {
				result = rental;
				break;
			}
		}	
		return result;
	}

	
	public Rental[] findRentalByUserNoArray(int userNo) {
		Rental[] rentals = new Rental[position];
		int count = 0;
		for(int i = 0; i < position; i++) {
			if(db[i].getUserNo()==userNo) {
				rentals[count] = db[i];
				count++;
			}
		}
		return Arrays.copyOfRange(rentals, 0, count);
	}

	
	public void returnAllRentalByUserNo(int userNo) {

		Rental[] rentals = this.findRentalByUserNoArray(userNo);
		for(int i = 0; i < rentals.length; i++) {
			rentals[i].setRent(false);
		}
	}

	
	public Rental[] getAllRentals() {

		return Arrays.copyOfRange(db, 0, position);
	}

}
