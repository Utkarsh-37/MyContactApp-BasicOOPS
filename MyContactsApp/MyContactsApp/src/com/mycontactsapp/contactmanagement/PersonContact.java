package com.mycontactsapp.contactmanagement;

public class PersonContact extends Contact {

    private String birthday;

    public PersonContact(String name, String email, String birthday) {
        super(name, email);
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }
    
    @Override
    public String toString() {
        return super.toString() +
               "Birthday: " + birthday + "\n";
    }
}