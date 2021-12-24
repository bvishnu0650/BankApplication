package com.example.ConsumerBank.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ConsumerBank.Dto.TransactionDto;
import com.example.ConsumerBank.Dto.TransactionRequestDto;
import com.example.ConsumerBank.Service.TransactionService;
import com.example.ConsumerBank.exception.AccountNotFoundException;
import com.example.ConsumerBank.exception.CustomerNotFoundException;
import com.example.ConsumerBank.exception.InsufficientBalanceException;

@ExtendWith(MockitoExtension.class)
public class TransactionControllerTest {

	@InjectMocks
	TransactionController transactionController;

	@Mock
	TransactionService transactionService;

	@Test
	public void moneyTransfer() throws InsufficientBalanceException, AccountNotFoundException {

		TransactionRequestDto transactionRequestDto = new TransactionRequestDto();
		transactionRequestDto.setAmount(3000d);
		transactionRequestDto.setReceiverAccountNumber(2l);
		transactionRequestDto.setSenderAccountNumber(3l);

		Mockito.when(transactionService.amountTrasfer(transactionRequestDto)).thenReturn("transaction successfull");
		ResponseEntity<String> responseEntity = transactionController.moneyTransfer(transactionRequestDto);
		assertEquals("transaction successfull", responseEntity.getBody().toString());
	}

	@Test
	public void transactionListByDateRange() throws CustomerNotFoundException, AccountNotFoundException {
		List<TransactionDto> transactionDtos = new ArrayList<TransactionDto>();
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAccountNumber(123345678l);
		transactionDto.setAmount(3000d);
		transactionDto.setTransactionDate(LocalDateTime.now());
		transactionDto.setTransactionNumber("tx01");
		transactionDto.setTransactionType("saving");

		transactionDtos.add(transactionDto);

		Mockito.when(transactionService.transactionListByDateRange(Mockito.anyInt(), Mockito.any(), Mockito.any()))
				.thenReturn(transactionDtos);

		ResponseEntity<List<TransactionDto>> responseEntity = transactionController.transactionListByDateRange(2,
				LocalDateTime.now(), LocalDateTime.now());
		assertEquals(transactionDtos.size(), responseEntity.getBody().size());
	}

	@Test
	public void transactionListByMonth() throws CustomerNotFoundException {
		List<TransactionDto> transactionDtos = new ArrayList<TransactionDto>();
		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAccountNumber(123345678l);
		transactionDto.setAmount(3000d);
		transactionDto.setTransactionDate(LocalDateTime.now());
		transactionDto.setTransactionNumber("tx01");
		transactionDto.setTransactionType("saving");

		transactionDtos.add(transactionDto);

		Mockito.when(transactionService.getTransactionsByMonth(Mockito.anyInt(), Mockito.any()))
				.thenReturn(transactionDtos);

		ResponseEntity<List<TransactionDto>> responseEntity = transactionController.getTransactionListByMonth(2,
				"DECEMBER");
		assertEquals(transactionDtos.size(), responseEntity.getBody().size());
	}

}
