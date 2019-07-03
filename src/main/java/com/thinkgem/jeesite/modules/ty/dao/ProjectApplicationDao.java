/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.Expert;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;
import com.thinkgem.jeesite.modules.ty.entity.ProjectApplication;

/**
 * 项目申报DAO接口
 * @author zqq
 * @version 2019-03-09
 */
@MyBatisDao
public interface ProjectApplicationDao extends CrudDao<ProjectApplication> {
	void updateStatusDd(ProjectApplication projectApplication);//修改状态
	List<ProjectApplication> findLists(ProjectApplication projectApplication);
	List<ProjectApplication> expense(ProjectApplication projectApplication);
	List<ProjectApplication> findReviewList(ProjectApplication projectApplication);//查询需要评审的项目
	List<ProjectApplication> ShiJifindList(ProjectApplication projectApplication);//市级查询所有
	/**
	 * 查询要验收的项目
	 * @return
	 */
	public List<ProjectApplication> findListChecks(ProjectApplication projectApplication);
	public List<ProjectApplication> xianJiAchievmentList(ProjectApplication projectApplication);//县级绩效
	public List<Expert> expertname(ProjectApplication projectApplication);//查询专家姓名
	public List<OaProjectReview> review(ProjectApplication projectApplication);//查询评审信息
}