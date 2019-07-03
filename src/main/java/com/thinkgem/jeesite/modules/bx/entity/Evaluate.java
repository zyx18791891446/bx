/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评价维修质量Entity
 * @author 评价维修质量
 * @version 2019-05-12
 */
public class Evaluate extends DataEntity<Evaluate> {
	
	private static final long serialVersionUID = 1L;
	private String repairId;		// 维修订单ID
	private User user;		// 工人ID
	private String evaluUserId;		// 评价人ID
	private String content;		// 评价内容
	private Date evlDate;		// 评价时间
	private String repairMan;//维修人姓名
	private String evulateMan;//评价人姓名
	public Evaluate() {
		super();
	}

	public Evaluate(String id){
		super(id);
	}

	@Length(min=0, max=32, message="repair_id长度必须介于 0 和 32 之间")
	public String getRepairId() {
		return repairId;
	}

	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=32, message="evalu_user_id长度必须介于 0 和 32 之间")
	public String getEvaluUserId() {
		return evaluUserId;
	}

	public void setEvaluUserId(String evaluUserId) {
		this.evaluUserId = evaluUserId;
	}
	
	@Length(min=0, max=20000, message="content长度必须介于 0 和 20000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvlDate() {
		return evlDate;
	}

	public void setEvlDate(Date evlDate) {
		this.evlDate = evlDate;
	}

	public String getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	public String getEvulateMan() {
		return evulateMan;
	}

	public void setEvulateMan(String evulateMan) {
		this.evulateMan = evulateMan;
	}
	
}