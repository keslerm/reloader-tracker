package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.model.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public interface ComponentRepository extends CrudRepository<Component, Integer>
{
}
