<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
		$(function(){
			
			$("#new").click(function(){
				 var todo = $("#todo").val();
				 var date = $("#date").val();
				var url = "${ctp}/plan/make";
				var chanceId = $("#chanceId").val();
				var args={"todo":todo,"date":date,"chance.id":chanceId};
				$.post(url,args,function(data){
					var id= parseInt(data);
					if(id>0){
						alert("操作成功");
						var $tr = $("<tr></tr>");
						$tr.append("<td class='list_data_text'>"+date+"&nbsp;</td>")
							.append("<td><input type='text' size='50' value='"+todo+"' id='todo-"+id+"'/>"+"<button class='save' id="+id+">保存</button>"+"<button class='delete' id="+id+">删除</button></td>")
							.insertAfter("#old");
						
						
						$tr.find(".save").click(function(){
							save(this);
							return false;
						});
						$tr.find(".delete").click(function(){
							dele(this);
							return false;
						});
					}
				});
				return false;
			});
			function save(button){
				var id= $(button).attr("id");
				var todo = $("#todo-"+id).val();
				var url = "${ctp}/plan/update";
				var args = {"id":id,"todo":todo};
				$.post(url,args,function(data){
					if("1"==data){
						alert("修改成功!");
					}else{
						alert("修改失败!");
					}
				});
				return false;
			}
			
			
			function dele(button){
				var id= $(button).attr("id");
				var url = "${ctp}/plan/delete";
				var args = {"id":id};
				var tr = $(button).parent().parent();
				$.post(url,args,function(data){
					if("1"==data){
						alert("修改成功!");
						tr.remove();
					}else{
						alert("修改失败!");
					}
			});
				return false;
			}
		$(".delete").click(function(){
			dele(this);
			return false;
		});
		$(".save").click(function(){
			save(this);
			return false;
		});	
		$("#execute").click(function(){
			window.location.href="${ctp}/plan/execute/"+${chance.id};
			return false;
		});
		
		
	});	
			
			
		
	</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute" >
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
		
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
	
						<td>
						${chance.id}
						</td>
					<th>
						机会来源
					</th>
	
					<td>
						${chance.source}
					</td>
				</tr>
				<tr>
					<th>
						客户名称
					</th>
					<td>
						${chance.custName}
					</td>
					<th>
						成功机率（%）
					</th>
	
					<td>
						${chance.rate}
					</td>
				</tr>
				<tr>
					<th>
						概要
					</th>
					<td colspan="3">
						${chance.title}
					</td>
				</tr>
				<tr>
					<th>
						联系人
					</th>
	
					<td>
						${chance.contact}
					</td>
					<th>
						联系人电话
					</th>
	
					<td>
						${chance.contactTel}
					</td>
				</tr>
				<tr>
					<th>
						机会描述
					</th>
					<td colspan="3">
						${chance.description}
					</td>
				</tr>
				<tr>
					<th>
						创建人
					</th>
					<td>
						${chance.createBy.name}
					</td>
					<th>
						创建时间
					</th>
					<td>
					<fmt:formatDate value="${chance.createDate}"/>
					</td>
				</tr>
				<tr>
					<th>
						指派给
					</th>
					<td>
						${chance.designee.name}
					</td>
	
				</tr>
			</table >
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
			<tr id="old">
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>
			
			<c:forEach items="${list}" var="plan">
				<tr >
					<td class="list_data_text">
						<fmt:formatDate value="${plan.date}"/>
						&nbsp;
					</td>
					<td class="list_data_ltext">
						<c:if test="${plan.result!=null}">
							<input type="text" size="50" value="${plan.result}" readonly="readonly"/>
							<input type="text" size="50" value="${plan.todo}" readonly="readonly"/>
						</c:if>
						<c:if test="${plan.result==null}">
							<input type="text" size="50" value="${plan.todo}" id="todo-${plan.id}"/>
							<button class="save" id="${plan.id}">保存</button>
							<button class="delete" id="${plan.id}">删除</button>
						</c:if>	
						
					</td>
				</tr>
			</c:forEach>
		</table>
		<form method="post"   >
		<input id="chanceId" type="hidden" name="chance.id" value="${chance.id}" />
		<div class="button_bar">
			<button id="new" class="common_button">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="150" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr >
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
