/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.service;

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
import com.thinkgem.jeesite.modules.lh.entity.Patent;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.bx.dao.OrderStateDao;
import com.thinkgem.jeesite.modules.bx.dao.OrdersDao;

/**
 * 维修订单Service
 * @author zyx
 * @version 2019-05-12
 */
@Service
@Transactional(readOnly = true)
public class OrdersService extends CrudService<OrdersDao, Orders> {

	@Autowired
	private OrderStateDao oderStateDao;
	public Orders get(String id) {
		return super.get(id);
	}
	
	public List<Orders> findList(Orders orders) {
		return super.findList(orders);
	}
	@Transactional(readOnly = false)
	public void updateStatus(Orders orders){
		dao.updateStatus(orders);
	}
	
	public Page<Orders> findPage(Page<Orders> page, Orders orders) {
		String officeId = UserUtils.getUser().getOffice().getId();
		User user = null;
		if(!"1".equals(officeId)){
			 user = UserUtils.getUser();
			orders.setUser(user);
		}
		if(user!=null){
			String workerType = user.getWorkerType();
			if(workerType != null && !workerType.equals("")){//维修人员的查询
				return getRepairOrders(page, orders);
			}	
		}
		return super.findPage(page, orders);
	}
	public Page<Orders> getRepairOrders(Page<Orders> page, Orders orders) {
		orders.setPage(page);
		page.setList(dao.getRepairOrders(orders));
		return page;
	}
	
	//查询维修项目的维修人
	public List<Orders> getRepairMan(Orders orders){
		return dao.getRepairMan(orders);
	}
	
	@Transactional(readOnly = false)
	public void save(Orders orders) {
		
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
		 
		if (orders.getIsNewRecord()){
			orders.preInsert();
			User user = UserUtils.getUser();
			orders.setUser(user);//设置用ID
			orders.setStatus("0");//未上传状态
			orders.setDatetimes(new Date());
			dao.insert(orders);
			
			orderState.setStatus("0");
			
		}else{
			orderState.setStatus(status);
			orders.preUpdate();
			dao.update(orders);
			oderStateDao.insert(orderState);
		}
	 
	}
	
	@Transactional(readOnly = false)
	public void delete(Orders orders) {
		super.delete(orders);
	}
	
}