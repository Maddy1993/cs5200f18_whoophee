package edu.northeastern.cs5200.models;

public class Phone {

	private String phone;
	private Boolean primary;

	public Phone() {
		super();
		
	}

	public Phone(String phone, Boolean primary) {
		super();
		this.phone = phone;
		this.primary = primary;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
}
