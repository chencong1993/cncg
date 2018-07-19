<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<script type="text/javascript">
function postSubmit(){
	post($("#saveForm").attr("action"),$("#saveForm").serialize(),1);
}
</script>
</head>
<body>
	<div class="container" style="margin-top:30px;">
		<form id="saveForm" class="form-horizontal" action="${ctx}/role/save">
			<input type="hidden" name="roleId" value="${role.roleId}">
			<div class="control-group">
				<label class="control-label" for="roleName">角色名</label>
				<div class="controls">
					<input type="text" id="roleName" name="roleName" class="input-medium" placeholder="角色名" value="${role.roleName}">
				</div>
	  		</div>
			<div class="control-group">
				<label class="control-label" for="roleDescription">备注</label>
				<div class="controls">
					<textarea rows="3" id="roleDescription" name="roleDescription" placeholder="备注">${role.roleDescription}</textarea>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<button class="btn" onclick="postSubmit();return false;" >确定</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>