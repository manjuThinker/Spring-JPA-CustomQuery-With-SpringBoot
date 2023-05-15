package com.SpringJPACustomQueryWithSpringBoot.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.SpringJPACustomQueryWithSpringBoot.model.Tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

	@Query("SELECT t FROM Tutorial t")
	List<Tutorial> findAll();

	@Query("SELECT t FROM Tutorial t WHERE t.published=?1")
	List<Tutorial> findByPublished(boolean isPublished);

	@Query("SELECT t FROM Tutorial t WHERE t.title LIKE %?1%")
	List<Tutorial> findByTitleLike(String title);

	@Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
	List<Tutorial> findByTitleLikeCaseInsensitive(String title);

	@Query("SELECT t FROM Tutorial t WHERE t.level >= ?1")
	List<Tutorial> findByLevelGreaterThanEqual(int level);

	@Query("SELECT t FROM Tutorial t WHERE t.createdAt >= ?1")
	List<Tutorial> findByDateGreaterThanEqual(Date date);

	@Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
	List<Tutorial> findByTitleAndSort(String title, Sort sort);

	@Query("SELECT t FROM Tutorial t WHERE t.published=?1")
	List<Tutorial> findByPublishedAndSort(boolean isPublished, Sort sort);

	@Query("SELECT t FROM Tutorial t")
	Page<Tutorial> findAllWithPagination(Pageable pageable);

}
