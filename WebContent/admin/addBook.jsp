<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加书籍</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
	</head>
	<body>
		<c:if test="${requestScope.message_flag==true }">
			<script type="text/javascript">
				alert('${requestScope.message}');
			</script>
		</c:if>
		<div class="container">
			<form action="<%=contextPath %>/BookServlet?info=add" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="书籍名称" name="bookTitle" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="作者" name="bookAuthor" />
				</div>
				<div class="form-group">
					<input type="text" class="form-control" placeholder="出版社" name="bookPub" />
				</div>
				<div class="form-group">
					<input type="date" class="form-control" placeholder="出版日期" name="bookPubDate" />
				</div>
				<div class="form-group">
					<textarea class="form-control" placeholder="内容简介" maxlength="255" rows="6" name="bookContent" ></textarea>
				</div>
				<div class="form-group">
					<label for="">分类</label>
					<select name="bookCate" class="form-control">
						<option value="1" selected>小说</option>
						<option value="2">童话</option>
						<option value="3">随笔</option>
						<option value="4">名著</option>
					</select>
				</div>
				<div class="form-group">
					<label>书籍封面</label>
					<input type="file" name="filename" required/>
					<p class="help-block">仅支持.jpg或.png格式</p>
				</div>
   				<button type="submit" class="btn btn-primary">添加</button>
			</form>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>