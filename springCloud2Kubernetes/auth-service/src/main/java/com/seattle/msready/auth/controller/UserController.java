package com.seattle.msready.auth.controller;

import com.seattle.msready.auth.domain.User;
import com.seattle.msready.auth.service.security.RestUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	RestUserDetailsService restUserDetailsService;

	@RequestMapping(value = "/current", method = RequestMethod.GET)
	public UserDetails getUser(Principal principal) {
		if( principal != null && !StringUtils.isEmpty(principal.getName())){
			return restUserDetailsService.loadUserByUsername(principal.getName());
		}else{
			return null;
		}
	}
}
