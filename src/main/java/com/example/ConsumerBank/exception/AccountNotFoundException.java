package com.example.ConsumerBank.exception;

public class AccountNotFoundException extends Exception {
	public AccountNotFoundException(String message) {
		super(message);
	}
}
