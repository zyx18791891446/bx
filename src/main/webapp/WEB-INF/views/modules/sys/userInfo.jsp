<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="个人信息" />
<html>
<head>
<title>${title}</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
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
</script>
</head>
<body>
	<div class="head">
		<div class="title">
			<h3>${title}</h3>
		</div>

	</div>
	<div class="content" style="height: 580px;">
		<div class="form-content">
			<form:form id="inputForm" modelAttribute="user"
				action="${ctx}/sys/user/info" method="post" class="form-horizontal">
				<form:hidden path="id" />
				<sys:message content="${message}" />

				<div class="form-item">
					<!-- 头像 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">头像：</label>
						<div class=" col-sm-3">
							<sys:imageupload id="1" input="photo" type="image"
								savePath="avatar" uploadPath="${ctx}/file" value="${user.photo}" />
						</div>
					</div>
					<!-- 第二行 -->
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">姓名：</label>
						<div class="control col-sm-3">
							<form:input type="text" path="name" disabled="true"
								class="form-control input-xlarge required" color="white"
								value="" />
				
						</div>
						<label for="" class="col-sm-2 control-label">电话：</label>
						<div class=" control col-sm-3">
							<form:input type="text" path="phone"
								class="form-control input-xlarge required" color="white"
								value="" />
						</div>
					</div>
					<!-- 第三行 -->
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">邮箱：</label>
						<div class="col-sm-3">
							<form:input type="text" path="email"
								class="form-control input-xlarge required" color="white"
								value="" />
						</div>
						<label for="" class="col-sm-2 control-label">归属部门：</label>
						<div class="col-sm-3">
							<input type="text" path=""
								class="form-control input-xlarge required" color="white"
								disabled="true" value="${user.office.name}" />
						</div>
					</div>
					<!-- 第四行 -->
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">手机：</label>
						<div class=" col-sm-3">
							<form:input type="text" path="mobile"
								class="form-control input-xlarge required" color="white"
								value="" />
						</div>
						<label for="" class="col-sm-2 control-label">用户类型：</label>
						<div class=" col-sm-3">
							<form:input type="text" path=""
								class="form-control input-xlarge required" color="white"
								disabled="true" value="${fns:getDictLabel(user.userType, 'sys_user_type', '无')}" />
						</div>
					</div>
					<!-- 以下注掉的是长框 -->
					<%-- <div class="form-group">
						<label class="col-sm-2 control-label">手机：</label>
						<div class="col-sm-8">
							<form:input type="text" path="mobile"
								class="form-control"  style="border:0"
								color="white" value=""/>
						</div>
					</div> --%>
					<!-- 第五行 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">用户角色：</label>
						<div class="col-sm-8">
							<form:input type="text" path=""
								class="form-control input-xlarge required" color="white"
								disabled="true" value="${user.roleNames}" />
						</div>
					</div>
					<!-- 第六行 -->
					<div class="form-group">
						<label class="col-sm-2 control-label">备注信息：</label>
						<div class="col-sm-8">
							<form:textarea path="remarks" rows="3"
								class="form-control input-xlarge required"></form:textarea>
						</div>
					</div>
					<div class="col-sm-10">
						<div class="col-sm-2">
						</div>						
						<div class="col-sm-8">
						&nbsp;&nbsp;&nbsp;&nbsp;上次登录： 	
								<label class="lbl" style="font-weight: normal">IP:
									${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate
										value="${user.oldLoginDate}" type="both" dateStyle="full" />
								</label>
						</div>	
					</div>


				</div>
				<div class="action col-sm-12">
					<div class="col-sm-5"></div>
					<div class="col-sm-7">
						<%-- <shiro:hasPermission name="oa:notify:announce:add"> --%>
						<input id="btnSubmit" class="btn btn-primary" type="submit"
							value="保 存" />&nbsp;
						<%-- </shiro:hasPermission> --%>
					</div>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>