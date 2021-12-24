package com.example.ConsumerBank.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ConsumerBank.Dto.CustomerNewResponseDto;
import com.example.ConsumerBank.Dto.CustomerRequestDto;
import com.example.ConsumerBank.Dto.CustomerRequestUpdateDto;
import com.example.ConsumerBank.Dto.CustomerResponse;
import com.example.ConsumerBank.Dto.CustomerResponseDto;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@Service
public interface CustomerService {

	String saveCustomerData(CustomerRequestDto customerRequestDto);

	CustomerNewResponseDto getCustomerDetailsById(Integer customerId) throws CustomerNotFoundException;

	List<CustomerNewResponseDto> getCustomerDetails();

	String updateCustomerDetails(CustomerRequestUpdateDto customerRequestUpdateDto) throws CustomerNotFoundException;

}
