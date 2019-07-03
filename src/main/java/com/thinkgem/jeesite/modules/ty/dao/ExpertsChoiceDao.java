/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;

/**
 * 专家选择DAO接口
 * @author zdd
 * @version 2019-03-09
 */
@MyBatisDao
public interface ExpertsChoiceDao extends CrudDao<ExpertsChoice> {
	void insertByBatch(List<ExpertsChoice> expertsChoices);
}