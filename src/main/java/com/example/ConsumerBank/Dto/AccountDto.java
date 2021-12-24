package com.example.ConsumerBank.Dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountDto {
	
	
	@NotEmpty(message = "accountType should not be empty")
	@Pattern(regexp = "Savings|Current|Loan", message = "Account Type should be Savings/Cuurent/Loan")
	private String accountType;
	private Integer customerId;
	@NotNull
	@Min(value = 1000, message = "Minimum account balance should be 1000")
	private Double balance;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountDto [ accountType= "+ accountType + ", customerId= " + customerId + ", balance= " + balance + "]";
	}
}
