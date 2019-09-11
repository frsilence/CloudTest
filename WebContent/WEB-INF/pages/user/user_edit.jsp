<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- Bootstrap 3.3.7 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/font-awesome/css/font-awesome.min.css">
<!-- Bootstrap-treeview -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/bootstrap-treeview/css/bootstrap-treeview.css">
<!-- select2 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/select2/select2.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/dist/css/AdminLTE.min.css">

<title>用户信息编辑</title>

</head>
<body>
	<section class="center">
		<div class="box box-info">
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal">
              <div class="box-body">
              	<input id="userId"  hidden="hidden" value="" />
                <div class="form-group">
                  <label for="account" class="col-sm-2 control-label">工号</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="account" placeholder="Account">
                  </div>
                </div>
                <div class="form-group">
                  <label for="username" class="col-sm-2 control-label">用户名</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="username" placeholder="Username">
                  </div>
                </div>
                <div class="form-group">
                  <label for="email" class="col-sm-2 control-label">Email</label>

                  <div class="col-sm-8">
                    <input type="text" class="form-control" id="email" placeholder="Email">
                  </div>
                </div>
                <div class="form-group">
                  <label for="password" class="col-sm-2 control-label">密码</label>

                  <div class="col-sm-8">
                    <input type="password" class="form-control" id="password" placeholder="若不更改则不填写">
                  </div>
                </div>
                <div class="form-group">
                	<label  for="freeze" class="col-sm-2 control-label">冻结</label>
                	<div class="col-sm-8">
						<select id="freeze" class="form-control select2">
							<option value="true">是</option>
							<option value="false">否</option>
						</select>
					</div>
                </div>
                <div class="form-group">
                	<label  for="department" class="col-sm-2 control-label">部门</label>
                	<div class="col-sm-8">
						<select id="department" class="form-control select2">
							
						</select>
					</div>
                </div>
                <div class="form-group">
                	<label  for="roles" class="col-sm-2 control-label">角色</label>
                	<div  class="col-sm-8">
                		<select id="roles" class="form-control select2" multiple="multiple" data-placeholder="选择角色进行添加">
							
						</select>
                	</div>
                </div>
                
              </div>
              <!-- /.box-body -->
              <div class="box-footer">
                <button id="closeIframe" type="submit" class="btn btn-default" style="margin-left: 30%;">取消</button>
                <button id="user_update" type="button" class="btn btn-info" style="margin-right: 30%;">确认</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
	</section>
	

</body>



<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/layer/layer.js"></script>
<!-- Bootstrap-treeview -->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/bootstrap-treeview/js/bootstrap-treeview.js"></script>
<!-- select2 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/static/adminlte/plugins/select2/select2.full.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$(".select2").select2();
	//设置部门和角色信息
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/user/updateinformation",
		dataType:"json",
		success:function(res){
			for(var i=0,len=res.data.departments.length;i<len;i++){
				$("#department").append('<option value="'+res.data.departments[i].id+'">'+res.data.departments[i].departmentName+'</option>');
			};
			for(var i=0,len=res.data.roles.length;i<len;i++){
				$("#roles").append('<option value="'+res.data.roles[i].id+'">'+res.data.roles[i].roleName+'</option>');
			}
		}
	})
	
});

function getUserInfo(userId){
	$("#userId").val(userId);
	var userdata={
			userId:userId,
		};
		//查询用户详细信息(不使用json发送参数)
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/user/userinformation",
			//contentType:"application/json;charset=utf-8",
			contentType : "application/x-www-form-urlencoded",
			dataType:"json",
			data:userdata,
			//data: JSON.stringify(userdata),
			success:function(res){
				$("#account").val(res.data.user.account);
				$("#username").val(res.data.user.username);
				if(res.data.user.freeze!=null){
					if(res.data.user.freeze==true){
						$("#freeze").val("true").select2();
					}else{
						$("#freeze").val("false").select2();
					}
				}
				$("#email").val(res.data.user.email);
				$("#department").val(res.data.user.department.id).select2();
				var roles = [];
				for(var i=0,len=res.data.user.roles.length;i<len;i++){
					roles[i] = res.data.user.roles[i].id;
				}
				$("#roles").val(roles).select2();
			}
		});
}

//关闭iframe
$('#closeIframe').click(function(){
	window.parent.layer.confirm('放弃此次用户编辑?', {icon: 3, title:'提示'}, function(myindex){
		window.parent.layer.close(myindex);
	  	var index = window.parent.layer.getFrameIndex(window.name); //获取窗口索引
	  	console.log(index);
    	window.parent.layer.close(index);
	});
    
});

//保存分类的修改
$('#user_update').click(function(){
			layer.confirm('确认修改？该操作不可逆！',{icon:3,title:'提示'},function(index){
				layer.close(index);
				var data = {
						id:$("#userId").val(),
						account:$("#account").val(),
						username:$("#username").val(),
						email:$("#email").val(),
						password:$("#password").val(),
						freeze:$("#freeze").val(),
						department:$("#department").val(),
						roles:$("#roles").val(),
					};
				$.ajax({
					url:"${pageContext.request.contextPath}/user/userupdate",
					type:'post',
					contentType:"application/json;charset=utf-8",
					dataType:"json",
				    data: JSON.stringify(data),
					success:function(res){
						if(res.code==0){
							layer.msg(res.msg,{icon:1,time:2000},function(){
								var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
	    						parent.layer.close(index);
	    						parent.location.reload();
								parent.$('#hiddcategorytable_refreshed').click();
							});
							
						}else{
							layer.msg(res.msg,{icon:0,time:2000});
						}
					},
					error:function(){
						layer.msg('系统错误，请重试',{icon:2,time:2000},function(){
									window.location.reload();
								});
					}
				});
			});
})

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>