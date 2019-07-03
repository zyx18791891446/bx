/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lh.entity.Researchprojecttype;
import com.thinkgem.jeesite.modules.lh.dao.ResearchprojecttypeDao;

/**
 * 科研项目类型Service
 * @author 刘航
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class ResearchprojecttypeService extends CrudService<ResearchprojecttypeDao, Researchprojecttype> {

	public Researchprojecttype get(String id) {
		return super.get(id);
	}
	
	public List<Researchprojecttype> findList(Researchprojecttype researchprojecttype) {
		return super.findList(researchprojecttype);
	}
	
	public Page<Researchprojecttype> findPage(Page<Researchprojecttype> page, Researchprojecttype researchprojecttype) {
		return super.findPage(page, researchprojecttype);
	}
	
	@Transactional(readOnly = false)
	public void save(Researchprojecttype researchprojecttype) {
		super.save(researchprojecttype);
	}
	
	@Transactional(readOnly = false)
	public void delete(Researchprojecttype researchprojecttype) {
		super.delete(researchprojecttype);
	}
	
}