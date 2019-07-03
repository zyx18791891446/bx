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
import com.thinkgem.jeesite.modules.ty.entity.Accessory;
import com.thinkgem.jeesite.modules.ty.service.AccessoryService;

/**
 * 附件Controller
 * @author zdd
 * @version 2019-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/accessory")
public class AccessoryController extends BaseController {

	@Autowired
	private AccessoryService accessoryService;
	
	@ModelAttribute
	public Accessory get(@RequestParam(required=false) String id) {
		Accessory entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = accessoryService.get(id);
		}
		if (entity == null){
			entity = new Accessory();
		}
		return entity;
	}
	
	@RequiresPermissions("ty:accessory:view")
	@RequestMapping(value = {"list", ""})
	public String list(Accessory accessory, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Accessory> page = accessoryService.findPage(new Page<Accessory>(request, response), accessory); 
		model.addAttribute("page", page);
		return "modules/ty/accessoryList";
	}

	@RequiresPermissions("ty:accessory:view")
	@RequestMapping(value = "form")
	public String form(Accessory accessory, Model model) {
		model.addAttribute("accessory", accessory);
		return "modules/ty/accessoryForm";
	}

	@RequiresPermissions("ty:accessory:edit")
	@RequestMapping(value = "save")
	public String save(Accessory accessory, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, accessory)){
			return form(accessory, model);
		}
		accessoryService.save(accessory);
		addMessage(redirectAttributes, "保存附件成功");
		return "redirect:"+Global.getAdminPath()+"/ty/accessory/?repage";
	}
	
	@RequiresPermissions("ty:accessory:edit")
	@RequestMapping(value = "delete")
	public String delete(Accessory accessory, RedirectAttributes redirectAttributes) {
		accessoryService.delete(accessory);
		addMessage(redirectAttributes, "删除附件成功");
		return "redirect:"+Global.getAdminPath()+"/ty/accessory/?repage";
	}

}