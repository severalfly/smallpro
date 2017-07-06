<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
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
    <div class="container">
        <div class="row">
            <div class="col-xs-12 text-center">
                <h2>home</h2>
            </div>
        </div>
        <div class="row">
            <div id="alert1"  class="alert alert-success fade in text-center col-xs-2 col-xs-offset-5 hide">
                <strong>RegisterSuccess</strong>
            </div>
        </div>
        <form id="form" class="form-horizontal" role="form" style="margin-top: 73px;">
          <div class="form-group"  >
            <label for="username" class="col-xs-2 control-label col-sm-offset-3" >Username</label>
            <div class="col-xs-2">
              <input type="text" class="form-control" id="username" rel="tooltip"/>
            </div>
          </div>
          <div class="form-group">
            <label for="password" class="col-xs-2 control-label col-sm-offset-3">Password</label>
            <div class="col-xs-2">
              <input type="password" class="form-control" id="password"/>
            </div>
          </div>
          <div class="form-group">
            <div class="col-sm-offset-5 col-xs-1">
              <button type="button" class="btn btn-success" id="login">Login</button>
            </div>
            <div class="col-sm-1">
              <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">Register</button>
            </div>
          </div>
        </form>
    </div>

   

    <script src="http://labfile.oss.aliyuncs.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //点击登录
            $('#login').click(function() {
                //提交登录表单
                $.post('${pageContext.request.contextPath}' + '/sys/login.do?type=1',
                {
                    username: $('#username').val(),
                    password: $('#password').val()
                },
                function(data, status) {
                	if (data.code == 0) {
                        location.href = 'home.jsp';
                    }
                	else {
                		createPopOver('#username', 'right', 'wrong username or password', 'show');
                	}
                		
                		
                });
            });

            //点击注册按钮
            $('#register').click(function() {
                //提交注册表单
                $.post('${pageContext.request.contextPath}' + '/sys/login.do?type=2',
                {
                    username: $('#reg_username').val(),
                    password: $('#reg_password').val(),
                    repassword: $('#reg_repassword').val()
                },
                function(data, status) {
                    if (data == '1') {
                        createPopOver('#reg_username', 'right', 'not null', 'show');
                    } else if (data == '2') {
                        createPopOver('#reg_password', 'right', 'not null', 'show');
                    } else if (data == '3') {
                        createPopOver('#reg_repassword', 'right', 'not null', 'show');
                    } else if (data == '4') {
                        createPopOver('#reg_repassword', 'right', 'Passwords do not match', 'show');
                    } else if (data == '5') {
                        createPopOver('#reg_username', 'right', 'Username already exists', 'show');
                    } else if (data == '6') {
                        $('#reg_username').val('');
                        $('#reg_password').val('');
                        $('#reg_repassword').val('');
                        $('#myModal').modal('hide');
                        $('#alert1').removeClass('hide');
                        $('#form').css('margin-top', '0px');
                    }
                });
            }); 

            //显示弹出框
            function createPopOver(id, placement, content, action) {
                $(id).popover({
                    placement: placement,
                    content: content
                });
                $(id).popover(action);
            }

            //点击输入框时销毁弹出框
            $('#username').click(function() {
                $('#username').popover('destroy');
            });

            $('#password').click(function() {
                $('#password').popover('destroy');
            });

            $('#reg_username').click(function() {
                $('#reg_username').popover('destroy');
            });

            $('#reg_password').click(function() {
                $('#reg_password').popover('destroy');
            });

            $('#reg_repassword').click(function() {
                $('#reg_repassword').popover('destroy');
            });
        });
    </script>
  </body>
</html>