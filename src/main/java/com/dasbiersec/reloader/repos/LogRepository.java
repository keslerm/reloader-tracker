package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer>
{
}
