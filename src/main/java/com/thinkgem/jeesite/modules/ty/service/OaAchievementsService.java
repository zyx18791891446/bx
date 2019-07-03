/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.OaAchievements;
import com.thinkgem.jeesite.modules.ty.dao.OaAchievementsDao;

/**
 * 绩效Service
 * @author zyx
 * @version 2019-03-13
 */
@Service
@Transactional(readOnly = true)
public class OaAchievementsService extends CrudService<OaAchievementsDao, OaAchievements> {

	public OaAchievements get(String id) {
		return super.get(id);
	}
	
	public List<OaAchievements> findList(OaAchievements oaAchievements) {
		return super.findList(oaAchievements);
	}
	
	public Page<OaAchievements> findPage(Page<OaAchievements> page, OaAchievements oaAchievements) {
		return super.findPage(page, oaAchievements);
	}
	
	
	@Transactional(readOnly = false)
	public void save(OaAchievements oaAchievements) {
		super.save(oaAchievements);
	}
	
	@Transactional(readOnly = false)
	public void delete(OaAchievements oaAchievements) {
		super.delete(oaAchievements);
	}
	
}