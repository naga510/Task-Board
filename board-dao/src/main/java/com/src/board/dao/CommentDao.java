package com.src.board.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.src.board.domain.Comment;
import com.src.board.domain.Item;

@Component
public class CommentDao {

    @PersistenceContext
	private EntityManager em;
    
    public List<Comment> getComments(Long itemId)
    {
    	List<Comment> comments=null;
    	String hql = "from Comment c where c.itemId = :itemId";
    	Query query = em.createQuery(hql);
    	query.setParameter("itemId", itemId);
    	comments=query.getResultList();
    	return comments;
    }
    
    @Transactional
    public void addComment(Comment comment)
    {    	
    	em.persist(comment);    
    	em.close();
    }
    
    public EntityManager getEntityManager() {
    	return em;
    	}
    	 
    	public void setEntityManager(EntityManager entityManager) {
    	this.em = entityManager;
    	}
}
