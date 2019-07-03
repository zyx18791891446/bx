/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;
import com.thinkgem.jeesite.modules.bx.dao.EvaluateDao;

/**
 * 评价维修质量Service
 * @author 评价维修质量
 * @version 2019-05-12
 */
@Service
@Transactional(readOnly = true)
public class EvaluateService extends CrudService<EvaluateDao, Evaluate> {

	public Evaluate get(String id) {
		return super.get(id);
	}
	
	public List<Evaluate> findList(Evaluate evaluate) {
		return super.findList(evaluate);
	}
	
	public List<Evaluate> evaluateDetails(Evaluate evaluate) {
		return dao.evaluateDetails(evaluate);
	}
	
	public Page<Evaluate> findPage(Page<Evaluate> page, Evaluate evaluate) {
		return super.findPage(page, evaluate);
	}
	
	@Transactional(readOnly = false)
	public void save(Evaluate evaluate) {
		super.save(evaluate);
	}
	
	@Transactional(readOnly = false)
	public void delete(Evaluate evaluate) {
		super.delete(evaluate);
	}
	
}