package com.sample.bookstore.service;

import java.util.Arrays;

import com.sample.bookstore.vo.User;

public class UserService {

	private User[] db = new User[100];	
	private int position = 0;
	private int userSequence = 10001;

	public UserService() {
		User u1 = new User(userSequence++, "강현욱", "010-1234-4567", 100, false);
		User u2 = new User(userSequence++, "김안영", "010-1444-5567", 100, false);
		User u3 = new User(userSequence++, "최현성", "010-8234-4777", 100, false);
		User u4 = new User(userSequence++, "박성찬", "010-6234-7867", 100, false);

		db[position++] = u1;
		db[position++] = u2;
		db[position++] = u3;
		db[position++] = u4;

	}

	public void insertUser(User user) {
		user.setNo(userSequence);
		db[position] = user;
		position++;
		userSequence++;

	}

	public User findUserByNo(int userNo) {
		User result = null;
		for(int i = 0; i < position; i++) {
			User user = db[i];
			if(userNo == user.getNo()) {
				result = user;
				break;
			}
		}
		return result;
	}

	public void updateUser(User user) {

		User oldUser = findUserByNo(user.getNo());
		oldUser.setName(user.getName());
		oldUser.setTel(user.getTel());
	}
	
	public User[] getAllUsers() {
		return Arrays.copyOfRange(db, 0, position);
	}
	

}
