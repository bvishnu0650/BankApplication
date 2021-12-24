package com.example.ConsumerBank.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConsumerBank.Dto.AccountRequestDto;
import com.example.ConsumerBank.Dto.AccountResponseDto;
import com.example.ConsumerBank.Dto.CustomerNewResponseDto;
import com.example.ConsumerBank.Dto.CustomerRequestDto;
import com.example.ConsumerBank.Dto.CustomerRequestUpdateDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Entity.Customer;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.Service.CustomerService;
import com.example.ConsumerBank.exception.CustomerNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public String saveCustomerData(CustomerRequestDto customerRequestDto) {
		logger.info(customerRequestDto.toString());
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerRequestDto, customer);
	//	=List<Account> accountlist
		List<Account> accounts =customerRequestDto.getAccounts().parallelStream().map(accountRequestDto->{
			Account account=new Account();
			BeanUtils.copyProperties(accountRequestDto, account);
			return account;
		}).collect(Collectors.toList());
		customer.setAccounts(accounts);
		Customer savedCustomer = customerRepository.save(customer);
		if (savedCustomer != null)
			return "Details Added Successfully";
		return "Details Added Unsuccessfully";

	}

	@Override
	public CustomerNewResponseDto getCustomerDetailsById(Integer customerId) throws CustomerNotFoundException {
		logger.info("getCustomerDetailsById - customerId" + customerId);
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (!customer.isPresent()) {
			throw new CustomerNotFoundException("Customer not found");
		}

		CustomerNewResponseDto responseDto = new CustomerNewResponseDto();
		BeanUtils.copyProperties(customer.get(), responseDto);
		List<AccountResponseDto> accountResponseDtoList = 
				customer.get().getAccounts().parallelStream().map(account -> {
			AccountResponseDto accountResponseDto = new AccountResponseDto();
			BeanUtils.copyProperties(account, accountResponseDto);
		//	accountResponseDto.setCustomerId(account.getCustomer().getCustomerId());
			return accountResponseDto;
		}).collect(Collectors.toList());
		responseDto.setAccounts(accountResponseDtoList);
		return responseDto;
	}
	
	@Override
	public List<CustomerNewResponseDto> getCustomerDetails() {
		List<Customer> customerlist = customerRepository.findAll();
		List<CustomerNewResponseDto> customerNewResponseDtoList = customerlist.parallelStream().map(customer -> {
			CustomerNewResponseDto customerNewResponseDto = new CustomerNewResponseDto();
			BeanUtils.copyProperties(customer, customerNewResponseDto);
			List<AccountResponseDto> accountResponseDtoList = customer.getAccounts().parallelStream().map(account -> {
				AccountResponseDto accountResponseDto = new AccountResponseDto();
				BeanUtils.copyProperties(account, accountResponseDto);
			//	accountResponseDto.setCustomerId(account.getCustomer().getCustomerId());
				return accountResponseDto;
			}).collect(Collectors.toList());
			customerNewResponseDto.setAccounts(accountResponseDtoList);
			return customerNewResponseDto;
		}).collect(Collectors.toList());
		return customerNewResponseDtoList;
	}

	

	@Override
	public String updateCustomerDetails(CustomerRequestUpdateDto customerRequestUpdateDto)
			throws CustomerNotFoundException {
		Optional<Customer> customerOptional = customerRepository.findById(customerRequestUpdateDto.getCustomerId());

		if (!customerOptional.isPresent()) {
			logger.error("CustomerId is Not Found for CustomerId:" + customerRequestUpdateDto.getCustomerId());
			throw new CustomerNotFoundException(
					"customerId:" + customerRequestUpdateDto.getCustomerId() + " Not found ");
		}
		
		BeanUtils.copyProperties(customerRequestUpdateDto, customerOptional.get());
		Customer savedCustomer = customerRepository.save(customerOptional.get());
		if (savedCustomer != null)
			return "Updated details Successfully";
		return "updated details UnSuccessfull";

	}

}