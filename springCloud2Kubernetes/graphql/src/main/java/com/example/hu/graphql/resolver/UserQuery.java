package com.example.hu.graphql.resolver;

import com.example.hu.graphql.feign.UserServiceClient;
import com.seattle.msready.account.domain.User;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    private UserServiceClient userServiceClient;

    public CompletableFuture<User> getUser(String username) {
        // TODO
        //return userServiceClient.getUserByName(username).toFuture();
        return null;
    }
}
