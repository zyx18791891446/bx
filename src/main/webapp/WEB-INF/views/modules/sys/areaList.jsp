<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="区域管理" />
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
        $(document).ready(function() {
            var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
            var data = ${fns:toJson(list)}, rootId = "0";
            addRow("#treeTableList", tpl, data, rootId, true);
            $("#treeTable").treeTable({expandLevel : 5});
        });
        function addRow(list, tpl, data, pid, root){
            for (var i=0; i<data.length; i++){
                var row = data[i];
                if ((${fns:jsGetVal('row.parentId')}) == pid){
                    $(list).append(Mustache.render(tpl, {
                        dict: {
                            type: getDictLabel(${fns:toJson(fns:getDictList('sys_area_type'))}, row.type)
                        }, pid: (root?0:pid), row: row
                    }));
                    addRow(list, tpl, data, row.id);
                }
            }
        }
	</script>
</head>
<body>
<div class="head">

	<div class="title">
		<h3>${title}</h3>
		<shiro:hasPermission name="sys:area:edit"><span class="pull-right"><a class="btn btn-primary" href="${ctx}/sys/area/form">区域添加</a></span></shiro:hasPermission>
	</div>
	<sys:message content="${message}"/>
</div>

<div class="content">
	<div class="body">
		<table id="treeTable" class="table">
			<thead><tr><th>区域名称</th><th>区域编码</th><th>区域类型</th><th>备注</th><shiro:hasPermission name="sys:area:edit"><th width="200">操作</th></shiro:hasPermission></tr></thead>
			<tbody id="treeTableList"></tbody>
			<tfoot>
			<tr>
				<td colspan="100"  class="pagination-container">
					<div class="pagination">${page}</div>
				</td>
			</tr>
			</tfoot>
		</table>
		<script type="text/template" id="treeTableTpl">
			<tr id="{{row.id}}" pId="{{pid}}">
				<td><a href="${ctx}/sys/area/form?id={{row.id}}">{{row.name}}</a></td>
				<td>{{row.code}}</td>
				<td>{{dict.type}}</td>
				<td>{{row.remarks}}</td>
				<shiro:hasPermission name="sys:area:edit"><td>
					<a href="${ctx}/sys/area/form?id={{row.id}}">修改</a>
					<a href="${ctx}/sys/area/delete?id={{row.id}}" onclick="return confirmx('要删除该区域及所有子区域项吗？', this.href)">删除</a>
					<a href="${ctx}/sys/area/form?parent.id={{row.id}}">添加下级区域</a>
				</td></shiro:hasPermission>
			</tr>
		</script>
	</div>
</div>

</body>
</html>