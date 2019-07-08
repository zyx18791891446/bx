/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工人类型Entity
 * @author zyx
 * @version 2019-05-12
 */
public class Worktype extends DataEntity<Worktype> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	public Worktype() {
		super();
	}

	public Worktype(String id){
		super(id);
	}

	@Length(min=0, max=32, message="name长度必须介于 0 和 32 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}