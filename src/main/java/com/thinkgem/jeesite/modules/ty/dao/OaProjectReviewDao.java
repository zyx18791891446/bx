/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;

/**
 * 项目评审DAO接口
 * @author zyx
 * @version 2019-03-13
 */
@MyBatisDao
public interface OaProjectReviewDao extends CrudDao<OaProjectReview> {
	List<OaProjectReview> getExpert(OaProjectReview oaProjectReview);//查询评审专家
}