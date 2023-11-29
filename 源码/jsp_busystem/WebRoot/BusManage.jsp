<%@page import="Com.Model.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ArrayList al = (ArrayList)request.getAttribute("al");
int pageNow = Integer.parseInt((String)request.getAttribute("pageNow"));
int pageCount = Integer.parseInt((String)request.getAttribute("pageCount"));
String flag = (String)request.getAttribute("flag");

String BusName = (String)request.getAttribute("BusName");
String BeginTime = (String)request.getAttribute("BeginTime");
String LastTime = (String)request.getAttribute("LastTime");
String a = (String)request.getAttribute("a");
  if(BusName==null){
   BusName ="";
  }
  if(BeginTime==null){
   BeginTime ="";
  }
   if(LastTime==null){
   LastTime ="";
  }				
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">
function Add(){
	window.open("BusManageAdd.jsp","kkzy");
}

 function Empty(){
 	document.getElementById("BusName").value="";
 	document.getElementById("BeginTime").value="";
 	document.getElementById("LastTime").value="";
 }
 
function CheckAll(){ 
     for (var i=0;i<eval(form.elements.length);i++){ 
     var e=form.elements[i]; 
     if (e.name!="ifAll") e.checked=form.ifAll.checked; 
    } 
   } 
     
 
function Check(){ 
	var flag = false;
	for (var i=0;i<eval(form.elements.length);i++){ 
		if(form.elements[i].checked){
			flag = true;
   		}
 	} 
	if(!flag){
		window.alert("你还没有选择要操作的数据");  
   
    }else{ 
      flag= window.confirm("你确定要删除所选吗？");
    }
    
    
   return flag;
} 

</script>

<style type="text/css">
	body{margin:0px;padding:0px;font-family:'微软雅黑';}
	.text1{font-size:20px;}
	input[type='text']{height:40px;width:340px}
	 .btn3{width:110px;height:45px;margin-right:25px;}
	 .busdata td{}
</style>
</head>

<body>
<%
 if(a=="ok")
 {
 %>
 
 <script type="text/javascript">
  alert("操作成功!");
 </script>
 <% 
 }
 %>
<center>
<div style=" width:990px; ">
<div style=" width:900px; height:150px;">
 <form action="BusInfoServlet?flag=chaxun&pageNow=1" method="post">
 	
	  <table width="100%" height="100px">
	    <tr>
	      <td width="32%" align="right" class="text1">车名：</td>
	      <td width="33%">
	        <input type="text" name="BusName" value="<%=BusName %>"/></td>
	      <td width="34%"></td>
	    </tr>
	    <tr>
	      <td colspan="3" align="center">
	        <input type="submit" name="Submit" value="查询" class="btn3"/>
	        <input type="button" name="Submit2" value="增加" onclick="Add()" class="btn3"/>
	      </td>
	    </tr>
	  </table>
  </form>
</div>

<!--信息显示-->

<div style="width:900px; height:100px;">
<form name="form" action="BusInfoServlet?flag=delAll&pageNow=<%=pageNow %>" onsubmit="return Check()">
<table width="100%" height="100%" border="0" class="busdata">
  <tr>
    <td width="5%" height="31">&nbsp;</td>
    <td width="12%">编号</td>
    <td width="14%">车名</td>
    <td width="27%">始末站</td>
    <td width="14%">首班车时间</td>
    <td width="14%">末班车时间</td>
    <td width="7%">修改</td>
    <td width="7%">删除</td>
  </tr>
  <%
  for(int i=0;i<al.size();i++){
   BusBean bb = (BusBean)al.get(i);
   %>
  <tr>
    <td height="44"><label>
      <input type="checkbox" name="checkbox" value="<%=bb.getID() %>" />
    </label></td>
    <td><%=bb.getID() %> </td>
    <td><%=bb.getBusName() %> </td>
    <td><%=bb.getRegion() %> </td>
    <td><%=bb.getBeginTime() %> </td>
    <td><%=bb.getLastTime() %> </td>
    <td><a href="BusManageRevise.jsp?busId=<%=bb.getID() %>&pageNow=<%=pageNow %>">修改</a></td>
    <td><a onclick="return window.confirm('你确认要删除 <%=bb.getBusName() %> 车 吗？')" href="BusInfoServlet?flag=del&BusId=<%=bb.getID() %>&pageNow=<%=pageNow %>">删除</a></td>
  </tr>
  <%} %>
  <tr>
    <td>

     <input type="checkbox" name="ifAll" value="checkbox"  onclick="CheckAll()"/>
   </td>
    <td colspan="7"><input type="submit" name="submit4" value="批量删除" >
     <%
     out.println("<a href=BusInfoServlet?flag="+flag+"&pageNow=1&BusName="+BusName+"&BeginTime="+BeginTime+"&LastTime="+LastTime+">首页</a>");
     
     if(pageNow>1){
     out.println("<a href=BusInfoServlet?flag="+flag+"&pageNow="+(pageNow-1)+"&BusName="+BusName+"&BeginTime="+BeginTime+"&LastTime="+LastTime+">上一页</a>");
     }
     
     if(pageNow!=pageCount){
     out.println("<a href=BusInfoServlet?flag="+flag+"&pageNow="+(pageNow+1)+"&BusName="+BusName+"&BeginTime="+BeginTime+"&LastTime="+LastTime+">下一页</a>");
     }
     out.println("<a href=BusInfoServlet?flag="+flag+"&pageNow="+pageCount+"&BusName="+BusName+"&BeginTime="+BeginTime+"&LastTime="+LastTime+">末页</a>");
     %>
    </td>
    </tr>
</table>

</form>
</div>





</div>
</center>
</body>
</html>

