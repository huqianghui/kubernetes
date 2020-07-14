package com.example.hu.graphql.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.example.hu.graphql.domain.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagRepository {

    private Map<String, Tag> tags;

    public TagRepository() {
        tags = new HashMap<>();
    }

    public Tag save(Tag greeting) {
        String id = UUID.randomUUID().toString();

        tags.put(id, greeting);
        greeting.setId(id);

        return greeting;
    }

    public Tag find(String id) {
        return tags.get(id);
    }
}
