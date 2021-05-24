package com.udemy.demo.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.tomcat.util.buf.UEncoder.SafeCharsSet;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;

@Entity
@Table(name = "USER")
//@JsonIgnoreProperties({"lastName"})---static fileter(jsnIgnore and JsonIgnoreProperties)
//@JsonFilter(value="UserFilter")----filter using mappingJacksonValue
public class User extends RepresentationModel<User>{

	@Id
	@GeneratedValue
	@Column(name="USERID")
	@JsonView(Views.Internal.class)//--Filter using JsonView
	private long userId;
	
	@Column(name="USERNAME",length=50,nullable=false,unique = true)
	@JsonView(Views.External.class)
	@NotEmpty(message="User name filed can not be empty or blank")
	private String userName;
	
	@Column(name="FIRSTNAME",length=50,nullable=false)
	@Size(min=3,max=10,message="FirstName length should be between 3 to 10")
	@JsonView(Views.External.class)
	private String firstName;
	
	@Column(name="LASTNAME",length=50,nullable=false)
	//@JsonIgnore
	@JsonView(Views.External.class)
	private String lastName;
	
	@Column(name="ROLL",length=50,nullable=false)
	@NotBlank(message="Roll number can not be blank")
	@JsonView(Views.Internal.class)
	//@JsonIgnore
	private String roll;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Order> order;
	
	public User() {
	}

	public User(long id, String userName, String firstName, String lastName, String roll) {
		super();
		this.userId = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roll = roll;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long id) {
		this.userId = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	
}
