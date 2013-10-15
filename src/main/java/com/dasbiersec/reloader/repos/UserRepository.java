package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Account, Integer>
{
	Account findUserByUsername(String username);
}
