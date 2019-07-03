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
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;
import com.thinkgem.jeesite.modules.bx.service.EvaluateService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 评价维修质量Controller
 * @author 评价维修质量
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/evaluate")
public class EvaluateController extends BaseController {

	@Autowired
	private EvaluateService evaluateService;
	
	@ModelAttribute
	public Evaluate get(@RequestParam(required=false) String id) {
		Evaluate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = evaluateService.get(id);
		}
		if (entity == null){
			entity = new Evaluate();
		}
		return entity;
	}
	
	@RequiresPermissions("bx:evaluate:view")
	@RequestMapping(value = {"list", ""})
	public String list(Evaluate evaluate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Evaluate> page = evaluateService.findPage(new Page<Evaluate>(request, response), evaluate); 
		model.addAttribute("page", page);
		return "modules/bx/evaluateList";
	}

	@RequestMapping(value = "form")
	public String form(Evaluate evaluate, Model model) {
		model.addAttribute("evaluate", evaluate);
		return "modules/bx/evaluateForm";
	}

	@RequestMapping(value = "save")
	public String save(Evaluate evaluate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, evaluate)){
			return form(evaluate, model);
		}
		String evaluUserId = UserUtils.getUser().getId();
		evaluate.setEvaluUserId(evaluUserId);
		evaluateService.save(evaluate);
		addMessage(redirectAttributes, "保存评价维修质量成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/evaluation?id="+evaluate.getRepairId();
	} 
	
	@RequiresPermissions("bx:evaluate:edit")
	@RequestMapping(value = "delete")
	public String delete(Evaluate evaluate, RedirectAttributes redirectAttributes) {
		evaluateService.delete(evaluate);
		addMessage(redirectAttributes, "删除评价维修质量成功");
		return "redirect:"+Global.getAdminPath()+"/bx/evaluate/?repage";
	}

}