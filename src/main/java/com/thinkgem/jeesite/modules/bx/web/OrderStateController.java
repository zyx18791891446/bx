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
import com.thinkgem.jeesite.modules.bx.entity.OrderState;
import com.thinkgem.jeesite.modules.bx.service.OrderStateService;

/**
 * 维修订单状态Controller
 * @author zyx
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/orderState")
public class OrderStateController extends BaseController {

	@Autowired
	private OrderStateService orderStateService;
	
	@ModelAttribute
	public OrderState get(@RequestParam(required=false) String id) {
		OrderState entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = orderStateService.get(id);
		}
		if (entity == null){
			entity = new OrderState();
		}
		return entity;
	}
	
	@RequiresPermissions("bx:orderState:view")
	@RequestMapping(value = {"list", ""})
	public String list(OrderState orderState, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<OrderState> page = orderStateService.findPage(new Page<OrderState>(request, response), orderState); 
		model.addAttribute("page", page);
		return "modules/bx/orderStateList";
	}

	@RequiresPermissions("bx:orderState:view")
	@RequestMapping(value = "form")
	public String form(OrderState orderState, Model model) {
		model.addAttribute("orderState", orderState);
		return "modules/bx/orderStateForm";
	}

	@RequiresPermissions("bx:orderState:edit")
	@RequestMapping(value = "save")
	public String save(OrderState orderState, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orderState)){
			return form(orderState, model);
		}
		orderStateService.save(orderState);
		addMessage(redirectAttributes, "保存维修订单状态成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orderState/?repage";
	}
	
	@RequiresPermissions("bx:orderState:edit")
	@RequestMapping(value = "delete")
	public String delete(OrderState orderState, RedirectAttributes redirectAttributes) {
		orderStateService.delete(orderState);
		addMessage(redirectAttributes, "删除维修订单状态成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orderState/?repage";
	}

}