package com.seattle.msready.account.controller;

import com.seattle.msready.account.domain.User;
import com.seattle.msready.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ThreadFactory;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(path = "/current", method = RequestMethod.GET)
    public User getCurrentAccount() throws Exception {
        Thread.sleep(50);
        User user = new User();
        user.setUsername("test");
        return user;
    }

    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String name) {
        return userRepository.findByUsername(name);
    }
}
