/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.bx.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 维修订单Entity
 * @author zyx
 * @version 2019-05-12
 */
public class Orders extends DataEntity<Orders> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// user_id
	private String username;		// 故障名称
	private String content;		// content
	private Date datetimes;		// datetimes
	private String addres;		// addres
	private String status;		// status
	private String roomNum;		// room_num
	private String repairMan; //维修人姓名
	private String repairManId;//维修人ID
	
	private String files;
	public Orders() {
		super();
	}

	public Orders(String id){
		super(id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@ExcelField(align=1,title="故障名称",sort=1)
	@Length(min=0, max=32, message="故障名称长度必须介于 0 和 32 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@ExcelField(align=1,title="故障内容",sort=2)
	@Length(min=0, max=32, message="故障内容长度必须介于 0 和 32 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@ExcelField(title="创建时间", align=1, sort=3)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDatetimes() {
		return datetimes;
	}

	public void setDatetimes(Date datetimes) {
		this.datetimes = datetimes;
	}
	@ExcelField(title="地址", align=1, sort=4)
	@Length(min=0, max=32, message="地址长度必须介于 0 和 32 之间")
	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}
	@ExcelField(title="状态", dictType="roomfixStatus", align=1, sort=5)
	@Length(min=0, max=1, message="状态长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@ExcelField(title="宿舍号", align=1, sort=6)
	@Length(min=0, max=20, message="宿舍号长度必须介于 0 和 20 之间")
	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getRepairMan() {
		return repairMan;
	}

	public void setRepairMan(String repairMan) {
		this.repairMan = repairMan;
	}

	public String getRepairManId() {
		return repairManId;
	}

	public void setRepairManId(String repairManId) {
		this.repairManId = repairManId;
	}
	
}