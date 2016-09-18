<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加/修改用户</title>
  </head>

  <body class="main">
  	<form:form action="${ctp}/user/input" method="post" modelAttribute="user">
  		<c:if test="${requestScope.user.id!=null }">
  			<span class="page_title">用户管理　&gt;　修改用户</span>
  			<form:input path="id" type="hidden"/>
  			<input name="_method" value="PUT" type="hidden"/>
  			
  		</c:if>
  		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[0].submit()">保存</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">用户名</th>
				<td class="input_content">
					<form:input path="name"/>
					<div id='divCheck'></div>
				</td>
				
				<th class="input_title">密码</th>
				<td class="input_content">
					<form:input path="password"/>
				</td>
			</tr>
			<tr>
				<th class="input_title">角色</th>
				<td class="input_content">
					<form:select path="role.id">
						<c:forEach items="${roles}" var="role">
							<form:option value="${role.id}" label="${role.name}"/>
						</c:forEach>
					</form:select>
				</td>
				<th class="input_title">状态</th>
				<td class="input_content">
					有效<form:checkbox value="1" path="enabled" title="有效" />
					无效<form:checkbox value="0" path="enabled" title="无效"/>
				</td>
			</tr>
		</table>
  	</form:form>
  	
	
  </body>
</html>
