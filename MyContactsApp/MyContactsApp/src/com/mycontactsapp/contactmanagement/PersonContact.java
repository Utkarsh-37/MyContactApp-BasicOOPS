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
}