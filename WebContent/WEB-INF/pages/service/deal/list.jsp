<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>

<script type="text/javascript">
Date.prototype.format = function(format){ 
	var o = { 
    	"M+" : this.getMonth()+1, //month 
    	"d+" : this.getDate(), //day 
    	"h+" : this.getHours(), //hour 
    	"m+" : this.getMinutes(), //minute 
    	"s+" : this.getSeconds(), //second 
    	"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
    	"S" : this.getMilliseconds() //millisecond 
	} 

	if(/(y+)/.test(format)) { 
		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	} 

	for(var k in o) { 
    	if(new RegExp("("+ k +")").test(format)) { 
    		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
    	} 
    } 
	
    return format; 
} 

/*
	//使用方法 
	var now = new Date(); 
	var nowStr = now.format("yyyy-MM-dd hh:mm:ss"); 
	//使用方法2: 
	var testDate = new Date(); 
	var testStr = testDate.format("YYYY年MM月dd日hh小时mm分ss秒"); 
	alert(testStr); 
	//示例： 
	alert(new Date().Format("yyyy年MM月dd日")); 
	alert(new Date().Format("MM/dd/yyyy")); 
	alert(new Date().Format("yyyyMMdd")); 
	alert(new Date().Format("yyyy-MM-dd hh:mm:ss"));
*/	

</script>






<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务处理</title>
	
</head>
<body>

	<div class="page_title">
		客户服务处理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="">
			新建
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp }/service/deal/list" method="POST">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKE_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_LIKE_customerName" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_D_minDate" size="10" />
					-
					<input type="text" name="search_D_maxDate" size="10" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						分配时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="service">
					<tr>
						<td class="list_data_number">
							${service.id }
						</td>
						<td class="list_data_text">
							${service.serviceType }
						</td>
						<td class="list_data_ltext">
							${service.serviceTitle }
						</td>
	
						<td class="list_data_text">
							${service.customer.name }
						</td>
						<td class="list_data_text">
							${service.createdby.name }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${service.createDate }"/>
						</td>
	
						<td class="list_data_text">
							<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp }/service/deal?id=${service.id}'" 
								title="处理" src="${ctp }/static/images/bt_deal.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
					
				
			</table>
			<tag:page page="${page }" queryString="${queryString }"/>
	
	</form>
</body>
</html>
