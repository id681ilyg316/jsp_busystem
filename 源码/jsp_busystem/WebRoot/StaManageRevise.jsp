<%@ page language="java" import="java.util.*,Com.Model.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String StaId = (String)request.getParameter("staId");

StaBeanCl sbc = new StaBeanCl();
ArrayList  al= sbc.getStainfoByStaID(StaId);
StaBean sb = (StaBean)al.get(0);

String pageNow = request.getParameter("pageNow");
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
	body{margin:0px;padding:0px;font-family:'微软雅黑';}
	.STYLE2{font-size:18px;}
	input[type='text']{height:40px;width:340px}
	 .btn3{width:110px;height:45px;margin-right:25px;}
	 .busdata td{}
</style>
</head>

<body>
<center>

<div style="width:700px; height:150px;">
<form action="StaInfoServlet?flag=Revise&StaId=<%=sb.getID()%>&pageNow=<%=pageNow %>" method="post">
  <table width="450" height="100%" border="0" class="busdata">
    <tr>
      <td width="150" align="right"><span class="STYLE2">站点ID</span></td>
      <td width="300"><%=sb.getID()%></td>
    </tr>
    <tr>
      <td align="right"><span class="STYLE2">车名</span></td>
      <td><input type="text" name="StaName" value="<%=sb.getStaName()%>"></td>
    </tr>
    <tr style="height:100px;">
      <td colspan="2" align="center""><input type="submit" name="Submit" value="修改" class="btn3"><input type="button" name="Submit3" value="返回" onclick="history.go(-1)" class="btn3"></td>
      </tr>
  </table>
</form>
</div>
</center>
</body>
</html>
