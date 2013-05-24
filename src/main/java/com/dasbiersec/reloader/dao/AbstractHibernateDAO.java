package com.dasbiersec.reloader.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public abstract class AbstractHibernateDAO< T extends Serializable> {

	private final Class< T> clazz;
	@Autowired
	SessionFactory sessionFactory;

	public AbstractHibernateDAO(final Class< T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public T getById(final Integer id) {
		return (T) this.getCurrentSession().get(this.clazz, id);
	}

	public List< T> getAll() {
		return this.getCurrentSession()
				.createQuery("from " + this.clazz.getName()).list();
	}

	public void create(final T entity) {
		this.getCurrentSession().persist(entity);
	}

	public void update(final T entity) {
		this.getCurrentSession().merge(entity);
	}

	public void delete(final T entity) {
		this.getCurrentSession().delete(entity);
	}

	public void deleteById(final Integer entityId) {
		final T entity = this.getById(entityId);
		this.delete(entity);
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected final Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
}


