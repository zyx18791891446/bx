/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bx.entity.OrderState;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;

import java.util.List;

/**
 * 维修订单状态DAO接口
 * @author zyx
 * @version 2019-05-12
 */
@MyBatisDao
public interface StatisticsWorkerDao extends CrudDao<StatisticsWorker> {
         List<StatisticsWorker> staticsWoker(StatisticsWorker statisticsWorker);
}