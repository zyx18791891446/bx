<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty patent.id?'修改':'添加'}专利信息" />
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
					action="${ctx}/lh/patent/save" method="post"
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
								<form:input path="name" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 专利号：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="patentnum" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
					</tr>
					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 发明人：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="inventor" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 完成单位：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="publishunit" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
					</tr>
					<tr>
					<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 授权类型：</td>
						<td class="form-smallestcell">
							<form:select path="type"  style="width:200px;height:30px;" >
								<c:forEach items="${patentTypes }" var="patentType">
									<c:if test="${patentType.name eq patent.type }">
										<option selected="selected" value="${patentType.name }" >${patentType.name }</option>
									</c:if>	
									<c:if test="${patentType.name != patent.type }">
										<option value="${patentType.name }" >${patentType.name }</option>
									</c:if>	
									
								</c:forEach>
						</form:select>
						</td>
						
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 完成时间：</td>
						<td class="form-smallestcell">
						<input id="beginDate" name="publishdate"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${patent.publishdate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
						</td>
						
					</tr>
	
					<tr>
						<td class="form-label">备注：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
					<tr>
					<td class="form-label">上传相关资料：</td>
						<td class="form-smallestcell" colspan="3">
							<sys:fileupload id="files" input="files" type="files" maxSize="10" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${patent.files}"/>
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
	</div>
</body>
</html>




