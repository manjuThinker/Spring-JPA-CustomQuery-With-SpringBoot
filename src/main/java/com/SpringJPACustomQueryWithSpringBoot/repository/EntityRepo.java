package com.SpringJPACustomQueryWithSpringBoot.repository;

import org.springframework.stereotype.Repository;

import com.SpringJPACustomQueryWithSpringBoot.model.Tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class EntityRepo {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	 public Tutorial save(Tutorial tutorial) {
		 entityManager.persist(tutorial);
	    return tutorial;
	  }

	  @Transactional
	  public int deleteAll() {
	    Query query = entityManager.createQuery("DELETE FROM Tutorial");
	    return query.executeUpdate();
	  }


}
