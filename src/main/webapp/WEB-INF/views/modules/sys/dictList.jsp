<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="字典管理" />
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
        function page(n,s){
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
	</script>
</head>
<body>
<div class="head">
	<div class="title">
		<h3>${title}</h3>
		<shiro:hasPermission name="sys:dict:edit"><span class="pull-right"><a class="btn btn-primary" href="${ctx}/sys/dict/form?sort=10">字典添加</a></span></shiro:hasPermission>
	</div>

	<form:form id="searchForm" modelAttribute="dict" action="${ctx}/sys/dict/" method="post" class="search form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>

		<div class="form-group">
			<label>类型：</label>
			<form:select id="type" path="type" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${typeList}" htmlEscape="false"/>
			</form:select>
		</div>
		&nbsp;&nbsp;&nbsp;
		<div class="form-group">
			<label>描述 ：</label>
			<form:input path="description" htmlEscape="false" maxlength="50" class="form-control input-medium"/>
		</div>
		&nbsp;&nbsp;&nbsp;

		<div class="form-group operate">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
</div>

<div class="content">
	<div class="body">

		<table id="contentTable" class="table">
			<thead>
			<tr>
				<th>键值</th>
				<th>标签</th>
				<th>类型</th>
				<th>描述</th>
				<th>排序</th>
				<shiro:hasPermission name="sys:dict:edit"><th width="200">操作</th></shiro:hasPermission>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${page.list}" var="dict">
				<tr>
					<td>${dict.value}</td>
					<td><a href="${ctx}/sys/dict/form?id=${dict.id}">${dict.label}</a></td>
					<td><a href="javascript:" onclick="$('#type').val('${dict.type}');$('#searchForm').submit();return false;">${dict.type}</a></td>
					<td>${dict.description}</td>
					<td>${dict.sort}</td>
					<shiro:hasPermission name="sys:dict:edit"><td>
						<a href="${ctx}/sys/dict/form?id=${dict.id}">修改</a>
						<a href="${ctx}/sys/dict/delete?id=${dict.id}&type=${dict.type}" onclick="return confirmx('确认要删除该字典吗？', this.href)">删除</a>
						<a href="<c:url value='${fns:getAdminPath()}/sys/dict/form?type=${dict.type}&sort=${dict.sort+10}'><c:param name='description' value='${dict.description}'/></c:url>">添加键值</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
			</tbody>
			<tfoot>
			<tr>
				<td colspan="100"  class="pagination-container">
					<div class="pagination">${page}</div>
				</td>
			</tr>
			</tfoot>
		</table>
	</div>
</div>
</body>
</html>