/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lh.entity.Patent;

/**
 * 专利DAO接口
 * @author 刘航
 * @version 2019-04-30
 */
@MyBatisDao
public interface PatentDao extends CrudDao<Patent> {
	void updateStatus(Patent patent);
	List<Patent> findLists(Patent patent);//查询所有的专利
}