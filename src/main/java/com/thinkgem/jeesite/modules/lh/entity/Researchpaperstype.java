/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 科研论文类型Entity
 * @author zyx
 * @version 2019-04-30
 */
public class Researchpaperstype extends DataEntity<Researchpaperstype> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	
	public Researchpaperstype() {
		super();
	}

	public Researchpaperstype(String id){
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