package com.example.ConsumerBank.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ConsumerBank.Dto.AccountDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Entity.Customer;
import com.example.ConsumerBank.Repository.AccountRepository;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
	@InjectMocks
	AccountServiceImpl accountServiceImpl;
	@Mock
	CustomerRepository customerRepository;
	@Mock
	AccountRepository accountRepository;

	/*
	 * @Test public void saveAccount() throws CustomerNotFoundException { Customer
	 * customer = new Customer(); customer.setCustomerName("ankith");
	 * customer.setCustomerId(2); customer.setPhoneNo("999999");
	 * customer.setAddress("Hyderabad");
	 * 
	 * AccountDto accountDto = new AccountDto(); // accountDto.setAccountId(1); //
	 * accountDto.setAccountNumber(123456789l); accountDto.setAccountType("saving");
	 * accountDto.setBalance(20000d); accountDto.setCustomerId(2);
	 * Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.
	 * of(customer));
	 * Mockito.when(accountRepository.save(Mockito.any())).thenReturn(new
	 * Account());
	 * 
	 * String response = accountServiceImpl.saveAccount(accountDto);
	 * assertEquals("Account added successfully", response);
	 * 
	 * }
	 * 
	 * @Test public void saveAccount_unsuccssfully() throws
	 * CustomerNotFoundException {
	 * 
	 * Customer customer = new Customer(); customer.setCustomerName("ankith");
	 * customer.setCustomerId(2); customer.setPhoneNo("999999");
	 * customer.setAddress("Hyderabad");
	 * 
	 * AccountDto accountDto = new AccountDto(); // accountDto.setAccountId(1); //
	 * accountDto.setAccountNumber(123456789l); accountDto.setAccountType("saving");
	 * accountDto.setBalance(20000d); accountDto.setCustomerId(2);
	 * Mockito.when(accountRepository.save(Mockito.any())).thenReturn(null);
	 * Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.
	 * of(customer)); String response = accountServiceImpl.saveAccount(accountDto);
	 * assertEquals("Account added unsuccessfully", response);
	 * 
	 * }
	 */

}
