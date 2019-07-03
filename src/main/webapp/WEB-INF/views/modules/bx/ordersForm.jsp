<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty orders.id?'修改':'添加'}维修订单" />
<html>
<head>
	<title>${title }</title>
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
	
<style>
td{
padding:10px;}
</style>
	
</head>
<body>
	<div class="head">
		<div class="title">
			<h3>${title}</h3>
		
		</div>
	</div>
	<div class="content">

		<div class="form-container">
		<form:form id="inputForm" modelAttribute="orders"
					action="${ctx}/bx/orders/save" method="post"
					class="form-horizontal">
					<form:hidden path="id" />
				<sys:message content="${message}" />
		<div class="form-item">	
			<table>
				<tbody>

					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 故障名称：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="username" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 地址：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="addres" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
					</tr>
					
					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 宿舍号：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="roomNum" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">上传相关资料：</td>
						<td class="form-smallestcell" colspan="3">
							<sys:fileupload id="files" input="files" type="files" maxSize="10" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${orders.files}"/>
						</td>	
					</tr>
					<tr>
						<td class="form-label">内容：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="content" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
				 </tbody>
				</table>
			  </div>
			  <div class="action" style="text-align:center">
					
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
					
					<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
			</form:form>
			</div>	
				
		</div>
	

</body>
</html>