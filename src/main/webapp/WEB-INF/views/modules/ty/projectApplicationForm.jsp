<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="项目申报" />
<html>
<head>
<title></title>
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

	function testDouble(){
	  var projectFund=document.getElementById("projectFund");
	  	if(!/^[-\+]?\d+(\.\d+)?$/.test(projectFund.value)){
	  		tip.innerHTML='请输入数字，且不能有空格。'; 
	  		
	  	}else {
	  		tip.innerHTML='';
	  	}
	  }
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
	<form:form id="inputForm" modelAttribute="projectApplication" method="post" action="${ctx}/ty/projectApplication/save"  class="form-horizontal">
		<form:hidden path="id"/>
		<div class="form-group">
			<label for="constructionUnit" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font></span>建设单位：</label>
			<div class=" col-sm-3">
				<form:input type="text" path="constructionUnit" class="form-control required" maxlength="64"/>
			</div>
			<label for="projectName" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>项目名称：</label>
			<div class="col-sm-3">
				<form:input type="text" class="form-control required" path="projectName" maxlength="64" />
			</div>
		</div>
		<div class="form-group">
			<label for="projectFund" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>项目经费：</label>
			<div class="col-sm-3">
				<form:input type="text" class="form-control required" id="projectFund" path="projectFund" maxlength="15" onblur="testDouble()" /><span id="tip" style="color:red"></span>
			</div>
			<label for="fundSource" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>经费来源：</label>
			<div class="col-sm-3">
				<form:input type="text" class="form-control required" path="fundSource" maxlength="128" />
			</div>
		</div>
	   
			<input type="hidden" name="office.id" value="${fns:getUser().office.id}" >
		
		<div class="form-group">
			<label for="applyScope" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>项目应用范围：</label>
			<div class="col-sm-3">
				<form:input type="text" class="form-control required" path="applyScope" maxlength="128" />
			</div>
			<label for="constructionMode" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>建设方式：</label>
			<div class="col-sm-3">
				<form:input type="text" class="form-control required" path="constructionMode" maxlength="128" />
			</div>
		</div>
		<div class="form-group">
			<label for="constructionNecessity" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>项目建设必要性：</label>
			<div class="col-sm-8">
				<form:input type="text" class="form-control required" path="constructionNecessity" maxlength="128" />
			</div>
		</div>
		<div class="form-group">
			<label for="achieveAim" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>项目实现目标：</label>
			<div class="col-sm-8">
			<form:textarea style="resize:none" class="form-control required" path="achieveAim" rows="3" maxlength="2000"></form:textarea>
			</div>
		</div>
		<form:hidden path="fjId"/><!-- 项目附件ID -->
		<div class="form-group">
			<label for="projectIntroduce" class="col-sm-2 control-label">项目简介：</label>
			<div class="col-sm-8">
			<form:textarea style="resize:none" class="form-control" path="projectIntroduce" rows="3" ></form:textarea>
			</div>
		</div>
		<div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">上传项目相关资料：</label>
			<div class="col-sm-8" >
			<sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${projectApplication.files}"/>
		    </div>
	   </div>
		<div class="form-group" style="text-align: center;">
		<button id="btnSubmit" class="btn btn-primary" type="submit" ><shiro:hasPermission name="ty:projectApplication:edit">保&nbsp;存</shiro:hasPermission></button>
		<button id="btnCancel" class="btn" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
		</div>
		</form:form>	
	</div>
	</div>
</div>	
</body>
</html>