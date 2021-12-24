package com.example.ConsumerBank.Dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

public class CustomerRequestDto {
//	private Integer customerId;
	@NotBlank(message = "customer name should not be empty")
	private String customerName;
	@NotBlank(message = "phoneno should not be empty")
	private String phoneNo;

	@NotBlank(message = "address should not be empty")
	private String address;

	private List<AccountRequestDto> accounts;
	/*
	 * public Integer getCustomerId() { return customerId; }
	 * 
	 * public void setCustomerId(Integer customerId) { this.customerId = customerId;
	 * }
	 */

	

	public String getCustomerName() {
		return customerName;
	}

	public List<AccountRequestDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountRequestDto> accounts) {
		this.accounts = accounts;
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

	@Override
	public String toString() {
		return "CustomerRequestDto [customerName=" + customerName + ", phoneNo="
				+ phoneNo + ", address=" + address + "]";
	}

}
