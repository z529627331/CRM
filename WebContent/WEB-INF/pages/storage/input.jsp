<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>编辑库存</title>
</head>

<body class="main">

	<span class="page_title">编辑库存</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
	
	<form:form action="${ctp}/storage/insert" method="POST" modelAttribute="storage">
		<c:if test="${storage.id!=null}">
			<input type="hidden" name="_method" value="PUT"/>
			<form:hidden path="id"/>
		</c:if>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					产品
				</th>
<!-- 					<td> -->
<!-- 						<select name=""> -->
<%-- 							<c:forEach items="${list}" var="product"> --%>
								
<%-- 									<option <c:if test="${product.id==storage.product.id}">selected='selected'</c:if>>${product.name}</option> --%>
								
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<!-- 					</td> -->
					<td>
						<c:if test="${storage.id!=null}">
							<form:select path="product.id" disabled="true">
								<c:forEach items="${list}" var="product">
									<form:option value="${product.id}" label="${product.name}"/>
								</c:forEach>
							</form:select>
						</c:if>
						<c:if test="${storage.id==null}">
							<form:select path="product.id" >
								<c:forEach items="${list}" var="product">
									<form:option value="${product.id}" label="${product.name}"/>
								</c:forEach>
							</form:select>
						</c:if>
						
					</td>
				
			</tr>
			<tr>
				<th>仓库</th>
				<td>
					<c:if test="${storage.id!=null}">
						<form:input path="wareHouse" readonly="true"/>	
					</c:if>
					<c:if test="${storage.id==null}">
						<form:input path="wareHouse" />	
					</c:if>
				</td>
			</tr>
			<tr>
				<th>货位</th>
				<td>
					<c:if test="${storage.id!=null}">
						<form:input path="stockWare" readonly="true"/>
					</c:if>
					<c:if test="${storage.id==null}">
						<form:input path="stockWare" />
					</c:if>
					
				</td>
			</tr>
			<tr>
				<th>数量</th>
				<td><form:input path="stockCount" /></td>
			</tr>
			<tr>
				<th>备注</th>
				<td><c:if test="${storage.id!=null}">
						<form:input path="memo" readonly="true"/>
					</c:if>
					<c:if test="${storage.id==null}">
						<form:input path="memo" />
					</c:if></td>
			</tr>
			
			
		</table>
	</form:form>

</body>
</html>
