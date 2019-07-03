/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lh.entity.Patent;
import com.thinkgem.jeesite.modules.lh.entity.Researchpapers;

/**
 * 科研论文DAO接口
 * @author zyx
 * @version 2019-04-30
 */
@MyBatisDao
public interface ResearchpapersDao extends CrudDao<Researchpapers> {
	void updateStatus(Researchpapers researchpapers);
}