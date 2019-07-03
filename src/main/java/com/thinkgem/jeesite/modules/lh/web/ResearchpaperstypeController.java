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
import com.thinkgem.jeesite.modules.lh.entity.Researchpaperstype;
import com.thinkgem.jeesite.modules.lh.service.ResearchpaperstypeService;

/**
 * 科研论文类型Controller
 * @author zyx
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/researchpaperstype")
public class ResearchpaperstypeController extends BaseController {

	@Autowired
	private ResearchpaperstypeService researchpaperstypeService;
	
	@ModelAttribute
	public Researchpaperstype get(@RequestParam(required=false) String id) {
		Researchpaperstype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = researchpaperstypeService.get(id);
		}
		if (entity == null){
			entity = new Researchpaperstype();
		}
		return entity;
	}
	
	@RequiresPermissions("lh:researchpaperstype:view")
	@RequestMapping(value = {"list", ""})
	public String list(Researchpaperstype researchpaperstype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Researchpaperstype> page = researchpaperstypeService.findPage(new Page<Researchpaperstype>(request, response), researchpaperstype); 
		model.addAttribute("page", page);
		return "modules/lh/researchpaperstypeList";
	}

	@RequiresPermissions("lh:researchpaperstype:view")
	@RequestMapping(value = "form")
	public String form(Researchpaperstype researchpaperstype, Model model) {
		model.addAttribute("researchpaperstype", researchpaperstype);
		return "modules/lh/researchpaperstypeForm";
	}

	@RequiresPermissions("lh:researchpaperstype:edit")
	@RequestMapping(value = "save")
	public String save(Researchpaperstype researchpaperstype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, researchpaperstype)){
			return form(researchpaperstype, model);
		}
		researchpaperstypeService.save(researchpaperstype);
		addMessage(redirectAttributes, "保存科研论文类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpaperstype/?repage";
	}
	
	@RequiresPermissions("lh:researchpaperstype:edit")
	@RequestMapping(value = "delete")
	public String delete(Researchpaperstype researchpaperstype, RedirectAttributes redirectAttributes) {
		researchpaperstypeService.delete(researchpaperstype);
		addMessage(redirectAttributes, "删除科研论文类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpaperstype/?repage";
	}

}