<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>评论</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			h1 {
				font-size: 25px;
				margin-bottom: 30px;
			}
			.aside {
				margin-top: 50px;
				color: #999999;
				font-size: 12px;
			}
			.book {
				margin-top: 30px;
			}
		</style>
	</head>
	<body>
		<c:if test="${login_flag!=true}">
			<%response.sendRedirect("login.jsp"); %>
		</c:if>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row">
				<div class="col-md-offset-1 col-md-7">
					<h1><strong>评论 ${book.bookTitle }</strong></h1>
					<form action="<%=contextPath %>/ReviewServlet?info=add" method="post">
						<div class="form-group">
							<label for="inputTitle">题目</label>
							<input type="text" class="form-control" id="inputTitle" name="commentTitle"/>
						</div>
						<div class="form-group">
							<label for="inputComment">正文</label>
							<textarea class="form-control" rows="15" name="commentText" maxlength="255"></textarea>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary">发表</button>
						</div>
						<input type="hidden" value="${book.bookId }" name="bookId" />
					</form>
				</div>
				
				<div class="col-md-3 aside">
					<p>为了尊重创作者的劳动，请不要转载他人文章或提供下载信息。</p>
					<div class="book">
						<h5><a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}"><span class="fa fa-angle-right"></span> ${book.bookTitle }</a></h5>
						<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}"><img src="img/${book.bookPic }" class="img-responsive" width="128px"/></a>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>