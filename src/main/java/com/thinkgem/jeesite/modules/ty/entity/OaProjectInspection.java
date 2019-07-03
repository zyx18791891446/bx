/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目申请（初审）Entity
 * @author zyx
 * @version 2019-03-13
 */
public class OaProjectInspection extends DataEntity<OaProjectInspection> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// 项目ID
	private String statusDd;		// 状态（数据字典）
	private Date inspectionDate;		// 初审时间
	private String reason;		// 通过或不通过原因
	private String inspectionFiles;//市级初审反馈文件
	private String projectFiles;//项目文件
	
	public OaProjectInspection() {
		super();
	}

	public OaProjectInspection(String id){
		super(id);
	}

	@Length(min=1, max=32, message="项目ID长度必须介于 1 和 32 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=1, message="状态（数据字典）长度必须介于 0 和 1 之间")
	public String getStatusDd() {
		return statusDd;
	}

	public void setStatusDd(String statusDd) {
		this.statusDd = statusDd;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="初审时间不能为空")
	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	@Length(min=0, max=2000, message="通过或不通过原因长度必须介于 0 和 2000 之间")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInspectionFiles() {
		return inspectionFiles;
	}

	public void setInspectionFiles(String inspectionFiles) {
		this.inspectionFiles = inspectionFiles;
	}

	public String getProjectFiles() {
		return projectFiles;
	}

	public void setProjectFiles(String projectFiles) {
		this.projectFiles = projectFiles;
	}

	
	
}