/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.Accessory;

/**
 * 附件DAO接口
 * @author zdd
 * @version 2019-03-09
 */
@MyBatisDao
public interface AccessoryDao extends CrudDao<Accessory> {
	
}