package com.example.SplitMoney.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JsonProperty("groupMember")
    @JsonIgnore
    private Set<GroupMember> groupMember = new HashSet<>();

    public User() {}

    public User(String name, String userName, String password) {
        this.name = name;
        this.username = userName;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public void addPayments(Payment payment) {
    	this.payments.add(payment);
    	payment.setUser(this);
    }


	public Set<GroupMember> getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(Set<GroupMember> members) {
		this.groupMember = members;
	}
}
