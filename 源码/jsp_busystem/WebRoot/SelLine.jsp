<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
<head>
<style type="text/css">
<!--
.STYLE1 {
	font-size: 22px;
	color: #006699;
}
-->
</style>
</head>
  
<body>
<form name="a" action="UserSelInfoServlet?flag=SelLine" onsubmit="return Null()" target="_parent">
<table width="980" border="0" align="left">
  <tr>
    <td width="350"><div align="right" class="STYLE1" >请输入起点：</div></td>
    <td width="350"><input type="text" name="StartSta" style="width:340px; height:40px"  /></td>
    <td width="230"rowspan="2"><input type="submit" name="Submit" value="查询" style="width:110px; height:45px"/></td>
  </tr>
  <tr>
    <td width="350"><div align="right" class="STYLE1" >请输入终点：</div></td>
    <td width="350"><input type="text" name="EndSta" style="width:340px; height:40px"/></td>
    
  </tr>
</table>
</form>
</body>
</html>