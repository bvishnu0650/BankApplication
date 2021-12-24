package com.example.ConsumerBank.Service;

import java.util.List;

import com.example.ConsumerBank.Dto.AccountDto;

public interface AccountService {

	// public String saveAccount(AccountDto accountDto) throws CustomerNotFoundException;

	List<AccountDto> getAccounts();
}
