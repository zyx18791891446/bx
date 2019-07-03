<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="审核专利信息" />
<html>
<head>
<style>
td{
padding:10px;}
</style>
<title>${title}</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm").validate(
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
														".input-appensd")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
				$('.delete').hide(); 
				$('.file-btn-wj').hide();
			});
</script>
<!-- 绑定年龄的操作 -->
</head>
<body>
	<div class="head">
		<div class="title">
			<h3>${title}</h3>
		
		</div>
	</div>
	<div class="content">

		<div class="form-container">
		<form:form id="inputForm" modelAttribute="patent"
					action="${ctx}/lh/patent/inspection" method="post"
					class="form-horizontal">
					<form:hidden path="id" />
				<sys:message content="${message}" />
		<div class="form-item">	
			<table>
				<tbody>

					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 发明名称：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="name" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 专利号：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="patentnum" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
					</tr>
					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 发明人：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="inventor" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 完成单位：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="publishunit" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
					</tr>
					<tr>
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 授权类型：</td>
						<td class="form-smallestcell">
						<form:input path="type" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
						</td>
						
						<td class="form-label"><span class="help-inline"><font color="red">*</font>完成时间</span> </td>
						<td class="form-smallestcell">
						<input id="beginDate" name="publishdate"
						type="text" 
						disabled="disabled"
						value="<fmt:formatDate value="${patent.publishdate}" pattern="yyyy-MM-dd"/>"
						class="form-control" style="border: 0;"  
						 />
						
						</td>
						
					</tr>
	
					<tr>
						<td class="form-label">备注：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control" disabled="true"  color="white"/>
						</td>
					</tr>
					<tr>
					<td class="form-label"> 相关资料：</td>
						<td class="form-smallestcell">
							<div class="control">
								<sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${patent.files}"/>
							</div>
						</td>
					<td class="form-label"> 审核：</td>
						<td class="form-smallestcell">
							<div class="control">
								<select name="status"  >
								<option value="2">通过</option>
								<option value="3">不通过</option>
							</select>
							</div>
						</td>
					</tr>
					<tr>
						<td class="form-label">审核意见：</td>
						<td class="form-smallestcell" colspan="3">
							<textarea  name="reason"  rows="4" maxlength="255" class="form-control"></textarea>
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




