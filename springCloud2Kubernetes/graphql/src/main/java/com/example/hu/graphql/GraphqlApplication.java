package com.example.hu.graphql;

import com.example.hu.graphql.feign.UserServiceClient;
import com.seattle.msready.account.domain.User;
import feign.Headers;
import feign.RequestLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactivefeign.spring.config.EnableReactiveFeignClients;
import reactor.core.publisher.Mono;

@SpringCloudApplication
@EnableDiscoveryClient
@RestController
@EnableReactiveFeignClients
@EnableFeignClients
public class GraphqlApplication {

    private  UserServiceClient userServiceClient;

    public static void main(String[] args) {
        SpringApplication.run(GraphqlApplication.class, args);
    }

    @Autowired
    public void setUserServiceClient(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @GetMapping("/user")
    public Mono<User> getUserByName(){
     return this.userServiceClient.getUserByName();
    }

}
