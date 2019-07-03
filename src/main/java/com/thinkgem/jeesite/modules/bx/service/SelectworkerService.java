/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.bx.entity.OrderState;
import com.thinkgem.jeesite.modules.bx.entity.Orders;
import com.thinkgem.jeesite.modules.bx.entity.Selectworker;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.bx.dao.OrderStateDao;
import com.thinkgem.jeesite.modules.bx.dao.OrdersDao;
import com.thinkgem.jeesite.modules.bx.dao.SelectworkerDao;

/**
 * 选择工人Service
 * @author zyx
 * @version 2019-05-12
 */
@Service
@Transactional(readOnly = true)
public class SelectworkerService extends CrudService<SelectworkerDao, Selectworker> {
	
	@Autowired
	private OrdersDao ordersDao;
	@Autowired
	private OrderStateDao orderStateDao;
	
	public Selectworker get(String id) {
		return super.get(id);
	}
	
	public List<Selectworker> findList(Selectworker selectworker) {
		return super.findList(selectworker);
	}
	
	public Page<Selectworker> findPage(Page<Selectworker> page, Selectworker selectworker) {
		return super.findPage(page, selectworker);
	}
	/**
	 * 选择维修工人 可能选择多个工人 
	 * 1.选择工人表加一条记录 2.修改订单表 3.订单状态加一条记录
	 * @param selectworker
	 * @param selectworkers
	 */
	@Transactional(readOnly = false)
	public void save(Selectworker selectworker,String[] selectworkers) {
		String repairId =  selectworker.getRepairId();
		List<Selectworker> list = new ArrayList<>();
		
		for (int i = 0; i < selectworkers.length; i++) {
			Selectworker sw = new Selectworker();
			sw.setId(IdGen.uuid());
			sw.setRepairId(repairId);
			User u = new User();
			u.setId(selectworkers[i]);
			sw.setUser(u);
			list.add(sw);
		}
		
		Orders order = new Orders();
		order.setId(repairId);
		
		Orders orders =  ordersDao.get(order);
		String username = orders.getUsername();		 
		 String content = orders.getContent();		 
		 Date datetimes = new Date();		 
		 String addres = orders.getAddres();		 
		 String status = orders.getStatus();	
		 String orderId = orders.getId();
		 
		 OrderState orderState = new OrderState();
		 orderState.setId(IdGen.uuid());
		 orderState.setOrderId(orderId);
		 orderState.setUsername(username);
		 orderState.setContent(content);
		 orderState.setAddres(addres);
		 orderState.setDatetimes(datetimes);
		 orderState.setStatus("4");
		orders.setStatus("4");
		ordersDao.updateStatus(orders);
		orderStateDao.insert(orderState);
		dao.insertByBatch(list); 
	}
	
	@Transactional(readOnly = false)
	public void delete(Selectworker selectworker) {
		super.delete(selectworker);
	}
	
}