package com.example.ConsumerBank.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerDto {
	//private Integer customerId;
	@NotBlank(message = "Name should not be Empty")
	private String customerName;
	@NotBlank(message = "phoneNo should not be Empty")
	private String phoneNo;
	@NotBlank(message = "Address should not be Empty")
	private String address;

	/*
	 * public Integer getCustomerId() { return customerId; }
	 * 
	 * public void setCustomerId(Integer customerId) { this.customerId = customerId;
	 * }
	 */

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
}
