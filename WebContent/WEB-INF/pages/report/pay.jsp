<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ include file="/commons/common.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户贡献分析</title>
<link rel="stylesheet" type="text/css" href="${ctp }/static/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctp }/static/easyui/themes/icon.css">
	<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="${ctp }/static/jquery/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('.easyui-datebox').datebox({
			    formatter:function (date){
			    	var y = date.getFullYear();
			    	var m = date.getMonth()+1;
			    	var d = date.getDate();
			    	return y+"-"+m+"-"+d;
			    }
			});


		});
	</script>
</head>
<body>

	<div class="page_title">
		客户贡献分析
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="document.forms[0].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/report/pay">
		<div id="listView" style="display:block;">
			<table class="query_form_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						客户名称
					</th>
					<td>
						<input type="text" name="search_LIKE_custName" />
					</td>
					<th>
						日期
					</th>
					<td>
						<input id="date1" type="text" class="easyui-datebox" name="search_D_minDate" />
						-
						<input id="date2" type="text" class="easyui-datebox" name="search_D_maxDate" />
					</td>
				</tr>
			</table>
			<!-- 列表数据 -->
			<br />
			
			<c:if test="${!empty page}">
				<table class="data_list_table" border="0" cellPadding="3"
					cellSpacing="0">
					<tr>
						
						<th>
							客户名称
						</th>
						<th>
							订单金额（元）
						</th>
					</tr>
					
					<c:forEach var="customer" items="${page.content }">
						<tr>
							
							<td align="center">
								${customer.custName}
							</td>
							<td align="center">
								${customer.totalMoney }
							</td>
		
						</tr>
					</c:forEach>
				</table>
				<tag:page page="${page }" queryString="${queryString }"></tag:page>
			</c:if>
			<c:if test="${empty page}">
				没有任何数据
			</c:if>
		</div>
	</form>
</body>
</html>