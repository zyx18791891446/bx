/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.web;

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
import com.thinkgem.jeesite.modules.lh.entity.Researchprojecttype;
import com.thinkgem.jeesite.modules.lh.service.ResearchprojecttypeService;

/**
 * 科研项目类型Controller
 * @author 刘航
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/researchprojecttype")
public class ResearchprojecttypeController extends BaseController {

	@Autowired
	private ResearchprojecttypeService researchprojecttypeService;
	
	@ModelAttribute
	public Researchprojecttype get(@RequestParam(required=false) String id) {
		Researchprojecttype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = researchprojecttypeService.get(id);
		}
		if (entity == null){
			entity = new Researchprojecttype();
		}
		return entity;
	}
	
	@RequiresPermissions("lh:researchprojecttype:view")
	@RequestMapping(value = {"list", ""})
	public String list(Researchprojecttype researchprojecttype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Researchprojecttype> page = researchprojecttypeService.findPage(new Page<Researchprojecttype>(request, response), researchprojecttype); 
		model.addAttribute("page", page);
		return "modules/lh/researchprojecttypeList";
	}

	@RequiresPermissions("lh:researchprojecttype:view")
	@RequestMapping(value = "form")
	public String form(Researchprojecttype researchprojecttype, Model model) {
		model.addAttribute("researchprojecttype", researchprojecttype);
		return "modules/lh/researchprojecttypeForm";
	}

	@RequiresPermissions("lh:researchprojecttype:edit")
	@RequestMapping(value = "save")
	public String save(Researchprojecttype researchprojecttype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, researchprojecttype)){
			return form(researchprojecttype, model);
		}
		researchprojecttypeService.save(researchprojecttype);
		addMessage(redirectAttributes, "保存科研项目类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchprojecttype/?repage";
	}
	
	@RequiresPermissions("lh:researchprojecttype:edit")
	@RequestMapping(value = "delete")
	public String delete(Researchprojecttype researchprojecttype, RedirectAttributes redirectAttributes) {
		researchprojecttypeService.delete(researchprojecttype);
		addMessage(redirectAttributes, "删除科研项目类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchprojecttype/?repage";
	}

}