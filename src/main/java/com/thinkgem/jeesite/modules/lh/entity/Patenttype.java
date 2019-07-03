/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专利类型Entity
 * @author 刘航
 * @version 2019-04-30
 */
public class Patenttype extends DataEntity<Patenttype> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	
	public Patenttype() {
		super();
	}

	public Patenttype(String id){
		super(id);
	}

	@Length(min=1, max=20, message="name长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}