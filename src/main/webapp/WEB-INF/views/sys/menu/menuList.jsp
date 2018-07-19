<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>菜单管理</title>
</head>
<body>
<div class="container">
	<ul class="breadcrumb">
	  <li><a href="#">系统设置</a> <span class="divider">/</span></li>
	  <li><a href="#">权限管理</a> <span class="divider">/</span></li>
	  <li class="active">菜单管理</li>
	</ul>
	<form id="searchForm" class="form-search" action="${ctx}/menu">
		<table>
			<tr>
				<input type="hidden" id="pageNo" name="pageNo" value="${page.pageNo}"/>
				<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}"/>
				<td>菜单名：</td>
				<td>
					<input type="text" class="input-medium" placeholder="菜单名" name="menuName" 
					onkeydown="if(event.keyCode==13) $('#searchForm').submit();"
					value="${param.menuName}"/>
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
	    		<th width="10%">菜单编号</th>
	    		<th width="10%">菜单名称</th>
	    		<th width="15%">菜单图标</th>
	    		<th width="15%">链接地址</th>
	    		<th width="30%">备注</th>
	    	</tr>
	    </thead>
	    <tbody>
	    	<c:forEach items="${page.list}" var="item">
	    		<tr>
	    			<td><input type="checkbox" name="ckChild" value="${item.menuId}"/></td>
	    			<td>${item.menuId}</td>
	    			<td>${item.menuName}</td>
	    			<td>${item.iconCls}</td>
	    			<td>${item.menuPath}</td>
	    			<td>${item.menuDescription}</td>
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
			get("${ctx}/menu/delete",{ids:ids});
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
	dialog("菜单编辑","${ctx}/menu/form?menuId="+id,500,400);
}

</script>
</html>