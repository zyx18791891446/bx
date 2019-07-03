package com.thinkgem.jeesite.modules.file;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.web.Servlets;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


@Controller
@RequestMapping
public class FileUploadController {

	
	@RequestMapping(value = "${adminPath}/file")
	@ResponseBody
	public ApiJson file(final HttpServletRequest request,final HttpServletResponse response, final boolean post) throws Exception{
			String path = "";

			String fileName = request.getParameter("_files[]");
	        long  startTime=System.currentTimeMillis();

			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

			String newFileName =  new SimpleDateFormat("HHmmss").format(new Date()) + ((int)((Math.random()*9+1)*100000)) + "." + fileExt;

			SimpleDateFormat df = new SimpleDateFormat("yyyy//MMdd//HHmmss");
			String basePath = df.format(new Date());
			String realPath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL + basePath;

			FileUtils.createDirectory(FileUtils.path(realPath));


	         //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
	        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
	                request.getSession().getServletContext());
	        //检查form中是否有enctype="multipart/form-data"
	        if(multipartResolver.isMultipart(request))
	        {
	            //将request变成多部分request
	            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
	           //获取multiRequest 中所有的文件名
	            Iterator iter=multiRequest.getFileNames();
	            while(iter.hasNext())
	            {
	                //一次遍历所有文件
	                MultipartFile file=multiRequest.getFile(iter.next().toString());
	               
	                if(file!=null)
	                {
	                	path=realPath+"/"+newFileName;
	                    System.out.println("path : "+path);
	                    //上传
	                    file.transferTo(new File(path));
	                }
	            }
	           
	        }
	        long  endTime=System.currentTimeMillis();
	        String baseUrl = FileUtils.path(Servlets.getRequest().getContextPath() + Global.USERFILES_BASE_URL  + "/" + basePath);

			newFileName = java.net.URLEncoder.encode(newFileName, "UTF-8");
        	ApiJson json = new ApiJson();
        	json.setCode(0);
			json.setMsg("success");
			json.setUrl(baseUrl+newFileName);

			return json;
	}
	

	

}
