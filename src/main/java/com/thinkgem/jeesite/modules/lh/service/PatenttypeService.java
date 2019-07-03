/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lh.entity.Patenttype;
import com.thinkgem.jeesite.modules.lh.dao.PatenttypeDao;

/**
 * 专利类型Service
 * @author 刘航
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class PatenttypeService extends CrudService<PatenttypeDao, Patenttype> {

	public Patenttype get(String id) {
		return super.get(id);
	}
	
	public List<Patenttype> findList(Patenttype patenttype) {
		return super.findList(patenttype);
	}
	
	public List<Patenttype> findAllList(Patenttype patenttype) {
		return dao.findAllList(patenttype);
	}
	
	public Page<Patenttype> findPage(Page<Patenttype> page, Patenttype patenttype) {
		return super.findPage(page, patenttype);
	}
	
	@Transactional(readOnly = false)
	public void save(Patenttype patenttype) {
		super.save(patenttype);
	}
	
	@Transactional(readOnly = false)
	public void delete(Patenttype patenttype) {
		super.delete(patenttype);
	}
	
}