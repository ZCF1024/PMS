package com.company.pms.pmsbase.service.impl;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.repository.GenericRepository;
import com.company.pms.pmsbase.service.GenericManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * This class serves as the Base class for all other Managers - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>
 * To register this class in your Spring context file, use the following XML.
 *
 * @param <T>
 *            a type variable
 * @param <PK>
 *            the primary key for that type
 */
@Transactional
public class GenericManagerImpl<T extends BaseEntity, PK extends Serializable> implements GenericManager<T, PK> {

	protected GenericRepository<T, PK> repository;

	@Override
	public void delete(PK id) {
		this.repository.delete(id);
	}

	@Override
	public List<T> findAll() {
		return this.repository.findAll();
	}

	@Override
	public List<T> findAll(Boolean deleted) {
		return this.repository.findAllByDeleted(deleted);
	}

	@Override
	public Page<T> findAll(Pageable page) {
		Page<T> result = this.repository.findAll(page);
		return result;
	}

	@Override
	public T findById(PK id) {
		return this.repository.getOne(id);
	}

	@Override
	public List<T> save(Iterable<T> entities) {
		return this.repository.save(entities);
	}

	@Override
	public T save(T entity) {
		return this.repository.saveAndFlush(entity);
	}

}
