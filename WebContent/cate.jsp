<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>
			<c:if test="${id==1 }">小说</c:if>
			<c:if test="${id==2 }">童话</c:if>
			<c:if test="${id==3 }">随笔</c:if>
			<c:if test="${id==4 }">名著</c:if>
		</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			h1 {
				margin-bottom: 30px;
			}
			.media-list li {
				margin: 0;
				padding: 15px;
				border-top: 1px dashed #ddd;
			}
			.media-list li:hover {
				background: #F8F8F8;
				box-shadow: 1px 1px 5px rgba(50, 50, 50, 0.3);
			}
			.media-left img {
				height: 150px;
			}
			/*标题*/
			.media-heading {
				font-size: 17px;
				margin-bottom: 10px;
			}
			/*作者出版社等信息*/
			.media-body .pub {
				font-size: 13px;
			}
			/*简介*/
			.media-body .describe {
				font-size: 13px;
			}
			/*评分*/
			.rating {
				color: #ffac2d;
				margin-bottom: 15px;
			}
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<h1>
				<c:if test="${id==1 }">小说</c:if>
				<c:if test="${id==2 }">童话</c:if>
				<c:if test="${id==3 }">随笔</c:if>
				<c:if test="${id==4 }">名著</c:if>
			</h1>
			
			<ul class="media-list">
				<c:forEach items="${bookList }" var="book">
					<li class="media">
						<div class="media-left">
							<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}">
								<img class="media-object" src="img/${book.bookPic }"/>
							</a>
						</div>
						<div class="media-body">
							<h4 class="media-heading"><a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}">${book.bookTitle }</a></h4>
							<p class="pub">${book.bookAuthor } / ${book.bookPub } / ${book.bookPubDate }</p>
							<p class="describe">${book.bookContent }</p>
							<div class="btn-group btn-group-sm" role="group">
								<a href="<%=contextPath %>/ReviewServlet?info=review&id=${book.bookId}" type="button" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span>&nbsp;评论</a>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>