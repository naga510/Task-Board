package com.src.board.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.src.board.domain.Item;


@Component
public class ItemDao {
	@PersistenceContext
	private EntityManager em;
    
    public Item getItem(Long id)
    {    	    
    	return em.find(Item.class, id);
    }
    
    public List<Item> getItemsByBoardId(Long boardId) {
    	List<Item> items=null;
    	String hql = "from Item item where item.board.boardId = :boardId";
    	Query query = em.createQuery(hql);
		query.setParameter("boardId", boardId);
		items=query.getResultList();
		return items;
    }
    
    @Transactional
    public void save(Item member)
    {
    	em.persist(member);    
    	em.close();
    }
    
    @Transactional
    public Item update(Item item) {
    	Item newItem=em.merge(item);
    	em.flush();
    	return newItem;
    }
    
    @Transactional
    public void delete(Item item) {
    	item=em.merge(item);
    	em.flush();
    	em.remove(item);
    }
        	
}
