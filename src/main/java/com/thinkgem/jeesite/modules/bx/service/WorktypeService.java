/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bx.entity.Worktype;
import com.thinkgem.jeesite.modules.bx.dao.WorktypeDao;

/**
 * 工人类型Service
 * @author zyx
 * @version 2019-05-12
 */
@Service
@Transactional(readOnly = true)
public class WorktypeService extends CrudService<WorktypeDao, Worktype> {

	public Worktype get(String id) {
		return super.get(id);
	}
	
	public List<Worktype> findList(Worktype worktype) {
		return super.findList(worktype);
	}
	
	public Page<Worktype> findPage(Page<Worktype> page, Worktype worktype) {
		return super.findPage(page, worktype);
	}
	
	@Transactional(readOnly = false)
	public void save(Worktype worktype) {
		super.save(worktype);
	}
	
	@Transactional(readOnly = false)
	public void delete(Worktype worktype) {
		super.delete(worktype);
	}
	
}