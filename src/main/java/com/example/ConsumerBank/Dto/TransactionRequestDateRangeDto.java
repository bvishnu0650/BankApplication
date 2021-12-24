package com.example.ConsumerBank.Dto;

import java.time.LocalDateTime;

public class TransactionRequestDateRangeDto {

	private Integer customerId;
	private LocalDateTime transactionStartDate;
	private LocalDateTime transactionEndDate;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public LocalDateTime getTransactionStartDate() {
		return transactionStartDate;
	}

	public void setTransactionStartDate(LocalDateTime transactionStartDate) {
		this.transactionStartDate = transactionStartDate;
	}

	public LocalDateTime getTransactionEndDate() {
		return transactionEndDate;
	}

	public void setTransactionEndDate(LocalDateTime transactionEndDate) {
		this.transactionEndDate = transactionEndDate;
	}

}
