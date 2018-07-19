<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>菜单树</title>
<style type="text/css">
#treeDemo{
	height:98%;
}
</style>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};
	
	var zNodes =eval('${treeStr}');
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//获取zTree type = { "Y":"ps", "N":"ps"} Y:选中 N:取消 p:关联父级 s:关联子级
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),type = { "Y":"p", "N":"p"};
		zTree.setting.check.chkboxType = type;
	});
	
	//弹窗回调方法
	function save(){
		var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var checkedNodes = zTreeObj.getCheckedNodes(true);		//获取ztree复选框/单选框选中的节点
		var menuIds="";
		for(var i=0;i<checkedNodes.length;i++){
			menuIds += "," + checkedNodes[i].id;
        }
		post("${ctx}/role/saveRoleMenu",{roleId:"${param.roleId}",menuIds:menuIds.substring(1)});
	}
</script>
</head>
<body>
	<div class="container" style="margin:0;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>