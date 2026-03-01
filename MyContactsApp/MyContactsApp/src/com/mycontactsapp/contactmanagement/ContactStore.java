package com.mycontactsapp.contactmanagement;

import java.util.ArrayList;
import java.util.List;

public class ContactStore {

    private List<Contact> contacts = new ArrayList<>();

    public void save(Contact c) {
        contacts.add(c);
    }

    public List<Contact> getAll() {
        return contacts;
    }
    
    public void delete(Contact contact) {
        contacts.remove(contact);
    }
}
