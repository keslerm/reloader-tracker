package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>
{
	Account findUserByUsername(String username);
}
