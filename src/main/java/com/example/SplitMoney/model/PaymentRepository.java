package com.example.SplitMoney.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


	List <Payment> findByAmount(Double Amount);
	List <Payment> findByGroup(Group group);
	void deleteByGroup(Group group);
}
