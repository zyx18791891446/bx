/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 维修订单状态Entity
 * @author zyx
 * @version 2019-05-12
 */
public class OrderState extends DataEntity<OrderState> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String username;		// username
	private String content;		// content
	private Date datetimes;		// datetimes
	private String addres;		// addres
	private String status;		// status
	
	public OrderState() {
		super();
	}

	public OrderState(String id){
		super(id);
	}

	@Length(min=0, max=32, message="username长度必须介于 0 和 32 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=32, message="content长度必须介于 0 和 32 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatetimes() {
		return datetimes;
	}

	public void setDatetimes(Date datetimes) {
		this.datetimes = datetimes;
	}
	
	@Length(min=0, max=32, message="addres长度必须介于 0 和 32 之间")
	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
	
	@Length(min=0, max=1, message="status长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}