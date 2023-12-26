package com.example.SplitMoney.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository <User,Long> {

	List<User> findByName(String name);
	Optional<User> findById(String id);
	Optional<User> findByUsername(String username);


}
