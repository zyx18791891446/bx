<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目申报管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(
			function() {
				//$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>
<style type="text/css">
input {
	width: 250px;
	height: 30px;
	border-radius: 8px; /* input指定宽度和高度并用border-radius适当调节变圆程度。 */
	border: 1px solid gray; /* 用border来修改input的边框颜色 */
	outline: none; /* 用outline去把input默认的矩形边框给去掉（none） */
}

textarea {
	width: 700px;
	border-radius: 8px;
	border: 1px solid gray;
	outline: none;
}

.control-group {
	overflow: auto;
	width: 100%;
	margin: 5px 0;
}

label {
	min-width: 130px;
	float: left;
	display: block;
	margin-right: 5px;
	text-transform: capitalize;
}

.formText {
	display: block;
	float: left;
}

/*    .table, .table * {font-size: 14px;font-family: Arial, 宋体, Helvetica, sans-serif;} */
.table {
	display: table;
	border-collapse: collapse;
}

.table-tr {
	display: table-row;
	height: 30px;
}

.table-td {
	display: table-cell;
	height: 100px;
}

.sub-table {
	width: 100%;
	height: 100%;
	display: table;
}

.sub-table-tr {
	display: table-row;
	height: 100%;
}

.sub-table-td {
	display: table-cell;
	height: 100%;
	border: 1px solid #ddd;
	text-align: center;
	vertical-align: middle;
}
</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ty/projectApplication/">项目申报列表</a></li>
		<li class="active"><a
			href="${ctx}/ty/projectApplication/form?id=${projectApplication.id}">项目申报<shiro:hasPermission
					name="ty:projectApplication:edit">${not empty projectApplication.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ty:projectApplication:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
	<!-- 用div制作表格 -->
	<div class="table table-bordered table-condensed">
		<div class="table-caption"
			style="background-color: #323a4e; font-size: 25px;color: #ffff;font-family: 宋体">项目信息申报</div>
		 <form:form id="inputForm" modelAttribute="projectApplication" action="${ctx}/ty/projectApplication/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/> 

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>建设单位：
							</label> <input type="text" class="formText" id="constructionUnit"
								required />
						</div>

						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>项目名称：
							</label> <input type="text" class="formText" id="projectName" required />
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>项目经费：
							</label> <input type="text" class="formText" id="projectFund" required />
						</div>

						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>经费来源：
							</label> <input type="text" class="formText" id="fundSource" required />
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 100%;">
							<label class="control-label">所属单位：</label>
							<sys:treeselect id="office" name="office.id"
								value="${projectApplication.office.id}" labelName="office.name"
								labelValue="${projectApplication.office.name}" title="部门"
								url="/sys/office/treeData?type=2" cssClass="" allowClear="true"
								notAllowSelectParent="true" />
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>项目应用范围：
							</label> <input type="text" class="formText" id="applyScope" required />
						</div>

						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>建设方式：
							</label> <input type="text" class="formText" id="constructionMode"
								required />
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 100%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>项目建设必要性：
							</label> <input type="text" class="formText" id="constructionNecessity"
								required />
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 100%;">
							<label class="control-label"> <span class="help-inline"><font
									color="red">*</font> </span>项目实现目标：
							</label>
							<textarea class="formText" id="achieveAim" rows="3" required></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label">项目简介：</label>
							<textarea class="formText" id="projectIntroduce" rows="3"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<label class="control-label">上传项目相关资料：</label>
							<textarea class="formText" id="remarks" rows="3"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="table-tr">
			<div class="table-td">
				<div class="sub-table">
					<div class="sub-table-tr">
						<div class="sub-table-td" style="width: 50%;">
							<shiro:hasPermission name="ty:projectApplication:edit">
								<button id="btnSubmit" class="btn btn-primary" type="submit">保&nbsp;存</button>
							</shiro:hasPermission>
							<button id="btnCancel" class="btn" type="button"
								onclick="history.go(-1)">返&nbsp;回</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form:form>

</body>
</html>