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
	public String getPasswordHash() {
		return passwordHash;
	}

	public void setname(String name) 
	{
		if (name == null || name.trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Full name cannot be empty.");
		}
		this.name = name;
	}

	public void setEmail(String email) 
	{
		if (!EmailValidator.isValid(email)) 
		{
			throw new IllegalArgumentException("Invalid email format.");
		}
		this.email = email;
	}

	public void setPassword(String newPassword) throws Exception 
	{
		if (newPassword == null || newPassword.length() < 6) 
		{
			throw new IllegalArgumentException("Password must be at least 6 characters.");
		}
		this.passwordHash = PasswordHasher.hash(newPassword);
	}

}
