<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="维修订单详情" />
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
			$('.delete').hide(); 
			$('.file-btn-wj').hide();
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
  <div class="page-title"><h5><strong>维修订单基本信息</strong></h5></div>
		<div class="form-container">
		<form:form id="inputForm" modelAttribute="orders"
					action="${ctx}/bx/orders/inspection" method="post"
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
							<form:input path="username" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
						<td class="form-label">
						<span  class="help-inline">
						<font color="red">*</font>
						</span> 地址：
						</td>
						<td class="form-smallestcell">
							<div class="control">
							<form:input path="addres" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
					</tr>
					
					<tr>
						<td class="form-label"><span class="help-inline""><font color="red">*</font></span> 宿舍号：</td>
						<td class="form-smallestcell">
							<div class="control">
							<form:input path="roomNum" htmlEscape="false" maxlength="64" class="form-control" disabled="true" style="border:0" color="white"/>
							</div>
						</td>
						
						<td class="form-label">相关资料：</td>
						<td class="form-smallestcell" colspan="3">
							<sys:fileupload id="files" input="files" type="files" maxSize="10" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${orders.files}"/>
						</td>	
					</tr>
					<tr>
					<td class="form-label"><span class="help-inline"><font color="red">*</font>上传时间</span> </td>
						<td class="form-smallestcell">
						<input  name="createDate"
						type="text" 
						disabled="disabled"
						value="<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd"/>"
						class="form-control" style="border: 0;"  
						 />
						 
						 <td class="form-label"><span class="help-inline"><font color="red">*</font>修改时间</span> </td>
						<td class="form-smallestcell">
						<input  name="updateDate"
						type="text" 
						disabled="disabled"
						value="<fmt:formatDate value="${orders.updateDate}" pattern="yyyy-MM-dd"/>"
						class="form-control" style="border: 0;"  
						 />
					
					
					<tr>
						<td class="form-label">内容：</td>
						<td class="form-smallestcell" colspan="3">
							<form:textarea style="resize:none" path="content" htmlEscape="false" rows="4" maxlength="255" class="form-control" disabled="true"  color="white"/>
						</td>
					</tr>
				 </tbody>
				</table>
			  </div>
			 
			</form:form>
			</div>	
				
		</div>

  <div class="content">
      <div class="page-title"><h5><strong>维修进度信息</strong></h5></div>
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
		               <th class="col-sm-3">日期</th>
		               <th class="col-sm-2">状态</th>
	                </tr>
		          </thead>
		          <tbody>
		            <c:forEach items="${list}" var="orderState">
		             <tr>
		             <!-- <td class="SortCLASS"></td> -->
		          	  <fmt:formatDate value="${orderState.datetimes }" pattern="yyyy-MM-dd HH:mm:ss" var="formatDate"/>
		          	  <td class="td">${formatDate }</td>
		        	  <td class="td">${fns:getDictLabel(orderState.status,'roomfixStatus','') }</td>
			          
			          
		            </tr>
		          </c:forEach>
		         </tbody>
		     </table>
		   </div>
	    </div>
	  </form:form>
	</div>
  </div>
  		<div class="content">
      <div class="page-title"><h5><strong>维修评价信息</strong></h5></div>
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
		               <th class="col-sm-2">维修人</th>
		               <th class="col-sm-3">评价人</th>
		               <th class="col-sm-3">评价内容</th>
		               <th class="col-sm-3">评价日期</th>
	                </tr>
		          </thead>
		          <tbody>
		            <c:forEach items="${evaluateList}" var="evaluate">
		             <tr>
		            <td class="td">${evaluate.repairMan }</td>
		            <td class="td">${evaluate.evulateMan }</td>
		            <td class="td">${evaluate.content }</td>
		          	  <fmt:formatDate value="${evaluate.createDate }" pattern="yyyy-MM-dd HH:mm:ss" var="formatDate"/>
		          	  <td class="td">${formatDate }</td>
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
  
   <div class="action" style="text-align:center">
		<input id="btnCancel" class="btn btn-default" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</div>
	

</body>
</html>