package com.example.ConsumerBank.Dto;

public class CustomerRequestUpdateDto {
	private Integer customerId;
	private String customerName;
	private String phoneNo;
	private String address;

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

	@Override
	public String toString() {
		return "CustomerRequestUpdateDto [customerId=" + customerId + ", customerName=" + customerName + ", phoneNo="
				+ phoneNo + ", address=" + address + "]";
	}

}
