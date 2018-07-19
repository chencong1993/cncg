<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script type="text/javascript">
function postSubmit(){
	if($("#roleId").val()=='') {
		layer.alert("角色不能为空！");
		return false;
	}else{
		post($("#saveForm").attr("action"),$("#saveForm").serialize(),1);
	}
}
</script>
</head>
<body>
	<div class="container" style="margin-top:30px;">
		<form id="saveForm" class="form-horizontal" action="${ctx}/user/save">
			<input type="hidden" name="userId" value="${user.userId}">
			<div class="control-group">
				<label class="control-label" for="loginName">登录名</label>
				<div class="controls">
					<input type="text" id="loginName" name="loginName" class="input-medium" placeholder="登录名" value="${user.userName}">
				</div>
	  		</div>
			<div class="control-group">
				<label class="control-label" for="roleId">角色</label>
				<div class="controls">
					<select id="roleId" name="roleId" style="width:150px;">
						<c:forEach items="${roles}" var="item">
							<option value="${item.roleId}" ${user.roleId eq item.roleId ? 'selected':''}>${item.roleName}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="userName">用户名</label>
				<div class="controls">
					<input type="text" id="userName" name="userName" class="input-medium" placeholder="用户名" value="${user.userName}">
				</div>
	  		</div>
			<div class="control-group">
				<label class="control-label" for="password">密码</label>
				<div class="controls">
					<input type="password" id="password" name="password" class="input-medium" placeholder="密码" value="${user.password}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="userDescription">备注</label>
				<div class="controls">
					<textarea rows="3" id="userDescription" name="userDescription" placeholder="备注">${user.userDescription}</textarea>
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