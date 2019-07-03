/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 绩效Entity
 * @author zyx
 * @version 2019-03-13
 */
public class OaAchievements extends DataEntity<OaAchievements> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// 项目ID
	private String content;		// 内容
	private Date achievementsDate;		// 时间
	private String achievementsFiles;
	private Office office;
	
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public OaAchievements() {
		super();
	}

	public OaAchievements(String id){
		super(id);
	}

	@Length(min=1, max=32, message="项目ID长度必须介于 1 和 32 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=1, max=2000, message="内容长度必须介于 1 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="时间不能为空")
	public Date getAchievementsDate() {
		return achievementsDate;
	}

	public void setAchievementsDate(Date achievementsDate) {
		this.achievementsDate = achievementsDate;
	}

	public String getAchievementsFiles() {
		return achievementsFiles;
	}

	public void setAchievementsFiles(String achievementsFiles) {
		this.achievementsFiles = achievementsFiles;
	}
	
}