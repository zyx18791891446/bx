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
import com.thinkgem.jeesite.modules.lh.entity.Researchpaperstype;
import com.thinkgem.jeesite.modules.lh.entity.Researchproject;
import com.thinkgem.jeesite.modules.lh.entity.Researchprojecttype;
import com.thinkgem.jeesite.modules.lh.service.ResearchpaperstypeService;
import com.thinkgem.jeesite.modules.lh.service.ResearchprojectService;
import com.thinkgem.jeesite.modules.lh.service.ResearchprojecttypeService;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;

/**
 * 科研项目Controller
 * @author 刘航
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/researchproject")
public class ResearchprojectController extends BaseController {

	@Autowired
	private ResearchprojectService researchprojectService;
	@Autowired
	private ResearchprojecttypeService researchprojecttypeService;
	@Autowired
	private OaProjectInspectionService projectInspectionService;
	@ModelAttribute
	public Researchproject get(@RequestParam(required=false) String id) {
		Researchproject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = researchprojectService.get(id);
		}
		if (entity == null){
			entity = new Researchproject();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Researchproject researchproject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Researchproject> page = researchprojectService.findPage(new Page<Researchproject>(request, response), researchproject); 
		model.addAttribute("page", page);
		return "modules/lh/researchprojectList";
	}

	@RequiresPermissions("lh:researchproject:edit")
	@RequestMapping(value = "form")
	public String form(Researchproject researchproject, Model model) {
		List<Researchprojecttype> list = researchprojecttypeService.findList(new Researchprojecttype());
		model.addAttribute("list", list);
		model.addAttribute("researchproject", researchproject);
		return "modules/lh/researchprojectForm";
	}
	/**
	 * 上传的方法 教师的方法
	 * @param patent
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("lh:researchproject:edit")
	@RequestMapping(value = "upload")
	public String upload(Researchproject researchproject, Model model, RedirectAttributes redirectAttributes) {
		researchproject.setStatus("1");
		researchprojectService.updateStatus(researchproject);
		addMessage(redirectAttributes, "上传科研项目成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchproject/?repage";
	}
	@RequestMapping(value = "detail")
	public String detail(Researchproject researchproject, Model model) {
		OaProjectInspection oaProjectInspection = new OaProjectInspection();
		oaProjectInspection.setProjectId(researchproject.getId());
	 	List<OaProjectInspection> inspectionList =  projectInspectionService.findList(oaProjectInspection);
	 	model.addAttribute("inspectionList", inspectionList);

		model.addAttribute("researchproject", researchproject);
		return "modules/lh/researchprojectDetails";
	} 
	
	
	/**
	 * 去审核方法
	 * @param patent
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:researchproject:view")
	@RequestMapping(value = "toInspection")
	public String toInspection(Researchproject researchproject, Model model) {
		model.addAttribute("patent", researchproject);
		return "modules/lh/inspectionResearchProject";
	}
	/**
	 * 审核方法
	 * @param patent
	 * @param reason
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:researchproject:view")
	@RequestMapping(value = "inspection")
	public String inspection(Researchproject researchproject,String reason, Model model) {
		researchprojectService.inspection(researchproject, reason);
		return "redirect:"+Global.getAdminPath()+"/lh/researchproject/?repage";
	}
	

	@RequiresPermissions("lh:researchproject:edit")
	@RequestMapping(value = "save")
	public String save(Researchproject researchproject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, researchproject)){
			return form(researchproject, model);
		}
		researchprojectService.save(researchproject);
		addMessage(redirectAttributes, "保存科研项目成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchproject/?repage";
	}
	
	@RequiresPermissions("lh:researchproject:edit")
	@RequestMapping(value = "delete")
	public String delete(Researchproject researchproject, RedirectAttributes redirectAttributes) {
		researchprojectService.delete(researchproject);
		addMessage(redirectAttributes, "删除科研项目成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchproject/?repage";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
	  public String exportFile(Researchproject researchproject, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	            String fileName = "科研项目数据"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            Page<Researchproject> page = researchprojectService.findPage(new Page<Researchproject>(request, response), researchproject); 
	    		new ExportExcel("科研项目数据", Researchproject.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出科研项目数据失败！失败信息："+e.getMessage());
			}
		return "redirect:"+Global.getAdminPath()+"/lh/researchproject/?repage";
	    }

}