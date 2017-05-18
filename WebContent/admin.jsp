<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>后台登录</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			.container {
				padding-top: 30px;
			}
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
		<div class="container">
			<form action="AdminServlet?info=login" class="form-horizontal form-login" method="post">
				<div class="form-group text-center">
					<h2 class="form-login-heading">后台登录</h2>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
					<input type="text" id="inputUsername" class="form-control" name="adminName" placeholder="用户名" required autofocus/>
				</div>
				<div class="form-group input-group">
					<span class="input-group-addon"><span class="fa fa-key"></span></span>
					<input type="password" id="inputPassword" class="form-control" name="adminPassword" placeholder="密码" required/>
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary btn-block">登录</button>	
				</div>
			</form>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>