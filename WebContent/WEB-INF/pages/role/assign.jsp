<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/commons/common.jsp" %>   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			//父标签点击事件
			$(":checkbox[name!='authorityIds']").click(function(){
				var id=$(this).attr("id");
				var flag=$(this).prop("checked");
				$("."+id).prop("checked",flag);
			});
			//子标签点击事件
			$(":checkbox[name='authorityIds']").click(function(){
				var parentId=$(this).attr("class");
				var flag=$("."+parentId).length == $("."+parentId+":checked").length;
				$("#"+parentId).prop("checked",flag);
			});
			$(":checkbox[name!='authorityIds']").each(function(){
				var parentId=$(this).attr("id");
				var flag=$("."+parentId).length == $("."+parentId+":checked").length;
				$(this).prop("checked",flag);
			});	
			
		})
	</script>
</head>

<body class="main">
 	
 	
 	<form:form id="role" action="${ctp }/role/assign" method="PUT" modelAttribute="role">
 	
		<input type="hidden" name="id" value="${role.id }" />
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[0].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name}
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description}
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled?"有效":"无效"}
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					<c:forEach var="parent" items="${parentList }" >
						<br>
						<input type="checkbox" id="${parent.id }">${parent.displayName }
						<br/>
						&nbsp;&nbsp;&nbsp;
						<form:checkboxes items="${parent.subAuthorities }" 
							path="authorityIds"  cssClass="${parent.id }"
							itemLabel="displayName" itemValue="id" delimiter="<br>&nbsp;&nbsp;&nbsp;&nbsp;"/>
					</c:forEach>
				</td>
			</tr>
				
			
		</table>
	</form:form>
	
</body>
</html>
