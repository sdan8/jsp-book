<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看书籍</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			.btn-group {
				margin-bottom: 10px;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="form">
				<h2 class="theme-color">所有书籍</h2>
				<div class="row">
					<div class="col-md-12">
						<table class="table table-striped">
							<tr>
								<th>书籍ID</th>
								<th>书籍名称</th>
								<th>作者</th>
								<th>出版社</th>
								<th>出版日期</th>
							</tr>
							<tbody id="list-content">
								<c:forEach items="${list }" var="book">
									<tr>
										<td>${book.bookId }</td>
										<td>${book.bookTitle }</td>
										<td>${book.bookAuthor }</td>
										<td>${book.bookPub }</td>
										<td>${book.bookPubDate }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>