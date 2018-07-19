<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单管理</title>
<script type="text/javascript">
function postSubmit(){
	post($("#saveForm").attr("action"),$("#saveForm").serialize(),1);
}
$(function(){
	$("#parentName").click(function(){
		confirm("上级菜单","${ctx}/menu/pareMenu?parentId="+$("#parentId").val(),280,360);
	});
});
</script>
</head>
<body>
	<div class="container" style="margin-top:30px;">
		<form id="saveForm" class="form-horizontal" action="${ctx}/menu/save">
			<input type="hidden" name="menuId" value="${menu.menuId}">
			<div class="control-group">
				<label class="control-label" for="menuName">菜单名称</label>
				<div class="controls">
					<input type="text" id="menuName" name="menuName" class="input-medium" placeholder="菜单名称" value="${menu.menuName}">
				</div>
	  		</div>
			<div class="control-group">
				<label class="control-label" for="parentId">上级</label>
				<div class="controls">
					<input type="text" id="parentName" name="parentName" class="input-medium" placeholder="上级" value="${menu.parentName}">
					<input type="hidden" id="parentId" name="parentId" class="input-medium" value="${menu.parentId}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="iconCls">菜单图标</label>
				<div class="controls">
					<input type="text" id="iconCls" name="iconCls" class="input-medium" placeholder="菜单图标" value="${menu.iconCls}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="menuPath">链接地址</label>
				<div class="controls">
					<input type="text" id="menuPath" name="menuPath" class="input-medium" placeholder="链接地址" value="${menu.menuPath}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="menuDescription">备注</label>
				<div class="controls">
					<textarea rows="3" id="menuDescription" name="menuDescription" placeholder="备注">${menu.menuDescription}</textarea>
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