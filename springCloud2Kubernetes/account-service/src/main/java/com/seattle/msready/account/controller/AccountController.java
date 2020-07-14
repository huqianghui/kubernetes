package com.seattle.msready.account.controller;

import com.seattle.msready.account.domain.Account;
import com.seattle.msready.account.domain.User;
import com.seattle.msready.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(path = "/{name}", method = RequestMethod.GET)
	public Account getAccountByName(@PathVariable String name) {
		return accountService.findByName(name);
	}

	@RequestMapping(path = "/current", method = RequestMethod.GET)
	public Account getCurrentAccount(String username) throws Exception{
		Thread.sleep(50L);
		return new Account();
	}

	@RequestMapping(path = "/current", method = RequestMethod.PUT)
	public void saveCurrentAccount(String username, @Valid @RequestBody Account account) {
		accountService.saveChanges(username, account);
	}

	@RequestMapping(path = "/", method = RequestMethod.POST)
	public Account createNewAccount(@Valid @RequestBody User user) {

		return accountService.create(user);
	}
}
