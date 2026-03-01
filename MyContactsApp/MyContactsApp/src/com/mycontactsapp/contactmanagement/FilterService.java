package com.mycontactsapp.contactmanagement;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilterService {

    public List<Contact> filterByTag(String tag, ContactStore store) {
        List<Contact> result = new ArrayList<>();

        for (Contact c : store.getAll()) {
            for (String t : c.getTags()) {
                if (t.equalsIgnoreCase(tag)) {
                    result.add(c);
                    break;
                }
            }
        }
        return result;
    }

    public List<Contact> filterByDateBefore(LocalDateTime date, ContactStore store) {
        List<Contact> result = new ArrayList<>();

        for (Contact c : store.getAll()) {
            if (c.getCreatedAt().isBefore(date)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<Contact> filterByMinContactCount(int minCount, ContactStore store) {
        List<Contact> result = new ArrayList<>();

        for (Contact c : store.getAll()) {
            if (c.getContactCount() >= minCount) {
                result.add(c);
            }
        }
        return result;
    }

    // COMBINED FILTER (simple mode)
    public List<Contact> combineFilters(
            List<Contact> list1,
            List<Contact> list2,
            List<Contact> list3) {

        // intersection of non-null lists
        List<Contact> result = new ArrayList<>();

        for (Contact c : list1) {
            if ((list2 == null || list2.contains(c)) &&
                (list3 == null || list3.contains(c))) {
                result.add(c);
            }
        }
        return result;
    }
}