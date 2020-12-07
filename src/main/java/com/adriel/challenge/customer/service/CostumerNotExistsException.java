package com.adriel.challenge.customer.service;

public class CostumerNotExistsException extends RuntimeException {
	private static final long serialVersionUID = 4140060175064531047L;

	public CostumerNotExistsException() {
		super("Account not exists");
	}
	
}
