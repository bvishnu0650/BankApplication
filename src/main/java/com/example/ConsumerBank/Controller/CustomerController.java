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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConsumerBank.Dto.CustomerNewResponseDto;
import com.example.ConsumerBank.Dto.CustomerRequestDto;
import com.example.ConsumerBank.Dto.CustomerRequestUpdateDto;
import com.example.ConsumerBank.Dto.CustomerResponseDto;
import com.example.ConsumerBank.Service.CustomerService;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@RestController
@Validated
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<String> saveCustomerData(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
		// logger.info("CustomerId:" + customerRequestDto.getCustomerId());
		String response = customerService.saveCustomerData(customerRequestDto);
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<CustomerNewResponseDto>> getCustomerDetails() {
		List<CustomerNewResponseDto> customerslist = customerService.getCustomerDetails();
		return new ResponseEntity<List<CustomerNewResponseDto>>(customerslist, HttpStatus.OK);
	}

	@GetMapping("/customers/{customerId}")
	public ResponseEntity<CustomerNewResponseDto> getCustomerDetails(@PathVariable Integer customerId)
			throws CustomerNotFoundException {
		logger.info("getCustomerDetailsById-customerId:" + customerId);
		CustomerNewResponseDto customerNewResponseDto = customerService.getCustomerDetailsById(customerId);
		return new ResponseEntity<CustomerNewResponseDto>(customerNewResponseDto, HttpStatus.OK);
	}

	@PutMapping("/customers")
	public ResponseEntity<String> updateCustomerdata(@Valid @RequestBody CustomerRequestUpdateDto customerRequestUpdateDto)
			throws CustomerNotFoundException {
		logger.info("updateCustomerdata" + customerRequestUpdateDto.toString());
		String response = customerService.updateCustomerDetails(customerRequestUpdateDto);
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
	}

}
