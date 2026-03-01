package com.mycontactsapp.usermanagement;

public class User 
{
	private String email;
	private String passwordHash;
	private String name;
	private String userType; // "FREE" or "PREMIUM"

	public User(String email, String passwordHash, String name, String userType)
	{
		this.email = email;
		this.passwordHash = passwordHash;
		this.name = name;
		this.userType = userType;
	}

	public String getEmail() 
	{ 
		return email; 
	}
	public String getname() {
		return name;
	}
	public String getUserType() {
		return userType; 
	}
}
