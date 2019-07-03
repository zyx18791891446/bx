/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.bx.entity.Evaluate;
import com.thinkgem.jeesite.modules.bx.entity.OrderState;
import com.thinkgem.jeesite.modules.bx.entity.Orders;
import com.thinkgem.jeesite.modules.bx.service.EvaluateService;
import com.thinkgem.jeesite.modules.bx.service.OrderStateService;
import com.thinkgem.jeesite.modules.bx.service.OrdersService;


/**
 * 维修订单Controller
 * @author zyx
 * @version 2019-05-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bx/orders")
public class OrdersController extends BaseController {

	@Autowired
	private OrdersService ordersService;
	@Autowired
	private OrderStateService orderStateService;
	@Autowired
	private EvaluateService evluateService;
	@ModelAttribute
	public Orders get(@RequestParam(required=false) String id) {
		Orders entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = ordersService.get(id);
		}
		if (entity == null){
			entity = new Orders();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Orders orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orders> page = ordersService.findPage(new Page<Orders>(request, response), orders); 
		model.addAttribute("page", page);
		return "modules/bx/ordersList";
	}
	
/*	@RequestMapping(value = {"getRepairOrders", ""})
	public String getRepairOrders(Orders orders, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Orders> page = ordersService.findPage(new Page<Orders>(request, response), orders); 
		model.addAttribute("page", page);
		return "modules/bx/ordersList";
	}*/
	
	
	/**
	 * 去审核方法
	 * @param orders
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bx:orders:view")
	@RequestMapping(value = "toInspection")
	public String toInspection(Orders orders, Model model) {
		model.addAttribute("orders", orders);
		return "modules/bx/inspectionOrders";
	} 
	
	@RequestMapping(value = "detail")
	public String detail(Orders orders, Model model) {
		OrderState orderState = new OrderState();
		orderState.setOrderId(orders.getId());
		Evaluate evaluate = new Evaluate();
		evaluate.setRepairId(orders.getId());
		List<OrderState> list = orderStateService.findList(orderState);
		
		List<Evaluate> evaluateList = evluateService.evaluateDetails(evaluate);
		model.addAttribute("orders", orders);
		model.addAttribute("evaluateList",evaluateList);
		model.addAttribute("list",list);
		return "modules/bx/ordersDetails";
	}
	@RequestMapping(value = "export", method=RequestMethod.POST)
	  public String exportFile(Orders orders, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	            String fileName = "故障信息数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            Page<Orders> page = ordersService.findPage(new Page<Orders>(request, response), orders); 
	    		new ExportExcel("故障信息数据", Orders.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出故障信息数据失败！失败信息："+e.getMessage());
			}
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	    }
	/**
	 * 审核方法
	 * @param orders
	 * @param reason
	 * @param model
	 * @return
	 */
	@RequiresPermissions("bx:orders:view")
	@RequestMapping(value = "inspection")
	public String inspection(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		ordersService.save(orders);
		//System.out.println(orders.getStatus()+"===="+reason);
		addMessage(redirectAttributes, "保存故障信息成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	} 
	
	/**
	 * 上传的方法 教师的方法
	 * @param orders
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("bx:orders:edit")
	@RequestMapping(value = "upload")
	public String upload(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		orders.setStatus("1");
		ordersService.save(orders);
		addMessage(redirectAttributes, "上传故障信息成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	}
	
	/**
	 * 维修完成 修改状态
	 * @param orders
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "finishRepair")
	public String finishRepair(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		orders.setStatus("5");
		ordersService.save(orders);
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	}
	
	
	@RequestMapping(value = "evaluation")
	public String evaluation(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		List<Orders> list = ordersService.getRepairMan(orders);
		model.addAttribute("list",list);
		return "modules/bx/evaluateList";
	}
	
	
	
	@RequiresPermissions("bx:orders:edit")
	@RequestMapping(value = "form")
	public String form(Orders orders, Model model) {
		model.addAttribute("orders", orders);
		return "modules/bx/ordersForm";
	}

	@RequiresPermissions("bx:orders:edit")
	@RequestMapping(value = "save")
	public String save(Orders orders, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, orders)){
			return form(orders, model);
		}
		ordersService.save(orders);
		addMessage(redirectAttributes, "保存维修订单成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	}
	
	@RequiresPermissions("bx:orders:edit")
	@RequestMapping(value = "delete")
	public String delete(Orders orders, RedirectAttributes redirectAttributes) {
		ordersService.delete(orders);
		addMessage(redirectAttributes, "删除故障信息清单成功");
		return "redirect:"+Global.getAdminPath()+"/bx/orders/?repage";
	}

}