package com.example.ConsumerBank.ServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ConsumerBank.Dto.TransactionDto;
import com.example.ConsumerBank.Dto.TransactionRequestDto;
import com.example.ConsumerBank.Entity.Account;
import com.example.ConsumerBank.Entity.Customer;
import com.example.ConsumerBank.Entity.Transaction;
import com.example.ConsumerBank.Repository.AccountRepository;
import com.example.ConsumerBank.Repository.CustomerRepository;
import com.example.ConsumerBank.Repository.TransactionRepository;
import com.example.ConsumerBank.exception.AccountNotFoundException;
import com.example.ConsumerBank.exception.CustomerNotFoundException;
import com.example.ConsumerBank.exception.InsufficientBalanceException;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {
	@InjectMocks
	TransactionServiceImpl transactionServiceImpl;

	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountRepository accountRepository;

	@Mock
	CustomerRepository customerRepository;

	@Test
	public void amountTrasfer() throws AccountNotFoundException, InsufficientBalanceException {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAmount(2000d);
		transactionRequestDto.setReceiverAccountNumber(2l);
		transactionRequestDto.setSenderAccountNumber(4l);

		Account senderAcount = new Account();
		senderAcount.setAccountId(1);
		senderAcount.setAccountNumber(4l);
		senderAcount.setAccountType("svings");
		senderAcount.setBalance(9000D);

		Account receiverAcount = new Account();
		receiverAcount.setAccountId(4);
		receiverAcount.setAccountNumber(2l);
		receiverAcount.setAccountType("svings");
		receiverAcount.setBalance(8000D);

		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(2l))).thenReturn(senderAcount);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(4l))).thenReturn(receiverAcount);

		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(senderAcount);
		Mockito.when(accountRepository.save(Mockito.any())).thenReturn(receiverAcount);

		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(new Transaction());
		Mockito.when(transactionRepository.save(Mockito.any())).thenReturn(new Transaction());

		String response = transactionServiceImpl.amountTrasfer(transactionRequestDto);
		assertEquals("transaction successfull", response);
	}

	@Test
	public void amountTrasfer_senderAc_not_found() {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		Mockito.when(accountRepository.findByAccountNumber(Mockito.any())).thenReturn(null);
		Throwable throwable = Assertions.catchThrowable(() -> {
			transactionServiceImpl.amountTrasfer(transactionRequestDto);
		});
		assertEquals(AccountNotFoundException.class, throwable.getClass());
	}

	@Test
	public void amountTrasfer_receiverAc_not_found() {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAmount(2000d);
		transactionRequestDto.setReceiverAccountNumber(2l);
		transactionRequestDto.setSenderAccountNumber(4l);

		Account senderAcount = new Account();
		senderAcount.setAccountId(1);
		senderAcount.setAccountNumber(2l);
		senderAcount.setAccountType("svings");
		senderAcount.setBalance(9000D);

		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(4l))).thenReturn(senderAcount);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(2l))).thenReturn(null);
		Throwable throwable = Assertions.catchThrowable(() -> {
			transactionServiceImpl.amountTrasfer(transactionRequestDto);
		});
		assertEquals(AccountNotFoundException.class, throwable.getClass());
	}

	@Test
	public void amountTrasfer_requested_amount_morethan_sender_account_balance_insufficient() {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAmount(2000d);
		transactionRequestDto.setReceiverAccountNumber(2l);
		transactionRequestDto.setSenderAccountNumber(4l);

		Account senderAcount = new Account();
		senderAcount.setAccountId(1);
		senderAcount.setAccountNumber(2l);
		senderAcount.setAccountType("svings");
		senderAcount.setBalance(1500d);

		Account receiverAcount = new Account();
		receiverAcount.setAccountId(1);
		receiverAcount.setAccountNumber(2l);
		receiverAcount.setAccountType("svings");
		receiverAcount.setBalance(9000D);

		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(4l))).thenReturn(senderAcount);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(2l))).thenReturn(receiverAcount);
		Throwable throwable = Assertions.catchThrowable(() -> {
			transactionServiceImpl.amountTrasfer(transactionRequestDto);
		});
		assertEquals(InsufficientBalanceException.class, throwable.getClass());
	}

	@Test
	public void amountTrasfer_moreThan_initila_amount_insufficien_exceptiont() {
		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAmount(800d);
		transactionRequestDto.setReceiverAccountNumber(2l);
		transactionRequestDto.setSenderAccountNumber(4l);

		Account senderAcount = new Account();
		senderAcount.setAccountId(1);
		senderAcount.setAccountNumber(2l);
		senderAcount.setAccountType("svings");
		senderAcount.setBalance(1500d);

		Account receiverAcount = new Account();
		receiverAcount.setAccountId(1);
		receiverAcount.setAccountNumber(2l);
		receiverAcount.setAccountType("svings");
		receiverAcount.setBalance(9000D);

		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(4l))).thenReturn(senderAcount);
		Mockito.when(accountRepository.findByAccountNumber(Mockito.eq(2l))).thenReturn(receiverAcount);
		Throwable throwable = Assertions.catchThrowable(() -> {
			transactionServiceImpl.amountTrasfer(transactionRequestDto);
		});
		assertEquals(InsufficientBalanceException.class, throwable.getClass());
	}

	@Test
	public void transactionListByDateRange() throws CustomerNotFoundException, AccountNotFoundException {

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

		List<Transaction> transactionList = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(123345678l);
		transaction.setAmount(3000d);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionNumber("tx01");
		transaction.setTransactionType("saving");

		transactionList.add(transaction);
		account.setTransaction(transactionList);
		accountList.add(account);

		customer.setAccounts(accountList);

		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));

		List<TransactionDto> transactionDtosList = transactionServiceImpl.transactionListByDateRange(2,
				LocalDateTime.now(), LocalDateTime.now());

		assertEquals(1, transactionDtosList.size());

	}

	@Test
	public void transactionListByMonth() throws CustomerNotFoundException {

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

		List<Transaction> transactionList = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(123345678l);
		transaction.setAmount(3000d);
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionNumber("tx01");
		transaction.setTransactionType("saving");

		transactionList.add(transaction);
		account.setTransaction(transactionList);
		accountList.add(account);

		customer.setAccounts(accountList);

		Mockito.when(customerRepository.findById(Mockito.any())).thenReturn(Optional.of(customer));

		List<TransactionDto> transactionDtosList = transactionServiceImpl.getTransactionsByMonth(2, "DECEMBER");

		assertEquals(1, transactionDtosList.size());

	}

}
