package edu.northeastern.cs5200.models;

import java.sql.Date;
import java.util.Collection;

public class User extends Person {

	private String user_key;
	private boolean user_agreement;

	public User() {
		super();
		
	}

	public User(int id, String first_name, String last_name) {
		super(id, first_name, last_name, null, null, null, null);
		user_agreement = false;
	}

	public User(int id, String user_key, String first_name, String last_name, String username, String password,
			String email, Date dob) {
		super(id, first_name, last_name, username, password, email, dob);
		this.user_key = user_key;
	}

	public User(int id, String user_key, String first_name, String last_name, String username, String password,
			String email, Date dob, Collection<Phone> phones, Collection<Address> addresses) {
		super(id, first_name, last_name, username, password, email, dob, addresses, phones);
		this.user_key = user_key;
	}

	public User(String user_key, boolean user_agreement) {
		super();
		this.user_key = user_key;
		this.user_agreement = user_agreement;
	}

	
	public String getUser_key() {
		return user_key;
	}

	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}

	public boolean getUser_agreement() {
		return user_agreement;
	}

	public void setUser_agreement(boolean user_agreement) {
		this.user_agreement = user_agreement;
	}

}
