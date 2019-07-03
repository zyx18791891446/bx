<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty dict.id?'修改':'添加'}字典" />
<html>
<head>
	<title>${title}</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#value").focus();
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
		<span class="pull-right"><button class="btn btn-default"  onclick="history.go(-1)">返回</button></span>
	</div>
</div>
<div class="content">

	<div class="form-container">

		<form:form id="inputForm" modelAttribute="dict" action="${ctx}/sys/dict/save" method="post" class="form-horizontal">
			<form:hidden path="id"/>
			<sys:message content="${message}"/>
			<div class="form-group">
				<label class="col-sm-2 control-label">键值:</label>
				<div class="col-sm-6 controls">
					<form:input path="value" htmlEscape="false" maxlength="50" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">标签:</label>
				<div class="col-sm-6 controls">
					<form:input path="label" htmlEscape="false" maxlength="50" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">类型:</label>
				<div class="col-sm-6 controls">
					<form:input path="type" htmlEscape="false" maxlength="50" class="form-control required abc"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">描述:</label>
				<div class="col-sm-6 controls">
					<form:input path="description" htmlEscape="false" maxlength="50" class="form-control required"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">排序:</label>
				<div class="col-sm-6 controls">
					<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control required digits"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">备注:</label>
				<div class="col-sm-6 controls">
					<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control input-xlarge"/>
				</div>
			</div>
			<div class="action">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<shiro:hasPermission name="sys:dict:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
						<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
</body>
</html>