/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.Expert;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.ty.dao.ExpertDao;

/**
 * 专业人员库Service
 * @author zdd
 * @version 2019-03-09
 */
@Service
@Transactional(readOnly = true)
public class ExpertService extends CrudService<ExpertDao, Expert> {
	@Autowired
	private UserDao userdao;
	public Expert get(String id) {
		return super.get(id);
	}
	
	public List<Expert> findList(Expert expert) {
		return super.findList(expert);
	}
	
	public Page<Expert> findPage(Page<Expert> page, Expert expert) {
		return super.findPage(page, expert);
	}
	/**
	 * 获取专家详细信息 （包括评审的项目）
	 * @param page
	 * @param expert
	 * @return
	 */
	public Page<Expert> getExpert(Page<Expert> page, Expert expert) {
		expert.setPage(page);
		page.setList(dao.getExpert(expert));
		return page;
	}
	//修改和删除的方法，自动生成ID
	@Transactional(readOnly = false)
	public void save(Expert expert) {
		super.save(expert);
		
	}
	
		//修该专家信息时，需要同步修改用户信息
		@Transactional(readOnly = false)
		public void updateExpert(Expert expert) {
			super.save(expert);
			User user = userdao.get(expert.getId());
			user.setName(expert.getExpertname());
			user.setPhoto(expert.getPhoto());
			userdao.update(user);
			
		}
	
	
	//添加用户时，给专家表加一条数据
	@Transactional(readOnly = false)
	public void save1(Expert expert,String id) {
			expert.preInsert();
			expert.setId(id);
			dao.insert(expert);
	}
	
	@Transactional(readOnly = false)
	public void delete(Expert expert) {
		super.delete(expert);
	}
	
}