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
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;
import com.thinkgem.jeesite.modules.ty.service.ExpertsChoiceService;

/**
 * 专家选择Controller
 * @author zdd
 * @version 2019-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/expertsChoice")
public class ExpertsChoiceController extends BaseController {

	@Autowired
	private ExpertsChoiceService expertsChoiceService;
	
	@ModelAttribute
	public ExpertsChoice get(@RequestParam(required=false) String id) {
		ExpertsChoice entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expertsChoiceService.get(id);
		}
		if (entity == null){
			entity = new ExpertsChoice();
		}
		return entity;
	}
	
	@RequiresPermissions("ty:expertsChoice:view")
	@RequestMapping(value = {"list", ""})
	public String list(ExpertsChoice expertsChoice, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ExpertsChoice> page = expertsChoiceService.findPage(new Page<ExpertsChoice>(request, response), expertsChoice); 
		model.addAttribute("page", page);
		return "modules/ty/expertsChoiceList";
	}

	@RequiresPermissions("ty:expertsChoice:view")
	@RequestMapping(value = "form")
	public String form(ExpertsChoice expertsChoice, Model model) {
		model.addAttribute("expertsChoice", expertsChoice);
		return "modules/ty/expertsChoiceForm";
	}

	@RequiresPermissions("ty:expertsChoice:edit")
	@RequestMapping(value = "save")
	public String save(ExpertsChoice expertsChoice, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expertsChoice)){
			return form(expertsChoice, model);
		}
		expertsChoiceService.save(expertsChoice);
		addMessage(redirectAttributes, "保存专家选择成功");
		return "redirect:"+Global.getAdminPath()+"/ty/expertsChoice/?repage";
	}
	
	@RequiresPermissions("ty:expertsChoice:edit")
	@RequestMapping(value = "delete")
	public String delete(ExpertsChoice expertsChoice, RedirectAttributes redirectAttributes) {
		expertsChoiceService.delete(expertsChoice);
		addMessage(redirectAttributes, "删除专家选择成功");
		return "redirect:"+Global.getAdminPath()+"/ty/expertsChoice/?repage";
	}

}