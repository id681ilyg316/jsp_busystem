<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="Com.Model.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

ArrayList BusName = (ArrayList)request.getAttribute("BusName");
ArrayList StaName;
String SName = (String)request.getAttribute("SName");

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
	color: #eeeeee;
}
.STYLE3 {
	font-size: 30px;
	color: #333333;
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
      <%if(BusName.size()==0){ %>
      	您所查询的站点不存在，请重新输入！
    <%}else{ 
    
    StringBuffer  buf = new StringBuffer();
    UserSelBeanCl usbc = new UserSelBeanCl();
    UserSelBean usb = new UserSelBean();
    
    for(int i=0;i<BusName.size();i++){ 
  		String  aa = (String)BusName.get(i);
        StaName = usbc.getinfoByBusName(aa);
        BusBean bb = usbc.getBusinfoByBusName(aa);
        %>
        <font size="5"><%=aa %>(<%=bb.getRegion() %> ) 首班车时间：<%=bb.getBeginTime() %> 末班车时间:<%=bb.getLastTime() %></font><br><br>
        <% 
       
        for(int j=0;j<StaName.size();j++){
           usb =(UserSelBean)StaName.get(j);
		       if(j!=(StaName.size()-1))
		       
		       {
			        if(!(usb.getStaName().trim().equals(SName.trim()))){
			        %>
			         <%=usb.getStaName()+"---->" %>
			       <%
			        }
			        else{
			           %>
			         <font color="red" size="5"><%=usb.getStaName()%></font>---->
			       <%
			        }
	       }else{
		          if(!(usb.getStaName().trim().equals(SName.trim()))){
		        %>
		         <%=usb.getStaName()+"<br>"+"<br>" %>
		       <%
		        }
		        else{
		           %>
		         <font color="red"><%=usb.getStaName()%></font><br><br>
		       <%
		        }
	       }
	    }
	    
	    
    }
    %>
    <%} %>
    
    </td>
  </tr>
</table>

</body>
</html>
