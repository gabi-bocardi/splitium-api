package com.example.SplitMoney;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.SplitMoney.model.Group;
import com.example.SplitMoney.model.GroupMember;
import com.example.SplitMoney.model.GroupRepository;
import com.example.SplitMoney.model.Payment;
import com.example.SplitMoney.model.PaymentRepository;
import com.example.SplitMoney.model.User;
import com.example.SplitMoney.model.UserRepository;
import com.example.SplitMoney.model.memberRepository;


@SpringBootApplication
public class SplitMoneyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitMoneyApplication.class, args);
	}

	private void loadData(UserRepository userRepo, GroupRepository groupRepo,
			memberRepository memberRepo,  PaymentRepository PaymentRepo){






        //create users

        	User user1 = new User();
			user1.setName("John Smith");
			user1.setUsername("johnsmith");
			user1.setPassword("password1");



        	User user2 = new User();
        	user2.setName("Jane Doe");
			user2.setUsername("janedoe");
			user2.setPassword("password2");



			userRepo.save(user1);
			userRepo.save(user2);


		// Create groups
			Group group1 = new Group();
			group1.setName("Group 1");
			group1.setTotal(100.00);
			group1.setDescription("This is a a group of friends sharing a bill from March 26th");
			
			Group group2 = new Group();
			group2.setName("Group 2");
			group2.setTotal(150.00);
			group2.setDescription("This is a a group of friends sharing a bill from March 29th");



			groupRepo.save(group1);
			groupRepo.save(group2);


		// Create members


			GroupMember gm =  new GroupMember(user1,group1,50.0);

			memberRepo.save(gm);
			gm=new GroupMember(user2,group1,50.0);
			memberRepo.save(gm);
			gm=new GroupMember(user1,group2,100.0);
			memberRepo.save(gm);


	      	// Create payments
        	Payment payment1 = new Payment(user1, group1,10.0);


        	Payment payment2 = new Payment(user2,group1,20.0);


        	Payment payment3 = new Payment(user1,group2,30.0);
        	PaymentRepo.save(payment1);
        	PaymentRepo.save(payment2);
        	PaymentRepo.save(payment3);






			// Print all users
			userRepo.findAll().forEach(System.out::println);

    }

	@Bean
	ApplicationRunner init(UserRepository userRepo, GroupRepository groupRepo,
			memberRepository memberRepo,  PaymentRepository PaymentRepo) {


		return args -> {
			loadData(userRepo, groupRepo, memberRepo,PaymentRepo);
		};
	}



}
