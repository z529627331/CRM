<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctp}/chance/input";
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form id="command" action="${ctp}/chance/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
				<th class="input_title">
					创建时间
				</th>
				<td class="input_content">
					<input type="text" name="search_D_minCreateDate" />
					-
					<input type="text" name="search_D_maxCreateDate" />
				</td>
				<th class="input_title">
					成功几率
				</th>
				<td class="input_content">
					<input type="text" name="search_minRate" />
					-
					<input type="text" name="search_maxRate" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		<c:if test="${empty page.content }">
		沒有任何記錄
		</c:if>
		<c:if test="${!empty page.content }">
		</c:if>
		
		<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<th>
					客户名称
				</th>
				<th>
					概要
				</th>
				<th>
					联系人
				</th>
				<th>
					联系人电话
				</th>
				<th>
					创建时间
				</th>
				<th>
					操作
				</th>
			</tr>
			<c:forEach items="${page.content }" var="item">
				<tr>
					<td class="list_data_number">${item.id }</td>
					<td class="list_data_text">${item.custName }</td>
					<td class="list_data_text">${item.title }</td>
					<td class="list_data_text">${item.contact }</td>
					<td class="list_data_text">${item.contactTel }</td>
					<td class="list_data_text">
						<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
					</td>
					<td class="list_data_op">
						<img onclick="window.location.href='${ctp}/chance/dispatch?${item.id }'" 
							title="指派" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
						<img onclick="window.location.href='${ctp}/chance/save/${item.id }'" 
							title="编辑" src="${ctp}/static/images/bt_edit.gif"
							class="op_button" />
						<img onclick="window.location.href='${ctp}/chance/delete/${item.id}?_method=DELETE'" 
							title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
					</td>
				</tr>
			</c:forEach>
		</table>

		
		
		<tag:page page="${page }" queryString="${queryString }"/>
		
		
	</form>
	
</body>
</html>
