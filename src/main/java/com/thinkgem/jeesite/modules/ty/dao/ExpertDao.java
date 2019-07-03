/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import java.util.List;

import com.opensymphony.module.sitemesh.Page;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.Expert;

/**
 * 专业人员库DAO接口
 * @author zdd
 * @version 2019-03-09
 */
@MyBatisDao
public interface ExpertDao extends CrudDao<Expert> {
	//获取专家信息
	List<Expert> getExpert(Expert expert);
}