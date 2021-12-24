package com.example.ConsumerBank.ServiceImpl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import com.example.ConsumerBank.Dto.AccountRequestDto;
import com.example.ConsumerBank.Dto.CustomerNewResponseDto;
import com.example.ConsumerBank.Dto.CustomerRequestDto;
import com.example.ConsumerBank.Dto.CustomerRequestUpdateDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Entity.Customer;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
	@Mock
	CustomerRepository customerRepository;
	@InjectMocks
	CustomerServiceImpl customerServiceImpl;

	CustomerRequestDto customerRequestDto;

	CustomerRequestUpdateDto customerRequestUpdateDto = new CustomerRequestUpdateDto();

	Customer customer = new Customer();

	@BeforeEach
	public void setup() {
		customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setCustomerName("Ankit");
		customerRequestDto.setPhoneNo("997799887");
		customerRequestDto.setAddress("Kurnool");

		customer.setCustomerId(2);
		customerRequestUpdateDto.setCustomerId(2);
		BeanUtils.copyProperties(customerRequestDto, customer);

	}

	@Test
	public void saveCustomerDataTest() {

		CustomerRequestDto customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setAddress("Hyderabad");
		customerRequestDto.setCustomerName("vishnu");
		customerRequestDto.setPhoneNo("900099900");

		List<AccountRequestDto> accounts = new ArrayList<AccountRequestDto>();
		AccountRequestDto accountRequestDto = new AccountRequestDto();
		accountRequestDto.setAccountType("Savings");
		accountRequestDto.setBalance(10000D);

		accounts.add(accountRequestDto);
		customerRequestDto.setAccounts(accounts);

		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

		String actual = customerServiceImpl.saveCustomerData(customerRequestDto);

		assertEquals("Details Added Successfully", actual);
	}

	@Test
	public void updateCustomerDataTest() throws CustomerNotFoundException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(customer);

		String actual = customerServiceImpl.updateCustomerDetails(customerRequestUpdateDto);

		assertEquals("Updated details Successfully", actual);
	}

	@Test
	public void updateCustomerDataTest_Exception() throws CustomerNotFoundException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		Throwable throwable = assertThrows(Throwable.class, () -> {
			customerServiceImpl.updateCustomerDetails(customerRequestUpdateDto);
		});
		assertEquals(CustomerNotFoundException.class, throwable.getClass());
	}

	@Test
	public void updateCustomerDataTest_unsuccessfull() throws CustomerNotFoundException {
		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));
		Mockito.when(customerRepository.save(Mockito.any())).thenReturn(null);

		String actual = customerServiceImpl.updateCustomerDetails(customerRequestUpdateDto);

		assertEquals("updated details UnSuccessfull", actual);
	}

	@Test
	public void getCustomerDetailsById() throws CustomerNotFoundException {

		Customer customer = new Customer();
		customer.setCustomerName("ankith");
		customer.setCustomerId(2);
		customer.setPhoneNo("999999");
		customer.setAddress("Hyderabad");

		List<Account> accountList = new ArrayList<Account>();
		Account account = new Account();
		account.setAccountId(1);
		account.setAccountNumber(1111L);
		account.setAccountType("svings");
		account.setBalance(9000D);

		accountList.add(account);

		customer.setAccounts(accountList);

		Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));

		CustomerNewResponseDto responseDtos = customerServiceImpl.getCustomerDetailsById(2);
		assertEquals(responseDtos.getAccounts().size(), responseDtos.getAccounts().size());
	}

}
