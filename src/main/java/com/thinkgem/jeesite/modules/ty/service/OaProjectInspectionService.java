/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.dao.OaProjectInspectionDao;

/**
 * 初审项目Service
 * @author zyx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class OaProjectInspectionService extends CrudService<OaProjectInspectionDao, OaProjectInspection> {

	public OaProjectInspection get(String id) {
		return super.get(id);
	}
	
	public List<OaProjectInspection> findList(OaProjectInspection oaProjectInspection) {
		return super.findList(oaProjectInspection);
	}
	
	public Page<OaProjectInspection> findPage(Page<OaProjectInspection> page, OaProjectInspection oaProjectInspection) {
		return super.findPage(page, oaProjectInspection);
	}
	
	@Transactional(readOnly = false)
	public void save(OaProjectInspection oaProjectInspection) {
		super.save(oaProjectInspection);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProjectInspection oaProjectInspection) {
		super.delete(oaProjectInspection);
	}
	
}