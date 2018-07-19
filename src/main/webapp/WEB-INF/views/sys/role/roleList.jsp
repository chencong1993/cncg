<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色管理</title>
</head>
<body>
<div class="container">
	<ul class="breadcrumb">
	  <li><a href="#">系统设置</a> <span class="divider">/</span></li>
	  <li><a href="#">权限管理</a> <span class="divider">/</span></li>
	  <li class="active">角色管理</li>
	</ul>
	<form id="searchForm" class="form-search" action="${ctx}/role">
		<table>
			<tr>
				<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
				<td>角色名：</td>
				<td>
					<input type="text" class="input-medium" placeholder="角色名" name="roleName" 
					onkeydown="if(event.keyCode==13) $('#searchForm').submit();"
					value="${param.roleName}"/>
				</td>
				<td>
					<div class="btn-group">
						<button class="btn" onclick="apply();return false;"><i class="icon-wrench"></i>&nbsp;授权</button>
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
	    		<th width="15%">角色名称</th>
	    		<th style="display:none">菜单ids</th>
	    		<th width="30%">备注</th>
	    	</tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${page.list}" var="item">
	    		<tr>
	    			<td><input type="checkbox" name="ckChild" value="${item.roleId}"/></td>
	    			<td>${item.roleId}</td>
	    			<td>${item.roleName}</td>
	    			<td style="display:none">${item.menuIds}</td>
	    			<td>${item.roleDescription}</td>
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
			get("${ctx}/role/delete",{ids:ids});
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
	dialog("角色编辑","${ctx}/role/form?roleId="+id,500,400);
}

//授权
function apply(){
	if(selectId()) {
		confirm("角色授权","${ctx}/menu/roleMenu?roleId="+selectId(),280,390);
	}
}
</script>
</html>