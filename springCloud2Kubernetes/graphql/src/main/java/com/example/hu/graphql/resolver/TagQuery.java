package com.example.hu.graphql.resolver;

import com.example.hu.graphql.domain.Tag;
import com.example.hu.graphql.repository.TagRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TagQuery implements GraphQLQueryResolver {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getTag(String id) {
        return tagRepository.find(id);
    }
}
