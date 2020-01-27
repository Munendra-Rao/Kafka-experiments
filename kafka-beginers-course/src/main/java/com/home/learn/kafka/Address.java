package com.home.learn.kafka;

public class Address {
	
	private String add1;
	private int doornum;
	
	public Address(String add1, int doornum) {
		super();
		this.add1 = add1;
		this.doornum = doornum;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public int getDoornum() {
		return doornum;
	}
	public void setDoornum(int doornum) {
		this.doornum = doornum;
	}

}
