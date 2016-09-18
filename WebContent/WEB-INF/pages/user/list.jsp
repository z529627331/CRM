<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="/CRM" + "/user/create";
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form action="${ctp }/user/list" method="post">
		<div class="page_title">
			用户管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_name" />
				</td>
				<th class="input_title">
					状态
				</th>
				<td class="input_content">
					<select name="search_EQ_enabled">
						<option value="">
							全部
						</option>
						<option value="1">
							正常
						</option>
						<option value="0">
							已删除
						</option>
					</select>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" style="width: 40px;">
						编号
					</th>
					<th class="data_title" style="width: 50%;">
						用户名
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="user">
					<tr>
						<td class="data_cell" style="text-align: right; padding: 0 10px;">
						${user.id}
						</td>
						<td class="data_cell" style="text-align: center;">
						${user.name}
						</td>
						<td class="data_cell" align="center">
							<c:if test="${user.enabled==0}">
								无效
							</c:if>
							<c:if test="${user.enabled==1}">
								有效
							</c:if>
						</td>
						<td class="data_cell">
							<img onclick="window.location.href='delete/${user.id}?_method=DELETE'" 
								title="删除" src="${ctp }/static/images/bt_del.gif" class="op_button" />
							<img onclick="window.location.href='create/${user.id}'" 
								class="op_button" src="${ctp }/static/images/bt_edit.gif" title="编辑" />
						</td>
					</tr>
				</c:forEach>
					
				<tag:page page="${page}"></tag:page>	
			</table>
			
	</form>
</body>
</html>
