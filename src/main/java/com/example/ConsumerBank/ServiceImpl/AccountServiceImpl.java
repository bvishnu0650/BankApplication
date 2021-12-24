package com.example.ConsumerBank.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ConsumerBank.Dto.AccountDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Repository.AccountRepository;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.Service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository curtomerRepository;
	/*
	 * @Override public String saveAccount(AccountDto accountDto) throws
	 * CustomerNotFoundException { logger.info(accountDto.toString());
	 * Optional<Customer> customerOptional =
	 * curtomerRepository.findById(accountDto.getCustomerId()); if
	 * (!customerOptional.isPresent()) { throw new
	 * CustomerNotFoundException("Customer id not found to create account"); }
	 * Account account = new Account(); BeanUtils.copyProperties(accountDto,
	 * account); account.setCustomer(customerOptional.get()); Account saveAccount =
	 * accountRepository.save(account); if (saveAccount != null) return
	 * "Account added successfully"; return "Account added unsuccessfully"; }
	 */

	@Override
	public List<AccountDto> getAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> accountDtoList = accounts.parallelStream().map(acc -> {
			AccountDto responseDto = new AccountDto();
			BeanUtils.copyProperties(acc, responseDto);
			responseDto.setCustomerId(acc.getCustomer().getCustomerId());
			return responseDto;
		}).collect(Collectors.toList());
		return accountDtoList;
	}

}
