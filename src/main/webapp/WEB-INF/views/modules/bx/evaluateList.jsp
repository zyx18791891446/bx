<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="评价维修质量" />
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
	<style type="text/css">
		/**长名称隐藏尾部*/
		.jsunit {
			max-width: 100px;
			white-space: nowrap;
			overflow: hidden;
			text-overflow: ellipsis;
		}
	</style>
</head>
<body>
	<div class="head" style="height: 160px;">
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
				<th class="jsunit">故障名称</th>
				<th class="jsunit">维修人</th>
				<th class="jsunit">宿舍号</th>
				<th class="jsunit">创建时间</th>
				<th class="jsunit">修改时间</th>
				<th class="jsunit">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${list}" var="orders" varStatus="status">
			<tr>
				<td class="jsunit">${status.index+1 }</td>
				<td class="jsunit">${orders.username }</td>
				<td class="jsunit">${orders.repairMan }</td>
				<td class="jsunit">${orders.roomNum }</td>
				<td class="jsunit">
					<fmt:formatDate value="${orders.createDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td class="jsunit">
					<fmt:formatDate value="${orders.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				
						<a href="${ctx}/bx/evaluate/form?repairId=${orders.id}&user.id=${orders.repairManId}">新增评价</a>
			 
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