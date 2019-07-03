<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title"  value="用户列表"/>
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<style>
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
	<style>
	</style>
</head>
<body>
<div class="head">
	<div class="title">
		<h3>${title}</h3>
		<%-- <%-- <shiro:hasPermission name="ty:expert:edit">
			<span  class="pull-right"><a class="btn btn-primary" href="${ctx}/ty/expert/form">教师人员添加</a></span>
		</shiro:hasPermission> --%> 
	</div>
	<form:form id="searchForm" modelAttribute="expert" action="${ctx}/ty/expert/" method="post" class="search form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<div class="form-group">
			<label class="control-label">姓名：</label>
			<form:input path="expertname" htmlEscape="false" maxlength="32"
						class="form-control required" />
		</div>
		<div class="form-group">
			<label class="control-label">专业：</label>
			<form:input path="specialty" htmlEscape="false" maxlength="32"
						class="form-control required" />
		</div>
		<div class="form-group operate">
			<input  style="margin-left:90px" id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
</div>
<div class="content">
<div class="body">
<div class="row">
<c:forEach items="${page.list}" var="list">
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail" style="text-align:center">
    <a href="${ctx}/ty/expert/get?id=${list.id}">
    	<c:if test="${!empty list.photo }">
    		  <img style="width:140px;height:140px" src="${list.photo }">
    	</c:if>
    	<c:if test="${empty list.photo }">
    		  <img style="width:140px;height:140px" src="/static/images/touxiang.png">
    	</c:if>
      </a>
      <div class="caption">
        <h5>${list.expertname}</h5>
        <p>
        	<a href="${ctx}/ty/expert/form?id=${list.id}" class="btn btn-primary" role="button">修改</a>
         	<a href="${ctx}/ty/expert/delete?id=${list.id}" class="btn btn-default" role="button">删除</a>
         </p>
      </div>
    </div>
  </div>
  </c:forEach>
</div>
<div class="pagination">${page}</div>
</div>
</div>
</body>
</html>