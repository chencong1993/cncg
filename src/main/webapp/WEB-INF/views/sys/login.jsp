<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<html>
<head>
<title>信息管理系统登录</title>
<script src="${ctxStatic}/plugin/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<style type=text/css>
	body {
		text-align: center;
		padding-bottom: 0px;
		background-color: #ddeef2;
		margin: 0px;
		padding-left: 0px;
		padding-right: 0px;
		padding-top: 0px
	}
	
	a:link {
		color: #000000;
		text-decoration: none
	}
	
	a:visited {
		color: #000000;
		text-decoration: non
	}
	
	a:hover {
		color: #ff0000;
		text-decoration: underline
	}
	
	a:active {
		text-decoration: none
	}
	
	.input {
		border-bottom: #ccc 1px solid;
		border-left: #ccc 1px solid;
		line-height: 20px;
		width: 182px;
		height: 20px;
		border-top: #ccc 1px solid;
		border-right: #ccc 1px solid
	}
	
	.input1 {
		border-bottom: #ccc 1px solid;
		border-left: #ccc 1px solid;
		line-height: 20px;
		width: 120px;
		height: 20px;
		border-top: #ccc 1px solid;
		border-right: #ccc 1px solid;
	}
</style>
</head>
<body>
<form id="adminlogin" method="post" name="adminlogin">
<table style="margin: auto; width: 100%; height: 100%; border:0px" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td height="150px">&nbsp;</td>
		</tr>
		<tr style="height: 254px">
			<td>
			<div style="margin: 0px auto; width: 936px"><img style="display: block" src="${ctxStatic}/core/img/body_03.jpg"></div>
			<div style="background-color: #278296">
			<div style="margin: 0px auto; width: 936px">
			<div style="background: url(${ctxStatic}/core/img/body_05.jpg) no-repeat; height: 155px">
			<div style="text-align: left; width: 265px; float: right; height: 125px; _height: 95px">
			<table border=0 cellspacing=0 cellpadding=0 width="100%">
				<tbody>
					<tr>
						<td style="height: 45px"><input type="text" class=input  name="userName" id="userName"></td>
					</tr>
					<tr>
						<td><input type="password" class=input  name="password" id="password"/></td>
					</tr>
					<tr>
						<td>
							<select id="roleName" name="roleId" class="input" style="margin-top: 15px;height: 24px">
								<option value="">请选择用户类型...</option>
								<c:forEach items="${roles }" var="role">
									<option value="${role.roleId}" >${role.roleName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			<div style="height: 1px; clear: both"></div>
			<div style="width: 380px; float: right; clear: both">
			<table border=0 cellspacing=0 cellpadding=0 width=300>
				<tbody>
					<tr>
						<td width=100 align=right>
							<input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px"
							id=btnlogin src="${ctxStatic}/core/img/btn1.jpg" type=image name=btnlogin onclick="javascript:login();return false;">
						</td>
						<td width=100 align=middle>
							<input style="border-right-width: 0px; border-top-width: 0px; border-bottom-width: 0px; border-left-width: 0px"
							id=btnreset src="${ctxStatic}/core/img/btn2.jpg" type=image name=btnreset onclick="javascript:adminlogin.reset();return false;">
						</td>
					</tr>
				</tbody>
			</table>
			</div>
			</div>
			</div>
			</div>
			<div style="margin: 0px auto; width: 936px"><img src="${ctxStatic}/core/img/body_06.jpg"></div>
			</td>
		</tr>
		<tr style="height: 30%">
			<td>&nbsp;</td>
		</tr>
	</tbody>
</table>
</form>
</body>
</html>
<script type=text/javascript>

	function login(){
		var userName=$("#userName").val();
		var password=$("#password").val();
		var roleName=$("#roleName").val();
		if(userName==null||userName.trim()==""){
			alert("用户名不能为空！");
			return;
		}
		if(password==null||password.trim()==""){
			alert("密码不能为空！");
			return;
		}
		if(roleName==null||roleName.trim()==""){
			alert("请选择用户类型！");
			return;
		}
		
		$.post("${ctx}/userLogin",$("#adminlogin").serialize(),function(data){
			if(data.status==1){
				console.log("登陆成功");
				location.href="${ctx}/main";
			}else{
				alert(data.message);
			}
		});
	}
</script>