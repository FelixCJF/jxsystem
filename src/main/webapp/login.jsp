<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common.jspf"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link rel="stylesheet" href="plug-ins/easyui/css/default/easyui.css" />
<link rel="stylesheet" href="plug-ins/easyui/css/icon.css" />
<link rel="stylesheet" href="plug-ins/index/css/style.css" />
</head>
<body>
	<div id="loginWindow">
		<form action="${contextPath}/login" method="post">
			<table style="width: 80%; margin: 10px auto;">
				<tr>
					<td>用户名：</td>
					<td>
						<input name="username" type="text" class="easyui-textbox" required="required" />
					</td>
				</tr>
				<tr>
					<td>密码：</td>
					<td>
						<input name="password" type="password" class="easyui-textbox" required="required" />
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a href="javascript:void(0)" onclick="submit();" class="easyui-linkbutton">登录</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="https://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
	<script type="text/javascript" src="plug-ins/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="plug-ins/easyui/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="plug-ins/index/js/easyui-extends.js"></script>
	<script type="text/javascript">
		$(function() {
			$.window("#loginWindow", "登录");
			$("#loginWindow").window({
				closable : false,
				draggable : false,
				resizable : false,
				height : "160px"
			});
		});

		function submit() {
			$.messager.progress();
			$("form").form("submit", {
				url : "${contextPath}/login?method=login",
				onSubmit : function() {
					var isvalid = $(this).form("validate");
					if (!isvalid) {
						$.messager.progress("close");
					}
					return isvalid;
				},
				success : function(data) {
					$.messager.progress("close");
					var json = eval("(" + data + ")");
					if (json.status == "success") {
						window.location.href = "${contextPath}/index.jsp";
					} else {
						$.messager.show({
							title : "提示信息",
							msg : "用户名或密码错误"
						})
					}
				}
			});
		}
	</script>
</body>
</html>