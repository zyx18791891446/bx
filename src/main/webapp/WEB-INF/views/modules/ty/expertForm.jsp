<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="${not empty expert.id?'修改':'添加'}用户人员" />
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
			<form:form id="inputForm" modelAttribute="expert"
				action="${ctx}/ty/expert/save" method="post" class="form-horizontal">
				<form:hidden path="id" />
				<sys:message content="${message}" />
		<div class="form-item">	
			<table>
				<tbody>
					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 姓名：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:input path="expertname" htmlEscape="false" maxlength="64" class="form-control input-xlarge required"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 专业：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							  <input type="text"  value="${expert.specialty }" class="form-control" disabled="disabled" style="border:0" color="white"/>
								
							</div>
						</td>
						<td rowspan="3" width="20%" style="text-align: center;">
							<sys:imageupload id="1" input="photo" type="image" savePath="management" uploadPath="${ctx}/file" selectMultiple="false" value="${expert.photo}"/>
							<div style="width:80%;margin:5px 0px 0px 30px">大小不要超过500K
							建议使用1寸证件照170*100像素</div>
						</td>
					</tr>
					<tr>
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 性别：</td>
						<td class="form-smallestcell">
							<div class="control">
								<form:radiobuttons path="sex" items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" cssClass="required" cssStyle="margin-right: 5px;margin-left: 80px;"/>
							</div>
						</td>
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 年龄：</td>
						<td class="form-smallestcell">
							<form:select path="age"  style="width:200px;height:30px;" >
								<c:forEach var="i" begin="20" end="100" >
									<option value="${i }" >${i }</option>
								</c:forEach>
						</form:select>
						</td>
					</tr>
					<tr>
						<td class="form-label">个人介绍：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="content" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
					<tr>
						<td class="form-label">研究成果：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="researchresult" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
					<tr>
						<td class="form-label">备注：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control  input-xxlarge "/>
						</td>
					</tr>
				</tbody>
				</table>
			</div>	
				<div class="action" style="text-align:center">
					<shiro:hasPermission name="ty:expert:edit">
						<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
					</shiro:hasPermission>
					<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>