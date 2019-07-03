/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.lh.entity.Patenttype;

/**
 * 专利类型DAO接口
 * @author 刘航
 * @version 2019-04-30
 */
@MyBatisDao
public interface PatenttypeDao extends CrudDao<Patenttype> {
	
}