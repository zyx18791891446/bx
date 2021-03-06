<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="科研论文" />
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
																			"${ctx}/lh/researchpapers/export");
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
	<form:form id="searchForm" modelAttribute="researchpapers"
			action="${ctx}/lh/researchpapers" method="post"
			class="search form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<div class="col-sm-12">
				<div class="col-sm-10">
					<div class="form-group" style="float: left;">
						<label class=" control-label">科研论文名称</label>
						<form:input path="name" htmlEscape="false"
							maxlength="32" class="form-control required" />
					</div>
						<div class="form-group" style="float: left;">
						<label class=" control-label">作者</label>
						<form:input path="author" htmlEscape="false"
							maxlength="32" class="form-control required" />
					</div>

					<label>完成年限:</label> <input id="finishdate" name="finishdate"
						type="text" readonly="readonly"
						class="input-mini Wdate form-control"
						value="<fmt:formatDate value="${researchpapers.finishdate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
						style="width: 100px" />
				</div>
				<button type="submit" class="btn btn-primary">查询</button>
					<input id="btnExport" class="btn btn-primary" type="button"
						value="导出" />
			</div>
		</form:form>
	</div>
	<sys:message content="${message}"/>
	<div class="content">
		<div class="body">
		 <table id="contentTable" class="table  table-striped">
			
		<thead>
			<tr>
				<th class="jsunit">序号</th>
				<th class="jsunit">名称</th>
				<th class="jsunit">作者</th>
				<th class="jsunit">状态</th>
				<th class="jsunit">创建时间</th>
				<th class="jsunit">完成时间</th>
				<th class="jsunit">类型</th>
				
				<%-- <shiro:hasPermission name="lh:researchpapers:edit"> --%>
					<th class="jsunit">操作</th>
			<%-- 	</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="researchpapers" varStatus="status">
			<tr>

				<td class="jsunit">${status.index+1 }</td>
				<td class="jsunit">${researchpapers.name }</td>
				<td class="jsunit">${researchpapers.author }</td>
				<td class="jsunit">
				${fns:getDictLabel(researchpapers.status,'researchStatus','') }
				
				</td>
				<td class="jsunit">
					<fmt:formatDate value="${researchpapers.createDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td class="jsunit">
					<fmt:formatDate value="${researchpapers.finishdate}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="jsunit">${researchpapers.type }</td>
				
				<td>
				<shiro:hasPermission name="lh:researchpapers:edit">
					<c:if test="${researchpapers.status eq '0' or researchpapers.status eq '3' }">
						<a href="${ctx}/lh/researchpapers/upload?id=${researchpapers.id}">上传</a>
    					<a href="${ctx}/lh/researchpapers/form?id=${researchpapers.id}">修改</a>
						<a href="${ctx}/lh/researchpapers/delete?id=${researchpapers.id}" onclick="return confirmx('确认要删除该专利吗？', this.href)">删除</a>
					</c:if>
				</shiro:hasPermission>	
						<a href="${ctx}/lh/researchpapers/detail?id=${researchpapers.id}">查看详细</a>
				<shiro:hasPermission name="lh:researchpapers:view">
				<c:if test="${researchpapers.status eq '1' }">
					<a href="${ctx}/lh/researchpapers/toInspection?id=${researchpapers.id}">审核</a>
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