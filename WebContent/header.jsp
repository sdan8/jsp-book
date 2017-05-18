<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<header>
	<!--导航开始-->
	<nav class="navbar navbar-default">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
		    	</button>
				<a class="navbar-brand" href="index.jsp">Book</a>
			</div>
	
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active">
						<a href="index.jsp">首页 <span class="sr-only">(current)</span></a>
					</li>
					<li><a href="<%=contextPath%>/ShowServlet?info=cate&id=1">小说</a></li>
					<li><a href="<%=contextPath%>/ShowServlet?info=cate&id=2">童话</a></li>
					<li><a href="<%=contextPath%>/ShowServlet?info=cate&id=3">随笔</a></li>
					<li><a href="<%=contextPath%>/ShowServlet?info=cate&id=4">名著</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${login_flag!=true}">
						<li><a href="<%=contextPath%>/login.jsp">登录</a></li>
					</c:if>
					<c:if test="${login_flag==true}">
						<li><a href="<%=contextPath %>/user.jsp"><span class="fa fa-user"></span>&nbsp;${user.userName }</a></li>
						<li><a href="<%=contextPath %>/UserServlet?info=exit">退出</a></li>
					</c:if>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!--导航结束-->
</header>
