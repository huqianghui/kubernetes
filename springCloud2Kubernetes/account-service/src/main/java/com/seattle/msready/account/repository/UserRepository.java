package com.seattle.msready.account.repository;

import com.seattle.msready.account.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByUsername(String username);

}
