/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.lh.dao.ResearchprojectDao;
import com.thinkgem.jeesite.modules.lh.entity.Researchproject;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;

/**
 * 科研项目Service
 * @author 刘航
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class ResearchprojectService extends CrudService<ResearchprojectDao, Researchproject> {
	@Autowired
	private OaProjectInspectionService inspectionService;
	public Researchproject get(String id) {
		return super.get(id);
	}
	
	public List<Researchproject> findList(Researchproject researchproject) {
		return super.findList(researchproject);
	}
	
	@Transactional(readOnly = false)
	public void updateStatus(Researchproject researchproject){
		dao.updateStatus(researchproject);
	}
	/**
	 * 审核地方法
	 * 
	 * @param researchproject
	 */
	@Transactional(readOnly = false)
	public void inspection(Researchproject researchproject,String reason){
		
		
		OaProjectInspection projectInspection = new OaProjectInspection();
		String projectId = researchproject.getId();
		String statusDd = researchproject.getStatus();
		Date inspectionDate = new Date();
		
		projectInspection.setProjectId(projectId);
		projectInspection.setStatusDd(statusDd);
		projectInspection.setInspectionDate(inspectionDate);
		projectInspection.setReason(reason);	
		
		dao.updateStatus(researchproject);
		inspectionService.save(projectInspection);
	}
	public Page<Researchproject> findPage(Page<Researchproject> page, Researchproject researchproject) {
		String officeId = UserUtils.getUser().getOffice().getId();
		if(!"1".equals(officeId)){//非管理员设置用户ID
			String userId = UserUtils.getUser().getId();
			researchproject.setUserid(userId);
		}
		
		return super.findPage(page, researchproject);
	}
	
	@Transactional(readOnly = false)
	public void save(Researchproject researchproject) {
		if (researchproject.getIsNewRecord()){
			researchproject.preInsert();
			String userId = UserUtils.getUser().getId();
			researchproject.setUserid(userId);//设置用ID
			researchproject.setStatus("0");//未上传状态
			dao.insert(researchproject);
		}else{
			researchproject.preUpdate();
			dao.update(researchproject);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Researchproject researchproject) {
		super.delete(researchproject);
	}
	
}