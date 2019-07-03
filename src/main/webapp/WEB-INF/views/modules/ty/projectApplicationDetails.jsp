<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="项目详情" />
<html>
<head>
<title>项目详情页面</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
 $(document).ready(function() {
	//$("#name").focus();
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
	$('.delete').hide(); 
	$('.file-btn-wj').hide();
}); 
</script>
<style type="text/css" scoped="scoped">
.td{
/* 文本超出自动换行 */
word-wrap:break-word;
text-overflow:ellipsis;
position: relative;
word-break:break-all;
}
.progress{
display: none;}
</style>
</head>
<body>
	<div class="head">
	  <div class="title">
		<h3>${title}</h3>
		<div class="form-group" style="text-align:right;">
		<button class="btn btn-primary" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
		</div>
	  </div>
	</div>
 <!-- 项目基本信息模块 -->
	<div class="content">
          <div class="page-title"><h5><strong>项目基本信息</strong></h5></div>
	   <div class="form-container">
	     <div class="form-horizontal">
	       <form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/form"  class="form-horizontal">
		     <div class="form-group">
			   <label for="constructionUnit" class="col-sm-2 control-label">建设单位：</label>
			     <div class=" col-sm-3">
				  <form:input type="text" path="constructionUnit" class="form-control" disabled="true" style="border:0" color="white"/>
			     </div>
			   <label for="projectName" class="col-sm-2 control-label">项目名称：</label>
			     <div class="col-sm-3">
				<form:input type="text" path="projectName" class="form-control" disabled="true" style="border:0" color="white"/>
			     </div>
		     </div>
		     <div class="form-group">
		      <label for="projectFund" class="col-sm-2 control-label">项目经费：</label>
			   <div class="col-sm-3">
				<form:input type="text" path="projectFund" class="form-control" disabled="true" style="border:0" color="white"/>
			   </div>
			  <label for="fundSource" class="col-sm-2 control-label">经费来源：</label>
			    <div class="col-sm-3">
				 <form:input type="text" path="fundSource" class="form-control" disabled="true" style="border:0" color="white"/>
			    </div>
		     </div>
	         <div class="form-group">
			   <label for="office" class="col-sm-2 control-label">所属县区：</label>
			     <div class="col-sm-3">
				   <form:input type="text" path="officeName" class="form-control" disabled="true" style="border:0" color="white"/>
			     </div>
			       <label for="updateDate" class="col-sm-2 control-label">申报日期：</label>
			         <div class="col-sm-3">
				      <fmt:formatDate value="${projectApplication.createDate }" pattern="yyyy-MM-dd HH:mm" var="formatDate"/>
				      <form:input  path="updateDate" value="${formatDate }" class="form-control" disabled="true" style="border:0" color="white"/>
			        </div>
		    </div>
		    <div class="form-group">
			   <label for="applyScope" class="col-sm-2 control-label">项目应用范围：</label>
			     <div class="col-sm-3">
				   <form:input type="text" path="applyScope" class="form-control" disabled="true" style="border:0" color="white"/>
			     </div>
			       <label for="constructionMode" class="col-sm-2 control-label">建设方式：</label>
			          <div class="col-sm-3">
			 	        <form:input type="text" path="constructionMode" class="form-control" disabled="true" style="border:0" color="white"/>
			          </div>
		    </div>
		    <div class="form-group">
			   <label for="constructionNecessity" class="col-sm-2 control-label">项目建设必要性：</label>
			      <div class="col-sm-8">
				    <form:input type="text" path="constructionNecessity" class="form-control" disabled="true" style="border:0" color="white"/>
			      </div>
		    </div>
		    <div class="form-group">
			   <label for="achieveAim" class="col-sm-2 control-label">项目实现目标：</label>
			     <div class="col-sm-8">
			       <form:textarea style="resize:none;border: none;" path="achieveAim" rows="3" class="form-control" disabled="true" ></form:textarea>
			     </div>
		    </div>
		    <div class="form-group">
			  <label for="projectIntroduce" class="col-sm-2 control-label">项目简介：</label>
			    <div class="col-sm-8">
			      <form:textarea style="resize:none;border: none;" path="projectIntroduce" rows="3" class="form-control" disabled="true"></form:textarea>
			    </div>
		   </div>
		   <div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">项目相关资料：</label>
			<div class="col-sm-8" style="width: 20px">
			<sys:fileupload id="files" input="files"  type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${projectApplication.files}"/>
		    </div>
	       </div>
	      
		   <div class="form-group">
			  <label for="statusDd" class="col-sm-2 control-label">审核状态：</label>
			    <div class=" col-sm-3">
				 <input type="text" name="statusDd" value="${fns:getDictLabel(projectApplication.statusDd,'projectStatus','') }" class="form-control" disabled="true" style="border:0" color="white"/>
			    </div>
		  </div>
	    </form:form>
	   </div>
	 </div>
   </div>	
