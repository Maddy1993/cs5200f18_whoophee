package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class Developer extends Person {

	private String developer_key;
	Collection<Website> websites;

	public Developer() {
		super();
		
	}

	public Developer(int id, String developer_key, String first_name, String last_name) {
		super(id, first_name, last_name, "whoophee", "password123", "sairam.r@husky.neu.edu", new Date(1970 - 1 - 1));
		this.developer_key = developer_key;
	}

	public Developer(int id, String developer_key, String first_name, String last_name, String username,
			String password, String email, Date dob) {
		super(id, first_name, last_name, username, password, email, dob);
		this.developer_key = developer_key;
	}

	public Developer(int id, String developer_key, String first_name, String last_name, String username,
			String password, String email, Date dob, Collection<Phone> phones, Collection<Address> addresses) {
		super(id, first_name, last_name, username, password, email, dob, addresses, phones);
		this.developer_key = developer_key;
	}

	
	public String getDeveloper_key() {
		return developer_key;
	}

	public void setDeveloper_key(String developer_key) {
		this.developer_key = developer_key;
	}

	public Collection<Website> getWebsites() {
		return websites;
	}

	public void setWebsites(Collection<Website> websites) {
		this.websites = websites;
	}

}
