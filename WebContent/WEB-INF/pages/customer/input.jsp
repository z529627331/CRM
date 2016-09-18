<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>编辑客户</title>
</head>

<body class="main">

	<span class="page_title">编辑客户</span>
	
	<div class="button_bar">
		<button class="common_button" onclick="">
			联系人
		</button>
		<button class="common_button" onclick="">
			交往记录
		</button>
		<button class="common_button" onclick="">
			历史订单
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button class="common_button" onclick="document.forms[0].submit();">
			保存
		</button>
	</div>
	
		
		
	<form:form action="${ctp }/customer/list" method="post" modelAttribute="customer">
		<form:input path="id" type="hidden"/>
		<input name="_method" value="put" type="hidden">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					客户编号
				</th>
				<td>
					${customer.no}
					<form:input path="no" type="hidden"/>
				</td>
				<th>
					客户名称
				</th>
				<td>
					<form:input path="name" />
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					地区
				</th>
				<td>
					<form:select path="region">
						<form:options items="${regions }"itemValue="item" itemLabel="item" />
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					客户经理
				</th>
				<td>
					<form:select path="manager.id">
						<form:options items="${contacts }" itemLabel="name" itemValue="id"/>
					</form:select>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					客户等级
				</th>
				<td>
					<form:select path="level" >
						<form:options items="${levels }" var="level" itemValue="item" itemLabel="item"/>
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					客户状态
				</th>
				<td>
					<form:select path="state">
						<form:option value="正常">正常</form:option>
						<form:option value="预警">预警</form:option>
						<form:option value="删除">删除</form:option>
						<form:option value="流失">流失</form:option>
					</form:select>
<!-- 					<select id="state" name="state"><option value="正常">正常</option><option value="预警">预警</option><option value="删除">删除</option><option value="流失">流失</option></select> -->
				</td>
			</tr>
			<tr>
				<th>
					满意度
				</th>
				<td>
					<form:select path="satify">
						<form:options items="${satifies }" var="level" itemLabel="item" itemValue="item" />
					</form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					信用度
				</th>
				<td>
					<form:select path="credit">
						<form:options items="${credits }" var="level" itemLabel="item" itemValue="item"/>
					</form:select>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					地址
				</th>
				<td>
					<form:input path="address" />
					<span class="red_star">*</span>
				</td>
				<th>
					邮政编码
				</th>
				<td>
					<form:input path="zip"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					电话
				</th>
				<td>
					<form:input path="tel"/>
					<span class="red_star">*</span>
				</td>
				<th>
					传真
				</th>
				<td>
					<form:input path="fax"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					网址
				</th>
				<td>
					<form:input path="websit"/>
					<span class="red_star">*</span>
				</td>
				<th>
					&nbsp;
				</th>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>
		<br />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					营业执照注册号
				</th>
				<td>	
					<form:input path="licenceNo"/>
				</td>
				<th>
					法人
				</th>
				<td>
					<form:input path="chief"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					注册资金(万元)
				</th>
				<td>
					<form:input path="bankroll"/>
				</td>
				<th>
					年营业额
				</th>
				<td>
					<form:input path="turnover"/>
				</td>
			</tr>
			<tr>
				<th>
					开户银行
				</th>
				<td>
					<form:input path="bank"/>
					<span class="red_star">*</span>
				</td>
				<th>
					银行账号
				</th>
				<td>
					<form:input path="bankAccount"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					地税登记号
				</th>
				<td>
					<form:input path="localTaxNo"/>
				</td>
				<th>
					国税登记号
				</th>
				<td>
					<form:input path="nationalTaxNo"/>
				</td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>