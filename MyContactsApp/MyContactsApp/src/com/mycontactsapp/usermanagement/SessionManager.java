package com.mycontactsapp.usermanagement;

public class SessionManager {
    
    private User loggedInUser;

    public void login(User user) 
    {
        this.loggedInUser = user;
    }

    public void logout() 
    {
        this.loggedInUser = null;
    }

    public User getLoggedInUser() 
    {
        return loggedInUser;
    }
}