/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;

/**
 * 项目申请（初审）DAO接口
 * @author zyx
 * @version 2019-03-13
 */
@MyBatisDao
public interface OaProjectInspectionDao extends CrudDao<OaProjectInspection> {
	
}