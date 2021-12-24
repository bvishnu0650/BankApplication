package com.example.ConsumerBank.Repository;

import com.example.ConsumerBank.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
