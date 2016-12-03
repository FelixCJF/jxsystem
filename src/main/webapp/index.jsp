<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/common.jspf"%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="plug-ins/easyui/css/default/easyui.css" />
<link rel="stylesheet" href="plug-ins/easyui/css/icon.css" />
<link rel="stylesheet" href="plug-ins/index/css/style.css" />
<title>进销存系统</title>
</head>
<body class="easyui-layout">

	<div data-options="region:'north'"
		style="height: 80px; background-image: url('plug-ins/index/images/top_bg.jpg' ) no-repeat right center; float: right; BACKGROUND: #A8D7E9; padding: 1px; overflow: hidden;">
		<img alt="logo" src="plug-ins/index/images/head.png" />
	</div>
	<div data-options="region:'west',title:'功能菜单',collapsible:false" style="width: 220px">
		<ul id="menu"></ul>
	</div>
	<div data-options="region:'east',collapsible:'true',title:'工具',collapsed:true" style="width: 220px">
		<div class="easyui-calendar" style="width: 100%;"></div>
	</div>
	<div data-options="region:'center'">
		<div id="tabs" class="easyui-tabs" style="width: 100%; height: 100%;"></div>
	</div>


	<script src="http://cdn.bootcss.com/jquery/3.1.1/jquery.js"></script>
	<script type="text/javascript" src="plug-ins/easyui/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="plug-ins/easyui/js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="plug-ins/index/js/easyui-extends.js"></script>
	<script type="text/javascript">
		var tabs;
		$(function() {
			tabs = $("#tabs").tabs({
				onClose : function(title, index) {
					removeWindow();
				},
				tools : [ {
					iconCls : "icon-lock",
					handler : function() {
						window.location.href = "${contextPath}/login?method=loginout";
					}
				} ],
			});
			$("#menu").tree({
				url : "${contextPath}/ajax?method=getfuntree",
				formatter : function(node) {
					return node.functionName;
				},
				onClick : function(node) {
					if (node.children != null)
						return;
					var seltab = tabs.tabs("getTab", node.functionName);
					if (seltab != null) {
						tabs.tabs("select", node.functionName);
						return;
					}
					tabs.tabs("add", {
						title : node.functionName,
						fit : true,
						pill : true,
						closable : true,
						href : node.functionUrl,
						tools : [ {
							iconCls : "icon-mini-refresh",
							handler : function() {
								removeWindow();
								var selTab = tabs.tabs("getSelected");
								selTab.panel("refresh", selTab.panel("options").href);
							}
						} ]
					});
				},

			});
		});

		function removeWindow() {
			$(".panel.window").remove();
			$(".window-shadow").remove();
			$(".window-mask").remove();
		}

		Date.prototype.Format = function(fmt) { //author: meizz 
			var o = {
				"M+" : this.getMonth() + 1, //月份 
				"d+" : this.getDate(), //日 
				"h+" : this.getHours(), //小时 
				"m+" : this.getMinutes(), //分 
				"s+" : this.getSeconds(), //秒 
				"q+" : Math.floor((this.getMonth() + 3) / 3),
				"S" : this.getMilliseconds()
			//毫秒 
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
			return fmt;
		}
	</script>
</body>
</html>