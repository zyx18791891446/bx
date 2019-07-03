/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.ty.entity.ProjectChart;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.entity.Accessory;
import com.thinkgem.jeesite.modules.ty.entity.Chart;
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;
import com.thinkgem.jeesite.modules.ty.entity.OaAchievements;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;
import com.thinkgem.jeesite.modules.ty.entity.ProjectApplication;
import com.thinkgem.jeesite.modules.ty.service.AccessoryService;
import com.thinkgem.jeesite.modules.ty.service.ExpertsChoiceService;
import com.thinkgem.jeesite.modules.ty.service.OaAchievementsService;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;
import com.thinkgem.jeesite.modules.ty.service.OaProjectReviewService;
import com.thinkgem.jeesite.modules.ty.service.ProjectApplicationService;

/**
 * 项目申报Controller
 * @author zqq
 * @version 2019-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/projectApplication")
public class ProjectApplicationController extends BaseController {

	@Autowired
	private ProjectApplicationService projectApplicationService;
	@Autowired
	private OaProjectInspectionService oaProjectInspectionService;
	@Autowired
	private OaProjectReviewService oaProjectReviewService;
	@Autowired
	private AccessoryService accessoryService; 
	@Autowired
	private OaAchievementsService oaAchievementsService;
	
	@ModelAttribute
	public ProjectApplication get(@RequestParam(required=false) String id) {
		ProjectApplication entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = projectApplicationService.get(id);
		}
		if (entity == null){
			entity = new ProjectApplication();
		}
		return entity;
	}
	@RequestMapping(value = "projectApplicationChart")
	@ResponseBody
	public String ProjectApplicationChart(Chart chart) {
		List<Chart> list = projectApplicationService.expense(chart);

		List<Chart> list2 = new ArrayList<>();
		for (Chart projectApplication2 : list) {

			if (projectApplication2.getApfund() == null) {
				projectApplication2.setApfund("0");
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}

	@RequestMapping(value = "projectChart")
	@ResponseBody
	public String ProjectChart(Chart chart) {
		List<Chart> list = projectApplicationService.expense(chart);
		List<ProjectChart> ProjectChartList = new ArrayList<>();
		for (Chart projectApplication2 : list) {
			// 相当于ProjectApplication projectApplication2=list[i]
			ProjectChart projectChart = new ProjectChart();
			projectChart.setName(projectApplication2.getGname());
			projectChart.setValue(projectApplication2.getApfund());
			if (projectChart.getValue() == null) {
				projectChart.setValue("0");
			}
			ProjectChartList.add(projectChart);

		}

		String json = new Gson().toJson(ProjectChartList);
		return json;
	}

	@RequestMapping(value = "projectChartGP")
	@ResponseBody
	public String ProjectChartGP(Chart chart) {
		// Map<String, Object> result = Maps.newHashMap();
		List<Chart> list = projectApplicationService.expense(chart);
		List<ProjectChart> ProjectChartList = new ArrayList<>();
		for (Chart projectApplication2 : list) {
			ProjectChart projectChart = new ProjectChart();
			projectChart.setName(projectApplication2.getGname());
			projectChart.setValue(projectApplication2.getApname());
			if (projectChart.getValue() == null) {
				projectChart.setValue("0");
			}
			ProjectChartList.add(projectChart);
		}

		String json = new Gson().toJson(ProjectChartList);
		return json;
	}

	@RequestMapping(value = "chart")
	public String chart(Chart chart, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		List<Chart> list = projectApplicationService.expense(chart);
		model.addAttribute("list", list);
		return "modules/ty/projectApplicationChart";
	}
	/**
	 * 验收项目
	 * @param project
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "checkAccept")
	public String checkAccept(ProjectApplication project, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectApplication> page = projectApplicationService.findListChecks(new Page<ProjectApplication>(request, response), project); 
		model.addAttribute("page", page);
		return "modules/ty/checkList";
	}
	
	/**
	 * 评审的查询方法（根据状态查询）
	 * @param projectApplication
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ty:projectApplication:view")
	@RequestMapping(value = "review")
	public String review(ProjectApplication projectApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProjectApplication> page = projectApplicationService.findReviewPages(new Page<ProjectApplication>(request, response), projectApplication); 
		model.addAttribute("page", page);
		return "modules/ty/oaProjectReviewList";
	}
	
	/**
	 * 
	 * 绩效中的项目列表
	 * @param projectApplication
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ty:projectApplication:view")
	@RequestMapping(value = "achievementList")
	public String achievementList(ProjectApplication projectApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Office office = UserUtils.getUser().getOffice(); //获取当前用户的部门
		projectApplication.setOffice(office);
		String officeId = UserUtils.getUser().getOffice().getId();
		
		Page<ProjectApplication> page = null;
		//利用officeID判断市级或者县级登陆
		if(officeId.equals("1")){//ID为1是市级
			 page = projectApplicationService.findPages(new Page<ProjectApplication>(request, response), projectApplication); 
		}else{
			 page = projectApplicationService.xianJiAchievmentList(new Page<ProjectApplication>(request, response), projectApplication); 
		}
		
		model.addAttribute("page", page);
		return "modules/ty/projectAchievementList";
	}
	
//	projectAchievementList.jsp
	
	/**
	 * 查看评审详细信息
	 * @param projectApplication
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ty:projectApplication:view")
	@RequestMapping(value = "reviewForm")
	public String reviewForm(ProjectApplication projectApplication, Model model) {
		model.addAttribute("projectApplication", projectApplication);
		return "modules/ty/oaProjectReviewForm";
	}
	
	/**
	 * 项目评审： 1.改项目状态 2.给专家选择表加一条记录  3.给评审表加一条记录
	 * @param projectApplication
	 * @param model
	 * @param expertId  专家ID
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ty:projectApplication:edit")
	@RequestMapping(value = "updateStatusDd")
	public String selectExpert(ProjectApplication projectApplication,String[] expertID,String expertNum, Model model,RedirectAttributes redirectAttributes) {		
		try {
			projectApplicationService.selectExpert(projectApplication,expertID,expertNum);
		} catch (Exception e) {
			addMessage(redirectAttributes, "没有可选择的专家");
			e.printStackTrace();
		}
		return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/review?StatusDd=3";
	}
	//项目上报
		@RequiresPermissions("ty:projectApplication:edit")
		@RequestMapping(value="upload")
		public String upload(ProjectApplication projectApplication, RedirectAttributes redirectAttributes) { 
			projectApplication.setStatusDd("1");//修改项目的状态
			
			projectApplicationService.save(projectApplication);
			addMessage(redirectAttributes, "项目上传成功");
			return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/?repage";
		}
		
		@RequiresPermissions("ty:projectApplication:view")
		@RequestMapping(value = {"list", ""})
		public String list(ProjectApplication projectApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
			Office office = UserUtils.getUser().getOffice(); //获取当前用户的部门
			projectApplication.setOffice(office);
			String officeId = UserUtils.getUser().getOffice().getId();
			
			Page<ProjectApplication> page = null;
			//利用officeID判断市级或者县级登陆
			if(officeId.equals("1")){//ID为1是市级
				 page = projectApplicationService.ShiJifindList(new Page<ProjectApplication>(request, response), projectApplication); 
			}else{
				 page = projectApplicationService.findPage(new Page<ProjectApplication>(request, response), projectApplication); 
			}
			model.addAttribute("page", page);
			return "modules/ty/projectApplicationList";
		}
		//项目初审列表
		@RequiresPermissions("ty:projectApplication:view")
		@RequestMapping(value = "listed")
		public String listed(ProjectApplication projectApplication, HttpServletRequest request, HttpServletResponse response, Model model) {
			Page<ProjectApplication> page = projectApplicationService.findPages(new Page<ProjectApplication>(request, response), projectApplication); 
			model.addAttribute("page", page);
			System.out.println("-_-");
			return "modules/ty/projectApplicationListed";
		}
		//项目初审结果
		@RequiresPermissions("ty:projectApplication:view")
		@RequestMapping(value="firstinstance")
		public String firstInstance(ProjectApplication projectApplication, RedirectAttributes redirectAttributes) { 
			System.out.println(projectApplication.getFiles()+"==="+projectApplication.getCityfiles());
			String inspectionFiles = projectApplication.getCityfiles();//获取市级初审项目时文件名
			String projectFiles = projectApplication.getFiles();//获取申报项目时的文件
			
			projectApplicationService.save(projectApplication);
			
			OaProjectInspection oaProjectInspection = projectApplication.getOaProjectInspection();
			oaProjectInspection.setProjectId(projectApplication.getId());
			oaProjectInspection.setStatusDd(projectApplication.getStatusDd());
			oaProjectInspection.setInspectionDate(new Date());
			oaProjectInspection.setInspectionFiles(inspectionFiles);
			oaProjectInspection.setProjectFiles(projectFiles);
			oaProjectInspectionService.save(oaProjectInspection);
			addMessage(redirectAttributes, "项目初审成功");
			return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/listed?StatusDd=1";
		}
		
		//管理员查看详情
		@RequiresPermissions("ty:projectApplication:view")
		@RequestMapping(value = "details")
		public String details(ProjectApplication projectApplication, Model model,HttpServletRequest request) {
			//查询初审
			OaProjectInspection oaProjectInspection = new OaProjectInspection();
			String projectId = projectApplication.getId();
			oaProjectInspection.setProjectId(projectId);
			Page<OaProjectInspection> inspectionPage  =  oaProjectInspectionService.findPage(new Page<>(), oaProjectInspection);
			//查询评审   查询专家
			String string=projectApplicationService.expertname(projectApplication);
			List<OaProjectReview> oaprojectReview=projectApplicationService.review(projectApplication);
			/*OaProjectReview oaProjectReview = new OaProjectReview();
			oaProjectReview.setProjectId(projectId);
			Page<OaProjectReview> reviewPage  = oaProjectReviewService.findExpert(new Page<>(), oaProjectReview);*/
			//查询绩效
			OaAchievements oaAchievements = new OaAchievements();
			oaAchievements.setProjectId(projectId);
			Page<OaAchievements> oaAchievementsPage  = oaAchievementsService.findPage(new Page<>(), oaAchievements);
			
			
			
			model.addAttribute("projectApplication", projectApplication);
			model.addAttribute("inspectionPage",inspectionPage );
			model.addAttribute("string", string);
            model.addAttribute("oaprojectReview", oaprojectReview);
			model.addAttribute("oaAchievementsPage",oaAchievementsPage );
			
			return "modules/ty/projectApplicationDetails";
		}
		
		//市级管理员查看详情
	@RequiresPermissions("ty:projectApplication:view")
	@RequestMapping(value = "chuShenDetails")
	public String chuShenDetails(ProjectApplication projectApplication, Model model) {
		
		model.addAttribute("projectApplication", projectApplication);
		return "modules/ty/oaProjectInspectionForm";
	}
		
		

	@RequiresPermissions("ty:projectApplication:view")
	@RequestMapping(value = "form")
	public String form(ProjectApplication projectApplication, Model model) {
		model.addAttribute("projectApplication", projectApplication);
		return "modules/ty/projectApplicationForm";
	}

	@RequiresPermissions("ty:projectApplication:edit")
	@RequestMapping(value = "save")
	public String save(ProjectApplication projectApplication, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, projectApplication)){
			return form(projectApplication, model);
		}
		projectApplication.setStatusDd("0");//设置添加以后的项目状态
		projectApplicationService.save(projectApplication);
 		
		String filepath = projectApplication.getFiles();// 申请表文件路径	
		if(filepath!=null && !filepath.equals("")){//判断文件名是否为空
			Accessory accessory = new   Accessory();
			String declareId = projectApplication.getId();		// 项目id
		
			accessory.setId(projectApplication.getFjId());
			accessory.setDeclareId(declareId);
			accessory.setFilepath(filepath);
	 		accessoryService.save(accessory);
		}
		
		addMessage(redirectAttributes, "保存项目申报成功");
		return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/?repage";
	}
	
	@RequiresPermissions("ty:projectApplication:edit")
	@RequestMapping(value = "delete")
	public String delete(ProjectApplication projectApplication, RedirectAttributes redirectAttributes) {
		projectApplicationService.delete(projectApplication);
		addMessage(redirectAttributes, "删除项目申报成功");
		return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/?repage";
	}
	/**
	 * 项目验收
	 * @param projectApplication
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("ty:projectApplication:edit")
	@RequestMapping(value = "checkAccepts")
	public String checkAccept(ProjectApplication projectApplication,RedirectAttributes redirectAttributes) {
		projectApplication.setStatusDd("6");//设置状态为绩效中
		projectApplicationService.checkAccept(projectApplication);
		addMessage(redirectAttributes, "验收成功");
		return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/checkAccept";
	}
	/**
	 * 进入验收页面
	 * @param projectApplication
	 * @param model
	 * @param request
	 * @return
	 */
			@RequiresPermissions("ty:projectApplication:view")
			@RequestMapping(value = "toCheckAccept")
			public String toCheckAccept(ProjectApplication projectApplication, Model model,HttpServletRequest request) {
				model.addAttribute("projectApplication", projectApplication);
				
				return "modules/ty/checkVerify";
			}
			/**
			 * 导出数据
			 * @param projectApplication
			 * @param request
			 * @param response
			 * @param redirectAttributes
			 * @return
			 */
			@RequiresPermissions("ty:projectApplication:view")
			@RequestMapping(value = "export", method=RequestMethod.POST)
				  public String exportFile(ProjectApplication projectApplication, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
					try {
				            String fileName = "项目数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
				            Page<ProjectApplication> page = projectApplicationService.findPages(new Page<ProjectApplication>(request, response), projectApplication); 
				    		new ExportExcel("项目数据", ProjectApplication.class).setDataList(page.getList()).write(response, fileName).dispose();
				    		return null;
						} catch (Exception e) {
							addMessage(redirectAttributes, "导出项目失败！失败信息："+e.getMessage());
						}
						return "redirect:" + adminPath + "/ty/projectApplication/list?repage";
				    }
			/**
			 * 导出数据
			 * @param projectApplication
			 * @param request
			 * @param response
			 * @param redirectAttributes
			 * @return
			 */
			@RequiresPermissions("ty:projectApplication:view")
			@RequestMapping(value = "export1", method=RequestMethod.POST)
			public String exportFile1(Chart chart, HttpServletRequest request,
					HttpServletResponse response, RedirectAttributes redirectAttributes) {
				try {
					String fileName = "项目数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
					List<Chart> list = projectApplicationService.expense(chart);
					new ExportExcel("项目数据", Chart.class).setDataList(list).write(response, fileName).dispose();
					return null;
				} catch (Exception e) {
					addMessage(redirectAttributes, "导出项目失败！失败信息：" + e.getMessage());
				}
				return "redirect:" + adminPath + "/ty/projectApplication/list?repage";
			}

}
