package com.example.ConsumerBank.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.ConsumerBank.Dto.TransactionDto;
import com.example.ConsumerBank.Dto.TransactionRequestDto;
import com.example.ConsumerBank.exception.AccountNotFoundException;
import com.example.ConsumerBank.exception.CustomerNotFoundException;
import com.example.ConsumerBank.exception.InsufficientBalanceException;

public interface TransactionService {
	String amountTrasfer(TransactionRequestDto transactionRequestDto)
			throws AccountNotFoundException, InsufficientBalanceException;

	List<TransactionDto> getTransactions();

	public List<TransactionDto> transactionListByDateRange(Integer customerId, LocalDateTime transactionStartDate,
			LocalDateTime transactionEndDate) throws CustomerNotFoundException, AccountNotFoundException;

	List<TransactionDto> getTransactionsByMonth(Integer customerId, String month) throws CustomerNotFoundException;

}