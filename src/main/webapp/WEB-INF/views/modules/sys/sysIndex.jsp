<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
	<c:if test="${tabmode eq '1'}"><link rel="Stylesheet" href="${ctxStatic}/jerichotab/css/jquery.jerichotab.css" />
		<script type="text/javascript" src="${ctxStatic}/jerichotab/js/jquery.jerichotab.js"></script></c:if>

	<script type="text/javascript">
        $(document).ready(function() {
            // <c:if test="${tabmode eq '1'}"> 初始化页签
            $.fn.initJerichoTab({
                renderTo: '#right', uniqueId: 'jerichotab',
                contentCss: { 'height': $('#right').height() - tabTitleHeight },
                tabs: [], loadOnce: true, tabWidth: 110, titleHeight: tabTitleHeight
            });//</c:if>
            // 绑定菜单单击事件
            $("#menu a.menu").click(function(){
                // 一级菜单焦点
                $("#menu li.menu").removeClass("active");
                $(this).parent().addClass("active");
                // 左侧区域隐藏
                if ($(this).attr("target") == "mainFrame"){
                   $("#left,#openClose").hide();
                    wSizeWidth();
                    // <c:if test="${tabmode eq '1'}"> 隐藏页签
                    $(".jericho_tab").hide();
                    $("#mainFrame").show();//</c:if>
                    return true;
                }
                // 左侧区域显示
                $("#left,#openClose").show();
                $("#mainFrame").attr({"src":""});

                $('#mainFrame').contents().find("body").empty();

                if(!$("#openClose").hasClass("close")){
                    $("#openClose").click();
                }
                
                // 显示二级菜单
                var menuId = "#menu-" + $(this).attr("data-id");
                if ($(menuId).length > 0){
                    $("#left .accordion").hide();
                    $(menuId).show();
                    // 初始化点击第一个二级菜单
                    if (!$(menuId + " .accordion-body:first").hasClass('in')){
                        $(menuId + " .accordion-heading:first a").click();
                    }
                    if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
                        $(menuId + " .accordion-body a:first i").click();
                    }
                    // 初始化点击第一个三级菜单
                    $(menuId + " .accordion-body li:first li:first a:first i").click();
                }else{
                    // 获取二级菜单数据
                    $.get($(this).attr("data-href"), function(data){
                        if (data.indexOf("id=\"loginForm\"") != -1){
                            alert('未登录或登录超时。请重新登录，谢谢！');
                            top.location = "${ctx}";
                            return false;
                        }
                        $("#left .accordion").hide();
                        $("#left").append(data);
                        // 链接去掉虚框
                        $(menuId + " a").bind("focus",function() {
                            if(this.blur) {this.blur()};
                        });
                        // 二级标题
                        $(menuId + " .accordion-heading a").click(function(){

                            $(menuId + " .accordion-body").removeClass("in");
                            $(menuId + " .accordion-toggle i").removeClass('icon-angle-down').addClass('icon-angle-right');



                            if(!$($(this).attr('data-href')).hasClass('in')){
                                $(this).children("i:last").removeClass('icon-angle-right').addClass('icon-angle-down');
                            }
                        });
                        // 二级内容
                        $(menuId + " .accordion-body a").click(function(){
                            $(menuId + " li").removeClass("active");
                     	       $(menuId + " li i").removeClass("icon-white");
                            $(this).parent().addClass("active");
                            $(this).children("i").addClass("icon-white");
                        });
                        // 展现三级
                        $(menuId + " .accordion-inner a").click(function(){
                            var href = $(this).attr("data-href");
                            if($(href).length > 0){
                                $(href).toggle().parent().toggle();
                                return false;
                            }
                            // <c:if test="${tabmode eq '1'}"> 打开显示页签
                            return addTab($(this)); // </c:if>
                        });
                        // 默认选中第一个菜单
                        $(menuId + " .accordion-body a:first i").click();
                        $(menuId + " .accordion-body li:first li:first a:first i").click();
                    });
                }
                // 大小宽度调整
                wSizeWidth();
                return false;
            });
            // 初始化点击第一个一级菜单
            $("#menu a.menu:first span").click();
            // <c:if test="${tabmode eq '1'}"> 下拉菜单以选项卡方式打开
            $("#userInfo .dropdown-menu a").mouseup(function(){
                return addTab($(this), true);
            });// </c:if>
            // 鼠标移动到边界自动弹出左侧菜单
            $("#openClose").mouseover(function(){
                if($(this).hasClass("open")){
                    $(this).click();
                }
            });
            // 获取通知数目  <c:set var="oaNotifyRemindInterval" value="${fns:getConfig('oa.notify.remind.interval')}"/>
            function getNotifyNum(){
                $.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
                    var num = parseFloat(data);
                    if (num > 0){
                        $("#notifyNum,#notifyNum2").show().html("("+num+")");
                    }else{
                        $("#notifyNum,#notifyNum2").hide()
                    }
                });
            }
            getNotifyNum(); //<c:if test="${oaNotifyRemindInterval ne '' && oaNotifyRemindInterval ne '0'}">
            setInterval(getNotifyNum, ${oaNotifyRemindInterval}); //</c:if>
        });
        // <c:if test="${tabmode eq '1'}"> 添加一个页签
        function addTab($this, refresh){
            $(".jericho_tab").show();
            $("#mainFrame").hide();
            $.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
            return false;
        }// </c:if>
	</script>
	<style>
		.collapse #menu li:active{
			background-color: #4c4c4e;
		}
		#left .accordion-inner .nav-list li.active a{
		    background: #4680e7;
            color: #fff;
		}
		.navbar-default .navbar-nav > li > a:hover,
		.navbar-default .navbar-nav > .active > a {
		    background-color: #3d70ca
		}
		.navbar-default .navbar-nav > li > a:hover, 
		.navbar-default .navbar-nav > .open > a, 
		.navbar-default .navbar-nav > .active > a {
    		background-color: #4680e7;
    	}
	</style>
