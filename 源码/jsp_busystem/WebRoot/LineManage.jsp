<%@page import="Com.Model.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ArrayList al = (ArrayList)request.getAttribute("al");
int pageNow = Integer.parseInt((String)request.getAttribute("pageNow"));
int pageCount = Integer.parseInt((String)request.getAttribute("pageCount"));
String flag = (String)request.getAttribute("flag");


  String Bus_Id = (String)request.getAttribute("Bus_Id");
  String Sta_Id = (String)request.getAttribute("Sta_Id");
  if(Bus_Id==null){
   Bus_Id ="";
  }
  if(Sta_Id==null){
   Sta_Id ="";
  }
			
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<script type="text/javascript">
function Add(){
	window.open("LineManageAdd.jsp","kkzy");
}

 function Empty(){
 	document.getElementById("Bus_Id").value="";
 	document.getElementById("Sta_Id").value="";
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
 if(request.getAttribute("suc")!=null)
 {
 %>
 
 <script type="text/javascript">
  alert("删除成功!");
 </script>
 <% 
 }
 %>
<center>
<div style=" width:990px; ">
<div style=" width:990px; height:150px;">
 <form action="LineInfoServlet?flag=chaxun&pageNow=1" method="post">
 	
	  <table width="100%" height="100" border="0" >
	    <tr>
	      <td width="17%" align="right" class="text1">公交编号：</td>
	      <td width="33%"><input type="text" name="Bus_Id" value="<%=Bus_Id %>"/></td>
	      <td width="18%" align="right" class="text1">站点ID：</td>
	      <td width="32%"><input type="text" name="Sta_Id" value="<%=Sta_Id %>"/></td>
	    </tr>
	    <tr>
	      <td colspan="4" align="center">
	        <input type="submit" name="Submit" value="查询" class="btn3"/>
	        <input type="button" name="Submit2" value="增加" onclick="Add()" class="btn3"/>
		  </td>
	      </tr>
	  </table>
  </form>
</div>

<!--信息显示-->

<div style="width:900px; height:100px;">
<form name="form" action="LineInfoServlet?flag=delAll&pageNow=<%=pageNow %>" onsubmit="return Check()">
<table width="100%" height="100%" border="0" class="busdata">
  <tr>
    <td width="5%" height="31">&nbsp;</td>
    <td width="11%">公交编号</td>
    <td width="13%">站点ID</td>
    <td width="17%">路程(公里)</td>
    <td width="7%">修改</td>
    <td width="7%">删除</td>
  </tr>
  <%
  for(int i=0;i<al.size();i++){
   LineBean lb = (LineBean)al.get(i);
   %>
  <tr>
    <td height="44"><label>
      <input type="checkbox" name="checkbox" value="<%=lb.getLineID()%>" />
    </label></td>
    <td><%=lb.getBus_ID() %> </td>
    <td><%=lb.getSta_ID() %> </td>
    <td><%=lb.getDistance() %> </td>
    <td><a href="LineManageRevise.jsp?lineId=<%=lb.getLineID() %>&pageNow=<%=pageNow %>">修改</a></td>
    <td><a onclick="return window.confirm('你确认要删除 公交编号为<%=lb.getBus_ID() %> 的线路 吗？')" href="LineInfoServlet?flag=del&LineId=<%=lb.getLineID() %>&pageNow=<%=pageNow %>">删除</a></td>
  </tr>
  <%} %>
  <tr>
    <td>

     <input type="checkbox" name="ifAll" value="checkbox"  onclick="CheckAll()"/>
   </td>
    <td colspan="7"><input type="submit" name="submit4" value="批量删除" >
     <%
     out.println("<a href=LineInfoServlet?flag="+flag+"&pageNow=1&Bus_Id="+Bus_Id+"&Sta_Id="+Sta_Id+">首页</a>");
     
     if(pageNow>1){
     out.println("<a href=LineInfoServlet?flag="+flag+"&pageNow="+(pageNow-1)+"&Bus_Id="+Bus_Id+"&Sta_Id="+Sta_Id+">上一页</a>");
     }
     
     if(pageNow!=pageCount){
     out.println("<a href=LineInfoServlet?flag="+flag+"&pageNow="+(pageNow+1)+"&Bus_Id="+Bus_Id+"&Sta_Id="+Sta_Id+">下一页</a>");
     }
     out.println("<a href=LineInfoServlet?flag="+flag+"&pageNow="+pageCount+"&Bus_Id="+Bus_Id+"&Sta_Id="+Sta_Id+">末页</a>");
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

