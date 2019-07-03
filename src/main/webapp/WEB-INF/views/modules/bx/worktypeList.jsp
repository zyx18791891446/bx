<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="工人类型信息" />
<html>
<head>
	<title>${title }</title>
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
	<div class="head" >
		<div class="title">
			<h3>${title}</h3>
			
		</div>
	</div>
	<sys:message content="${message}" />
	<div class="content">
		<div class="body">
		 <table id="contentTable" class="table  table-striped">
			
		<thead>
			<tr>
				<th class="jsunit">序号</th>
				<th class="jsunit">名称</th>
				<th class="jsunit">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="worktype" varStatus="status">
			<tr>
				<td class="jsunit">${status.index+1 }</td>
					<td class="jsunit">${worktype.name }</td>
				<td>
				<!--  -->
					<!-- 未上传的和审核不通过的 可以修改和删除 -->
    					<a href="${ctx}/bx/worktype/form?id=${worktype.id}">修改</a>
						<a href="${ctx}/bx/worktype/delete?id=${worktype.id}" onclick="return confirmx('确认要删除该专利吗？', this.href)">删除</a>
				
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
	<div class="pagination">${page}</div>
	
</body>
</html>