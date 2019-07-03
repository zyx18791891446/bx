<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty orders.id?'修改':'添加'}选择工人" />
<html>
<head>
<title>${title }</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
	
		

		
		function getWorker(){
			var workerType = $("#workerType").val(); 
			var url = "${ctx}/sys/user/getWorker?workerType="+workerType;
			var parent = $("#worker");
			 $("#worker #workerID").remove();
			$("#worker #itemName").remove();
			$.getJSON(url,function(data){		
				$.each(data, function(i,item){
						var name = item.name;
						var span = $("<span id='itemName'>"+item.name+"</span>");
						var node = $("<input type='checkbox' id='workerID'  name='workerID'/>");
						node.attr({value:item.id});
						parent.append(node); 
						parent.append(span); 
						});
			});
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
			<form:form id="inputForm" modelAttribute="selectworker"
				action="${ctx}/bx/selectworker/save" method="post"
				class="form-horizontal">
				<form:hidden path="id" />
				<form:hidden path="repairId" />
				<sys:message content="${message}" />
				<div class="form-item">
					<div class="form-group">
						<label class="col-sm-2 control-label">工人类型:</label>
						<div class="col-sm-6 controls">
							<select id="workerType" class="input-xlarge">
								<c:forEach items="${allWorktype}" var="workType">
										<option value="${workType.id }">${workType.name }</option>
								</c:forEach>
								
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input  class="btn btn-primary" type="button" value="查询" onclick="getWorker()"/>
							
						</div>
					</div>
				</div>
				<div class="form-item">
					<div class="form-group">
						<label class="col-sm-2 control-label">工人:</label>
						<div id="worker" class="col-sm-6 controls">
							
						</div>
					</div>
				</div>
				
				<div class="action" style="text-align: center">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp; <input id="btnCancel" class="btn btn-default"
						type="button" value="返 回" onclick="history.go(-1)" />
				</div>
			</form:form>
		</div>

	</div>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/bx/selectworker/">选择工人列表</a></li>
		<li class="active"><a
			href="${ctx}/bx/selectworker/form?id=${selectworker.id}">选择工人<shiro:hasPermission
					name="bx:selectworker:edit">${not empty selectworker.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="bx:selectworker:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="selectworker"
		action="${ctx}/bx/selectworker/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<sys:message content="${message}" />
		<div class="control-group">
			<label class="control-label">：</label>
			<div class="controls">
				<form:input path="repairId" htmlEscape="false" maxlength="32"
					class="input-xlarge " />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">user_id：</label>
			<div class="controls">
				<sys:treeselect id="user" name="user.id"
					value="${selectworker.user.id}" labelName="user.name"
					labelValue="${selectworker.user.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="" allowClear="true"
					notAllowSelectParent="true" />
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="bx:selectworker:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form> --%>
</body>
</html>