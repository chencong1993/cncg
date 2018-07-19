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
		},
		check: {
            enable: true,
            chkStyle: "radio",  //单选框
            radioType: "all"   //对所有节点设置单选
        }
	};
	
	var zNodes =eval('${treeStr}');
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//获取zTree type = { "Y":"ps", "N":"ps"} Y:选中 N:取消 p:关联父级 s:关联子级
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),type = { "Y":"", "N":""};
		zTree.setting.check.chkboxType = type;
	});
	
	//弹窗回调方法
	function save(){
		var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var checkedNodes = zTreeObj.getCheckedNodes(true);		//获取ztree复选框/单选框选中的节点
		var menuIds="";
		if(checkedNodes.length > 1)
			layer.alert("请选择一个上级目录！");
		parent.document.getElementById("parentId").value = checkedNodes[0].id;
		parent.document.getElementById("parentName").value = checkedNodes[0].name;
	}
</script>
</head>
<body>
	<div class="container" style="margin:0;">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>