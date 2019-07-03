<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="修改密码" />
<html>
<head>
<title>修改密码</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				$("#oldPassword").focus();
				$("#inputForm")
						.validate(
								{
									rules : {},
									messages : {
										confirmNewPassword : {
											equalTo : "输入与上面相同的密码"
										}
									},
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
	<div class="content" style="height: 210px;">
		<div class="form-content">
			<form:form id="inputForm" modelAttribute="user"
				action="${ctx}/sys/user/modifyPwd" method="post"
				class="form-horizontal">
				<form:hidden path="id" />
				<sys:message content="${message}" />
				<div class="form-item">
					<div class="col-sm-4"></div>
					<div class="col-sm-6">
						<table>
							<tbody>
								<tr>
									<td class="form-label"><span class="required" style="color: red">*</span>
										旧密码：</td>
									<td class="form-smallcell">
										<div class="control">
											<input id="oldPassword" name="oldPassword" type="password"
												value="" maxlength="50" minlength="3"
												class="form-control input-xlarge required" />
										</div>
									</td>
								</tr>
								<tr>
									<td class="form-label"><span class="required" style="color: red">*</span>
										新密码：</td>
									<td class="form-smallcell">
										<div class="control">
											<input id="newPassword" name="newPassword" type="password"
												value="" maxlength="50" minlength="3"
												class="form-control input-xlarge required" />
										</div>
									</td>
								</tr>
								<tr>
									<td class="form-label"><span class="required" style="color: red">*</span>
										确认新密码：</td>
									<td class="form-smallcell">
										<div class="control">
											<input id="confirmNewPassword" name="confirmNewPassword"
												type="password" value="" maxlength="50" minlength="3"
												class="form-control input-xlarge required"
												equalTo="#newPassword" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<%-- <div class="action">
					<shiro:hasPermission name="oa:notify:announce:add">
						<input id="btnSubmit" class="btn btn-primary" type="submit"
							value="保 存" />&nbsp;</shiro:hasPermission>

				</div> --%>
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