package com.example.ConsumerBank.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ConsumerBank.Dto.AccountDto;
import com.example.ConsumerBank.Dto.AccountResponseDto;
import com.example.ConsumerBank.Dto.CustomerNewResponseDto;
import com.example.ConsumerBank.Dto.CustomerRequestDto;
import com.example.ConsumerBank.Dto.CustomerRequestUpdateDto;
import com.example.ConsumerBank.Dto.CustomerResponseDto;
import com.example.ConsumerBank.Service.CustomerService;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	CustomerRequestDto customerRequestDto;

	CustomerRequestUpdateDto customerRequestUpdateDto;

	@BeforeEach
	public void SetUp() {

		customerRequestDto = new CustomerRequestDto();
		customerRequestDto.setCustomerName("Ankit");
		customerRequestDto.setPhoneNo("997799887");
		customerRequestDto.setAddress("Kurnool");
	}

	@Test
	@DisplayName("Save customer Data:positive")
	public void saveCustomerDataTest_Positive() {

		Mockito.when(customerService.saveCustomerData(Mockito.any())).thenReturn("Details Added Successfully");

		ResponseEntity<String> result = customerController.saveCustomerData(customerRequestDto);

		assertEquals("Details Added Successfully", result.getBody().toString());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}

	@Test
	public void getCustomerDetailsTest_positive() throws CustomerNotFoundException {
		CustomerNewResponseDto customerNewResponseDto = new CustomerNewResponseDto();
		customerNewResponseDto.setCustomerName("ankith");
		customerNewResponseDto.setCustomerId(2);
		customerNewResponseDto.setPhoneNo("999999");
		customerNewResponseDto.setAddress("Hyderabad");

		List<AccountResponseDto> accountResponseDtoList = new ArrayList<AccountResponseDto>();
		AccountResponseDto accountResponseDto = new AccountResponseDto();
	//	accountDto.setAccountId(1);
	//	accountDto.setAccountNumber(1111L);
		accountResponseDto.setAccountType("svings");
		accountResponseDto.setBalance(9000D);
	//	accountResponseDto.setCustomerId(2);

		accountResponseDtoList.add(accountResponseDto);

		customerNewResponseDto.setAccounts(accountResponseDtoList);

		Mockito.when(customerService.getCustomerDetailsById(Mockito.any())).thenReturn(customerNewResponseDto);

		ResponseEntity<CustomerNewResponseDto> customerNewResponseEntity = customerController.getCustomerDetails(2);

		assertEquals(customerNewResponseDto.getCustomerId(), customerNewResponseEntity.getBody().getCustomerId());
		assertEquals(HttpStatus.OK, customerNewResponseEntity.getStatusCode());
	}

	@Test
	public void updateCustomerdata() throws CustomerNotFoundException {

		Mockito.when(customerService.updateCustomerDetails(Mockito.any())).thenReturn("updated customer details");
		CustomerRequestUpdateDto customerRequestUpdateDto=new CustomerRequestUpdateDto();
		ResponseEntity<String> result = customerController.updateCustomerdata(customerRequestUpdateDto);

		assertEquals("updated customer details", result.getBody().toString());
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());

	}

}
