package com.example.ConsumerBank.Dto;

import org.springframework.beans.factory.annotation.Value;

public interface CustomerResponse {
	@Value("#{target.customerName}")
	String getCustomerName();

	@Value("#{target.phoneNo}")
	String getphoneNo();

	@Value("#{target.address}")
	String getaddress();

}
