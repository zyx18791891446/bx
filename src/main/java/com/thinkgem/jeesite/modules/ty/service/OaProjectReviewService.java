/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;
import com.thinkgem.jeesite.modules.ty.dao.OaProjectReviewDao;

/**
 * 项目评审Service
 * @author zyx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class OaProjectReviewService extends CrudService<OaProjectReviewDao, OaProjectReview> {

	public OaProjectReview get(String id) {
		return super.get(id);
	}
	
	public List<OaProjectReview> findList(OaProjectReview oaProjectReview) {
		return super.findList(oaProjectReview);
	}
	
	public Page<OaProjectReview> findPage(Page<OaProjectReview> page, OaProjectReview oaProjectReview) {
		return super.findPage(page, oaProjectReview);
	}
	
	/**
	 * //查询评审专家
	 * @param page
	 * @param oaProjectReview
	 * @return
	 */
	public Page<OaProjectReview> findExpert(Page<OaProjectReview> page, OaProjectReview oaProjectReview){
		oaProjectReview.setPage(page);
		page.setList(dao.getExpert(oaProjectReview));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(OaProjectReview oaProjectReview) {
		super.save(oaProjectReview);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaProjectReview oaProjectReview) {
		super.delete(oaProjectReview);
	}
	
	
	
}