<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>科研论文类型管理</title>
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
		<li class="active"><a href="${ctx}/lh/researchpaperstype/">科研论文类型列表</a></li>
		<shiro:hasPermission name="lh:researchpaperstype:edit"><li><a href="${ctx}/lh/researchpaperstype/form">科研论文类型添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="researchpaperstype" action="${ctx}/lh/researchpaperstype/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>name：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<shiro:hasPermission name="lh:researchpaperstype:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="researchpaperstype">
			<tr>
				<td><a href="${ctx}/lh/researchpaperstype/form?id=${researchpaperstype.id}">
					${researchpaperstype.name}
				</a></td>
				<shiro:hasPermission name="lh:researchpaperstype:edit"><td>
    				<a href="${ctx}/lh/researchpaperstype/form?id=${researchpaperstype.id}">修改</a>
					<a href="${ctx}/lh/researchpaperstype/delete?id=${researchpaperstype.id}" onclick="return confirmx('确认要删除该科研论文类型吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>