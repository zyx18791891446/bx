/**
 * Copyright &copy; 2112-2116 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.entity;

import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 项目申报Entity
 * @author zdd
 * @version 2019-01-09
 */
public class ProjectApplication extends DataEntity<ProjectApplication> {
	private static final String PSTG  =  "4";
	private static final String PSBTG =  "5";
	private static final String CSTG  =  "3";
	private static final String CSBTG =  "2";
	private static final String PSZ =  "8";
	
	
	private static final long serialVersionUID = 1L;
	private Office office;		// 部门id（所属单位）
	private String constructionUnit;		// 建设单位
	private String projectName;		// 项目名称
	private String projectFund;		// 项目经费
	private String statusDd;		// 状态（数据字典）
	private String fundSource;		// 经费来源
	private String applyScope;		// 项目应用范围
	private String constructionMode;		// 建设方式
	private String constructionNecessity;		// 项目建设必要性
	private String achieveAim;		// 项目实现目标
	private String projectIntroduce;		// 项目介绍
	private String ysyj;//验收意见
	private String files;//申报项目文件名称
	private String cityfiles;//市级初审反馈文件名称
	private String reviewFiles;//市级评审反馈文件名称
	private String checkAcceptFiles;//市级验收项目文件名称
	
	private String fjId;//附件ID
	private OaProjectReview oaProjectReview;//评审实体类
	private OaProjectInspection oaProjectInspection;//项目初审实体
	private String officeName;//office名称
	private Expert expert;//评审专家
	private String apfund;          //建设总费用
	private String gname;	//县区名
	private String bpfund;          //通过评审总费用
	private String apname;           //项目总数
	private String bpname;           //办结数
	private String cpname;           //评审不通过数 
	private String dpname;           //初审不通过数 
	private String epname;           //初审通过数 
	private String fpname;           //退办数
	
	
	
	
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public Expert getExpert() {
		return expert;
	}

	public void setExpert(Expert expert) {
		this.expert = expert;
	}

	public String getFjId() {
		return fjId;
	}

	public void setFjId(String fjId) {
		this.fjId = fjId;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getYsyj() {
		return ysyj;
	}

	public void setYsyj(String ysyj) {
		this.ysyj = ysyj;
	}

	public OaProjectInspection getOaProjectInspection() {
		return oaProjectInspection;
	}

	public void setOaProjectInspection(OaProjectInspection oaProjectInspection) {
		this.oaProjectInspection = oaProjectInspection;
	}

	public static String getPstg() {
		return PSTG;
	}

	public static String getPsbtg() {
		return PSBTG;
	}

	public static String getCstg() {
		return CSTG;
	}
	
	public static String getPsz() {
		return PSZ;
	}

	public static String getCsbtg() {
		return CSBTG;
	} 
	@ExcelField(title="建设总费用", align=1, sort=1)
	public String getApfund() {
		return apfund;
	}

	public void setApfund(String apfund) {
		this.apfund = apfund;
	}
	public String getBpfund() {
		return bpfund;
	}

	public void setBpfund(String bpfund) {
		this.bpfund = bpfund;
	}
	public String getApname() {
		return apname;
	}

	public void setApname(String apname) {
		this.apname = apname;
	}
	public String getBpname() {
		return bpname;
	}

	public void setBpname(String bpname) {
		this.bpname = bpname;
	}
	public String getCpname() {
		return cpname;
	}

	public void setCpname(String cpname) {
		this.cpname = cpname;
	}
	public String getDpname() {
		return dpname;
	}

	public void setDpname(String dpname) {
		this.dpname = dpname;
	}
	public String getEpname() {
		return epname;
	}

	public void setEpname(String epname) {
		this.epname = epname;
	}
	public String getFpname() {
		return fpname;
	}

	public void setFpname(String fpname) {
		this.fpname = fpname;
	}

	public OaProjectReview getOaProjectReview() {
		return oaProjectReview;
	}

	public void setOaProjectReview(OaProjectReview oaProjectReview) {
		this.oaProjectReview = oaProjectReview;
	}

	public ProjectApplication() {
		super();
	}

	public ProjectApplication(String id){
		super(id);
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
	@Length(min=1, max=64, message="建设单位长度必须介于1 和 64 之间")
	@ExcelField(title="建设单位", align=1, sort=1)
	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	
	@Length(min=1, max=64, message="项目名称长度必须介于 1 和 64 之间")
	@ExcelField(title="项目名称", align=1, sort=10)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Length(min=1, max=15, message="项目经费长度必须介于 1 和 15 之间")
	@ExcelField(title="项目经费", align=1, sort=20)
	public String getProjectFund() {
		return projectFund;
	}

	public void setProjectFund(String projectFund) {
		this.projectFund = projectFund;
	}
	
	@Length(min=0, max=1, message="状态（数据字典）长度必须介于 0 和 1 之间")
	@ExcelField(title="状态", align=1, sort=30, dictType="projectStatus")
	public String getStatusDd() {
		return statusDd;
	}

	public void setStatusDd(String statusDd) {
		this.statusDd = statusDd;
	}
	
	@Length(min=1, max=128, message="经费来源长度必须介于 1 和 128 之间")
	@ExcelField(title="经费来源", align=1, sort=40)
	public String getFundSource() {
		return fundSource;
	}

	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	
	@Length(min=1, max=128, message="项目应用范围长度必须介于 1 和 128 之间")
	@ExcelField(title="项目应用范围", align=1, sort=50)
	public String getApplyScope() {
		return applyScope;
	}

	public void setApplyScope(String applyScope) {
		this.applyScope = applyScope;
	}
	
	@Length(min=1, max=128, message="建设方式长度必须介于 1 和 128 之间")
	@ExcelField(title="建设方式", align=1, sort=60)
	public String getConstructionMode() {
		return constructionMode;
	}

	public void setConstructionMode(String constructionMode) {
		this.constructionMode = constructionMode;
	}
	
	@Length(min=1, max=128, message="项目建设必要性长度必须介于 1 和 128 之间")
	public String getConstructionNecessity() {
		return constructionNecessity;
	}

	public void setConstructionNecessity(String constructionNecessity) {
		this.constructionNecessity = constructionNecessity;
	}
	
	@Length(min=1, max=2000, message="项目实现目标长度必须介于 1 和 2000 之间")
	public String getAchieveAim() {
		return achieveAim;
	}

	public void setAchieveAim(String achieveAim) {
		this.achieveAim = achieveAim;
	}
	
	
	public String getProjectIntroduce() {
		return projectIntroduce;
	}

	public void setProjectIntroduce(String projectIntroduce) {
		this.projectIntroduce = projectIntroduce;
	}

	public String getCityfiles() {
		return cityfiles;
	}

	public void setCityfiles(String cityfiles) {
		this.cityfiles = cityfiles;
	}

	public String getReviewFiles() {
		return reviewFiles;
	}

	public void setReviewFiles(String reviewFiles) {
		this.reviewFiles = reviewFiles;
	}

	public String getCheckAcceptFiles() {
		return checkAcceptFiles;
	}

	public void setCheckAcceptFiles(String checkAcceptFiles) {
		this.checkAcceptFiles = checkAcceptFiles;
	}	
}