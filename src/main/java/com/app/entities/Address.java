package com.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity

public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String streetAddress;
	
	private String city;
	
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	private String mobile;

	public Address() {
		super();
	}

	public Address(Long id, String firstName, String lastName, String streetAddress, String city, String zipCode,
			User user, String mobile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.zipCode = zipCode;
		this.user = user;
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

//	@Override
//	public String toString() {
//		return "Address [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", streetAddress="
//				+ streetAddress + ", city=" + city + ", zipCode=" + zipCode + ", user=" + user + ", mobile=" + mobile
//				+ "]";
//	}
	
	
	
	

}
