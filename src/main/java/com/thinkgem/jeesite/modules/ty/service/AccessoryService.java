/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ty.entity.Accessory;
import com.thinkgem.jeesite.modules.ty.dao.AccessoryDao;

/**
 * 附件Service
 * @author zdd
 * @version 2019-03-09
 */
@Service
@Transactional(readOnly = true)
public class AccessoryService extends CrudService<AccessoryDao, Accessory> {

	public Accessory get(String id) {
		return super.get(id);
	}
	
	public List<Accessory> findList(Accessory accessory) {
		return super.findList(accessory);
	}
	
	public Page<Accessory> findPage(Page<Accessory> page, Accessory accessory) {
		return super.findPage(page, accessory);
	}
	
	@Transactional(readOnly = false)
	public void save(Accessory accessory) {
		super.save(accessory);
	}
	
	@Transactional(readOnly = false)
	public void delete(Accessory accessory) {
		super.delete(accessory);
	}
	
}