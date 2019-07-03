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
import com.thinkgem.jeesite.modules.lh.entity.Patent;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.dao.OaProjectInspectionDao;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;
import com.thinkgem.jeesite.modules.lh.dao.PatentDao;

/**
 * 专利Service
 * @author 刘航
 * @version 2019-04-30
 */
@Service
@Transactional(readOnly = true)
public class PatentService extends CrudService<PatentDao, Patent> {
	@Autowired
	private OaProjectInspectionService inspectionService;
	
	public Patent get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void updateStatus(Patent patent){
		dao.updateStatus(patent);
	}
	
	public List<Patent> findList(Patent patent) {
		return super.findList(patent);
	}
	/**
	 * 查询某个教师的所有专利
	 */
	public Page<Patent> findPage(Page<Patent> page, Patent patent) {
		String officeId = UserUtils.getUser().getOffice().getId();
		if(!"1".equals(officeId)){
			String userId = UserUtils.getUser().getId();
			patent.setUerid(userId);
		}
		return super.findPage(page, patent);
	}
	/**
	 * 审核地方法
	 * 
	 * @param patent
	 */
	@Transactional(readOnly = false)
	public void inspection(Patent patent,String reason){
		
		
		OaProjectInspection projectInspection = new OaProjectInspection();
		String projectId = patent.getId();
		String statusDd = patent.getStatus();
		Date inspectionDate = new Date();
		
		projectInspection.setProjectId(projectId);
		projectInspection.setStatusDd(statusDd);
		projectInspection.setInspectionDate(inspectionDate);
		projectInspection.setReason(reason);	
		
		dao.updateStatus(patent);
		inspectionService.save(projectInspection);
	}
	
	@Transactional(readOnly = false)
	public void save(Patent patent) {
		if (patent.getIsNewRecord()){
			patent.preInsert();
			String userId = UserUtils.getUser().getId();
			patent.setUerid(userId);//设置用ID
			patent.setStatus("0");//未上传状态
			dao.insert(patent);
		}else{
			patent.preUpdate();
			dao.update(patent);
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(Patent patent) {
		super.delete(patent);
	}
	
}