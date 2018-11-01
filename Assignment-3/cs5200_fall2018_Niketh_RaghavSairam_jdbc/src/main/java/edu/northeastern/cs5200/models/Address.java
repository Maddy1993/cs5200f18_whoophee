package edu.northeastern.cs5200.models;

public class Address {

	private String street_1;
	private String street_2;
	private String city;
	private String state;
	private String zip;
	private Boolean primary;

	public Address() {
		

	}

	public Address(String street_1, String street_2, String city, String state, String zip, Boolean primary) {
		super();
		this.street_1 = street_1;
		this.street_2 = street_2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.primary = primary;
	}

	public String getStreet_1() {
		return street_1;
	}

	public void setStreet_1(String street_1) {
		this.street_1 = street_1;
	}

	public String getStreet_2() {
		return street_2;
	}

	public void setStreet_2(String street_2) {
		this.street_2 = street_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Boolean getPrimary() {
		return primary;
	}

	public void setPrimary(Boolean primary) {
		this.primary = primary;
	}
}
