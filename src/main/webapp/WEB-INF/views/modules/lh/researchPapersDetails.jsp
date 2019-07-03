<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="科研论文" />
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
			$('.delete').hide(); 
			$('.file-btn-wj').hide();
		});
	</script>
</head>
<body>
	<div class="head">
		<div class="title">
			<h3>${title}</h3>
			<div class="form-group" style="text-align:right;">
		<button class="btn btn-primary" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
		</div>
		</div>
	</div>
	<div class="content">

		<div class="form-container">
		<form:form id="inputForm" modelAttribute="researchpapers"
					action="" method="post"
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
								<form:input path="name" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 作者：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							<form:input path="author" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
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
							<form:input path="finishunit" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 科研论文类型：</td>
						<td class="form-smallestcell">
							<form:input path="type" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
						
						</td>
						
					</tr>
					<tr>
					
						
						<td class="form-label"><span class="help-inline"><font color="red">*</font></span> 完成时间：</td>
						<td class="form-smallestcell">
						<input id="finishdate" name="finishdate" disabled="disabled"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${researchpapers.finishdate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
						</td>
						
						<td class="form-label">上传相关资料：</td>
						<td class="form-smallestcell" colspan="3">
							<sys:fileupload id="files" input="files" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${researchpapers.files}"/>
						</td>	
					</tr>
	
					<tr>
						<td class="form-label">备注：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control" disabled="true"  color="white"/>
						</td>
					</tr>
					<tr>
					
				</tbody>
				</table>
			  </div>
			</form:form>
		</div>
		</div>
		<div class="content">
      <div class="page-title"><h5><strong>科研论文审核信息</strong></h5></div>
  <div class="form-container">
	 <div class="form-horizontal">
	    <form:form id="inputForm" modelAttribute="projectApplication" method="post" action=""  class="form-horizontal">
	      <div class="form-group">
		  <div class="col-sm-1"></div>
		    <div class="col-sm-9">
		<!-- 表格固定 -->
	           <table style="table-layout: fixed;" id="contentTable" class="table table-striped table-bordered table-condensed">
		          <thead>
		             <tr>
		               <th class="col-sm-3">初审日期</th>
		               <th class="col-sm-2">初审状态</th>
		               <th class="col-sm-2">初审意见</th>
	                </tr>
		          </thead>
		          <tbody>
		            <c:forEach items="${inspectionList}" var="inspection">
		             <tr>
		             <!-- <td class="SortCLASS"></td> -->
		          	  <fmt:formatDate value="${inspection.inspectionDate }" pattern="yyyy-MM-dd HH:mm" var="formatDate"/>
		          	  <td class="td">${formatDate }</td>
		        	  <td class="td">${fns:getDictLabel(inspection.statusDd,'researchStatus','') }</td>
			          
			          <td class="td">${inspection.reason }</td>
			          
		            </tr>
		          </c:forEach>
		         </tbody>
		     </table>
		   </div>
	    </div>
	  </form:form>
	</div>
  </div>
</div>	
</body>
</html>