</head>
<body>
<div id="main">
	<nav id="header" class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${ctx}">
				 	<img alt="Brand" src="/static/images/2.png"> 
				</a>
			</div>


			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" style="background-color: #4680e7;">
				<ul class="nav navbar-nav" id="menu">
					<c:set var="firstMenu" value="true"/>
					<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
						<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
							<li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
								<c:if test="${empty menu.href}">
									<a class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}">
										<i class="icon-${menu.icon}"></i>
										<br>
										<span>${menu.name}</span>
									</a>
								</c:if>
								<c:if test="${not empty menu.href}">
									<a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame">
										<i class="icon-${menu.icon}"></i>
										<br>
										<span>${menu.name}</span>
									</a>
								</c:if>

								<div class="arrow"><img src="/static/images/nav_arrow.png" /></div>
							</li>
							<c:if test="${firstMenu}">
								<c:set var="firstMenuId" value="${menu.id}"/>
							</c:if>
							<c:set var="firstMenu" value="false"/>
						</c:if>
					</c:forEach>
				</ul>

				<ul class="nav navbar-nav navbar-right control">
					<!--
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="icon-th-large"></i> </a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
							<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul>
					</li>
					-->
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">您好, ${fns:getUser().name}&nbsp; <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
						</ul>
					</li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
				</ul>
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

	<div class="container-fluid">
		<div id="content" class="row-fluid">
			<div id="left" class="sidebar"><%--
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
			</div>

			<div id="right">
				<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
			</div>
		</div>

	</div>
</div>
<script type="text/javascript">
    var leftWidth = 220; // 左侧窗口大小
    var tabTitleHeight = 33; // 页签的高度
    var htmlObj = $("html"), mainObj = $("#main");
    var headerObj = $("#header"), footerObj = $("#footer");
    var frameObj = $("#left, #openClose, #right, #right iframe");
    function wSize(){
        var minHeight = 500, minWidth = 980;
        var strs = getWindowSize().toString().split(",");
        htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
        mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");

        console.log(headerObj.height());
        frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() -  (strs[1] < minWidth ? 42 : 0));

        <c:if test="${tabmode eq '1'}">
        $(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); //
        </c:if>
        wSizeWidth();
    }
    function wSizeWidth(){
        var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
        if($("#left").is(":hidden")){
            $("#right").width($("#content").width());
		}else{
            $("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
		}

    }// <c:if test="${tabmode eq '1'}">
    function openCloseClickCallBack(b){
        $.fn.jerichoTab.resize();
    } // </c:if>

</script>
<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>