/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.web;

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
import com.thinkgem.jeesite.modules.bx.entity.Worktype;
import com.thinkgem.jeesite.modules.bx.service.WorktypeService;

/**
 * 工人类型Controller
 * @author zyx
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/worktype")
public class WorktypeController extends BaseController {

	@Autowired
	private WorktypeService worktypeService;
	
	@ModelAttribute
	public Worktype get(@RequestParam(required=false) String id) {
		Worktype entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = worktypeService.get(id);
		}
		if (entity == null){
			entity = new Worktype();
		}
		return entity;
	}
	
	@RequiresPermissions("bx:worktype:view")
	@RequestMapping(value = {"list", ""})
	public String list(Worktype worktype, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Worktype> page = worktypeService.findPage(new Page<Worktype>(request, response), worktype); 
		model.addAttribute("page", page);
		return "modules/bx/worktypeList";
	}

	@RequiresPermissions("bx:worktype:view")
	@RequestMapping(value = "form")
	public String form(Worktype worktype, Model model) {
		model.addAttribute("worktype", worktype);
		return "modules/bx/worktypeForm";
	}

	@RequiresPermissions("bx:worktype:edit")
	@RequestMapping(value = "save")
	public String save(Worktype worktype, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, worktype)){
			return form(worktype, model);
		}
		worktypeService.save(worktype);
		addMessage(redirectAttributes, "保存工人类型成功");
		return "redirect:"+Global.getAdminPath()+"/bx/worktype/?repage";
	}
	
	@RequiresPermissions("bx:worktype:edit")
	@RequestMapping(value = "delete")
	public String delete(Worktype worktype, RedirectAttributes redirectAttributes) {
		worktypeService.delete(worktype);
		addMessage(redirectAttributes, "删除工人类型成功");
		return "redirect:"+Global.getAdminPath()+"/bx/worktype/?repage";
	}

}