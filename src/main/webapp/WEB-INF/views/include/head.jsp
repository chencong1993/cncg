<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>

<!-- jquery插件 -->
<script src="${ctxStatic}/plugin/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/plugin/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/plugin/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/plugin/jquery-select2/3.4/select2.min.css" rel="stylesheet">
<link href="${ctxStatic}/plugin/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet">

<!--tip提示-->
<%-- <script type="text/javascript" src="${ctxStatic}/js/jquery.pure.tooltips.js"></script>
<link href="${ctxStatic}/css/tooltips.css" rel="stylesheet" />
<script type="text/javascript" src="${ctxStatic}/plugin/jquery-tips/justTools.js"></script>
<link href="${ctxStatic}/plugins/jquery-tips/just-tip.css" rel="stylesheet" /> --%>

<link href="${ctxStatic}/plugin/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet">
<script src="${ctxStatic}/plugin/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lte IE 7]><link href="${ctxStatic}/plugin/bootstrap/2.3.1/awesome/font-awesome-ie7.min.css" type="text/css" rel="stylesheet" /><![endif]-->
<!--[if lte IE 6]><link href="${ctxStatic}/plugin/bootstrap/bsie/css/bootstrap-ie6.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/plugin/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->

<!-- layer弹窗 -->
<script type="text/javascript" src="${ctxStatic}/plugin/layer/layer.js" ></script>

<!--日期插件-->
<script src="${ctxStatic}/plugin/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${ctxStatic}/plugin/My97DatePicker/skin/WdatePicker.css" rel="stylesheet" type="text/css">

<!--树形插件-->
<script type="text/javascript" src="${ctxStatic}/plugin/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/plugin/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.min.js"></script>
<link href="${ctxStatic}/plugin/jquery-ztree/3.5.12/css/zTreeStyle/zTreeStyle.min.css" type="text/css" rel="stylesheet" />

<!-- 主界面样式 -->
<script src="${ctxStatic}/plugin/vue-2.5.16/dist/vue.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/plugin/common/jeesite.min.css" type="text/css" rel="stylesheet">
<script src="${ctxStatic}/plugin/common/jeesite.min.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="${ctxStatic}/core/css/common.css">
<script type="text/javascript" src="${ctxStatic}/core/js/common.js" ></script>
<script type="text/javascript"> var ctx = '${ctx}', ctxStatic='${ctxStatic}'; </script>
