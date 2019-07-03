<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科研项目类型管理</title>
	<meta name="decorator" content="default"/>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/lh/researchprojecttype/">科研项目类型列表</a></li>
		<shiro:hasPermission name="lh:researchprojecttype:edit"><li><a href="${ctx}/lh/researchprojecttype/form">科研项目类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="researchprojecttype" action="${ctx}/lh/researchprojecttype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>name</th>
				<shiro:hasPermission name="lh:researchprojecttype:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="researchprojecttype">
			<tr>
				<td><a href="${ctx}/lh/researchprojecttype/form?id=${researchprojecttype.id}">
					${researchprojecttype.name}
				</a></td>
				<shiro:hasPermission name="lh:researchprojecttype:edit"><td>
    				<a href="${ctx}/lh/researchprojecttype/form?id=${researchprojecttype.id}">修改</a>
					<a href="${ctx}/lh/researchprojecttype/delete?id=${researchprojecttype.id}" onclick="return confirmx('确认要删除该科研项目类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>