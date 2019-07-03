]<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title"  value="角色管理" />
<html>
<head>
	<title>${title}</title>
	<meta name="decorator" content="default"/>
</head>
<body>

<div class="head">
	<div class="title">
		<h3>${title}</h3>
		<shiro:hasPermission name="sys:role:edit"><span class="pull-right"><a class="btn btn-primary" href="${ctx}/sys/role/form">添加角色</a></span></shiro:hasPermission>
	</div>

	<sys:message content="${message}"/>
</div>
<div class="content">
	<div class="body">
		<table id="contentTable" class="table">
			<thead>
			<tr><th>角色名称</th><th>英文名称</th><th>归属机构</th><th>数据范围</th><shiro:hasPermission name="sys:role:edit"><th>操作</th></shiro:hasPermission></tr>
			</thead>
			<c:forEach items="${list}" var="role">
				<tr>
					<td>${role.name}</td>
					<td>${role.enname}</td>
					<td>${role.office.name}</td>
					<td>${fns:getDictLabel(role.dataScope, 'sys_data_scope', '无')}</td>
					<shiro:hasPermission name="sys:role:edit"><td>
						<a href="${ctx}/sys/role/assign?id=${role.id}">分配</a>
						<c:if test="${(role.sysData eq fns:getDictValue('是', 'yes_no', '1') && fns:getUser().admin)||!(role.sysData eq fns:getDictValue('是', 'yes_no', '1'))}">
							<a href="${ctx}/sys/role/form?id=${role.id}">修改</a>
						</c:if>
						<a href="${ctx}/sys/role/delete?id=${role.id}" onclick="return confirmx('确认要删除该角色吗？', this.href)">删除</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

</body>
</html>