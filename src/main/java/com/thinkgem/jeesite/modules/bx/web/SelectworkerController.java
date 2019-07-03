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
import com.thinkgem.jeesite.modules.bx.entity.Selectworker;
import com.thinkgem.jeesite.modules.bx.entity.Worktype;
import com.thinkgem.jeesite.modules.bx.service.SelectworkerService;
import com.thinkgem.jeesite.modules.bx.service.WorktypeService;

/**
 * 选择工人Controller
 * @author zyx
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/selectworker")
public class SelectworkerController extends BaseController {

	@Autowired
	private SelectworkerService selectworkerService;
	@Autowired
	private WorktypeService worktypeService;
	
	@ModelAttribute
	public Selectworker get(@RequestParam(required=false) String id) {
		Selectworker entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = selectworkerService.get(id);
		}
		if (entity == null){
			entity = new Selectworker();
		}
		return entity;
	}
	
	//@RequiresPermissions("bx:selectworker:view")
	@RequestMapping(value = {"list", ""})
	public String list(Selectworker selectworker, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Selectworker> page = selectworkerService.findPage(new Page<Selectworker>(request, response), selectworker); 
		model.addAttribute("page", page);
		return "modules/bx/selectworkerList";
	}

	//@RequiresPermissions("bx:selectworker:view")
	@RequestMapping(value = "form")
	public String form(Selectworker selectworker, Model model) {
		model.addAttribute("allWorktype", worktypeService.findList(new Worktype()));
		model.addAttribute("selectworker", selectworker);
		return "modules/bx/selectworkerForm";
	}
	
	//@RequiresPermissions("bx:selectworker:edit")
	@RequestMapping(value = "save")
	public String save(Selectworker selectworker,String[] workerID, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, selectworker)){
			return form(selectworker, model);
		}
		selectworkerService.save(selectworker,workerID);
		addMessage(redirectAttributes, "选择工人成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	}
	
//	@RequiresPermissions("bx:selectworker:edit")
	@RequestMapping(value = "delete")
	public String delete(Selectworker selectworker, RedirectAttributes redirectAttributes) {
		selectworkerService.delete(selectworker);
		addMessage(redirectAttributes, "删除选择工人成功");
		return "redirect:"+Global.getAdminPath()+"/bx/selectworker/?repage";
	}

}