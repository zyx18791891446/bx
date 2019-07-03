/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.web;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.entity.OaAchievements;
import com.thinkgem.jeesite.modules.ty.entity.ProjectApplication;
import com.thinkgem.jeesite.modules.ty.service.OaAchievementsService;
import com.thinkgem.jeesite.modules.ty.service.ProjectApplicationService;

/**
 * 绩效Controller
 * @author zyx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/oaAchievements")
public class OaAchievementsController extends BaseController {

	@Autowired
	private OaAchievementsService oaAchievementsService;
	@Autowired
	private ProjectApplicationService projectApplicationService;
	
	@ModelAttribute
	public OaAchievements get(@RequestParam(required=false) String id) {
		OaAchievements entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaAchievementsService.get(id);
		}
		if (entity == null){
			entity = new OaAchievements();
		}
		return entity;
	}
	/**
	 * 查询项目基本信息和绩效信息
	 * @param oaAchievements
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	/*@RequiresPermissions("ty:oaAchievements:view")*/
	@RequestMapping(value = {"list", ""})
	public String list(OaAchievements oaAchievements, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaAchievements> page = oaAchievementsService.findPage(new Page<OaAchievements>(request, response), oaAchievements);
		String projectId = oaAchievements.getProjectId();
		
		ProjectApplication projectApplication = new ProjectApplication();
		projectApplication.setId(projectId);
		ProjectApplication project = projectApplicationService.get(projectApplication);
		
		request.setAttribute("project", project);
		model.addAttribute("page", page);
		return "modules/ty/oaAchievementsList";
	}

	@RequiresPermissions("ty:nihao:view")
	@RequestMapping(value = "form")
	public String form(OaAchievements oaAchievements,HttpServletRequest request , Model model) {
		String projectId =  oaAchievements.getProjectId();
		ProjectApplication project = projectApplicationService.get(projectId);
		request.setAttribute("project", project);
		model.addAttribute("oaAchievements", oaAchievements);
		return "modules/ty/oaAchievementsForm";
	}

	@RequiresPermissions("ty:nihao:view")
	@RequestMapping(value = "save")
	public String save(OaAchievements oaAchievements,Model model,HttpServletRequest request, RedirectAttributes redirectAttributes) {
		oaAchievements.setAchievementsDate(new Date());
		if (!beanValidator(model, oaAchievements)){
			return form(oaAchievements,request, model);
		}
	
		oaAchievementsService.save(oaAchievements);
		addMessage(redirectAttributes, "保存绩效成功");
		return "redirect:"+Global.getAdminPath()+"/ty/projectApplication/achievementList?statusDd=6";
	}
	
	@RequiresPermissions("ty:oaAchievements:edit")
	@RequestMapping(value = "delete")
	public String delete(OaAchievements oaAchievements, RedirectAttributes redirectAttributes) {
		oaAchievementsService.delete(oaAchievements);
		addMessage(redirectAttributes, "删除绩效成功");
		return "redirect:"+Global.getAdminPath()+"/ty/oaAchievements/?repage";
	}

}