<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}登录</title>
<meta name="decorator" content="blank" />
<style>
html, body {
	margin: 0;
	padding: 0;
	color: #555;
	background-color: white;
}

a {
	
}

.main {
	width: 858px;
	height: 515px;
	background-color: #f9fafc;
/* 	border-radius: 20px; */
	margin: auto;
	margin-top: 10%;
	background-image: url(/static/images/logo_right_bg.png);
	background-repeat: no-repeat;
	background-position: top right;
	box-shadow:0px 2px 30px darkblue;
    
}

.panel {
	margin: 120px 70px 0 10px;
	background: white;
	border: none;
	-webkit-box-shadow: none;
	box-shadow: none;
}

button {
	margin: 5px 0;
}

.left {
	width: 459px;
	height: 100%;
	float: left;
	/* border-top-left-radius: 2px;
	border-bottom-left-radius: 2px; */
}

.left h4 {
	font-size: 20px;
	font-weight: bold;
	letter-spacing: 3px;
	display: inline;
}

.lefttitle {
	margin-top: 35px;
	margin-left: 30px;
}

.leftimg {
	 margin-top: 50px;
	margin-left: 50px; 
}

.right {
	width: 399px;
	height: 100%;
	float: left;
}

.username, .password {
	padding-left: 40px;
	padding: 0;
	margin: 0;
	position: relative;
}

.username {
	background: url(/static/images/icon_username.png) 10px center no-repeat;
	background-color: #fff;
}

.password {
	background: url(/static/images/icon_password.png) 10px center no-repeat;
	background-color: #fff;
}

.username input, .password input {
	border: none;
	background: none;
	position: absolute;
	top: 0;
	right: 0;
	bottom: 0px;
	left: 40px;
	margin: 0;
	padding: 0;
	display: block;
}

.right .login-title {
	font-size: 24px;
	font-weight: bold;
	letter-spacing: 5px;
}

.panel-heading {
	margin: auto;
	margin-top: 50px;
	text-align: center;
}
</style>
<%--<style type="text/css">--%>
<%--html,body,table{background-color:#f5f5f5;width:100%;text-align:center;}.form-signin-heading{font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:36px;margin-bottom:20px;color:#0663a2;}--%>
<%--.form-signin{position:relative;text-align:left;width:300px;padding:25px 29px 29px;margin:0 auto 20px;background-color:#fff;border:1px solid #e5e5e5;--%>
<%---webkit-border-radius:5px;-moz-border-radius:5px;border-radius:5px;-webkit-box-shadow:0 1px 2px rgba(0,0,0,.05);-moz-box-shadow:0 1px 2px rgba(0,0,0,.05);box-shadow:0 1px 2px rgba(0,0,0,.05);}--%>
<%--.form-signin .checkbox{margin-bottom:10px;color:#0663a2;} .form-signin .input-label{font-size:16px;line-height:23px;color:#999;}--%>
<%--.form-signin .input-block-level{font-size:16px;height:auto;margin-bottom:15px;padding:7px;*width:283px;*padding-bottom:0;_padding:7px 7px 9px 7px;}--%>
<%--.form-signin .btn.btn-large{font-size:16px;} .form-signin #themeSwitch{position:absolute;right:15px;bottom:10px;}--%>
<%--.form-signin div.validateCode {padding-bottom:15px;} .mid{vertical-align:middle; }--%>
<%--.header{height:80px;padding-top:20px;} .alert{position:relative;width:300px;margin:0 auto;*padding-bottom:0px;}--%>
<%--label.error{background:none;width:270px;font-weight:normal;color:inherit;margin:0;}--%>
<%--</style>--%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#loginForm")
								.validate(
										{
											rules : {
												validateCode : {
													remote : "${pageContext.request.contextPath}/servlet/validateCodeServlet"
												}
											},
											messages : {
												username : {
													required : "请填写用户名."
												},
												password : {
													required : "请填写密码."
												},
												validateCode : {
													remote : "验证码不正确.",
													required : "请填写验证码."
												}
											},
											errorLabelContainer : "#messageBox",
											errorPlacement : function(error,
													element) {
												error.appendTo($("#loginError")
														.parent());
											}
										});
					});
	// 如果在框架或在对话框中，则弹出提示并跳转到首页
	if (self.frameElement && self.frameElement.tagName == "IFRAME"
			|| $('#left').length > 0 || $('.jbox').length > 0) {
		alert('未登录或登录超时。请重新登录，谢谢！');
		top.location = "${ctx}";
	}
</script>
</head>
<body>
	<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->

	<div class="main">
			<div class="left" style="background-color: white">
				 <div class="lefttitle">
					<!-- <img src="/static/images/inco.png" alt="" style="height: 40px"> -->
					&ensp;
				<h4 >计算机学院宿舍报修系统</h4>
				</div> 
				<div class="leftimg">
					<img src="/static/images/login.jpg" alt=""
						style="width: 350px; height: 350px">
				</div>
			</div>
			<div class="right" style="background-color: white">
				<div class="panel">
					<div class="panel-heading">
						<h4 class="login-title">用户登录</h4>

					</div>
					<div class="panel-body">
						<div class="">
							<form action="${ctx}/login" method="post">
								<%--<i class="icon-user icon-white"></i><br>--%>
								<div class="form-group">
									<div class="form-control input-lg username">
										<input type="text" name="username" class="required"
											placeholder="登录名" value="${username}">
									</div>

								</div>

								<div class="form-group">
									<div class="form-control input-lg password">
										<input type="password" name="password" class="required"
											placeholder="密码">
									</div>

								</div>
								<c:if test="${isValidateCodeLogin}">
									<div class="validateCode">
										<label class="input-label mid" for="validateCode">验证码</label>
										<sys:validateCode name="validateCode"
											inputCssStyle="height:36px !important; line-height:36px !important" />
									</div>
								</c:if>
								<button type="submit" style="background-color: #1797ed" class="btn btn-lg btn-primary btn-block">登
									录</button>
							</form>
						</div>
						<br>


						<div class="form-group pull-right">
							<p>
								<a href="${pageContext.request.contextPath}/userForm.jsp">用户注册</a>
							</p>

						</div> 

					</div>

				</div>
			</div>




	</div>


	<script type="text/javascript">
		var getWindowSize = function() {
			return [ "Height", "Width" ].map(function(name) {
				return window["inner" + name]
						|| document.compatMode === "CSS1Compat"
						&& document.documentElement["client" + name]
						|| document.body["client" + name];
			});
		};
		$(window).resize(function() {
			wSize();
		});

		wSize();
		function wSize() {
			var mainObj = $(".main");
			var strs = getWindowSize().toString().split(",");

			var offset = (strs[0] - mainObj.height()) / 2;

			mainObj.css({
				"margin-top" : offset
			});
		}
	</script>


</body>
</html>