<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="Com.Model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

int a = 3;
String MidSta=null;
String StartSta=null;
String EndSta=null;
String BestLine=null;
String BestLine2=null;
float Distance1 = 0;
float Distance2 = 0;
float Distance3 = 0;
float Distance4 = 0;
float Distance5 = 0;
int Time1 = 0;
int Time2 = 0;
int Time3 = 0;
String Hour = null;

UserSelBeanCl usbc = new UserSelBeanCl();

if((String)request.getAttribute("StartSta")==null){
	BestLine = (String)request.getAttribute("BestLine");
}else{
	a = Integer.parseInt((String)request.getAttribute("a"));
	MidSta = (String)request.getAttribute("MidSta");
	StartSta = (String)request.getAttribute("StartSta");
	EndSta = (String)request.getAttribute("EndSta");
	BestLine = (String)request.getAttribute("BestLine");
	BestLine2 = (String)request.getAttribute("BestLine2");
}
%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>JSP在线公交路线查询平台含管理</title>

<style type="text/css">
body{background:url('Images/bgbg.png') repeat center top;width:100%;pisotion:relative;}
*{padding:0;margin:0;font-family:'微软雅黑';}
.STYLE1 {color: #000000;}
.STYLE2 {
	font-size: 42px;
	color: #009900;
}
.STYLE3 {
	font-size: 30px;
	color: #999999;
}
.STYLE4 {font-size: 16px}
.whilebg{
	width:1280px;height:580px;top:35px;left:40px;position:absolute;border-radius:5px;
	filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;z-index:10;background:#fff;
}
.cc{position:absolute;z-index:99;left:5%;top:0px;}
#kkzy{margin:20px 50px;}
.btnpst{margin:0px auto;width:430px;}
.btnpst input[type='button']{margin-right:25px;}
.btn3{width:110px;height:40px;margin-right:25px;border:0px;background:url('Images/btn3.png')}
</style>
<script type="text/javascript">
function startTime()
{
var today=new Date()
var h=today.getHours()
var m=today.getMinutes()
var s=today.getSeconds()
// add a zero in front of numbers<10
m=checkTime(m)
s=checkTime(s)
document.getElementById('txt').innerHTML=h+":"+m+":"+s
t=setTimeout('startTime()',500)
}

function checkTime(i)
{
if (i<10) 
  {i="0" + i}
  return i
}

function Open(i)
{
	window.open(i,"kkzy");
}
</script>
</head>
<body onLoad="startTime()" topmargin="0">
<div class="whilebg"></div>
<table width="1200" border="0" align="center" class="cc">
  <tr>
    <td height="60" colspan="2" valign="top">

	<script language=JavaScript>
	
	
	today=new Date();
	var hours = today.getHours();
	var minutes = today.getMinutes();
	var seconds = today.getSeconds();
	function initArray(){
	this.length=initArray.arguments.length
	for(var i=0;i<this.length;i++)
	this[i+1]=initArray.arguments[i] }
	var d=new initArray("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
	document.write(today.getYear(),"年",today.getMonth()+1,"月",today.getDate(),"日 ",d[today.getDay()+1]," ");
	
    </script>
    <span id="txt"></span> 
    </td>
	<td width="14%" valign="top"><div align="right"><a href="login.jsp" target="_blank" class="STYLE1 STYLE4">管理员通道</a></div>
	</td>
  </tr>
  <tr>
    <td height="133" colspan="3"><div align="center"></div>      
    <div align="center">
      <table width="664" border="0">
        <tr>
			<td width="231"><div align="right"><img src="Images/gongjiao.png" width="179" height="121" /></div></td>
			<td width="50">&nbsp;</td>
			<td width="">
				<span class="STYLE2">在线</span>
				<span class="STYLE3">公交查询系统</span>
			</td>
        </tr>
      </table>
    </div></td>
  </tr>
  <tr>
   <td colspan="3"><p>
      <iframe src="SelBus.jsp" name="kkzy" width="990" height="100" scrolling="Auto" frameborder="0" id="kkzy" ></iframe>
    </p></td>
  </tr>
  <tr>
    <td  colspan="3">
      
      <div class="btnpst">
        <input type="button" name="Submit" value="换乘"  class="btn3" onClick="Open('SelLine.jsp')">
        <input type="button" name="Submit2" value="线路" class="btn3" onClick="Open('SelBus.jsp')">
        <input type="button" name="Submit3" value="站点" class="btn3" onClick="Open('SelSta.jsp')">
      </div></td>
  </tr>
  <tr>
    <td height="232" valign="top">
    <%if(BestLine=="start") {%>
    	您查询的起点不存在，请重新输入！
    <%}else if(BestLine=="end") { %>
    	您查询的终点不存在，请重新输入！
    <%}else{ %>
    <% if(a==0){
    	Distance1 = usbc.getDistance2(BestLine,StartSta);
    	Distance2 = usbc.getDistance2(BestLine,EndSta);
    	Distance3 = Math.abs(Distance2 - Distance1);
    	Time1 = (int)(Distance3/0.5f);
    %>

                      在 
		<%=StartSta %> 乘坐 
		<%=BestLine %>车
		(约<%if(Time1/60<1){%>
			<%=Time1%>分钟
		<%}else{
			Time2 = Time1 - (Time1/60)*60;
		%>
		<%=Time1/60%>小时
		<%=Time2%>分钟
		<%} %>) 
		可直达  
		<%=EndSta %>
		
	<%} %>
	<% if(a==1){
		Distance1 = usbc.getDistance2(BestLine2,StartSta);
    	Distance2 = usbc.getDistance2(BestLine,EndSta);
    	Distance3 = usbc.getDistance2(BestLine2,MidSta);
    	Distance4 = Math.abs(Distance3 - Distance1);
		Distance3 = usbc.getDistance2(BestLine,MidSta);
		Distance5 = Math.abs(Distance2 - Distance3);
    	Time1 = (int)(Distance4/0.5f);
    	Time2 = (int)(Distance5/0.5f);
    %>
	            在 
		<%=StartSta %> 乘坐 
		<%=BestLine2 %>车(约<%if(Time1/60<1){%>
			<%=Time1%>分钟
		<%}else{
			Time3 = Time1 - (Time1/60)*60;
		%>
		<%=Time1/60%>小时
		<%=Time3%>分钟
		<%} %>)  到 
		<%=MidSta %> 下，然后转 
		<%=BestLine %>(约<%if(Time2/60<1){%>
			<%=Time2%>分钟
		<%}else{
			Time3 = Time2 - (Time2/60)*60;
		%>
		<%=Time2/60%>小时
		<%=Time3%>分钟
		<%} %>)  抵达 
		<%=EndSta %>
	<%} %>
    <% if(a==3){%>
	     	 对不起，您所查询的线路在一次换乘内无法到达，建议您适当结合其他交通方式换乘，以便前往目的地
	<%}} %>
    </td>
  </tr>
</table>

</body>
</html>
