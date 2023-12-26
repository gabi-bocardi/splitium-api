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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double total;
    
    @Column
    private String description;

    @OneToMany(mappedBy ="group",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JsonProperty("groupMember")
    private Set<GroupMember> members = new HashSet<>();

    @OneToMany(mappedBy ="group",cascade = CascadeType.ALL, fetch=FetchType.LAZY)
//    @JsonIgnore
    private Set<Payment> payments = new HashSet<>();


    
    public Group(String name, double total, String description, Set<GroupMember> members,
			Set<Payment> payments) {
		super();
		this.name = name;
		this.total = total;
		this.description = description;
		this.members = members;
		this.payments = payments;
	}
 
    public Group(String name, double total, String description) {
		super();
		this.name = name;
		this.total = total;
		this.description = description;
	}

	public Group() {
    	
    }

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
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


    public Set<GroupMember> getGroupMember() {
        return members;
    }

    public void setGroupMember(Set<GroupMember> members) {
        this.members = members;
    }

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}

