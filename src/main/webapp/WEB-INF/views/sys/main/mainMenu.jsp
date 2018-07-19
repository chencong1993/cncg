<%-- <%@ page contentType="text/html;charset=UTF-8" %> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
	<div class="accordion" id="menu-${param.parentId}">
	<c:set var="firstMenu" value="true"/>
	<c:forEach items="${menus}" var="menu" varStatus="idxStatus">
	<c:if test="${menu.parentId eq param.parentId}">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${param.parentId}" data-href="#collapse-${menu.menuId}" href="#collapse-${menu.menuId}" title="${menu.menuDescription}"><i class="icon-chevron-${not empty firstMenu && firstMenu ? 'down' : 'right'}"></i>&nbsp;${menu.menuName}</a>
		    </div>
		    <div id="collapse-${menu.menuId}" class="accordion-body collapse ${not empty firstMenu && firstMenu ? 'in' : ''}">
				<div class="accordion-inner">
					
					<ul class="nav nav-list">
					<c:forEach items="${menus}" var="menu2">
					<c:if test="${menu2.parentId eq menu.menuId}">
						<li>
							<a data-href=".menu3-${menu2.menuId}" targetUrl="${ctx}/${not empty menu2.menuPath ? menu2.menuPath : '/404'}"><i class="icon-circle-arrow-right"></i>&nbsp;${menu2.menuName}</a>
							
							<%-- <ul class="nav nav-list hide" style="margin:0;padding-right:0;">
								<c:forEach items="${menus}" var="menu3">
									<c:if test="${menu3.parentId eq menu2.menuId}">
										<li class="menu3-${menu2.menuId} hide"><a href="${ctx}${not empty menu3.menuPath ? menu3.menuPath : '/404'}"><i class="icon-circle-arrow-right"></i>&nbsp;${menu3.menuName}</a></li>
									</c:if>
								</c:forEach>
							</ul> --%>
							
						</li>
						<c:set var="firstMenu" value="false"/>
					</c:if>
					</c:forEach>
					</ul>
					
				</div>
		    </div>
		</div>
	</c:if>
	</c:forEach></div>