/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lh.entity.Researchpaperstype;
import com.thinkgem.jeesite.modules.lh.dao.ResearchpaperstypeDao;

/**
 * 科研论文类型Service
 * @author zyx
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class ResearchpaperstypeService extends CrudService<ResearchpaperstypeDao, Researchpaperstype> {

	public Researchpaperstype get(String id) {
		return super.get(id);
	}
	
	public List<Researchpaperstype> findList(Researchpaperstype researchpaperstype) {
		return super.findList(researchpaperstype);
	}
	
	public Page<Researchpaperstype> findPage(Page<Researchpaperstype> page, Researchpaperstype researchpaperstype) {
		return super.findPage(page, researchpaperstype);
	}
	
	@Transactional(readOnly = false)
	public void save(Researchpaperstype researchpaperstype) {
		super.save(researchpaperstype);
	}
	
	@Transactional(readOnly = false)
	public void delete(Researchpaperstype researchpaperstype) {
		super.delete(researchpaperstype);
	}
	
}