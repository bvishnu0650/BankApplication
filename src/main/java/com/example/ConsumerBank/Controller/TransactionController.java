package com.example.ConsumerBank.Controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ConsumerBank.Dto.TransactionDto;
import com.example.ConsumerBank.Dto.TransactionRequestDto;
import com.example.ConsumerBank.Service.TransactionService;
import com.example.ConsumerBank.exception.AccountNotFoundException;
import com.example.ConsumerBank.exception.CustomerNotFoundException;
import com.example.ConsumerBank.exception.InsufficientBalanceException;

@RestController
@Validated
public class TransactionController {
	
	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	@PostMapping("/transaction")
	public ResponseEntity<String> moneyTransfer(@Valid @RequestBody TransactionRequestDto transactionRequestDto)
			throws InsufficientBalanceException, AccountNotFoundException {
		
		logger.info("senderAccount number: %s receviedAccoundNo: %s ", transactionRequestDto.getSenderAccountNumber(),transactionRequestDto.getReceiverAccountNumber());
		
		//logger.info("SenderAccountNo: %s"+transactionRequestDto + " RecieverAccountNo: " + transactionRequestDto.getReceiverAccountNumber());
		String response = transactionService.amountTrasfer(transactionRequestDto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	@GetMapping("/transaction")
	public ResponseEntity<List<TransactionDto>> getTransactions() {
		List<TransactionDto> transactionList = transactionService.getTransactions();
		return new ResponseEntity<List<TransactionDto>>(transactionList, HttpStatus.OK);
	}

	@GetMapping("/transaction/{customerId}/{transactionStartDate}/{transactionEndDate}")
	public ResponseEntity<List<TransactionDto>> transactionListByDateRange(@PathVariable Integer customerId,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime transactionStartDate,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime transactionEndDate) throws CustomerNotFoundException, AccountNotFoundException {
		logger.info("customerId" + customerId+"transactionStartDate:"+ transactionStartDate + "transactionEndDate:"+ transactionEndDate);
		List<TransactionDto> transactionDto = transactionService.transactionListByDateRange(customerId,
				transactionStartDate, transactionEndDate);
		return new ResponseEntity<List<TransactionDto>>(transactionDto, HttpStatus.OK);
	}

	@GetMapping("/transaction/{customerId}/{month}")
	public ResponseEntity<List<TransactionDto>> getTransactionListByMonth(@PathVariable Integer customerId,
			@PathVariable String month) throws CustomerNotFoundException {
		logger.info("customerId"+ customerId+"month:"+month);
		List<TransactionDto> transactionDto = transactionService.getTransactionsByMonth(customerId, month);
		return new ResponseEntity<List<TransactionDto>>(transactionDto, HttpStatus.OK);
	}

}