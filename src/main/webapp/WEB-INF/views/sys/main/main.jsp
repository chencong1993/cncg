<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
	<title>信息管理系统</title><!--  - Powered By JeeSite -->
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
	<meta name="author" content="http://jeesite.com/">
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10">
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<meta name="decorator" content="blank">
	
	<style type="text/css">
		#main {padding:0;margin:0;} 
		#main .container-fluid{padding:0 4px 0 6px;}
		#header {line-height: 30px;margin:0 0 8px;position:static;} 
		#header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} 
		#left{overflow-x:hidden;overflow-y:auto;} 
		#left .collapse{position:static;}
		/* #userControl>li>a{/*color:#fff;*/text-shadow:none;}  */
		/* #userControl>li>a:hover,  */#user #userControl>li.open>a{cursor: pointer;/* background:transparent; */}
		.navbar .nav>li>a {padding:15px 15px;}
		.nav-list li:hover {cursor: pointer;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			// 
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active");
				$(this).parent().addClass("active");
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// 
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
					// 初始化点击第一个二级菜单
					if (!$(menuId + " .accordion-body:first").hasClass('in')){
						$(menuId + " .accordion-heading:first a").click();
					}
					if (!$(menuId + " .accordion-body li:first ul:first").is(":visible")){
						$(menuId + " .accordion-body a:first i").click();
					}
					// 初始化点击第一个三级菜单
					$(menuId + " .accordion-body li:first li:first a:first i").click();
				}else{
					// 获取二级菜单数据*************************************
					$.get($(this).attr("data-href"), function(data){
						/* if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "/jeesite_ssm/a";
							return false;
						} */
						$("#left .accordion").hide();
						$("#left").append(data);//加载左侧菜单栏
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){   
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).parent().addClass("active");
							$(this).children("i").addClass("icon-white");
							$("#mainFrame").attr("src",$(this).attr("targetUrl"));
						});
						// 展现三级
						/* $(menuId + " .accordion-inner a").click(function(){
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								$(href).toggle().parent().toggle();
				 				return false;
							}
							// 
							return addTab($(this)); 
						}); */
						// 默认选中第一个菜单
						/* $(menuId + " .accordion-body a:first i").click(); */
						$(menuId + " .accordion-body li:first li:first a:first i").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:first span").click();
			// 
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
			
			/* $("#userControl li").click(function(){
				$(this).addClass("active");
			}); */
		});
		
		//退出
		function exit(){
			layer.confirm('是否退出系统？',{title:'确认退出'}, function(index){
		        layer.close(index);
		        location.href="${ctx}/logout";
		    },function(index){
		        layer.close(index);
		        //$("#userControl li").removeClass("active");
		        return false;
		    }); 
		}
		
		function updatePassword(){
			layer.open({
			    title:"修改密码",
			    content:"原密码：<br><input id='oldPassword' /><br>新密码：<br><input id='newPassword' /><br>确认密码：<br><input id='newPassword2' />",
			    btn:['确认'],
			    yes:function(index, layero){
			    	if(layero.find("#newPassword").val() != layero.find("#newPassword2").val()) {
			    		layer.alert("两次输入新密码不一致！");
			    		return ;
			    	}
			        $.get("${ctx}/modifyPassword",{oldPassword:layero.find('#oldPassword').val(),newPassword:layero.find('#newPassword').val()},function(data){
			            layer.alert(data.message);
			        });
			    }
			});
		}
		
		//查询翻页公共函数
		function page(n, s) {
		    $("#pageNo").val(n);
		    $("#pageSize").val(s);
		    $("#searchForm").submit();
		    return false;
		}
		// 
		/* function addTab($this, refresh){
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false;
		} */
	</script>
</head>
<body>
	<div id="main" style="width: auto;">
		<!-- 顶部 -->
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><span id="productName">SSM 快速开发平台</span></div>
				<ul id="userControl" class="nav pull-right">
					<li class="dropdown">
					    <a href="#" class="dropdown-toggle" data-toggle="dropdown">关于 <b class="caret"></b></a>
					    <ul class="dropdown-menu">
					        <li><a href="#" onclick="updatePassword()">修改密码</a></li>
					        <li class="divider"></li>
					        <li><a href="#" onclick="exit()">退出</a></li>
					    </ul>
					</li>
				</ul>
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
						<!-- <li class="menu">
							<a class="menu" href="javascript:" data-href="body.html" data-id="27"><span>我的面板</span></a>
						</li> -->
						<c:forEach items="${menus }" var="menu">
							<li class="menu active">
								<c:if test="${menu.parentId eq '-1' }">
								<a class="menu" href="javascript:" data-href="${ctx}/menu/mainMenu?parentId=${menu.menuId}" data-id="${menu.menuId}"><span>${menu.menuName }</span></a>
								</c:if>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
	    </div>
		<!-- 容器 -->
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left" style="width: 160px; height: 411.991px;">
				</div>
				
				<div id="openClose" class="close" style="height: 407px;">&nbsp;</div>
				<div id="right" style="height: 411.991px; width: 1223px;">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow: visible; height: 411.991px;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe>
				</div>
			</div>
			<!-- 底部 -->
		    <div id="footer" class="row-fluid">
	            Copyright © 2016-2018 MVNSSM 快速开发平台 - Powered By MVNSSM V1.0.0
			</div>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// 
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}
	</script>
	<script src="${ctxStatic }/plugin/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>