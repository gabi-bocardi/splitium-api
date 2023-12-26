package com.example.SplitMoney.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

	List<GroupMember> findByAmount(Double amount);
//	List<GroupMember> findByGroupIdAndMemberId(Long groupId, Long memberId);
}

