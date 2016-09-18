<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <script type="text/javascript">
  	$(function(){
  		$("#confirm").click(function(){
  			var div = $(this).parent();
  			var area = $("textarea").parent().parent();
  			div.empty();
  			area.replaceWith("<tr><th>原因</th>"
					+"<td colspan='3'><textarea name='reason' cols='50' rows='6'></textarea>&nbsp;</td>"
				+"</tr>");
  			div.append("<button class='common_button' onclick='javascript:history.go(-1);'>返回</button>"+
  			"<button class='common_button' onclick='document.forms[0].submit();'>保存</button>");
  			alert(div);
  			return false;
  		});
  	});
  
  </script>
    <title>暂缓流失</title>
  </head>

  <span class="page_title">暂缓流失</span>
  <div class="button_bar">
	<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
	<button id="confirm" class="common_button" >确认流失</button>
	<button class="common_button" onclick="document.forms[0].submit();">保存</button>
  </div>
	
  <body class="main">
	  <form action="${ctp }/drain/delay" method="post">
		  	<input type="hidden" name="id" value="${drain.id}"/>
			<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>编号</th>
					<td>${drain.id}</td>
					<th>客户</th>
					<td>${drain.customer.name }</td>
				</tr>
				<tr>
					<th>客户经理</th>
					<td>${drain.customer.manager.name }</td>
					<th>最后一次下单时间</th>
					<td>
						<fmt:formatDate value="${drain.lastOrderDate }"/>
					</td>
				</tr>
				<c:if test="${drain.delayStr!=null }">
					<c:forEach items="${drain.delayStr}" var="str" varStatus="s" >
						<tr>
							<th>暂缓措施-${s.index+1 }</th>
							<td colspan="3">${str }</td>
						</tr>
					</c:forEach>
				</c:if>
				
				<tr>
					<th>追加暂缓措施</th>
					<td colspan="3"><textarea name="delay" cols="50" rows="6"></textarea>&nbsp;</td>
				</tr>
			</table>
	   </form>	
  </body>
</html>