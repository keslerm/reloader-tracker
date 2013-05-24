package com.dasbiersec.reloader.dao;

import com.dasbiersec.reloader.model.Component;
import org.springframework.stereotype.Repository;

@Repository
public class ComponentDAO extends AbstractHibernateDAO
{
	public ComponentDAO()
	{
		super(Component.class);
	}
}
