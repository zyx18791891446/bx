/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.web;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bx.entity.StatisticWorktype;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;
import com.thinkgem.jeesite.modules.bx.service.StatisticsWorkerService;
import com.thinkgem.jeesite.modules.bx.service.StatisticsWorkertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 评价维修质量Controller
 * @author 评价维修质量
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/StatisticsWorktype")
public class StatisticsWorktypeController {

    @Autowired
    private StatisticsWorkertypeService statisticsWorkertypeService;
    @ResponseBody
    @RequestMapping(value =  "")
    public List<StatisticWorktype> list(HttpServletRequest request, HttpServletResponse response, Model model) {
           return statisticsWorkertypeService.getWorkerTypeCount();
    }

    @ResponseBody
    @RequestMapping(value = "getCountByWorkType")
    public String getCountByWorkType(HttpServletRequest request, HttpServletResponse response, Model model) {

        List<StatisticWorktype> list =  statisticsWorkertypeService.getCountByWorkType();
        String json = new Gson().toJson(list);
        return json;
    }


}