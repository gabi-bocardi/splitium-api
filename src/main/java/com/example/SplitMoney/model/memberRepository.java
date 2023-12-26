package com.example.SplitMoney.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface memberRepository extends JpaRepository<GroupMember, Long> {

	List<GroupMember> findByAmount(Double amount);

}
