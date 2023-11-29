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
<form name="a" action="UserSelInfoServlet?flag=SelBus" onsubmit="return Null()" target="_parent">
 <table width="980" height="60" border="0" align="left">
   <tr>
    <td width="350" height="60" align="center"><div align="right" class="STYLE1" >请输入公交线号：</div></td>
    <td width="350"><input type="text" name="BusName" style="width:340px; height:45px"  /></td>
    <td width="230"><input type="submit" name="Submit" value="查询" style="width:110px; height:45px"/>
    </td>
   </tr>
</table>
</form>
</body>
</html>