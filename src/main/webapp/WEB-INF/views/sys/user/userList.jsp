<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户管理</title>
</head>
<body>
<div class="container">
	<ul class="breadcrumb">
	  <li><a href="#">系统设置</a> <span class="divider">/</span></li>
	  <li><a href="#">权限管理</a> <span class="divider">/</span></li>
	  <li class="active">用户管理</li>
	</ul>
	<form id="searchForm" class="form-search" action="${ctx}/user">
		<table>
			<tr>
				<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
				<td>用户名：</td>
				<td>
					<input type="text" class="input-medium" placeholder="用户名" name="userName" 
					onkeydown="if(event.keyCode==13) $('#searchForm').submit();"
					value="${param.userName}"/>
				</td>
				<td>用户角色：</td>
				<td>
					<select name="roleId" style="width:150px;">
						<c:forEach items="${roles}" var="item">
							<option value="${item.roleId}" ${param.roleId eq item.roleId ? 'selected':''}>${item.roleName}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<div class="btn-group">
						<button class="btn" onclick="add('');return false;"><i class="icon-plus"></i>&nbsp;添加</button>
						<button class="btn" onclick="edit();return false;"><i class="icon-edit"></i>&nbsp;修改</button>
						<button class="btn" onclick="del();return false;"><i class="icon-trash"></i>&nbsp;删除</button>
						<button class="btn" onclick="$('#searchForm').submit()"><i class="icon-search"></i>&nbsp;搜索</button>
					</div>
				</td>
			</tr>
		</table>		
	</form>
	<table class="table table-bordered">
	    <thead>
	    	<tr>
	    		<th width="1%"><input type="checkbox" name="ckAll"/></th>
	    		<th width="10%">编号</th>
	    		<th width="10%">用户名</th>
	    		<th width="10%">用户密码</th>
	    		<th style="display:none">用户角色ID</th>
	    		<th width="15%">用户角色</th>
	    		<th width="30%">备注</th>
	    	</tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${page.list}" var="item">
	    		<tr>
	    			<td><input type="checkbox" name="ckChild" value="${item.userId}"/></td>
	    			<td>${item.userId}</td>
	    			<td>${item.userName}</td>
	    			<td>${item.password}</td>
	    			<td style="display:none">${item.roleId}</td>
	    			<td>${item.role.roleName}</td>
	    			<td>${item.userDescription}</td>
	    		</tr>
	    	</c:forEach>
	    </tbody>
	</table>
   	<div class="pagination">${page}</div>
</div>
</body>
<script type="text/javascript">

function del(){
	var ids = selectIds();
	if(ids){
		layer.confirm('确认执行该操作么?', function(index){
			get("${ctx}/user/delete",{ids:ids});
			layer.close(index);
		});
	}
}

function edit(){
	var id = selectId();
	if(id)
		add(id);
}

function add(id){
	dialog("人员编辑","${ctx}/user/form?userId="+id,500,400);
}

</script>
</html>