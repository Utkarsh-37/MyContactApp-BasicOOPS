package com.mycontactsapp.usermanagement;

public class FreeUser extends User 
{
    public FreeUser(String email, String passwordHash, String name) 
    {
        super(email, passwordHash, name, "FREE");
    }
}