/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.bx.entity.StatisticWorktype;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;
import com.thinkgem.jeesite.modules.bx.entity.Worktype;

import java.util.List;

/**
 * 统计工人类型DAO接口
 * @author zyx
 * @version 2019-05-12
 */
@MyBatisDao
public interface StatisticWorktypeDao {
   public  List<StatisticWorktype> getWorkerTypeCount();
    public  List<StatisticWorktype> getCountByWorkType();

}