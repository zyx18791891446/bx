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
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;
import com.thinkgem.jeesite.modules.ty.service.OaProjectReviewService;

/**
 * 项目评审Controller
 * @author zyx
 * @version 2019-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/ty/oaProjectReview")
public class OaProjectReviewController extends BaseController {

	@Autowired
	private OaProjectReviewService oaProjectReviewService;
	
	@ModelAttribute
	public OaProjectReview get(@RequestParam(required=false) String id) {
		OaProjectReview entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = oaProjectReviewService.get(id);
		}
		if (entity == null){
			entity = new OaProjectReview();
		}
		return entity;
	}
	
	@RequiresPermissions("ty:oaProjectReview:view")
	@RequestMapping(value = {"list", ""})
	public String list(OaProjectReview oaProjectReview, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OaProjectReview> page = oaProjectReviewService.findPage(new Page<OaProjectReview>(request, response), oaProjectReview); 
		model.addAttribute("page", page);
		return "modules/ty/oaProjectReviewList";
	}

	@RequiresPermissions("ty:oaProjectReview:view")
	@RequestMapping(value = "form")
	public String form(OaProjectReview oaProjectReview, Model model) {
		model.addAttribute("oaProjectReview", oaProjectReview);
		return "modules/ty/oaProjectReviewForm";
	}

	@RequiresPermissions("ty:oaProjectReview:edit")
	@RequestMapping(value = "save")
	public String save(OaProjectReview oaProjectReview, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, oaProjectReview)){
			return form(oaProjectReview, model);
		}
		oaProjectReviewService.save(oaProjectReview);
		addMessage(redirectAttributes, "保存项目评审成功");
		return "redirect:"+Global.getAdminPath()+"/ty/oaProjectReview/?repage";
	}
	
	@RequiresPermissions("ty:oaProjectReview:edit")
	@RequestMapping(value = "delete")
	public String delete(OaProjectReview oaProjectReview, RedirectAttributes redirectAttributes) {
		oaProjectReviewService.delete(oaProjectReview);
		addMessage(redirectAttributes, "删除项目评审成功");
		return "redirect:"+Global.getAdminPath()+"/ty/oaProjectReview/?repage";
	}

}