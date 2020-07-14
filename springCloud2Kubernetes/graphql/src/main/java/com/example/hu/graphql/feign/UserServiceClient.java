package com.example.hu.graphql.feign;

import com.seattle.msready.account.domain.User;
import feign.RequestLine;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "account-service", path = "/users")
public interface UserServiceClient {

    @RequestLine("GET /current")
    Mono<User> getUserByName();

   /* @RequestMapping(value = "/current", method = RequestMethod.GET)
    Mono<User> getUserByName(@RequestParam("username") String username);*/

}
