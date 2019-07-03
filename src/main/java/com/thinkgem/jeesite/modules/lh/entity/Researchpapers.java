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
 * 科研论文Entity
 * @author zyx
 * @version 2019-04-30
 */
public class Researchpapers extends DataEntity<Researchpapers> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String author;		// 作者
	private String finishunit;		// 完成单位
	private Date finishdate;		// 完成日期
	private String type;		// 类型
	private String status;		// 状态
	private String userid;		// 用户id
	private String files;
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	public Researchpapers() {
		super();
	}

	public Researchpapers(String id){
		super(id);
	}

	@Length(min=1, max=20, message="名称长度必须介于 1 和 20 之间")
	@ExcelField(title="科研名称", align=1, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="作者长度必须介于 1 和 20 之间")
	@ExcelField(title="作者", align=1, sort=2)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=1, max=20, message="完成单位长度必须介于 1 和 20 之间")
	@ExcelField(title="完成单位", align=1, sort=3)
	public String getFinishunit() {
		return finishunit;
	}

	public void setFinishunit(String finishunit) {
		this.finishunit = finishunit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="完成日期不能为空")
	@ExcelField(title="完成日期", align=1, sort=4)
	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}
	
	@Length(min=1, max=20, message="类型长度必须介于 1 和 20 之间")
	@ExcelField(title="类型", align=1, sort=5)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	@ExcelField(title="状态", align=1, sort=6, dictType="researchStatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=32, message="用户id长度必须介于 1 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}