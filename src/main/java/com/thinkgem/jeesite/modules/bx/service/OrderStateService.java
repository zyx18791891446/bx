/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.bx.entity.OrderState;
import com.thinkgem.jeesite.modules.bx.dao.OrderStateDao;

/**
 * 维修订单状态Service
 * @author zyx
 * @version 2019-05-12
 */
@Service
@Transactional(readOnly = true)
public class OrderStateService extends CrudService<OrderStateDao, OrderState> {

	public OrderState get(String id) {
		return super.get(id);
	}
	
	public List<OrderState> findList(OrderState orderState) {
		return super.findList(orderState);
	}
	
	public Page<OrderState> findPage(Page<OrderState> page, OrderState orderState) {
		return super.findPage(page, orderState);
	}
	
	@Transactional(readOnly = false)
	public void save(OrderState orderState) {
		super.save(orderState);
	}
	
	@Transactional(readOnly = false)
	public void delete(OrderState orderState) {
		super.delete(orderState);
	}
	
}