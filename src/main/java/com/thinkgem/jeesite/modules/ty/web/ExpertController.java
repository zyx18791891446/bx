/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.web;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ty.entity.Expert;
import com.thinkgem.jeesite.modules.ty.service.ExpertService;

/**
 * 专业人员库Controller
 * @author zdd
 * @version 2019-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/expert")
public class ExpertController extends BaseController {

	@Autowired
	private ExpertService expertService;
	
	@ModelAttribute
	public Expert get(@RequestParam(required=false) String id) {
		Expert entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = expertService.get(id);
		}
		if (entity == null){
			entity = new Expert();
		}
		return entity;
	}
	
	@RequiresPermissions("ty:expert:view")
	@RequestMapping(value = {"list", ""})
	public String list(Expert expert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Expert> page = expertService.findPage(new Page<Expert>(request, response), expert); 
		model.addAttribute("page", page);
		return "modules/ty/expertList";
	}
	/** 
	 * 通过ajax查询所有的专家
	 * @param expert
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("ty:expert:view")
	@RequestMapping(value = "queryExpert")
	public List<Expert> queryExpert(Expert expert, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Expert> page = expertService.findPage(new Page<Expert>(request, response), expert); 
		return (List<Expert>) page.getList();
	}

	@RequiresPermissions("ty:expert:view")
	@RequestMapping(value = "form")
	public String form(Expert expert, Model model) {
		model.addAttribute("expert", expert);
		return "modules/ty/expertForm";
	}
	/**
	 * 通过专家详细信息（包括评审过的项目）
	 * @param expert
	 * @param model
	 * @return
	 */
	@RequiresPermissions("ty:expert:view")
	@RequestMapping(value = "get")
	public String getExpert(Expert expert, Model model,HttpServletRequest request) {
		Page<Expert> page = expertService.getExpert(new Page<>(), expert);
		model.addAttribute("expert", expert);
		request.setAttribute("page", page);
		return "modules/ty/expertDetail";
	}
	//只做修改操作，添加操作给了管理员
	@RequiresPermissions("ty:expert:edit")
	@RequestMapping(value = "save")
	public String save(Expert expert, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, expert)){
			return form(expert, model);
		}	
		
		String photo = expert.getPhoto();
		if(photo != null && !photo.equals("")){
			if( photo.charAt(0)=='|'){
				String photos = photo.substring(1);
				expert.setPhoto(photos);
				
			}
			
		}
		expertService.updateExpert(expert);
		addMessage(redirectAttributes, "修改教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/ty/expert/?repage";
	}
	
	@RequiresPermissions("ty:expert:edit")
	@RequestMapping(value = "delete")
	public String delete(Expert expert, RedirectAttributes redirectAttributes) {
		expertService.delete(expert);
		addMessage(redirectAttributes, "删除教师信息成功");
		return "redirect:"+Global.getAdminPath()+"/ty/expert/?repage";
	}

}