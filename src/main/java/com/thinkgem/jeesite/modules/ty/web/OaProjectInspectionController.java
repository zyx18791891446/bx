/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.web;

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
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;

/**
 * 初审项目Controller
 * @author zyx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/oaProjectInspection")
public class OaProjectInspectionController extends BaseController {

	@Autowired
	private OaProjectInspectionService oaProjectInspectionService;
	
	@ModelAttribute
	public OaProjectInspection get(@RequestParam(required=false) String id) {
		OaProjectInspection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProjectInspectionService.get(id);
		}
		if (entity == null){
			entity = new OaProjectInspection();
		}
		return entity;
	}
	
	@RequiresPermissions("ty:oaProjectInspection:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProjectInspection oaProjectInspection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProjectInspection> page = oaProjectInspectionService.findPage(new Page<OaProjectInspection>(request, response), oaProjectInspection); 
		model.addAttribute("page", page);
		return "modules/ty/oaProjectInspectionList";
	}

	@RequiresPermissions("ty:oaProjectInspection:view")
	@RequestMapping(value = "form")
	public String form(OaProjectInspection oaProjectInspection, Model model) {
		model.addAttribute("oaProjectInspection", oaProjectInspection);
		return "modules/ty/oaProjectInspectionForm";
	}

	@RequiresPermissions("ty:oaProjectInspection:edit")
	@RequestMapping(value = "save")
	public String save(OaProjectInspection oaProjectInspection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProjectInspection)){
			return form(oaProjectInspection, model);
		}
		oaProjectInspectionService.save(oaProjectInspection);
		addMessage(redirectAttributes, "保存初审项目成功");
		return "redirect:"+Global.getAdminPath()+"/ty/oaProjectInspection/?repage";
	}
	
	@RequiresPermissions("ty:oaProjectInspection:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProjectInspection oaProjectInspection, RedirectAttributes redirectAttributes) {
		oaProjectInspectionService.delete(oaProjectInspection);
		addMessage(redirectAttributes, "删除初审项目成功");
		return "redirect:"+Global.getAdminPath()+"/ty/oaProjectInspection/?repage";
	}

}