<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
		$(function(){
			$(".save").click(function(){
				var str = $(this).attr("id");
				var strs = str.split("-");
				var id = strs[1];
				var result=$("#result-"+id).val();
				var inputLa=$("#result-"+id);
				var args= {"id":id,"result":result};
				var url="${ctp}/plan/execute"
				$.post(url,args,function(data){
					if("1"==data){
						alert("操作成功");
						$(inputLa).prop("disabled",true);
						$(inputLa).next("button").remove();
					}else{
						alert("操作失败");
					}
					return false;
				});
				return false;
			});
			
		
	});	
			
			
		
	</script>
</head>

<body class="main">
	<span class="page_title">执行计划</span>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/chance/stop?id=150'">终止开发</button>
		<button class="common_button" onclick="window.location.href='${ctp}/plan/make?id=150'">制定计划</button>
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>			
		<button class="common_button" onclick="window.location.href='${ctp}/chance/finish?id=150'">开发成功</button>
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
			<tr>
				<th width="200px">日期</th>
				<th>计划</th>
				<th>执行效果</th>
			</tr>
			<c:forEach items="${list}" var="plan">
				<tr>
					<td class="list_data_text">
						<fmt:formatDate value="${plan.date }"/>&nbsp;
					</td>
					<td class="list_data_ltext">${plan.todo }&nbsp;</td>
					
					<c:if test="${plan.result!=null }">
						<td>
						<input class="result" id="result-152" type="text" size="50" disabled="disabled" value="${plan.result}"/>
						
						</td>
					</c:if>
					<c:if test="${plan.result==null }">
						<td>
							<input class="result" id="result-${plan.id }" 
							type="text" size="50" value="${plan.result}"/>
							<button class="save" id="saveresult-${plan.id }">保存</button>
						</td>
					</c:if>
					
					
				</tr>
			</c:forEach>
				
			
				
				
		</table>	

	
</body>
</html>
