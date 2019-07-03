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
 * 专业人员库Entity
 * @author zdd
 * @version 2019-03-09
 */
public class Expert extends DataEntity<Expert> {
	
	private static final long serialVersionUID = 1L;
	private String expertname;		// 专家姓名
	private String specialty;		// 专业
	private String sex;		// 性别
	private String age;		// 年龄
	private String photo;		// 用户头像
	private String content;		// 个人介绍
	private String researchresult;		// 研究成果
	private ProjectApplication projectApplication;
	
	
	public ProjectApplication getProjectApplication() {
		return projectApplication;
	}

	public void setProjectApplication(ProjectApplication projectApplication) {
		this.projectApplication = projectApplication;
	}

	public Expert() {
		super();
	}

	public Expert(String id){
		super(id);
	}
	
	
	@Length(min=2, max=20, message="专家姓名必须介于2 和 20 之间")
	public String getExpertname() {
		return expertname;
	}

	public void setExpertname(String expertname) {
		this.expertname = expertname;
	}

	@Length(min=2, max=20, message="专业必须介于2 和 20 之间")
	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	@NotNull(message="性别必须为男或者女")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@NotNull(message="年龄必须介于 20 和 100 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=1000, message="用户头像长度必须介于 0 和 1000 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=2000, message="个人介绍长度必须介于 0 和 2000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=2000, message="研究成果长度必须介于 0 和 2000 之间")
	public String getResearchresult() {
		return researchresult;
	}

	public void setResearchresult(String researchresult) {
		this.researchresult = researchresult;
	}	
	
}