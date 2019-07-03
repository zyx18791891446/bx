/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.lh.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.lh.entity.Patent;
import com.thinkgem.jeesite.modules.lh.entity.Patenttype;
import com.thinkgem.jeesite.modules.lh.service.PatentService;
import com.thinkgem.jeesite.modules.lh.service.PatenttypeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.entity.ProjectApplication;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;

/**
 * 专利Controller
 * @author 刘航
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/patent")
public class PatentController extends BaseController {

	@Autowired
	private PatentService patentService;
	@Autowired
	private PatenttypeService patenttypeService;
	@Autowired
	private OaProjectInspectionService projectInspectionService;
	@ModelAttribute
	public Patent get(@RequestParam(required=false) String id) {
		Patent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = patentService.get(id);
		}
		if (entity == null){
			entity = new Patent();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Patent patent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Patent> page = patentService.findPage(new Page<Patent>(request, response), patent); 
		
		
		model.addAttribute("page", page);
		return "modules/lh/patentList";
	}

	@RequiresPermissions("lh:patent:edit")
	@RequestMapping(value = "form")
	public String form(Patent patent, Model model) {
		List<Patenttype> patentTypes = patenttypeService.findAllList(new Patenttype());
		model.addAttribute("patent", patent);
		model.addAttribute("patentTypes", patentTypes);
		return "modules/lh/patentForm";
	}
	@RequestMapping(value = "detail")
	public String detail(Patent patent, Model model) {
		OaProjectInspection oaProjectInspection = new OaProjectInspection();
		oaProjectInspection.setProjectId(patent.getId());
	 	List<OaProjectInspection> inspectionList =  projectInspectionService.findList(oaProjectInspection);
	 	model.addAttribute("inspectionList", inspectionList);
		model.addAttribute("patent", patent);
		return "modules/lh/patentDetails";
	}
	/**
	 * 去审核方法
	 * @param patent
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:patent:view")
	@RequestMapping(value = "toInspection")
	public String toInspection(Patent patent, Model model) {
		model.addAttribute("patent", patent);
		return "modules/lh/inspectionPatent";
	}
	/**
	 * 审核方法
	 * @param patent
	 * @param reason
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:patent:view")
	@RequestMapping(value = "inspection")
	public String inspection(Patent patent,String reason, Model model,  RedirectAttributes redirectAttributes) {
		patentService.inspection(patent, reason);
		//System.out.println(patent.getStatus()+"===="+reason);
		addMessage(redirectAttributes, "保存专利成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patent/?repage";
	}
	
	
	@RequiresPermissions("lh:patent:edit")
	@RequestMapping(value = "save")
	public String save(Patent patent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, patent)){
			return form(patent, model);
		}
		
		patentService.save(patent);
		addMessage(redirectAttributes, "保存专利成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patent/?repage";
	}
	/**
	 * 上传的方法 教师的方法
	 * @param patent
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("lh:patent:edit")
	@RequestMapping(value = "upload")
	public String upload(Patent patent, Model model, RedirectAttributes redirectAttributes) {
		patent.setStatus("1");
		patentService.updateStatus(patent);
		addMessage(redirectAttributes, "上传专利成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patent/?repage";
	}
	
	@RequiresPermissions("lh:patent:edit")
	@RequestMapping(value = "delete")
	public String delete(Patent patent, RedirectAttributes redirectAttributes) {
		patentService.delete(patent);
		addMessage(redirectAttributes, "删除专利成功");
		return "redirect:"+Global.getAdminPath()+"/lh/patent/?repage";
	}
	
	
	
	
	/**
	 * 导出数据
	 * @param projectApplication
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
		  public String exportFile(Patent patent, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
			try {
		            String fileName = "专利数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		            Page<Patent> page = patentService.findPage(new Page<Patent>(request, response), patent); 
		    		new ExportExcel("专利数据", Patent.class).setDataList(page.getList()).write(response, fileName).dispose();
		    		return null;
				} catch (Exception e) {
					addMessage(redirectAttributes, "导出专利数据失败！失败信息："+e.getMessage());
				}
			return "redirect:"+Global.getAdminPath()+"/lh/patent/?repage";
		    }

}