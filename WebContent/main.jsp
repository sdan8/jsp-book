<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>首页</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			a:hover {
				text-decoration: none;
			}
			.body-right {
				margin-top: 20px;
			}
			.bg {
				background-image: url(img/bg1.png)
			}
			.theme-bg {
				background-color: #27AE60;
			}
			.theme-color {
				color: #27AE60;
			}
			.white-color {
				color: #fff;
			}
			.rank-author {
				color: #337ab7;
				background-color: #fff;
				
			}
		</style>
	</head>
	<body>
		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="row text-center">
				<h1>图书分享平台</h1>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<!--左边部分开始-->
				<div class="col-md-offset-2 col-md-8">
					<!--推荐开始-->
					<section class="">
						<h3>推荐</h3>
						<hr />
						<div class="row">
							<c:forEach items="${rcmdList }" var="book">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}" class="thumbnail">
										<img src="img/${book.bookPic }">
										<div class="caption text-center">
											<h5>${book.bookTitle }</h5>
											<h6>${book.bookAuthor }</h6>
										</div>
									</a>
								</div>
							</c:forEach>
						</div><!-- /.row -->
					</section>
					<!--推荐结束-->
					<!--小说开始-->
					<section class="">
						<h3>小说</h3>
						<hr />
						<div class="row">
							<c:forEach items="${cate1 }" var="book">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}" class="thumbnail">
										<img src="img/${book.bookPic }">
										<div class="caption text-center">
											<h5>${book.bookTitle }</h5>
											<h6>${book.bookAuthor }</h6>
										</div>
									</a>
								</div>
							</c:forEach>
						</div><!-- /.row -->
					</section>
					<!--童话开始-->
					<section class="">
						<h3>童话</h3>
						<hr />
						<div class="row">
							<c:forEach items="${cate2 }" var="book">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}" class="thumbnail">
										<img src="img/${book.bookPic }">
										<div class="caption text-center">
											<h5>${book.bookTitle }</h5>
											<h6>${book.bookAuthor }</h6>
										</div>
									</a>
								</div>
							</c:forEach>
						</div><!-- /.row -->
					</section>
					<!--童话结束-->
					<!--随笔开始-->
					<section class="">
						<h3>随笔</h3>
						<hr />
						<div class="row">
							<c:forEach items="${cate3 }" var="book">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}" class="thumbnail">
										<img src="img/${book.bookPic }">
										<div class="caption text-center">
											<h5>${book.bookTitle }</h5>
											<h6>${book.bookAuthor }</h6>
										</div>
									</a>
								</div>
							</c:forEach>
						</div><!-- /.row -->
					</section>
					<!--随笔结束-->
					<!--名著开始-->
					<section class="">
						<h3>名著</h3>
						<hr />
						<div class="row">
							<c:forEach items="${cate4 }" var="book">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<a href="<%=contextPath %>/BookServlet?info=book&id=${book.bookId}" class="thumbnail">
										<img src="img/${book.bookPic }">
										<div class="caption text-center">
											<h5>${book.bookTitle }</h5>
											<h6>${book.bookAuthor }</h6>
										</div>
									</a>
								</div>
							</c:forEach>
						</div><!-- /.row -->
					</section>
					<!--名著结束-->
				</div>
				<!--左边部分结束-->
			</div>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>