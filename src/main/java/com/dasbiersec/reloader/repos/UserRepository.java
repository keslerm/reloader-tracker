package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
	User findUserByUsername(String username);
}
