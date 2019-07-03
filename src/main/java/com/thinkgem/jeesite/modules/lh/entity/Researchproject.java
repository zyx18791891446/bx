/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 科研项目Entity
 * @author 刘航
 * @version 2019-04-30
 */
public class Researchproject extends DataEntity<Researchproject> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户ID
	private String name;		// 姓名
	private String host;		// 主持人
	private String type;		// 类型
	private Date finishdate;		// 统计年度
	private String status;		// 状态
	private String finishunit;		// 完成单位
	private String numbering;		// 编号
	private String files;
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	public Researchproject() {
		super();
	}

	public Researchproject(String id){
		super(id);
	}

	@Length(min=1, max=32, message="用户ID长度必须介于 1 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=1, max=20, message="姓名长度必须介于 1 和 20 之间")
	@ExcelField(title="项目名称", align=1, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="主持人长度必须介于 1 和 20 之间")
	@ExcelField(title="主持人", align=1, sort=2)
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	@Length(min=1, max=20, message="类型长度必须介于 1 和 20 之间")
	@ExcelField(title="项目类型", align=1, sort=3)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="统计年度不能为空")
	@ExcelField(title="统计年度", align=1, sort=4)
	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	@ExcelField(title="状态", align=1, sort=5, dictType="researchStatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=30, message="完成单位长度必须介于 0 和 30 之间")
	@ExcelField(title="完成单位", align=1, sort=6)
	public String getFinishunit() {
		return finishunit;
	}

	public void setFinishunit(String finishunit) {
		this.finishunit = finishunit;
	}
	
	@Length(min=1, max=11, message="编号长度必须介于 1 和 11 之间")
	public String getNumbering() {
		return numbering;
	}

	public void setNumbering(String numbering) {
		this.numbering = numbering;
	}
	
}