package com.dasbiersec.reloader.dao;

import com.dasbiersec.reloader.model.Round;
import org.springframework.stereotype.Repository;

@Repository
public class RoundDAO extends AbstractHibernateDAO
{
	public RoundDAO()
	{
		super(Round.class);
	}
}
