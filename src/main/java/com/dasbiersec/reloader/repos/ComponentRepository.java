package com.dasbiersec.reloader.repos;

import com.dasbiersec.reloader.enums.ComponentType;
import com.dasbiersec.reloader.domain.Component;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponentRepository extends CrudRepository<Component, Integer>
{
	Iterable<Component> findComponentByType(ComponentType type);
	Iterable<Component> findAllByUserId(Integer userId);
	Iterable<Component> findComponentByUserIdAndType(Integer userId, ComponentType type);
	Component findOneByUserIdAndId(Integer userId, Integer id);
}
