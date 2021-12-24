package com.example.ConsumerBank.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class CustomerNewResponseDto {
	
	private Integer customerId;
	@NotBlank(message = "customername should not be empty")
	private String customerName;
	@NotBlank(message = "phoneno should not be empty")
	private String phoneNo;
	@NotBlank(message = "address should not be empty")
	private String address;
	
	
	private List<AccountResponseDto> accounts;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<AccountResponseDto> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountResponseDto> accounts) {
		this.accounts = accounts;
	}
}
