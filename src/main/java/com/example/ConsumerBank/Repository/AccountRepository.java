package com.example.ConsumerBank.Repository;

import com.example.ConsumerBank.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findByAccountNumber(Long senderACNumber);
}
