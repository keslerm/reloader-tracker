package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.domain.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchRepository extends CrudRepository<Component, Integer>
{
}
