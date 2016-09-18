<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>处理客户服务</title>
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
	<script type="text/javascript">
		$(function(){
			$("#dealDate").val(new Date().format("yyyy-MM-dd"));
		})
	</script>
</head>

<body class="main">
	
	<span class="page_title">处理客户服务</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	<form action="${ctp}/service/do" method="post">
		<input type="hidden" name="id" value="${service.id}"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<td>${service.id}</td>
				<th>
					服务类型
				</th>
				<td>${service.serviceType}</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">${service.serviceTitle}</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>${service.customer.name}</td>
				<th>
					状态
				</th>
				<td>
					已分配
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">${service.serviceRequest}</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>${service.createdby.name}</td>
				<th>
					创建时间
				</th>
				<td>
					<fmt:formatDate value="${service.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>

				<th>
					分配给
				</th>
				<td>${user.name}</td>
				<th>
					分配时间
				</th>
				<td>
					<fmt:formatDate value="${service.allotDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>
				<th>
					服务处理
				</th>
				<td colspan="3">
					<textarea name="serviceDeal" rows="6" cols="50"></textarea>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					处理人
				</th>
				<td>
					${user.name}
					<span class="red_star">*</span>
				</td>
				<th>
					处理时间
				</th>
				<td>
					<input name="dealDate" readonly="readonly" id="dealDate" />
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
