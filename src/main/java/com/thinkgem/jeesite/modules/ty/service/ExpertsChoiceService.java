/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;
import com.thinkgem.jeesite.modules.ty.dao.ExpertsChoiceDao;

/**
 * 专家选择Service
 * @author zdd
 * @version 2019-03-09
 */
@Service
@Transactional(readOnly = true)
public class ExpertsChoiceService extends CrudService<ExpertsChoiceDao, ExpertsChoice> {

	public ExpertsChoice get(String id) {
		return super.get(id);
	}
	
	public List<ExpertsChoice> findList(ExpertsChoice expertsChoice) {
		return super.findList(expertsChoice);
	}
	
	public Page<ExpertsChoice> findPage(Page<ExpertsChoice> page, ExpertsChoice expertsChoice) {
		return super.findPage(page, expertsChoice);
	}
	
	@Transactional(readOnly = false)
	public void save(ExpertsChoice expertsChoice) {
		super.save(expertsChoice);
	}
	
	@Transactional(readOnly = false)
	public void delete(ExpertsChoice expertsChoice) {
		super.delete(expertsChoice);
	}
	
}