<!-- 项目初审信息模块 -->
<c:if test="${ fn:length(inspectionPage.list) != 0}">
<div class="content">
      <div class="page-title"><h5><strong>项目初审信息</strong></h5></div>
  <div class="form-container">
	 <div class="form-horizontal">
	    <form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/form"  class="form-horizontal">
	      <div class="form-group">
		  <div class="col-sm-1"></div>
		    <div class="col-sm-9">
		<!-- 表格固定 -->
	           <table style="table-layout: fixed;" id="contentTable" class="table table-striped table-bordered table-condensed">
		          <thead>
		             <tr>
		               <th class="col-sm-3">初审日期</th>
		               <th class="col-sm-2">初审状态</th>
		               <th class="col-sm-2">项目资料</th>
		               <th class="col-sm-2">初审意见</th>
		               <th class="col-sm-2">初审资料</th>
	                </tr>
		          </thead>
		          <tbody>
		            <c:forEach items="${inspectionPage.list}" var="inspection">
		             <tr>
		             <!-- <td class="SortCLASS"></td> -->
		          	  <fmt:formatDate value="${inspection.inspectionDate }" pattern="yyyy-MM-dd HH:mm" var="formatDate"/>
		          	  <td class="td">${formatDate }</td>
		        	  <td class="td">${fns:getDictLabel(inspection.statusDd,'projectStatus','') }</td>
			          <td class="td" >
			          <sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${inspection.projectFiles}"/>
			          </td>
			          <td class="td">${inspection.reason }</td>
			          <td class="td" >
			          <sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${inspection.inspectionFiles}"/>
			          </td>
		            </tr>
		          </c:forEach>
		         </tbody>
		     </table>
		   </div>
	    </div>
	  </form:form>
	</div>
  </div>
</div>
 </c:if> 
<!-- 项目评审信息模块 -->
<c:if test="${ fn:length(oaprojectReview) != 0}">
<div class="content">
    <div class="page-title"><h5><strong>项目评审信息</strong></h5></div>
  <div class="form-container">
	<div class="form-horizontal">
	  <form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/form"  class="form-horizontal">
		<div class="form-group">
		    <div class="col-sm-1"></div>
		  <div class="col-sm-9">
		   <!-- 表格固定 -->
		          <h3 style="font-size: 22px">评审专家：<font style="font-size: 20px">${string}</font></h3>
	       <table style="table-layout: fixed;" id="contentTable" class="table table-striped table-bordered table-condensed">
		      <thead>
		         <tr>
		           <th class="col-sm-3">评审日期</th>
		           <th class="col-sm-2">评审状态</th>
		           <th class="col-sm-3">评审意见</th>
		           <th class="col-sm-3">评审资料</th>
		         </tr>
		      </thead>
		     <tbody>

		         <c:forEach items="${oaprojectReview}" var="review">
		         <tr>
		           <fmt:formatDate value="${review.createDate}" pattern="yyyy-MM-dd HH:mm" var="formatDate"/>
		           <td class="td">${formatDate }</td>
		          <%--  <td class="td">${review.expert.expertname }</td> --%>
		           <td class="td">${fns:getDictLabel(review.status,'projectStatus','') }</td>
	               <td class="td">${review.reviewAdvice }</td>	
			       <td class="td" >
	                  <sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${review.reviewFiles}"/>
	               </td>
	 	         </tr>
		        </c:forEach>
		     </tbody>
		   </table>
		 </div>
	  </div>
	</form:form>
	</div>
</div>
    </div>
    </c:if>
<!-- 项目验收信息模块 -->
<c:if test="${!empty  projectApplication.ysyj }">
<div class="content">
     <div class="page-title"><h5><strong>项目验收信息</strong></h5></div>
   <div class="form-container">
	  <div class="form-horizontal">
	    <form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/form"  class="form-horizontal">
		    <div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">相关资料：</label>
			<div class="col-sm-8" style="width: 20px">
				<sys:fileupload id="checkAcceptFiles" input="checkAcceptFiles"  type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${projectApplication.checkAcceptFiles}"/>
		    </div>
	       </div>
		   <div class="form-group">
			  <label for="projectIntroduce" class="col-sm-2 control-label">验收意见：</label>
			    <div class="col-sm-8">
			      <form:textarea style="resize:none;border: none;" path="ysyj" rows="3" class="form-control" disabled="true"></form:textarea>
			    </div>
		   </div>
	   </form:form>
	  </div>
   </div>
</div>
</c:if>
</body>
</html>