package com.home.learn.kafka.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.xml.validation.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {
	
	private int id;
	@NotNull
	@NotEmpty
	private String name;
	private boolean permanent;
	private Address address;
	private long[] phoneNumbers;
	private String role;
	private List<String> cities;
	private Map<String, String> properties;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPermanent() {
		return permanent;
	}
	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public long[] getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(long[] phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", permanent=" + permanent + ", address=" + address
				+ ", phoneNumbers=" + Arrays.toString(phoneNumbers) + ", role=" + role + ", cities=" + cities
				+ ", properties=" + properties + "]";
	}
	
	
	
	

}
