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
 * 专利Entity
 * @author 刘航
 * @version 2019-04-30
 */
public class Patent extends DataEntity<Patent> {
	
	private static final long serialVersionUID = 1L;
	private String uerid;		// uerid
	private String name;		// 发明名称
	private String patentnum;		// 专利号
	private String inventor;		// 发明人
	private String publishunit;		// 完成单位
	private Date publishdate;		// 统计年度
	private String status;		// 状态
	private String type;		// 授权类型
	private String files;
	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}
	
	public Patent() {
		super();
	}

	public Patent(String id){
		super(id);
	}

	
	
	
	@Length(min=1, max=20, message="发明名称长度必须介于 1 和 20 之间")
	@ExcelField(title="发明名称", align=1, sort=1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=20, message="专利号长度必须介于 1 和 20 之间")
//	@ExcelField(title="专利号", align=1, sort=2)
	public String getPatentnum() {
		return patentnum;
	}

	public void setPatentnum(String patentnum) {
		this.patentnum = patentnum;
	}
	
	@Length(min=1, max=20, message="发明人长度必须介于 1 和 20 之间")
	//	@ExcelField(title="发明人", align=1, sort=3)
	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor;
	}
	
	@Length(min=1, max=20, message="完成单位长度必须介于 1 和 20 之间")
	//	@ExcelField(title="完成单位", align=1, sort=4)
	public String getPublishunit() {
		return publishunit;
	}

	public void setPublishunit(String publishunit) {
		this.publishunit = publishunit;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="统计年度不能为空")
	@ExcelField(title="统计年度", align=1, sort=5)
	public Date getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(Date publishdate) {
		this.publishdate = publishdate;
	}
	
	@Length(min=1, max=20, message="状态长度必须介于 1 和 20 之间")
	@ExcelField(title="状态", align=1, sort=30, dictType="researchStatus")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=20, message="授权类型长度必须介于 1 和 20 之间")
	@ExcelField(title="授权类型", align=1, sort=7)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUerid() {
		return uerid;
	}

	public void setUerid(String uerid) {
		this.uerid = uerid;
	}

	


	
}