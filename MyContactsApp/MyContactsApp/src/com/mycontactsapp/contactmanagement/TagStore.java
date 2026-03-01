package com.mycontactsapp.contactmanagement;

import java.util.ArrayList;
import java.util.List;

public class TagStore {

    private List<String> tags = new ArrayList<>();

    public void addTag(String tag) {
        if (tag == null || tag.trim().isEmpty()) {
            throw new IllegalArgumentException("Tag cannot be empty.");
        }
        if (tags.contains(tag.trim())) {
            throw new IllegalArgumentException("Tag already exists.");
        }
        tags.add(tag.trim());
    }

    public List<String> getAllTags() {
        return tags;
    }

    public void deleteTag(String tag) {
        tags.remove(tag);
    }
}