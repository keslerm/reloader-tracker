package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>
{
	UserEntity findUserByUsername(String username);
}
