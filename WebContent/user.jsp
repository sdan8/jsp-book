<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
	</head>
	<body>
		<c:if test="${login_flag!=true}">
			<%response.sendRedirect("login.jsp"); %>
		</c:if>
		<jsp:include page="header.jsp"></jsp:include>
		<section class="main container">
			<div class="text-center">
				<h1>个人中心</h1>
				<br />
			</div>
			<!-- 侧边栏 -->
			<div class="sidebar col-md-2">
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					<div class="panel panel-default">
						<!-- 折叠标题 -->
						<!-- 个人资料 -->
						<div class="panel-heading" role="tab" id="headingOne">
							<h4 class="panel-title">
								<a href="#collapseSetting" role="button" data-toggle="collapse" data-parent="#accordion" aria-expanded="true" aria-controls="collapseSetting">帐号管理&nbsp;<span class="fa fa-angle-double-down pull-right"></span></a>
							</h4>
						</div>
						<!-- 折叠标题结束 -->
						<!-- 折叠内容 -->
						<div id="collapseSetting" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
							<div class="list-group">
								<a href="user/psw.jsp" target="iframe" class="list-group-item active">修改密码</a>
								<a href="user/avatar.jsp" target="iframe" class="list-group-item">上传头像</a>
							</div>
						</div>
						<!-- 折叠内容结束 -->
					</div>
				</div>
			</div>
			<!-- 侧边栏结束 -->
			<!-- 右侧内容 -->
			<div class="content col-md-10">
				<div class="iframe">
					<div class="embed-responsive embed-responsive-16by9">
						<iframe class="embed-responsive-item" name="iframe" src="user/psw.jsp">您的浏览器版本过低，请升级新版本浏览器！</iframe>
					</div>
				</div>
			</div>
			<!-- 右侧内容结束 -->
		</section>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		var taga = $(".list-group a");
		taga.on('click', function (){
			taga.removeClass("active");
			$(this).addClass("active");
		});
	</script>
</html>