/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ty.service;

import static org.hamcrest.CoreMatchers.not;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.ty.entity.Chart;
import com.thinkgem.jeesite.modules.ty.entity.Expert;
import com.thinkgem.jeesite.modules.ty.entity.ExpertsChoice;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectReview;
import com.thinkgem.jeesite.modules.ty.entity.ProjectApplication;
import com.thinkgem.jeesite.modules.ty.dao.ChartDao;
import com.thinkgem.jeesite.modules.ty.dao.ExpertDao;
import com.thinkgem.jeesite.modules.ty.dao.ExpertsChoiceDao;
import com.thinkgem.jeesite.modules.ty.dao.OaProjectReviewDao;
import com.thinkgem.jeesite.modules.ty.dao.ProjectApplicationDao;

/**
 * 项目申报Service
 * @author zqq
 * @version 2019-03-09
 */
@Service
@Transactional(readOnly = true)
public class ProjectApplicationService extends CrudService<ProjectApplicationDao, ProjectApplication> {
	@Autowired
	private ExpertsChoiceDao expertsChoiceDao;
	@Autowired
	private OaProjectReviewDao oaProjectReviewDao;
	@Autowired
	private ExpertDao  expertDao;
	@Autowired
	private ChartDao  chartDao;
	
	/**
	 * 验收项目
	 * @return
	 */
	public Page<ProjectApplication> findListChecks(Page<ProjectApplication> page, ProjectApplication projectApplication){
		projectApplication.setPage(page);
		page.setList(dao.findListChecks(projectApplication));
		return page;
	}
	/**
	 * 市级查询
	 * @return
	 */
	public Page<ProjectApplication> ShiJifindList(Page<ProjectApplication> page, ProjectApplication projectApplication){
		projectApplication.setPage(page);
		page.setList(dao.ShiJifindList(projectApplication));
		return page;
	}
	/**
	 * 查询县级绩效
	 * @param page
	 * @param projectApplication
	 * @return
	 */
	public Page<ProjectApplication> xianJiAchievmentList(Page<ProjectApplication> page, ProjectApplication projectApplication){
		projectApplication.setPage(page);
		page.setList(dao.xianJiAchievmentList(projectApplication));
		return page;
	}
	
	
	public ProjectApplication get(String id) {
		return super.get(id);
	}
	
	public List<ProjectApplication> findList(ProjectApplication projectApplication) {
		return super.findList(projectApplication);
	}
	
	public Page<ProjectApplication> findPage(Page<ProjectApplication> page, ProjectApplication projectApplication) {
		return super.findPage(page, projectApplication);
	}
	/***
	 * <!-- 根据状态查询项目 -->
	 * @param page
	 * @param projectApplication
	 * @return
	 */
	public Page<ProjectApplication> findPages(Page<ProjectApplication> page, ProjectApplication projectApplication) {
		projectApplication.setPage(page);
		page.setList(dao.findLists(projectApplication));
		return page;
	}
	/**
	 * 根据状态查询需要评审的项目
	 * @param page
	 * @param projectApplication
	 * @return
	 */
	public Page<ProjectApplication> findReviewPages(Page<ProjectApplication> page, ProjectApplication projectApplication) {
		projectApplication.setPage(page);
		page.setList(dao.findReviewList(projectApplication));
		return page;
	}
	
	@Transactional(readOnly = false)
	public void save(ProjectApplication projectApplication) {
		super.save(projectApplication);
	}
	/**
	 * 验收 修改状态
	 * @param projectApplication
	 */
	@Transactional(readOnly = false)
	public void checkAccept(ProjectApplication projectApplication){
		dao.updateStatusDd(projectApplication);
	}

	@Transactional(readOnly = false)
	public List<Chart> expense(Chart chart) {
		List<Chart> charts=chartDao.expense(chart);
		return charts;
	}
	
	
	/**
	 * 选择专家
	 * @param projectApplication
	 * @throws Exception 
	 */
	@Transactional(readOnly = false)
	public void selectExpert(ProjectApplication projectApplication,String[] expertID,String expertNum) throws Exception {
		
		String projecyId = projectApplication.getId();//获取项目ID
		
		
		String projecCurrrenttStatus = dao.get(projectApplication).getStatusDd();
		//获取项目当前状态
		if(!projecCurrrenttStatus.equals("8") && !projecCurrrenttStatus.equals("5") ){//选择专家  并且修改状态为评审中
			//当没有选择专家时执行该方法 
			List<ExpertsChoice> expertsChoices = new ArrayList<ExpertsChoice>();
			
			// 随机选择专家 
			if(expertID == null){//随机生成多个专家
				int count = 0 ;//记录评审专家人数
				try {
					count = Integer.parseInt(expertNum);
					
				} catch (Exception e) {
					count = 1;
				}
				
				List<Expert> expertList = expertDao.findList(new Expert());
				
				int size = expertList.size();
				if(size == 0){
					throw new Exception();
				}
				if(count>=size){//选中全部专家
					expertID = new String[size];
					for (int i = 0; i < expertID.length; i++) {
						expertID[i] = expertList.get(i).getId();
					}
				}else{//选中部分专家
					int[] index = new int[count];
					//生成两个小于size并且不相等的随机数 
					for (int i = 0; i < count; i++) {
						index[i] = (int) (Math.random() * size);
						for (int j = 0; j < i; j++) {
							if (index[i] == index[j]) {
								i--;
							}

						}
					}
					expertID = new String[count];
					for (int i = 0; i < count; i++) {
						expertID[i] = expertList.get(index[i]).getId();
					}
				}
			}
			
			//选择专家对象
			for(String expertid : expertID){
				ExpertsChoice ec = new ExpertsChoice();
				String oaId = IdGen.uuid();//生成ID
				ec.setOaId(oaId);
				ec.setOaProject(projecyId);//设置项目ID
				ec.setOaExpertid(expertid); 
				expertsChoices.add(ec);
			}
			expertsChoiceDao.insertByBatch(expertsChoices);
			
			projectApplication.setStatusDd("8");//修改状态为评审中
			dao.updateStatusDd(projectApplication);
		}else{
			//评审对象
			String projectStatus = projectApplication.getStatusDd();//获取项目状态
			String reviewFiles = projectApplication.getReviewFiles(); 
			OaProjectReview or = projectApplication.getOaProjectReview();//获得对象并且初始化对象
			
			or.preInsert();
			or.setReviewFiles(reviewFiles);
			or.setProjectId(projecyId);
			or.setStatus(projectStatus);
			oaProjectReviewDao.insert(or);
			dao.updateStatusDd(projectApplication);
		}
	
		
	
	}
	
	@Transactional(readOnly = false)
	public void delete(ProjectApplication projectApplication) {
		super.delete(projectApplication);
	}
	
	/**
	 * 专家姓名
	 * @param projectApplication
	 * @throws Expert 
	 */
	public String expertname(ProjectApplication projectApplication){
		List<Expert> list=dao.expertname(projectApplication);
		String pReview = new String();
	
		try{
			for (Expert projectReview : list) {
				String expert=projectReview.getExpertname();
				pReview+=expert+"、";
			}
			String substring = pReview.substring(0, pReview.length() - 1);
			return substring;
		}catch (Exception e) {
			return "";
		}
		
		
		
		
	}
	
	/**
	 * 评审
	 * @param projectApplication
	 * @throws Expert 
	 */
	public List<OaProjectReview> review(ProjectApplication projectApplication){
		List<OaProjectReview> list=dao.review(projectApplication);
		return list;
		
	}
}