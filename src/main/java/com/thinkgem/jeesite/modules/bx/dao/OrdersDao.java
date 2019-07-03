/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bx.entity.Orders;

/**
 * 维修订单DAO接口
 * @author zyx
 * @version 2019-05-12
 */
@MyBatisDao
public interface OrdersDao extends CrudDao<Orders> {
	void updateStatus(Orders orders);
	List<Orders> getRepairMan(Orders orders);//查询某个维修订单下的维修人
	List<Orders> getRepairOrders(Orders orders);//维修工人查询已分配和完成的任务
	
}