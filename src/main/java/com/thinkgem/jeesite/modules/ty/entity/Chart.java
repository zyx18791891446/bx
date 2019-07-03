package com.thinkgem.jeesite.modules.ty.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

public class Chart  extends DataEntity<Accessory> {
	private String apfund;          //建设总费用
	private String gname;	//县区名
	private String bpfund;          //通过评审总费用
	private String apname;           //项目总数
	private String bpname;           //办结数
	private String cpname;           //评审不通过数 
	private String dpname;           //初审不通过数 
	private String epname;           //初审通过数 
	
	@ExcelField(title="建设总费用", align=1, sort=2)
	public String getApfund() {
		return apfund;
	}
	public void setApfund(String apfund) {
		this.apfund = apfund;
	}
	@Override
	public String toString() {
		return "Chart [apfund=" + apfund + ", gname=" + gname + ", bpfund=" + bpfund + ", apname=" + apname
				+ ", bpname=" + bpname + ", cpname=" + cpname + ", dpname=" + dpname + ", epname=" + epname + "]";
	}
	@ExcelField(title="县区名", align=1, sort=1)
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	@ExcelField(title="评审通过总费用", align=1, sort=3)
	public String getBpfund() {
		return bpfund;
	}
	public void setBpfund(String bpfund) {
		this.bpfund = bpfund;
	}
	@ExcelField(title="项目总数", align=1, sort=4)
	public String getApname() {
		return apname;
	}
	public void setApname(String apname) {
		this.apname = apname;
	}
	@ExcelField(title="办结数", align=1, sort=5)
	public String getBpname() {
		return bpname;
	}
	public void setBpname(String bpname) {
		this.bpname = bpname;
	}
	@ExcelField(title="评审不通过数 ", align=1, sort=8)
	public String getCpname() {
		return cpname;
	}
	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	@ExcelField(title="初审不通过数 ", align=1, sort=6)
	public String getDpname() {
		return dpname;
	}
	public void setDpname(String dpname) {
		this.dpname = dpname;
	}
	@ExcelField(title="初审通过数", align=1, sort=7)
	public String getEpname() {
		return epname;
	}
	public void setEpname(String epname) {
		this.epname = epname;
	}
}
