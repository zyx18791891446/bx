<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<c:set var="title"  value="项目统计报表"/>
<html>
<head>
<style>
.content .row{
margin:	15px 10px 15px 10px; 
}
</style>
<title>项目统计报表</title>

<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document)
		.ready(function() {
			$("#btnExport1").click(function() {
			top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v, h, f) {
				if (v == "ok") {
					$("#searchForm").attr("action","${ctx}/ty/projectApplication/export1");
					$("#searchForm").submit();
				}
			},
				{buttonsFocus : 1});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
		});
		</script>
<script  src="${ctxStatic}/jquery/echarts.min.js" ">

</script>
</head>
<body>
<div class="head" style="height: 120px;">
		<div class="title">
			<h3>${title}</h3>
	<sys:message content="${message}" />
        </div>
        <form:form id="searchForm" modelAttribute="projectApplication"
			action="" method="post"
			class="search form-inline">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
				<div class="col-sm-10">
					<div class="form-group col-sm-3" style="float: left;">
						<label class=" control-label">县区:</label>
						<input name="gname" value="${gname }" htmlEscape="false"
							maxlength="32" class="form-control required" style="width:150px" />
					</div>
					<div class="col-sm-4">
						<!-- 起止日期 与 按钮 -->
						<label>起止日期:</label> <input id="beginDate" name="createDate"
							type="text" readonly="readonly"
							class="input-mini Wdate form-control"
							value="<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
							style="width: 110px" /> &nbsp;--&nbsp; <input id="endDate"
							name="updateDate" type="text" readonly="readonly"
							class="input-mini Wdate form-control"
							value="<fmt:formatDate value="${updateDate}" pattern="yyyy-MM-dd"/>"
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"
							style="width: 110px" />
						
					</div>
					<div class="col-sm-3">
						<button type="submit" class="btn btn-primary">查询</button>
						<input id="btnExport1" class="btn btn-primary" type="button"
						value="导出" />
					</div>
				</div>
		</form:form>
</div>
<div class="body">
<div class="content">
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>县区</th>
				<th>建设总费用</th>
				<th>通过评审总费用</th>
				<th>项目总数</th>
				<th>办结数</th>
				<th>评审不通过数</th>
				<th>初审不通过数</th>
				<th>初审通过数</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td>${list.gname}</td>
					<td><c:choose>
							<c:when test="${list.apfund==null}"> 0
                            </c:when>
							<c:otherwise> ${list.apfund}
                              </c:otherwise>
						</c:choose></td>
					<td><c:choose>
							<c:when test="${list.bpfund==null}"> 0
                            </c:when>
							<c:otherwise> ${list.bpfund}
                              </c:otherwise>
						</c:choose></td>
					<td>${list.apname}</td>
					<td>${list.bpname}</td>
					<td>${list.cpname}</td>
					<td>${list.dpname}</td>
					<td>${list.epname}</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
		 </div>
	</div> 
	
	
	<div class="content">
	<div class="pagination">${page}</div>
	

	
	
	<div id="echarts_areacount" style="height: 400px"></div>
	  </div>
	
	
	
	<div class="content" style="padding:0 15px">
	<div class="row" style="background: #f7f7f7">
		<div class="col-md-6" style="background:#fff; border-right:15px solid #f7f7f7">
			<div id="main1" style="height:350px;padding-top:30px"></div>
		</div>
		<div class="col-md-6" style="background:#fff;">
			<div id="main2" style="height:350px;padding-top:30px"></div>
		</div>
	</div>
	</div>  


	<!-- <div id="main3"  style="height: 400px" ></div> -->
<script type="text/javascript"> 
/* 单位费用统计表柱状图 */
$.ajax({
	type : "post",
	async : true,
	url : "${ctx}/ty/projectApplication/projectApplicationChart",
	dataType : "json",
	success : function(result) {
		//请求成功时执行该函数内容，result即为服务器返回的json对象
		var gname = [];
		var apfund = [];
		//请求成功时执行该函数内容，result即为服务器返回的json对象
		$.each(result, function(key, values) {
			gname.push(values.gname);
			apfund.push(values.apfund);
		});
		var myChart = echarts.init(document.getElementById('echarts_areacount'));
		var option = {
			color : [ '#3398DB' ],
			title : {	
		        text: '单位费用统计表柱状图',
		        x:'center'
		    },
			tooltip : {
				trigger : 'axis',
				axisPointer : { // 坐标轴指示器，坐标轴触发有效
					type : 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				data : gname,
				axisTick : {
					alignWithLabel : true
				},
				axisLine : {
					lineStyle : {
						color : '#666'
					}
				}
			} ],
			yAxis : [ {
				type : 'value',
				axisLine : {
					lineStyle : {
						color : '#666'
					}
				}
			} ],
			series : [ {
				name : '项目总费用',
				type : 'bar',
				barWidth : '60%',
				data : apfund
			} ]
		};
		myChart.setOption(option);
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		//请求失败时执行该函数
		alert("请求数据失败了!");
	}
});

/* 各单位总费用占比图 */
$.ajax({
		    type : "post",
		    async : true,
		    url : "${ctx}/ty/projectApplication/projectChart",
			dataType : "json",
			success : function(result) {
				var name = [];
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				$.each(result, function(key, values) {
					name.push(values.name);
				});
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				var myChart = echarts.init(document.getElementById('main1'));
                var option = {
                		title : {
    						text : '各单位总费用占比图',
    						x: 'center',
    					},
                	    tooltip: {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b}: {c} ({d}%)"
                	    },
                	    legend: {
                	        orient: 'vertical',
                	        x: 'left',
                	        data:name
                	    },
                	    series: [
                	        {
                	            name:'费用',
                	            type:'pie',
                	            radius: ['50%', '70%'],
                	            avoidLabelOverlap: false,
                	            label: {
                	                normal: {
                	                    show: false,
                	                    position: 'center'
                	                },
                	                emphasis: {
                	                    show: true,
                	                    textStyle: {
                	                        fontSize: '30',
                	                        fontWeight: 'bold'
                	                    }
                	                }
                	            },
                	            labelLine: {
                	                normal: {
                	                    show: false
                	                }
                	            },
                	            data:result
                	        }
                	    ]
                	};
                myChart.setOption(option);
		   },
		    error : function(XMLHttpRequest, textStatus, errorThrown) {
		        //请求失败时执行该函数
		    alert("请求数据失败!");
		    }
		});
/* 各单位项目数占比图 */
$.ajax({
	type : "get",
	async : true,
	url : "${ctx}/ty/projectApplication/projectChartGP",
	dataType : "json",
	success : function(result) {
		//请求成功时执行该函数内容，result即为服务器返回的json对象
		var name = [];
				//请求成功时执行该函数内容，result即为服务器返回的json对象
				$.each(result, function(key, values) {
					name.push(values.name);
				});
				//请求成功时执行该函数内容，result即为服务器返回的json对象
		var myChart = echarts.init(document.getElementById('main2'));
		var option = {
				title : {
			        text: '各单位项目数占比图',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    series : [
			        {   
			        	name:'项目数',
			            type:'pie',
			            radius : '65%',
			            center : ['50%', '50%'],
			            data:result
			        }
			    ]
		};
		myChart.setOption(option);
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
		//请求失败时执行该函数
		alert("请求数据失败!");
	}
});

</script>
</body>
</html>