/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 选择工人Entity
 * @author zyx
 * @version 2019-05-12
 */
public class Selectworker extends DataEntity<Selectworker> {
	
	private static final long serialVersionUID = 1L;
	private String repairId;		// repair_id
	private User user;		// user_id
	
	public Selectworker() {
		super();
	}

	public Selectworker(String id){
		super(id);
	}

	@Length(min=0, max=32, message="repair_id长度必须介于 0 和 32 之间")
	public String getRepairId() {
		return repairId;
	}
	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}