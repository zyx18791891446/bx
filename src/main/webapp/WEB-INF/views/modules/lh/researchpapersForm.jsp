<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty researchpapers.id?'修改':'添加'}科研论文" />
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<style>
		td{
			padding:10px;}
	</style>
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
		<form:form id="inputForm" modelAttribute="researchpapers"
					action="${ctx}/lh/researchpapers/save" method="post"
					class="form-horizontal">
					<form:hidden path="id" />
				<sys:message content="${message}" />
		<div class="form-item">	
			<table>
				<tbody>

					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 科研论文名称：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="name" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 作者：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="author" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
					</tr>
					<tr>
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 完成单位：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							 <form:input path="finishunit" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 科研论文类型：</td>
						<td class="form-smallestcell">
						<form:select path="type"  style="width:200px;height:30px;">
							<c:forEach items="${list }" var="researchpaperstype">
									
									<c:if test="${researchpaperstype.name eq researchpapers.type }">
										<option selected="selected" value="${researchpaperstype.name }" >${researchpaperstype.name }</option>
									</c:if>	
									<c:if test="${researchpaperstype.name != researchpapers.type }">
										<option value="${researchpaperstype.name }" >${researchpaperstype.name }</option>
									</c:if>	
									
								</c:forEach>
							</form:select>
						</td>
						
					</tr>
					<tr>
					
						
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 完成时间：</td>
						<td class="form-smallestcell">
						<input id="finishdate" name="finishdate"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${researchpapers.finishdate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
						</td>
						 
						<td class="form-label">上传相关资料：</td>
						<td class="form-smallestcell" colspan="3">
							<sys:fileupload id="files" maxSize="10" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${researchpapers.files}"/>
						</td>	
					</tr>
		
					<tr>
					 
						<td class="form-label">备注：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
					<tr>
					
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