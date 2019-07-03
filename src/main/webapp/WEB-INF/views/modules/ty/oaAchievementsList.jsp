<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title" value="查看绩效" />
<html>
<head>
<title>查看绩效</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
$(document).ready(function() {
	//$("#name").focus();
	$("#inputForm").validate({
		submitHandler: function(form){
			loading('正在提交，请稍等...');
			form.submit();
		},
		errorContainer: "#messageBox",
		errorPlacement: function(error, element) {
			$("#messageBox").text("输入有误，请先更正。");
			if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
				error.appendTo(element.parent().parent());
			} else {
				error.insertAfter(element);
			}
		}
	});
	$('.delete').hide(); 
	$('.file-btn-wj').hide();
}); 
/* 
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        } */
</script>
<style type="text/css" scoped="scoped">
.td{
/* 文本超出自动换行 */
word-wrap:break-word;
text-overflow:ellipsis;
position: relative;
word-break:break-all;
}
</style>
</head>
<body>
<div class="head">
	<div class="title">
		<h3>${title}</h3>
	</div>
</div>
<div class="content">
<div class="form-container">
	<div class="form-horizontal">
	<form:form id="inputForm" modelAttribute="oaAchievements" method="post" action="${ctx}/ty/oaAchievements/form"  class="form-horizontal">
		<form:hidden path="projectId"/>
		<form:hidden path="achievementsDate"/>
		<div class="form-group">
		    <label class="col-sm-2 control-label">建设单位：</label>
			<div class=" col-sm-3">
				<input type="text" value="${project.constructionUnit }" class="form-control" disabled style="border: none"/>
			</div>
			<label class="col-sm-2 control-label">项目名称：</label>
			<div class="col-sm-3">
			    <input type="text" value="${project.projectName }" class="form-control" disabled style="border: none"/>
			</div>
		</div>
	    <div class="form-group">
	        <label class="col-sm-2 control-label">项目经费：</label>
			<div class="col-sm-3">
			    <input type="text" value="${project.projectFund }" class="form-control" disabled style="border: none"/>
			</div>
			<label class="col-sm-2 control-label">经费来源：</label>
			<div class="col-sm-3">
			    <input type="text" value="${project.fundSource }" class="form-control" disabled style="border: none"/>
			</div>
		</div>
		<div class="form-group">
		    <label class="col-sm-2 control-label">所属县区：</label>
			<div class="col-sm-3">
				<input type="text" value="${project.officeName }" class="form-control" disabled style="border: none"/>
			</div>
			<label class="col-sm-2 control-label">申报日期：</label>
			<div class="col-sm-3">
				<input type="text"  value="<fmt:formatDate value="${project.createDate }" pattern="yyyy-MM-dd HH:mm"/> " class="form-control" disabled style="border: none"/>
			</div>
		</div>
		<sys:message content="${message}"/>
		<div class="form-group">
		<div class="col-sm-1"></div>
		<div class="col-sm-9">
		<!-- 表格固定 -->
	    <table style="table-layout: fixed;" id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr>
		<th class="col-sm-3">绩效内容</th>
		<th class="col-sm-3">绩效资料</th>
		<th class="col-sm-3">申报时间</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="oaAchievements">
		<tr><td class="td">${oaAchievements.content }</td>
		    <td>
		    	<sys:fileupload id="achievementsFiles" input="achievementsFiles" type="files" savePath="announce" uploadPath="${ctx}/file" selectMultiple="true" value="${oaAchievements.achievementsFiles }"  />
		    </td>
		    <td class="td"><fmt:formatDate value="${oaAchievements.achievementsDate }" pattern="yyyy-MM-dd HH:mm"/> </td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		<div class="pagination ">${page}</div>
		</div>
		</div>
		<div class="form-group"> 
		<div class="col-sm-10" style="text-align: center;">
		<button class="btn btn-primary" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
	    </div>
	    </div>
		
	</form:form>
	</div>
</div>
</div>	
</body>
</html>