package com.src.board.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao<T, PK> {
    T create(T t);
    T find(PK id);
    T update(T t);
    void delete(T t);
    public List<T> findByNamedQuery(String nameQuery);
    public T findSingleRecordByNamedQuery(String nameQuery);
    public List<T> findByNamedQueryAndParams(String nameQuery,Map<String,Object> params);
    public T findSingleRecordByNamedQueryAndParams(String nameQuery,Map<String,Object> params);
}