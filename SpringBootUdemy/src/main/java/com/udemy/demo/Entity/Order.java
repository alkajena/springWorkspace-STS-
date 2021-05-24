package com.udemy.demo.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.hateoas.RepresentationModel;


@Entity(name="Orders")
@Table(name="ORDERS")
public class Order extends RepresentationModel<Order>{
	
	@Id
	@GeneratedValue
	private Long orderId;
	
	@Column(name="ORDERDESC", length=50, nullable=false)
	private String OrderDesc;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;

	
	
	public Order() {
	}

	public Order(Long orderId, String orderDesc) {
		super();
		this.orderId = orderId;
		OrderDesc = orderDesc;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDesc() {
		return OrderDesc;
	}

	public void setOrderDesc(String orderDesc) {
		OrderDesc = orderDesc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
