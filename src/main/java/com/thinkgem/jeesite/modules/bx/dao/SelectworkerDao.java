/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bx.entity.Selectworker;
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;

/**
 * 选择工人DAO接口
 * @author zyx
 * @version 2019-05-12
 */
@MyBatisDao
public interface SelectworkerDao extends CrudDao<Selectworker> {
	void insertByBatch(List<Selectworker> selectworkers);
}