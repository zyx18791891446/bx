/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 附件Entity
 * @author zdd
 * @version 2019-03-09
 */
public class Accessory extends DataEntity<Accessory> {
	
	private static final long serialVersionUID = 1L;
	private String declareId;		// 申请表id
	private String filepath;		// 申请表文件路径
	
	public Accessory() {
		super();
	}

	public Accessory(String id){
		super(id);
	}

	@Length(min=0, max=32, message="申请表id长度必须介于 0 和 32 之间")
	public String getDeclareId() {
		return declareId;
	}

	public void setDeclareId(String declareId) {
		this.declareId = declareId;
	}
	
	@Length(min=0, max=2000, message="申请表文件路径长度必须介于 0 和 2000 之间")
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
}