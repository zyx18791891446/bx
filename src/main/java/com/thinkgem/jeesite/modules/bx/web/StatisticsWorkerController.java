/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;
import com.thinkgem.jeesite.modules.bx.entity.Orders;
import com.thinkgem.jeesite.modules.bx.entity.Selectworker;
import com.thinkgem.jeesite.modules.bx.entity.StatisticsWorker;
import com.thinkgem.jeesite.modules.bx.service.EvaluateService;
import com.thinkgem.jeesite.modules.bx.service.StatisticsWorkerService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 评价维修质量Controller
 * @author 评价维修质量
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/StatisticsWorker")
public class StatisticsWorkerController extends BaseController {

    @Autowired
    private StatisticsWorkerService statisticsWorkerService;

    @RequestMapping(value = {"list", ""})
    public String list(StatisticsWorker statisticsWorker, HttpServletRequest request, HttpServletResponse response, Model model) {

        Page<StatisticsWorker> page = statisticsWorkerService.staticsWoker(new Page<StatisticsWorker>(request, response), statisticsWorker);
        model.addAttribute("page", page);
        return "modules/bx/StatisticWorkerChart";
    }

    @RequestMapping(value = "export", method= RequestMethod.POST)
    public String exportFile(StatisticsWorker statisticsWorker, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            String fileName = "工人任务信息数据"+ DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<StatisticsWorker> page =statisticsWorkerService.staticsWoker(new Page<StatisticsWorker>(request, response), statisticsWorker);
            new ExportExcel("工人任务信息数据", StatisticsWorker.class).setDataList(page.getList()).write(response, fileName).dispose();
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出故障信息数据失败！失败信息："+e.getMessage());
        }
        return "redirect:"+Global.getAdminPath()+"/bx/StatisticsWorker/?repage";
    }
}