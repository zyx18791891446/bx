<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="项目初审" />
<html>
<head>
<title>项目初审</title>
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
  	
 }); 
 
$('.delete').hide(); 
$('.file-btn-wj').hide();
</script>
</head>
<body>
<div class="head">
	<div class="title">
		<h3>${title}</h3>
	</div>
</div>

<div class="content">

	<div class="form-container">
	<div class="form-horizontal">
	<form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/firstinstance"  class="form-horizontal">
		<form:hidden path="id"/>
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
				<form:input type="text" path="office.name" class="form-control" disabled="true" style="border:0" color="white"/>
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
			<form:textarea style="resize:none;border: none;" path="achieveAim" rows="3" class="form-control" disabled="true"></form:textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="projectIntroduce" class="col-sm-2 control-label">项目简介：</label>
			<div class="col-sm-8">
			<form:textarea style="resize:none;border: none;" path="projectIntroduce" rows="3" class="form-control" disabled="true"></form:textarea>
			</div>
		</div>
		<!-- 申报项目时项目的文价信息 -->
		<div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">项目相关资料：</label>
			<div class="col-sm-8" style="width: 20px">
			<sys:filepreview id="files" value="${projectApplication.files}"/>
		    </div>
	   </div>
		<div class="form-group">
			<label for="statusDd" class="col-sm-2 control-label">审核状态：</label>
			<div class=" col-sm-3">
					<input type="text" name="statusDd" value="${fns:getDictLabel(projectApplication.statusDd,'projectStatus','') }" class="form-control" disabled="true" style="border:0" color="white"/>
			</div>
		</div>
	   <div class="form-group">
			<label for="office" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>初审结果：</label>
			<div class="col-sm-10" >
			<label class="radio-inline">
			<form:radiobutton path="statusDd" class="optionsRadios" value="3" checked="checked"/>通过</label>
			<label class="radio-inline">
            <form:radiobutton path="statusDd" class="optionsRadios" value="2"/>不通过</label></div>
	   </div>
	    <div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">初审反馈资料：</label>
			<div class="col-sm-9" style="width: 20px">
			<sys:fileupload id="cityfiles" input="cityfiles" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true"  />
		    </div>
	   </div>  
	   <div class="form-group">
			<label for="reason" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>审核意见：</label>
			<div class="col-sm-8">
			<form:textarea style="resize:none" class="form-control required" path="oaProjectInspection.reason" rows="3" ></form:textarea>
			</div>
	   </div>
	   <div class="form-group" style="text-align: center;">
		    <button class="btn btn-primary" type="submit" ><shiro:hasPermission name="ty:projectApplication:edit">提交审核</shiro:hasPermission></button>
		    <button class="btn" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
	   </div>
	</form:form>
	</div>
	</div>
</div>	
</body>
</html>