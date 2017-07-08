<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>home</title>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="http://labfile.oss.aliyuncs.com/libs/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://labfile.oss.aliyuncs.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div>
		<p class="text-success">您的帐号：${user }</p>
	</div>
	<div>
		<div class="btn btn-info" data-toggle="modal" data-target="#myModa3">Add</div>
		<div class="btn btn-danger">
			<a href="#" data-toggle="modal" data-target="#myModa4">Delete</a>
		</div>
	</div>
	<table class="table table-striped table-hover table-bordered">
		<caption>您的密码</caption>
		<thead>
			<tr>
				<th>id</th>
				<th>uname</th>
				<th>password</th>
				<th>operator</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="passwd">
				<tr>
					<td><input type="checkbox" value="${passwd.id }" id="passrecord"></input>${passwd.id }</td>
					<td>${passwd.uname }</td>
					<td class="text-success">${passwd.passwd }</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- 新增对话框 start -->
	<div class="modal fade" id="myModa3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabe3">添加帐号密码</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" role="form" id="form">
						<div class="form-group">
							<label for="uname" class="col-xs-2 control-label">uname</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="uname" name="uname" />
							</div>
						</div>
						<div class="form-group">
							<label for="login_name" class="col-xs-2 control-label">loginName</label>
							<div class="col-xs-4">
								<input type="text" class="form-control" id="login_name" name="login_name" />
							</div>
						</div>
						<div class="form-group">
							<label for="passwdrecord" class="col-xs-2 control-label">uname</label>
							<div class="col-xs-4">
								<input type="password" class="form-control" id="passwdrecord" name="passwdrecord" />
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" id="addpasswd">add</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增对话框 end -->


	<!-- 删除对话框 start -->
	<div class="modal fade" id="myModa4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabe4">confirm to delete?</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-danger" id="delete">Confirm</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 删除对话框 end -->

	<script src="http://labfile.oss.aliyuncs.com/jquery/1.11.1/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			// 点击确定删除图片
			$('#delete').click(function() {
				var ids = "";
				var urls = "";
				$('input[type=checkbox]:checked').each(function() {
					ids += $(this).val() + ',';
					urls += $(this).attr('url') + ',';
				});
				$.post('${pageContext.request.contextPath}' + '/deletePasswd.action?type=2', {
					ids : ids,
					urls : urls
				}, function(data, status) {
					$('#myModa4').modal('hide');
					location.href = '${pageContext.request.contextPath}' + '/jsp/home.jsp';
				});
			});
			$('#addpasswd').click(function() {
				// alert('eheh' +$("#login_name").val());
				$.post('${pageContext.request.contextPath}' + '/sys/addPasswd.action?type=1', {
					uname : $("#uname").val(),
					loginName : $('#login_name').val(),
					passwdRecord : $('#passwdrecord').val()
				}, function(data, status) {
					$('#myModa3').modal('hide');
					location.href = '${pageContext.request.contextPath}' + '/jsp/home.jsp';
				});
			});
			// 显示弹出框
			function createPopOver(id, placement, content, action) {
				$(id).popover({
					placement : placement,
					content : content
				});
				$(id).popover(action);
			}

		});
	</script>
</body>
</html>