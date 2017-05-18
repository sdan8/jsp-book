<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>书名</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			.media h2{
				margin-top: 0;
				margin-bottom: 20px;
			}
			.des {
				text-indent: 2em;
				font-size: 16px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="col-md-offset-2 col-md-8">
				<div class="media">
					<div class="media-left">
						<img src="img/${book.bookPic }" class="media-object" height="200px"/>
					</div>
					<div class="media-body">
						<h2 class="media-heading">${book.bookTitle }</h2>
						<h4>作者：${book.bookAuthor } </h4>
						<h4>出版社: ${book.bookPub }</h4>
						<h4>出版日期: ${book.bookPubDate }</h4>
						<div class="btn-group btn-group-sm" role="group">
							<a href="<%=contextPath %>/ReviewServlet?info=review&id=${book.bookId}" type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>&nbsp;评论</a>
						</div>
					</div>
				</div>
				<br />
				<h3>内容简介</h3>
				<p class="des">${book.bookContent }</p>
				<br />
				<h3>评论</h3>
				<div class="comments-main">
					<ul class="media-list">
						<c:forEach items="${commentList }" var="comment">
							<li class="media">
								<div class="media-left">
									<img class="media-object" src="img/${comment.user.userAvatar }" width="50px" height="50px"/>
								</div>
								<div class="media-body">
									<span>${comment.user.userName }：</span><strong>${comment.commentTitle }</strong>
									<p>${comment.commentText }</p>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>