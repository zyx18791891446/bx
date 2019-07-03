<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty orders.id?'修改':'添加'}评价维修质量" />

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
</head>
<body>
	<div class="head">
		<div class="title">
			<h3>${title}</h3>
		
		</div>
	</div>
	<div class="content">

		<div class="form-container">
		<form:form id="inputForm" modelAttribute="evaluate"
					action="${ctx}/bx/evaluate/save" method="post"
					class="form-horizontal">
					<form:hidden path="id" />
					<form:hidden path="repairId"/>
					<form:hidden path="user.id"/>
				<sys:message content="${message}" />
		<div class="form-item">	
			<table>
				<tbody>
					<tr>
						<td class="form-label">评价内容：</td>
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