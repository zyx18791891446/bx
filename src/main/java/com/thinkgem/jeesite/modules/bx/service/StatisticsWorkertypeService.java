/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bx.dao.StatisticWorktypeDao;
import com.thinkgem.jeesite.modules.bx.dao.StatisticsWorkerDao;
import com.thinkgem.jeesite.modules.bx.entity.StatisticWorktype;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatisticsWorkertypeService {
    @Autowired
    private StatisticWorktypeDao statisticWorktypeDao;
    public List<StatisticWorktype> getWorkerTypeCount(){
        return statisticWorktypeDao.getWorkerTypeCount();
    }

    public  List<StatisticWorktype> getCountByWorkType(){
        return statisticWorktypeDao.getCountByWorkType();
    }

}