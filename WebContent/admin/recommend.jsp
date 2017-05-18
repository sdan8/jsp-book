<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>推荐书籍</title>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="<%=contextPath %>/css/bootstrap.min.css"/>
		<style type="text/css">
			.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
			    border-top: none;
			}
			h2 {
				margin-bottom: 25px;
			}
			h3 {
				margin-bottom: 25px;
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
			<div class="form">
				<div class="row">
					<div class="col-md-4">
						<h3>添加书籍</h3>
						<form action="<%=contextPath %>/BookServlet?info=rcmd" method="post" class="form-horizontal">
							<table class="table">
								<tr>
									<td><label class="control-label">书籍ID</label></td>
									<td><input type="text" class="form-control" name="bookId" /></td>
								</tr>
								<tr>
									<td colspan="2"><button type="submit" class="btn btn-primary">添加</button></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script src="<%=contextPath %>/js/jquery-3.1.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="<%=contextPath %>/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</html>