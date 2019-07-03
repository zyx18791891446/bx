/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目评审Entity
 * @author zyx
 * @version 2019-03-13
 */
public class OaProjectReview extends DataEntity<OaProjectReview> {
	
	private static final long serialVersionUID = 1L;
	private String projectId;		// 项目ID
	private String reviewAdvice;		// 评审意见
	private String status;		// 评审状态
	private Expert expert;//评审项目的ID
	private String reviewFiles;
	
	
	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public OaProjectReview() {
		super();
	}

	public OaProjectReview(String id){
		super(id);
	}

	@Length(min=1, max=32, message="项目ID长度必须介于 1 和 32 之间")
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
	@Length(min=0, max=2000, message="评审意见长度必须介于 0 和 2000 之间")
	public String getReviewAdvice() {
		return reviewAdvice;
	}

	public void setReviewAdvice(String reviewAdvice) {
		this.reviewAdvice = reviewAdvice;
	}
	
	@Length(min=1, max=1, message="评审状态长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OaProjectReview [projectId=" + projectId + ", reviewAdvice=" + reviewAdvice + ", status=" + status
				+ "]";
	}

	public String getReviewFiles() {
		return reviewFiles;
	}

	public void setReviewFiles(String reviewFiles) {
		this.reviewFiles = reviewFiles;
	}
	
	
}