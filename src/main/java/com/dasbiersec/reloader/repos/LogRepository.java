package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.entity.LogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<LogEntity, Integer>
{
}
