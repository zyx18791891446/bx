<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="上传绩效" />
<html>
<head>
<title>上传绩效</title>
<meta name="decorator" content="default"/>
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
	<form:form id="inputForm" modelAttribute="oaAchievements" method="post" action="${ctx}/ty/oaAchievements/save"  class="form-horizontal">
		<form:hidden path="projectId"/>
			<div class="form-group">
			<label for="" class="col-sm-2 control-label">
			<span class="help-inline"><font color="red">*</font> </span>上传绩效：</label>
			<div class="col-sm-8">
			<form:textarea style="resize:none" class="form-control required" path="content" rows="3" ></form:textarea>
			</div>
	   </div>
	    <div class="form-group">
			<label for="remarks" class="col-sm-2 control-label">绩效文件：</label>
			<div class="col-sm-8" style="width: 20px">
			<sys:fileupload id="achievementsFiles" input="achievementsFiles" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true"  />
		    </div>
	   </div> 
		   <div class="form-group" style="text-align: center;">
			    <%-- <shiro:hasPermission name="ty:oaAchievements:edit"> --%>
			    <button class="btn btn-primary" type="submit" >提&nbsp;交</button><%-- </shiro:hasPermission> --%>
			    <button class="btn" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
		   </div>
	</form:form>
	</div>
</div>
</div>	
</body>
</html>