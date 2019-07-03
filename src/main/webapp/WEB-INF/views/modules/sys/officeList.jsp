<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="机构列表" />
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
        $(document).ready(function() {
            var tpl = $("#treeTableTpl").html().replace(/(\/\/<!--)|(\/\/-->)/g,"");
            var data = ${fns:toJson(list)}, rootId = "${not empty office.id ? office.id : pid}";
            //console.log(rootId);
            addRow("#treeTableList", tpl, data, rootId, true);
            $("#treeTable").treeTable({expandLevel : 5});

        });
        function addRow(list, tpl, data, pid, root){
            for (var i=0; i<data.length; i++){
                var row = data[i];
                if ((${fns:jsGetVal('row.parentId')}) === pid){
                    $(list).append(Mustache.render(tpl, {
                        dict: {
                            type: getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type)
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
		<shiro:hasPermission name="sys:office:edit"><span class="pull-right"><a class="btn btn-primary" href="${ctx}/sys/office/form?parent.id=${office.id}">机构添加</a></span></shiro:hasPermission>
	</div>

	<sys:message content="${message}"/>
</div>

<div class="content">
	<div class="body">
		<table id="treeTable" class="table">
			<thead>
			<tr>
				<th>机构名称</th>
				<th>归属区域</th>
				<th>机构编码</th>
				<th>机构类型</th>
				<th>备注</th>
				<shiro:hasPermission name="sys:office:edit"><th width="200">操作</th></shiro:hasPermission>
			</tr>
			</thead>
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
				<td><a href="${ctx}/sys/office/form?id={{row.id}}">{{row.name}}</a></td>
				<td>{{row.area.name}}</td>
				<td>{{row.code}}</td>
				<td>{{dict.type}}</td>
				<td>{{row.remarks}}</td>
				<shiro:hasPermission name="sys:office:edit"><td>
					<a href="${ctx}/sys/office/form?id={{row.id}}">修改</a>
					<a href="${ctx}/sys/office/delete?id={{row.id}}" onclick="return confirmx('要删除该机构及所有子机构项吗？', this.href)">删除</a>
					<a href="${ctx}/sys/office/form?parent.id={{row.id}}">添加下级机构</a>
				</td></shiro:hasPermission>
			</tr>
		</script>
	</div>
</div>
</body>
</html>