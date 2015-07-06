package com.src.board.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

public class GenericDaoJpaImpl<T, PK> implements GenericDao<T, PK> {

protected Class<T> entityClass;

@PersistenceContext
protected EntityManager entityManager;


@Override
@Transactional
public T create(T t) {
    this.entityManager.persist(t);
    return t;
}

@Override
public T find(PK id) {
    return this.entityManager.find(entityClass, id);
}

@Override
@Transactional
public T update(T t) {
    return this.entityManager.merge(t);
}

@Override
@Transactional
public void delete(T t) {
    t = this.entityManager.merge(t);
    this.entityManager.remove(t);
}

@Override
public List<T> findByNamedQuery(String nameQuery) {
	Query query = this.entityManager.createNamedQuery(nameQuery);
	return (List<T>)query.getResultList();
}

@Override
public List<T> findByNamedQueryAndParams(String nameQuery,
		Map<String, Object> params) {
	Query query = this.entityManager.createNamedQuery(nameQuery);
	for(Parameter<?> p:query.getParameters()) {
		query.setParameter(p.getName(), params.get(p.getName()));
	}
	return (List<T>)query.getResultList();
}


}
