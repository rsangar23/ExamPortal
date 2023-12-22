package com.pariksha.helper;

public class UserFoundException extends Exception {
	
	public UserFoundException() {
		super("User is already there in DB!! try with another one");
	}
	
	public UserFoundException(String msg) {
		super(msg);
	}

}
