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
import com.thinkgem.jeesite.modules.lh.entity.Researchpapers;
import com.thinkgem.jeesite.modules.lh.entity.Researchpapers;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;
import com.thinkgem.jeesite.modules.lh.dao.ResearchpapersDao;

/**
 * 科研论文Service
 * @author zyx
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class ResearchpapersService extends CrudService<ResearchpapersDao, Researchpapers> {
	@Autowired
	private OaProjectInspectionService inspectionService;
	public Researchpapers get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void updateStatus(Researchpapers researchpapers){
		dao.updateStatus(researchpapers);
	}
	public List<Researchpapers> findList(Researchpapers researchpapers) {
		return super.findList(researchpapers);
	}
	
	public Page<Researchpapers> findPage(Page<Researchpapers> page, Researchpapers researchpapers) {
		String officeId = UserUtils.getUser().getOffice().getId();
		if(!"1".equals(officeId)){
			String userId = UserUtils.getUser().getId();
			researchpapers.setUserid(userId);
		}
		
		return super.findPage(page, researchpapers);
	}
	/**
	 * 审核地方法
	 * 
	 * @param Researchpapers
	 */
	@Transactional(readOnly = false)
	public void inspection(Researchpapers researchpapers,String reason){
		
		
		OaProjectInspection projectInspection = new OaProjectInspection();
		String projectId = researchpapers.getId();
		String statusDd = researchpapers.getStatus();
		Date inspectionDate = new Date();
		
		projectInspection.setProjectId(projectId);
		projectInspection.setStatusDd(statusDd);
		projectInspection.setInspectionDate(inspectionDate);
		projectInspection.setReason(reason);	
		
		dao.updateStatus(researchpapers);
		inspectionService.save(projectInspection);
	}
	@Transactional(readOnly = false)
	public void save(Researchpapers researchpapers) {
		if (researchpapers.getIsNewRecord()){
			researchpapers.preInsert();
			String userId = UserUtils.getUser().getId();
			researchpapers.setUserid(userId);//设置用ID
			researchpapers.setStatus("0");//未上传状态
			dao.insert(researchpapers);
		}else{
			researchpapers.preUpdate();
			dao.update(researchpapers);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(Researchpapers researchpapers) {
		super.delete(researchpapers);
	}
	
}