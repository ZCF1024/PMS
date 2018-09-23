package com.company.pms.pmsbase.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.company.pms.pmsbase.domain.BaseEntity;
import com.company.pms.pmsbase.domain.Result;
import com.company.pms.pmsbase.service.GenericManager;

import com.company.pms.pmsbase.utils.ResultUtil;
import com.company.pms.pmsbase.utils.UpdateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.*;

@RestController
public abstract class GenericController<T extends BaseEntity, PK extends Serializable, M extends GenericManager<T, PK>>
		extends BaseController {
	protected PK id;
	protected M manager;
	protected T model;
	protected Page<T> page;

	protected Pageable pageable;
	protected int pageNumber = 0;
	protected int pageSize = 20;

	/**
	 * @param model
	 * @return
	 */
	@PostMapping(value = "/", produces = "application/json", consumes = "application/json")
	public T create(@RequestBody T model) {
		this.model = model;
		Date date = new Date();
		this.model.setDateCreated(date);
		this.model.setDateModified(date);
		this.model = this.manager.save(this.model);
		return this.model;
	}

	/**
	 * @param id
	 * @throws IOException
	 */

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public Result delete(@PathVariable PK id) throws IOException {
		this.manager.delete(id);
		return ResultUtil.success();
	}

	/**
	 * 根据输入，返回分页结果中的当前页，包括当前页信息和其中的实体对象集合
	 *
	 * @return
	 */
	@GetMapping(value = "", produces = "application/json")
	public Page<T> get(@RequestParam(name = "page", defaultValue = "0") String pageNumber,
			@RequestParam(name = "limit", defaultValue = "20") String pageSize) {
		if (StringUtils.isNotBlank(pageNumber)) {
			this.pageNumber = Integer.valueOf(pageNumber) - 1;
		}
		if (StringUtils.isNotBlank(pageSize)) {
			this.pageSize = Integer.valueOf(pageSize);
		}
		this.pageable = new PageRequest(this.pageNumber, this.pageSize, new Sort(Direction.ASC, "id"));
		this.page = this.manager.findAll(this.pageable);
		logger.info(this.page);
		return this.page;
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}", produces = "application/json")
	public T getOne(@PathVariable PK id) {
		return this.manager.findById(id);
	}

	/**
	 * @param id
	 * @param model
	 * @return
	 */
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public T update(@PathVariable PK id, @RequestBody T model) {
		model.setId(Long.valueOf(id.toString()));
		model.setDateModified(new Date());// 更新修改时间
		T tmodel = this.manager.findById(id);
		UpdateUtils.copyNotNullProperties(model, tmodel);
		this.model = this.manager.save(tmodel);
		return model;
	}

}