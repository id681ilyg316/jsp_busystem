<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">  
    <title></title>
    <style type="text/css">
    	body{margin:0px;padding:0px;font-family:'微软雅黑';background:#eeeeee;}
    	a{text-decoration:none;}
    	table{margin:0px auto}
    	iframe{margin:0px 13%}
    	.text1{color:#fff;}
    	.text2{font-size:22px;text-valign:middle;}
    </style>
	
</head>
<body>
<table width="970" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="250" height="100" align="center">
    	<img src="Images/gongjiao.png" width="160px" height="90px"/>
    	<br />
    	<span class="text2">在线公交查询系统</span>
    </td>
    <td>
    	<table width="600" height="65" border="0" cellpadding="0" cellspacing="10" class="subbg" NAME=t1>      	
	        <tr>
	          <td width="150" align="center" valign="middle" background="Images/btt2.png" >
	          	<a href="BusInfoServlet?flag=fenye&pageNow=1" target="kkzy" class="text1">公交车信息管理</a>
	          </td>
	          <td width="150" align="center" valign="middle" background="Images/btt2.png" >
	          	<a href="StaInfoServlet?flag=fenye&pageNow=1" target="kkzy" class="text1">站点信息管理</a>
	          </td>
	          <td width="150" align="center" valign="middle" background="Images/btt2.png" >
	          	<a href="LineInfoServlet?flag=fenye&pageNow=1" target="kkzy" class="text1">线路信息管理</a>
	          </td>
	          <td align="right" valign="bottom">
	          	<a href="login.jsp?Action=LoginOut" target="_top" class="STYLE2">退出登录</a>
	          </td>
	        </tr>
     	 </table>
	</td>
  </tr>
</table>
<iframe name="kkzy" width="990" height="700" scrolling="auto" frameborder="0" style="border: 1px solid #999;background:#fff;margin-top:25px"></iframe>

<script language="javascript">
<!--
var displayBar=true;
function switchBar(obj){
	if (displayBar)
	{
		parent.frame.cols="0,*";
		displayBar=false;
		obj.title="打开左边管理菜单";
	}
	else{
		parent.frame.cols="195,*";
		displayBar=true;
		obj.title="关闭左边管理菜单";
	}
}
//-->
</script></body>
</html>
