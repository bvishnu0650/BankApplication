package com.example.ConsumerBank.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ConsumerBank.Dto.AccountDto;
import com.example.ConsumerBank.Service.AccountService;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {
	@InjectMocks
	AccountController accountController;
	@Mock
	AccountService accountService;

	/*
	 * @Test public void saveAccount() throws CustomerNotFoundException { AccountDto
	 * accountDto = new AccountDto(); // accountDto.setAccountId(1); //
	 * accountDto.setAccountNumber(123456789l); accountDto.setAccountType("saving");
	 * accountDto.setBalance(20000d); accountDto.setCustomerId(2);
	 * 
	 * Mockito.when(accountService.saveAccount(Mockito.any())).
	 * thenReturn("Account added successfully");
	 * 
	 * ResponseEntity<String> responseEntity =
	 * accountController.saveAccount(accountDto);
	 * 
	 * assertEquals("Account added successfully",
	 * responseEntity.getBody().toString()); }
	 */
}
