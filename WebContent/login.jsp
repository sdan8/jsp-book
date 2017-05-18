<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>登录</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			.form-login {
				max-width: 330px;
				padding: 15px;
				margin: 0 auto;
			}
			.form-login-heading {
				margin-bottom: 10px;
			}
			.form-login button {
				margin-bottom: 15px;
			}
		</style>
	</head>
	<body>
		<c:if test="${requestScope.message_flag==true }">
			<script type="text/javascript">
				alert('${requestScope.message}');
			</script>
		</c:if>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<form action="<%=contextPath %>/UserServlet?info=login" class="form-horizontal form-login" method="post">
				<div class="form-group text-center">
					<h2 class="form-login-heading">请登录</h2>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					<input type="text" id="inputUsername" class="form-control" name="userName" placeholder="用户名" required autofocus/>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"><span class="fa fa-key"></span></span>
					<input type="password" id="inputPassword" class="form-control" name="userPassword" placeholder="密码" required/>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-md-6">
							<button type="submit" class="btn btn-primary btn-block">登录</button>	
						</div>
						<div class="col-md-6">
							<a href="signup.jsp" class="btn btn-default btn-block">注册</a>
						</div>
					</div>
				</div>
			</form>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>