<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="维修订单信息" />
<html>
<head>
	<title>${title }</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document)
	.ready(
			function() {
				$("#btnExport")
						.click(
								function() {
									top.$.jBox
											.confirm(
													"确认要导出数据吗？",
													"系统提示",
													function(v, h, f) {
														if (v == "ok") {
															$(
																	"#searchForm")
																	.attr(
																			"action",
																			"${ctx}/bx/orders/export");
															$(
																	"#searchForm")
																	.submit();
														}
													},
													{
														buttonsFocus : 1
													});
									top.$('.jbox-body .jbox-icon').css(
											'top', '55px');
								});
			});
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
		<form:form id="searchForm" modelAttribute="orders"
			action="${ctx}/bx/orders/" method="post"
			class="search form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<div class="col-sm-12">
				<div class="col-sm-10">
					<div class="form-group" style="float: left;">
						<label class=" control-label">故障名称</label>
						<form:input path="username" htmlEscape="false"
							maxlength="32" class="form-control required" />
					</div>
					<label>开始时间:</label> <input id="createDate" name="createDate"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${orders.createDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
						
						<label>结束时间:</label> <input id="updateDate" name="updateDate"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${orders.updateDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
				</div>
				<button type="submit" class="btn btn-primary">查询</button>
					<input id="btnExport" class="btn btn-primary" type="button"
						value="导出" />
			</div>
		</form:form>
	</div>
	<sys:message content="${message}" />
	<div class="content">
		<div class="body">
		 <table id="contentTable" class="table  table-striped">
			
		<thead>
			<tr>
				<th class="jsunit">序号</th>
				<th class="jsunit">故障名称</th>
				<th class="jsunit">状态</th>
				<th class="jsunit">宿舍号</th>
				<th class="jsunit">创建时间</th>
				<th class="jsunit">修改时间</th>
				<th class="jsunit">操作</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${page.list}" var="orders" varStatus="status">
			<tr>
				<td class="jsunit">${status.index+1 }</td>
				<td class="jsunit">${orders.username }</td>
				<td class="jsunit">
				${fns:getDictLabel(orders.status,'roomfixStatus','') }
				</td>
				<td class="jsunit">${orders.roomNum }</td>
				<td class="jsunit">
					<fmt:formatDate value="${orders.createDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td class="jsunit">
					<fmt:formatDate value="${orders.updateDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
				<!--  -->
				<shiro:hasPermission name="bx:orders:edit">
					<!-- 未上传的和审核不通过的 可以修改和删除 -->
					<c:if test="${orders.status eq '0' or orders.status eq '3' }">
						<a href="${ctx}/bx/orders/upload?id=${orders.id}">上传</a>
    					<a href="${ctx}/bx/orders/form?id=${orders.id}">修改</a>
						<a href="${ctx}/bx/orders/delete?id=${orders.id}" onclick="return confirmx('确认要删除该专利吗？', this.href)">删除</a>
					</c:if>
				</shiro:hasPermission>	
						<c:if test="${orders.status eq '4' }"><!-- 修改状态为已完成 -->
							<a href="${ctx}/bx/orders/finishRepair?id=${orders.id}">维修完成</a>
						</c:if>
						<c:if test="${orders.status eq '5' }"><!-- 对于维修作出评价 -->
							<a href="${ctx}/bx/orders/evaluation?id=${orders.id}">评价</a>
						</c:if>
						<a href="${ctx}/bx/orders/detail?id=${orders.id}">查看详细</a>
				<shiro:hasPermission name="bx:orders:view">
					<c:if test="${orders.status eq '1' }"><!-- 只审核刚提交上来的 -->
						<a href="${ctx}/bx/orders/toInspection?id=${orders.id}">审核</a>
					</c:if>
					<c:if test="${orders.status eq '2' }"><!-- 给审核通过的订单选择维修人员 -->
						<a href="${ctx}/bx/selectworker/form?repairId=${orders.id}">选择维修人员</a>
					</c:if>
					
				</shiro:hasPermission>  
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