/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 专家选择Entity
 * @author zdd
 * @version 2019-03-09
 */
public class ExpertsChoice extends DataEntity<ExpertsChoice> {
	
	private static final long serialVersionUID = 1L;
	private String oaId;		// 主键
	private String oaExpertid;		// 专家ID
	private String oaProject;		// 项目ID
	
	public ExpertsChoice() {
		super();
	}

	public ExpertsChoice(String id){
		super(id);
	}

	@Length(min=1, max=32, message="主键长度必须介于 1 和 32 之间")
	public String getOaId() {
		return oaId;
	}

	public void setOaId(String oaId) {
		this.oaId = oaId;
	}
	
	@Length(min=0, max=32, message="专家ID长度必须介于 0 和 32 之间")
	public String getOaExpertid() {
		return oaExpertid;
	}

	public void setOaExpertid(String oaExpertid) {
		this.oaExpertid = oaExpertid;
	}
	
	@Length(min=0, max=32, message="项目ID长度必须介于 0 和 32 之间")
	public String getOaProject() {
		return oaProject;
	}

	public void setOaProject(String oaProject) {
		this.oaProject = oaProject;
	}

	@Override
	public String toString() {
		return "ExpertsChoice [oaId=" + oaId + ", oaExpertid=" + oaExpertid + ", oaProject=" + oaProject + "]";
	}
	
}