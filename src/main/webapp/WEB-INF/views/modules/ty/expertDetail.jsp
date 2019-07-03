<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title"  value="维修工人员详情"/>
<html>
<head>
<style>
td{
padding-bottom:12px;}

label{
font-size:12px;
font-weight:normal;}
.form-label{
padding-right:30px;
padding-left:30px} 
</style>
<title>${title}</title>
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
		});
	</script>
	<!-- 绑定年龄的操作 -->
</head>
<body>
<div class="head">
	  <div class="title">
		<h3>${title}</h3>
		<div class="form-group" style="text-align:right;">
		<button class="btn btn-primary" type="button" onclick="history.go(-1)" >返&nbsp;回</button>
		</div>
	  </div>
	</div>
<div class="content">
          <div class="page-title"><h5><strong>教师基本信息</strong></h5></div>
	   <div class="form-container">
	     <div class="form-horizontal">
		     <div class="form-group">
			   <label for="expertname" class="col-sm-2 control-label">姓名：</label>
			     <div class=" col-sm-3">        
				  <input type="text"  value="${expert.expertname }" class="form-control" disabled="disabled" style="border:0" color="white"/>
			     </div>
			   <label for="specialty" class="col-sm-2 control-label">专业：</label>
			     <div class="col-sm-3">
				<input type="text" value ="${expert.specialty }" class="form-control" disabled="disabled" style="border:0" color="white"/>
			     </div>
		     </div>
		     <div class="form-group">
		      <label for="sex" class="col-sm-2 control-label">性别：</label>
			   <div class="col-sm-3">
				<input type="text" value="${fns:getDictLabel(expert.sex,'sex','') }" class="form-control" disabled="disabled" style="border:0" color="white"/>
			   </div>
			  <label for="age" class="col-sm-2 control-label">年龄：</label>
			    <div class="col-sm-3">
				 <input type="text" value="${expert.age }" class="form-control" disabled="disabled" style="border:0" color="white"/>
			    </div>
		     </div>
	    
		    <div class="form-group">
			   <label for="content" class="col-sm-2 control-label">个人介绍：</label>
			     <div class="col-sm-8">
			       <textarea style="resize:none;border: none; rows=3" class="form-control" disabled="disabled" >${expert.content }</textarea>
			     </div>
		    </div>
		    <div class="form-group">
			  <label for="researchresult" class="col-sm-2 control-label">研究成果：</label>
			    <div class="col-sm-8">
			      <textarea style="resize:none;border: none;" rows="3" class="form-control" disabled="disabled">${expert.researchresult }</textarea>
			    </div>
		   </div>
		    <div class="form-group">
			  <label for="researchresult" class="col-sm-2 control-label">备注：</label>
			    <div class="col-sm-8">
			      <textarea style="resize:none;border: none;" rows="3" class="form-control" disabled="disabled">${expert.remarks }</textarea>
			    </div>
		   </div>
	   </div>
	 </div>
   </div>	


</body>
</html>