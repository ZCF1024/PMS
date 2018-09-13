package com.company.pms.pmsbase.service;

import java.io.Serializable;
import java.util.List;

import com.company.pms.pmsbase.domain.BaseEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Generic Manager that talks to GenericRepository to CRUD POJOs.
 * <p>
 * <p>
 * Extend this interface if you want type safe (no casting necessary) managers
 * for your domain objects.
 *
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 * @author xxx
 */
public interface GenericManager<T extends BaseEntity, PK extends Serializable> {

	/**
	 * * delete entity according given id
	 *
	 * @param id
	 */
	void delete(PK id);

	/**
	 * fetch all entities
	 *
	 * @return list for all entities
	 */
	List<T> findAll();

	/**
	 * fetch all undeleted entities
	 *
	 * @return list for all entities
	 */
	List<T> findAll(Boolean deleted);

	/**
	 * @param page
	 * @return
	 */
	Page<T> findAll(Pageable page);

	/**
	 * fetch specified entity according id;
	 *
	 * @param id
	 *            entity id;
	 * @return entity
	 */
	T findById(PK id);

	/**
	 * @param entities
	 * @return
	 */
	List<T> save(Iterable<T> entities);

	/**
	 * save specified entity;
	 *
	 * @param entity
	 *            entity for saving
	 * @return saved domain entity
	 */
	T save(T entity);
}