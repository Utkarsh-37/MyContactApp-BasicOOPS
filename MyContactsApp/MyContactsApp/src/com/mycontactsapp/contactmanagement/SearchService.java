package com.mycontactsapp.contactmanagement;

import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<Contact> searchByName(String keyword, ContactStore store) {
        List<Contact> results = new ArrayList<>();

        for (Contact c : store.getAll()) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(c);
            }
        }
        return results;
    }

    public List<Contact> searchByEmail(String email, ContactStore store) {
        List<Contact> results = new ArrayList<>();

        for (Contact c : store.getAll()) {
            if (c.getEmail().equalsIgnoreCase(email)) {
                results.add(c);
            }
        }
        return results;
    }

    public List<Contact> searchByPhone(String phone, ContactStore store) {
        List<Contact> results = new ArrayList<>();

        for (Contact c : store.getAll()) {
            for (PhoneNumber pn : c.getPhoneNumbers()) {
                if (pn.getNumber().contains(phone)) {
                    results.add(c);
                    break;
                }
            }
        }
        return results;
    }

    public List<Contact> searchByTag(String tag, ContactStore store) {
        List<Contact> results = new ArrayList<>();

        for (Contact c : store.getAll()) {
            for (String t : c.getTags()) {
                if (t.equalsIgnoreCase(tag)) {
                    results.add(c);
                    break;
                }
            }
        }
        return results;
    }
}