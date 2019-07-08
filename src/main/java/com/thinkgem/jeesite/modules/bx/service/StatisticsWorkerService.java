/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bx.dao.EvaluateDao;
import com.thinkgem.jeesite.modules.bx.dao.StatisticsWorkerDao;
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatisticsWorkerService extends CrudService<StatisticsWorkerDao, StatisticsWorker> {

    public  Page<StatisticsWorker> staticsWoker(Page<StatisticsWorker> page,StatisticsWorker statisticsWorker){
        statisticsWorker.setPage(page);
       List<StatisticsWorker> list = dao.staticsWoker(statisticsWorker);
      for (StatisticsWorker s: list){

          double i = Double.valueOf(s.getFinishi());
          double a = Double.valueOf(s.getUnfinish());
          double m = i+a;
        int t = (int)m;
          s.setAllmession(t+"");

          Double k = i/m;

          if(k==1){
              s.setRank("优秀");
          }else  if(k>=0.8){
              s.setRank("良好");
          }else  if(k>=0.6){
              s.setRank("一般");
          }else{
              s.setRank("差");
          }
      }
        page.setList(list);
        return page;
    }
}