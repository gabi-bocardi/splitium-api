package com.example.SplitMoney.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class GroupMember {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "user_id", nullable = false)
	    @JsonIgnore
	    @JsonProperty("user")
	    private User user;


	    @ManyToOne(fetch = FetchType.EAGER, optional = false)
	    @JoinColumn(name = "group_id", nullable = false)
	    @JsonIgnore
//	    @JsonProperty("group")
	    private Group group;

	    @Column
	    private Double amount;



		public GroupMember(User user, Group group, Double amount) {

			this.user = user;
			this.group = group;
			this.amount = amount;

			user.getGroupMember().add(this);
			group. getGroupMember().add(this);
		}

		public GroupMember() {
		}

		public GroupMember(Double amount) {
			super();
			this.amount = amount;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Group getGroup() {
			return group;
		}

		public void setGroup(Group group) {
			this.group = group;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		




}
