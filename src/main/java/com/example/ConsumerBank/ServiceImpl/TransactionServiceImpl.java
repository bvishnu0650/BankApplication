package com.example.ConsumerBank.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ConsumerBank.Dto.TransactionDto;
import com.example.ConsumerBank.Dto.TransactionRequestDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Entity.Customer;
import com.example.ConsumerBank.Entity.Transaction;
import com.example.ConsumerBank.Repository.AccountRepository;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.Repository.TransactionRepository;
import com.example.ConsumerBank.Service.TransactionService;
import com.example.ConsumerBank.exception.AccountNotFoundException;
import com.example.ConsumerBank.exception.CustomerNotFoundException;
import com.example.ConsumerBank.exception.InsufficientBalanceException;

@Service
public class TransactionServiceImpl implements TransactionService {

	Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	AccountRepository accountRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	TransactionRepository transactionRepository;

	@Transactional
	public String amountTrasfer(TransactionRequestDto transactionRequestDto)
			throws AccountNotFoundException, InsufficientBalanceException {

		Account senderAC = accountRepository.findByAccountNumber(transactionRequestDto.getSenderAccountNumber());
		if (senderAC == null) {
			logger.error("sender acoount is not available for " + transactionRequestDto.getSenderAccountNumber());
			throw new AccountNotFoundException("senderAccount does not exist");
		}
		Account receiverAC = accountRepository.findByAccountNumber(transactionRequestDto.getReceiverAccountNumber());
		if (receiverAC == null) {
			logger.error("reciever acoount is not available for " + transactionRequestDto.getReceiverAccountNumber());
			throw new AccountNotFoundException("receiver Account does not exist");
		}
		if (senderAC.getBalance() < transactionRequestDto.getAmount()) {
			logger.error("sender account is not having sufficient balance " + transactionRequestDto.getAmount());
			throw new InsufficientBalanceException("Insufficient Balance");
		}

		if ((senderAC.getBalance() - transactionRequestDto.getAmount()) < 1000) {
			logger.error("Minimum Balance should not be Transfered  " + transactionRequestDto.getAmount());
			throw new InsufficientBalanceException("Minimum Balance should not be Transfered ");
		}

		senderAC.setBalance(senderAC.getBalance() - transactionRequestDto.getAmount());
		accountRepository.save(senderAC);
		receiverAC.setBalance(receiverAC.getBalance() + transactionRequestDto.getAmount());
		accountRepository.save(receiverAC);
		String txNo="tx" + System.currentTimeMillis();
		transactionRepository.save(new Transaction(txNo,transactionRequestDto.getAmount(), "Debit",
				senderAC.getAccountNumber(), LocalDateTime.now()));
		transactionRepository.save(new Transaction(txNo,transactionRequestDto.getAmount(), "Credit",
				receiverAC.getAccountNumber(), LocalDateTime.now()));

		return "transaction successfull";

	}

	@Override
	public List<TransactionDto> getTransactions() {
		List<Transaction> transaction = transactionRepository.findAll();
		List<TransactionDto> dtoList = transaction.parallelStream().map(mapper -> {
			TransactionDto responseDto = new TransactionDto();
			BeanUtils.copyProperties(mapper, responseDto);
			return responseDto;
		}).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public List<TransactionDto> transactionListByDateRange(Integer customerId, LocalDateTime transactionStartDate,
			LocalDateTime transactionEndDate) throws CustomerNotFoundException, AccountNotFoundException {
		Optional<Customer> customeroptional = customerRepository.findById(customerId);
		if (!customeroptional.isPresent()) {
			logger.error("Customer not found for transactions  ");
			throw new CustomerNotFoundException("Customer not found for transactions");
		}
		Customer customer = customeroptional.get();
		if (customer.getAccounts() == null || customer.getAccounts().isEmpty()) {
			logger.error("Customer not found for transactions  ");
			throw new AccountNotFoundException("Accounts not found for customer");
		}
		return customer.getAccounts().parallelStream()
				.flatMap(account -> account.getTransaction().parallelStream().filter(transaction -> (transaction
						.getTransactionDate().toLocalDate().isBefore(transactionEndDate.toLocalDate())
						|| transaction.getTransactionDate().toLocalDate().equals(transactionEndDate.toLocalDate()))
						&& (transaction.getTransactionDate().toLocalDate().isAfter(transactionStartDate.toLocalDate())
								|| transaction.getTransactionDate().toLocalDate()
										.equals(transactionStartDate.toLocalDate())))
						.map(transaction -> {
							TransactionDto transactionDto = new TransactionDto();
							BeanUtils.copyProperties(transaction, transactionDto);
							return transactionDto;
						}).collect(Collectors.toList()).stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<TransactionDto> getTransactionsByMonth(Integer customerId, String month)
			throws CustomerNotFoundException {
		Optional<Customer> customeroptional = customerRepository.findById(customerId);
		if (!customeroptional.isPresent()) {
			throw new CustomerNotFoundException("Customer not found for transactions");
		}
		Customer customer = customeroptional.get();
		return customer.getAccounts().parallelStream().flatMap(account -> account.getTransaction().parallelStream()
				.filter(transaction -> transaction.getTransactionDate().getMonth().name().equalsIgnoreCase(month))
				.map(transaction -> {
					TransactionDto transactionDto = new TransactionDto();
					BeanUtils.copyProperties(transaction, transactionDto);
					return transactionDto;
				}).collect(Collectors.toList()).stream()).collect(Collectors.toList());
	}

}
