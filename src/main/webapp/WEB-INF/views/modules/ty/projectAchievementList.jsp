<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="项目绩效" />
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript">

	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					$("#searchForm").attr("action","${ctx}/ty/projectApplication/export");
					$("#searchForm").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	function page(n, s) {
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
	<div class="head" style="height: 120px;">
		<!-- 页面头部 -->
		<div class="title">
			<h3>${title}</h3>
		</div>
		
		<form:form id="searchForm" modelAttribute="projectApplication"
			action="${ctx}/ty/projectApplication/achievementList?statusDd=6" method="post"
			class="search form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<div class="col-sm-12">
				<div class="form-group" style="float: left;">
					<label class=" control-label">建设单位:</label>
					<form:input path="constructionUnit" htmlEscape="false"
						maxlength="32" class="form-control required" />
				</div>
				<div class="form-group" style="float: left;">
					<label class="control-label">项目名称:</label>
					<form:input path="projectName" htmlEscape="false" maxlength="32"
						class="form-control required" />
				</div>
			<!-- 起止日期 与 按钮 -->
				<label>起止日期:</label> <input id="beginDate" name="createDate"
					type="text" readonly="readonly"
					class="input-mini Wdate form-control"
					value="<fmt:formatDate value="${projectApplication.createDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width: 100px"/>
				&nbsp;--&nbsp; <input id="endDate" name="updateDate" type="text"
					readonly="readonly" class="input-mini Wdate form-control"
					value="<fmt:formatDate value="${projectApplication.updateDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" style="width: 100px"/>
				<!-- 按钮 -->
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="submit" class="btn btn-primary">查询</button>
				<input id="btnExport" class="btn btn-primary" type="button" value="导出"/>
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
						<!-- <th class="jsunit">建设单位</th> -->
						<th class="jsunit">项目名称</th>
						<th class="jsunit">项目经费</th>
						<th class="jsunit">所属县区</th>
						<th class="jsunit">申报日期</th>
						<th class="jsunit">审核状态</th>
						<!-- <th class="jsunit">更新时间</th> -->
						<th class="jsunit">查看绩效</th>
						<shiro:hasPermission name="ty:nihao:view">
						<th class="jsunit">上传绩效</th>
						</shiro:hasPermission>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list}" var="ProjectApplication"
						varStatus="status">
						<c:if test="${status.index%2==0}">
							<tr class="even">
						</c:if>
						<c:if test="${status.index%2!=0}">
							<tr class="even">
						</c:if>
						<!-- 序号 -->
						<td>${status.index+1}</td>
						<%-- <!-- 建设单位 -->
						<td class="jsunit">${ProjectApplication.constructionUnit}</td> --%>
						<!-- 项目名称 -->
						<td class="jsunit">${ProjectApplication.projectName}</td>
						<!-- 项目经费 -->
						<td class="jsunit">${ProjectApplication.projectFund}</td>
						<!-- 所属县区 -->
						<td class="jsunit">${ProjectApplication.officeName}</td>
						<!-- 申报日期 -->
						<td class="jsunit"><fmt:formatDate
								value="${ProjectApplication.createDate}" pattern="yyyy-MM-dd" /></td>
						<!-- 审核状态 -->
						<td class="jsunit">${fns:getDictLabel(ProjectApplication.statusDd,'projectStatus','') }</td>
						<%-- <!-- 更新时间 -->
						<td class="jsunit"><fmt:formatDate value="${ProjectApplication.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>

						
						<!-- 查看绩效 -->
						<td class="jsunit"><a
							href="${ctx}/ty/oaAchievements/list?projectId=${ProjectApplication.id}">查看绩效</a></td>
						
						<shiro:hasPermission name="ty:nihao:view">
						<td class="jsunit">
							<a href="${ctx}/ty/oaAchievements/form?projectId=${ProjectApplication.id}">上传绩效</a>
						</td>
						<!-- 上传绩效 -->
						</shiro:hasPermission> 
					</c:forEach>
				</tbody>
			</table>
			<div class="pagination">${page}</div>
		</div>
	</div>

</body>
</html>