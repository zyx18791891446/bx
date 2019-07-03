/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;

/**
 * 评价维修质量DAO接口
 * @author 评价维修质量
 * @version 2019-05-12
 */
@MyBatisDao
public interface EvaluateDao extends CrudDao<Evaluate> {
	List<Evaluate> evaluateDetails(Evaluate evaluate);
}