package com.example.ConsumerBank.Controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConsumerBank.Dto.AccountDto;
import com.example.ConsumerBank.Service.AccountService;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@RestController
@Validated
public class AccountController {
	@Autowired
	AccountService accountService;
	
	Logger logger = LoggerFactory.getLogger(AccountController.class);

	/*
	 * @PostMapping("/accounts") public ResponseEntity<String>
	 * saveAccount(@Valid @RequestBody AccountDto accountDto) throws
	 * CustomerNotFoundException { //logger.info("AccountId:" +
	 * accountDto.getAccountId()); String response =
	 * accountService.saveAccount(accountDto); return new
	 * ResponseEntity<String>(response, HttpStatus.ACCEPTED); }
	 */
	
	@GetMapping("/accounts")
	public ResponseEntity<List<AccountDto>> getAccounts() {
		List<AccountDto> accountsList = accountService.getAccounts();
		return new ResponseEntity<List<AccountDto>>(accountsList, HttpStatus.OK);
	}

}
