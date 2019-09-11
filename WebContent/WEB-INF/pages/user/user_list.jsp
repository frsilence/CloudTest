<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<!-- head -->
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>User-List</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/ionicons/css/ionicons.min.css">
  <!-- DataTables -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/plugins/datatables/dataTables.bootstrap.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/dist/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="${pageContext.request.contextPath }/static/adminlte/dist/css/skins/_all-skins.min.css">

  <!-- Google Font -->
  <!--<link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">-->
</head>
<!--  content  -->
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
<%@ include file="/WEB-INF/pages/common/header.jsp" %>
<%@ include file="/WEB-INF/pages/common/sidebar.jsp" %>
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        User Manage
        <small>User-List</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="${pageContext.request.contextPath}/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="${pageContext.request.contextPath}/user/user_list"><i class="fa fa-dashboard"></i> User Manage</a></li>
        <li class="active">All User</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">用户列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="allusers" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <th>ID</th>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>邮箱</th>
                  <th>部门</th>
                  <th>角色</th>
                  <th>最后登录时间</th>
                  <th>添加人</th>
                  <th>冻结</th>
                  <th>创建时间</th>
                  <th>更新时间</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
               
                
                </tbody>
                <tfoot>
                <tr>
	                 <th>ID</th>
	                 <th>工号</th>
	                 <th>姓名</th>
	                 <th>邮箱</th>
	                 <th>部门</th>
					<th>角色</th>
					<th>最后登录时间</th>
					<th>添加人</th>
					<th>冻结</th>
					<th>创建时间</th>
					<th>更新时间</th>
					<th>操作</th>
                </tr>
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>


<!-- footer -->
<%@ include file="/WEB-INF/pages/common/footer.jsp" %>
<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <%@ include file="/WEB-INF/pages/common/control_sidebar.jsp" %>
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<!-- Bootstrap 3.3.7 -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath }/static/adminlte/dist/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="${pageContext.request.contextPath }/static/adminlte/dist/js/demo.js"></script>
<!-- DataTables -->
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath }/static/adminlte/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/layer/layer.js"></script>

<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script>
	// 设置激活菜单
	function setSidebarActive(tagUri) {
		var liObj = $("#" + tagUri);
		if (liObj.length > 0) {
			liObj.parent().parent().addClass("active");
			liObj.addClass("active");
		}
	}

	$(document).ready(function() {

		// 激活导航位置
		setSidebarActive("user_list");
		
		//datatables
    $("#allusers").dataTable({
    	"autoWidth"		:true,
    	"info"			:true,
    	"jqueryui"		:true,
		"serverSide"	:true,
		"processing"	:true,
		"filter"		:true,
		"striped"		:true,
		"toolbar"		:"#toolbar",
		"showRefresh"	:true,
		"showColumns"	:false,
    	"oLanguage":{  
            "sProcessing":   "获取数据并处理中...",  
            "sLengthMenu":   "显示 _MENU_ 项结果",  
            "sZeroRecords":  "没有匹配结果",  
            "sInfo":         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",  
            "sInfoEmpty":    "显示第 0 至 0 项结果，共 0 项",  
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",  
            "sInfoPostFix":  "",  
            "sSearch":       "搜索:",  
            "sUrl":          "",  
            "oPaginate": {  
                "sFirst":    "首页",  
                "sPrevious": "上页",  
                "sNext":     "下页",  
                "sLast":     "末页"  
            }  
        },
        "paginate":true,
        "ajax":{
        	"url":"${pageContext.request.contextPath}/user/allusers",
					"type":"POST",
        },
				"columns":[
					{"data":"id"},
					{"data":"account"},
					{"data":"username"},
					{"data":"email"},
					{"data":"department.departmentName"},
					{"data":"roles","render":function(data){
						var roles = "";
						for(var i=0,len=data.length;i<len;i++){
							roles = roles + data[i].roleName+"\n";
						}
						return roles;
					}},
					{"data":"lastLoginTime"},
					{"data":"adduid"},
					{"data":"freezeStr"},
					{"data":"createTime"},
					{"data":"updateTime"},
					{"data":"id","render":function(data){
						var btns = '<button type="button" uid="'+data+'" class="btn btn-success btn-xs" style="margin-bottom:0px" onclick=editUser(this)>编辑</button>';
                        btns = btns + '<button type="button" uid="'+data+'" class="btn btn-danger btn-xs" style="margin-bottom:0px" onclick=deleteUser(this)>删除</button>'; 
                        return btns;
					}}
				],
     
    })
		
	});
	
	function editUser(e){
		var index = layer.open({
			type: 2,
			fixed: false,
			maxmin :true,
			area:['60%','70%'],
			title: "用户信息编辑",
			content: "${pageContext.request.contextPath}/user/user_edit",
			cancel: function(index,layero){
			  	layer.confirm('放弃此次编辑?', {icon: 3, title:'提示'}, function(myindex){ 
			  		layer.close(myindex);
			    	layer.close(index);
				});
				return false;
			},
			success:function(layero,index){
				var body = layer.getChildFrame('body',index);
				var iframeWin  = window[layero.find('iframe')[0]['name']];
				iframeWin.getUserInfo($(e).attr("uid"));
			}
	})
	}
	
	//删除分类
	function deleteUser(e){
		layer.confirm("确认删除该用户？操作不可逆",{icon:3,title:"提示"},function(myindex){
			layer.close(myindex);
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/user/userdelete",
				contentType : "application/x-www-form-urlencoded",
				async:false,
				dataType:"json",
				data:{
					uid:$(e).attr("uid"),
				},
				success:function(res){
					if(res.code==0){
						layer.msg(res.msg,{icon:1,time:2000},function(){
							location.reload();
						})
					}else{
						layer.msg(res.msg,{icon:0,time:2000},function(){
									location.reload();
								});
					}
				},
				error:function(){
						layer.msg('系统错误，请重试',{icon:2,time:2000},function(){
									location.reload();
								});
					}
			});
		})
	}

</script>

</body>
</html>