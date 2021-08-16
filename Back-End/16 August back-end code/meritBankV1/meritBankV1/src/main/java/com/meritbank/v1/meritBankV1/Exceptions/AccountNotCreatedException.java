package com.meritbank.v1.meritBankV1.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNotCreatedException extends Exception{

	public AccountNotCreatedException(String message){
		super(message);
	}
}
