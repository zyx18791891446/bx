<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="菜单管理" />
<html>
<head>
	<title>${title}</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#treeTable").treeTable({expandLevel : 3}).show();
        });
        function updateSort() {
            loading('正在提交，请稍等...');
            $("#listForm").attr("action", "${ctx}/sys/menu/updateSort");
            $("#listForm").submit();
        }
	</script>
</head>
<body>


<div class="head">
	<div class="title">
		<h3>${title}</h3>
		<shiro:hasPermission name="sys:menu:edit"><span class="pull-right"><a class="btn btn-primary" href="${ctx}/sys/menu/form">添加菜单</a></span></shiro:hasPermission>
	</div>
	<sys:message content="${message}"/>
</div>
<div class="content">
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-hover">
			<thead>
			<tr>
				<th>名称</th>
				<th>链接</th>
				<th style="text-align:center;">排序</th>
				<th>可见</th>
				<th>权限标识</th>
				<shiro:hasPermission name="sys:menu:edit">
					<th width="200">操作</th>
				</shiro:hasPermission>
			</tr>
			</thead>
			<tbody><c:forEach items="${list}" var="menu">
				<tr id="${menu.id}" pId="${menu.parent.id ne '1'?menu.parent.id:'0'}">
					<td nowrap><i class="icon-${not empty menu.icon?menu.icon:' hide'}"></i> <a href="${ctx}/sys/menu/form?id=${menu.id}">${menu.name}</a></td>
					<td title="${menu.href}">${fns:abbr(menu.href,30)}</td>
					<td style="text-align:center;">
						<shiro:hasPermission name="sys:menu:edit">
							<input type="hidden" name="ids" value="${menu.id}"/>
							<input name="sorts" type="text" value="${menu.sort}" style="width:50px;margin:0;padding:0;text-align:center;">
						</shiro:hasPermission><shiro:lacksPermission name="sys:menu:edit">
						${menu.sort}
					</shiro:lacksPermission>
					</td>

					<td>${menu.isShow eq '1'?'显示':'隐藏'}</td>
					<td title="${menu.permission}">${fns:abbr(menu.permission,30)}</td>
					<shiro:hasPermission name="sys:menu:edit"><td nowrap>
						<a href="${ctx}/sys/menu/form?id=${menu.id}">修改</a>
						<a href="${ctx}/sys/menu/delete?id=${menu.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">删除</a>
						<a href="${ctx}/sys/menu/form?parent.id=${menu.id}">添加下级菜单</a>
					</td></shiro:hasPermission>
				</tr>
			</c:forEach></tbody>

			<tfoot>
			<tr>
				<td colspan="100">
					<shiro:hasPermission name="sys:menu:edit">
						<div class="form-actions pagination-left">
							<input id="btnSubmit" class="btn btn-primary" type="button" value="保存排序" onclick="updateSort();"/>
						</div>
					</shiro:hasPermission>
				</td>
			</tr>
			</tfoot>
		</table>

	</form>
</div>
</body>
</html>