<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html style="overflow-x:auto;padding:0; margin:0;height:100%">
<head>
	<title><sitemesh:title/> - Powered By JeeSite</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>		
	<sitemesh:head/>
	<style type="text/css">
		.bigbg{
    	position: absolute; 
    	bottom:0px;  
    	
    	/* height:100px; */ 
    	left: 0;
    	right: 0; 
    	}
    	
    	.bigbg img{max-width:100%}
	</style>
</head>
<body style=" margin:0;min-height: 100%;padding-bottom:100px;position:relative;">
	<sitemesh:body/>
	<script type="text/javascript">//<!-- 无框架时，左上角显示菜单图标按钮。
		if(!(self.frameElement && self.frameElement.tagName=="IFRAME")){
			$("body").prepend("<i id=\"btnMenu\" class=\"icon-th-list\" style=\"cursor:pointer;float:right;margin:10px;\"></i><div id=\"menuContent\"></div>");
			$("#btnMenu").click(function(){
				top.$.jBox('get:${ctx}/sys/menu/treeselect;JSESSIONID=<shiro:principal property="sessionid"/>', {title:'选择菜单', buttons:{'关闭':true}, width:300, height: 350, top:10});
				//if ($("#menuContent").html()==""){$.get("${ctx}/sys/menu/treeselect", function(data){$("#menuContent").html(data);});}else{$("#menuContent").toggle(100);}
			});
		}//-->
	</script>
	<div class="bigbg">
		<img src="/static/images/bottombg.png">
	</div>
</body>
</html>