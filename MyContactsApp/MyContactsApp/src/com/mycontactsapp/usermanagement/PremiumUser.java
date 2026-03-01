package com.mycontactsapp.usermanagement;

public class PremiumUser extends User {
	public PremiumUser(String email, String passwordHash, String name) 
	{
		super(email, passwordHash, name, "PREMIUM");
	}
}
