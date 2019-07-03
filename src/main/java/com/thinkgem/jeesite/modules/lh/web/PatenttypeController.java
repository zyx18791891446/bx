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
import com.thinkgem.jeesite.modules.lh.entity.Patenttype;
import com.thinkgem.jeesite.modules.lh.service.PatenttypeService;

/**
 * 专利类型Controller
 * @author 刘航
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/patenttype")
public class PatenttypeController extends BaseController {

	@Autowired
	private PatenttypeService patenttypeService;
	
	@ModelAttribute
	public Patenttype get(@RequestParam(required=false) String id) {
		Patenttype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = patenttypeService.get(id);
		}
		if (entity == null){
			entity = new Patenttype();
		}
		return entity;
	}
	
	@RequiresPermissions("lh:patenttype:view")
	@RequestMapping(value = {"list", ""})
	public String list(Patenttype patenttype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Patenttype> page = patenttypeService.findPage(new Page<Patenttype>(request, response), patenttype); 
		model.addAttribute("page", page);
		return "modules/lh/patenttypeList";
	}

	@RequiresPermissions("lh:patenttype:view")
	@RequestMapping(value = "form")
	public String form(Patenttype patenttype, Model model) {
		model.addAttribute("patenttype", patenttype);
		return "modules/lh/patenttypeForm";
	}

	@RequiresPermissions("lh:patenttype:edit")
	@RequestMapping(value = "save")
	public String save(Patenttype patenttype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, patenttype)){
			return form(patenttype, model);
		}
		patenttypeService.save(patenttype);
		addMessage(redirectAttributes, "保存专利类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patenttype/?repage";
	}
	
	@RequiresPermissions("lh:patenttype:edit")
	@RequestMapping(value = "delete")
	public String delete(Patenttype patenttype, RedirectAttributes redirectAttributes) {
		patenttypeService.delete(patenttype);
		addMessage(redirectAttributes, "删除专利类型成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patenttype/?repage";
	}

}