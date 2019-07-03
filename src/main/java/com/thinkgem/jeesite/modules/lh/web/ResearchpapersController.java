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
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.lh.entity.Researchpapers;
import com.thinkgem.jeesite.modules.lh.entity.Researchpaperstype;
import com.thinkgem.jeesite.modules.lh.service.ResearchpapersService;
import com.thinkgem.jeesite.modules.lh.service.ResearchpaperstypeService;
import com.thinkgem.jeesite.modules.ty.entity.OaProjectInspection;
import com.thinkgem.jeesite.modules.ty.service.OaProjectInspectionService;

/**
 * 科研论文Controller
 * @author zyx
 * @version 2019-04-30
 */
@Controller
@RequestMapping(value = "${adminPath}/lh/researchpapers")
public class ResearchpapersController extends BaseController {

	@Autowired
	private ResearchpapersService researchpapersService;
	@Autowired
	private ResearchpaperstypeService researchpaperstypeService;
	@Autowired
	private OaProjectInspectionService projectInspectionService;
	@ModelAttribute
	public Researchpapers get(@RequestParam(required=false) String id) {
		Researchpapers entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = researchpapersService.get(id);
		}
		if (entity == null){
			entity = new Researchpapers();
		}
		return entity;
	}
	
	/**
	 * 去审核方法
	 * @param researchpapers
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:researchpapers:view")
	@RequestMapping(value = "toInspection")
	public String toInspection(Researchpapers researchpapers, Model model) {
		model.addAttribute("researchpapers", researchpapers);
		return "modules/lh/inspectionResearchPapers";
	}
	/**
	 * 审核方法
	 * @param researchpapers
	 * @param reason
	 * @param model
	 * @return
	 */
	@RequiresPermissions("lh:researchpapers:view")
	@RequestMapping(value = "inspection")
	public String inspection(Researchpapers researchpapers,String reason, Model model,  RedirectAttributes redirectAttributes) {
		researchpapersService.inspection(researchpapers, reason);
		addMessage(redirectAttributes, "保存专利成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpapers/?repage";
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(Researchpapers researchpapers, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Researchpapers> page = researchpapersService.findPage(new Page<Researchpapers>(request, response), researchpapers); 
		model.addAttribute("page", page);
		return "modules/lh/researchpapersList";
	}
	@RequestMapping(value = "detail")
	public String detail(Researchpapers researchpapers, Model model) {
		OaProjectInspection oaProjectInspection = new OaProjectInspection();
		oaProjectInspection.setProjectId(researchpapers.getId());
	 	List<OaProjectInspection> inspectionList =  projectInspectionService.findList(oaProjectInspection);
	 	model.addAttribute("inspectionList", inspectionList);
		model.addAttribute("researchpapers", researchpapers);
		return "modules/lh/researchPapersDetails";
	} 

	@RequiresPermissions("lh:researchpapers:edit")
	@RequestMapping(value = "form")
	public String form(Researchpapers researchpapers, Model model) {
		
		List<Researchpaperstype> list = researchpaperstypeService.findList(new Researchpaperstype());
		model.addAttribute("list", list);
		model.addAttribute("researchpapers", researchpapers);
		return "modules/lh/researchpapersForm";
	}

	@RequiresPermissions("lh:researchpapers:edit")
	@RequestMapping(value = "save")
	public String save(Researchpapers researchpapers, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, researchpapers)){
			return form(researchpapers, model);
		}
		researchpapersService.save(researchpapers);
		addMessage(redirectAttributes, "保存科研论文成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpapers/?repage";
	}
	/**
	 * 上传的方法 教师的方法
	 * @param researchpapers
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("lh:researchpapers:edit")
	@RequestMapping(value = "upload")
	public String upload(Researchpapers researchpapers, Model model, RedirectAttributes redirectAttributes) {
		researchpapers.setStatus("1");
		researchpapersService.updateStatus(researchpapers);
		addMessage(redirectAttributes, "上传科研论文成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpapers/?repage";
	}
	@RequiresPermissions("lh:researchpapers:edit")
	@RequestMapping(value = "delete")
	public String delete(Researchpapers researchpapers, RedirectAttributes redirectAttributes) {
		researchpapersService.delete(researchpapers);
		addMessage(redirectAttributes, "删除科研论文成功");
		return "redirect:"+Global.getAdminPath()+"/lh/researchpapers/?repage";
	}
	
	@RequestMapping(value = "export", method=RequestMethod.POST)
	  public String exportFile(Researchpapers researchpapers, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
	            String fileName = "科研论文"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
	            Page<Researchpapers> page = researchpapersService.findPage(new Page<Researchpapers>(request, response), researchpapers); 
	    		new ExportExcel("科研论文", Researchpapers.class).setDataList(page.getList()).write(response, fileName).dispose();
	    		return null;
			} catch (Exception e) {
				addMessage(redirectAttributes, "导出科研论文失败！失败信息："+e.getMessage());
			}
		return "redirect:"+Global.getAdminPath()+"/lh/researchpapers/?repage";
	    }


}