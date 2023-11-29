<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
  <style type="text/css">
body{margin:0px;padding:0px;font-family:'微软雅黑';}
.text1{font-size:20px;}
.btn3{width:110px;height:40px;margin-right:25px;border:0px;background:url('Images/btn3.png')}
.loginbg{padding:30px;border:1px solid #ccc;border-radius:10px;margin:50px 100px;background:#eee;]}


</style>
<script type = "text/javascript">
	function check()
	{
		
		if(form.UserName.value=="")
		{
			alert("请填写您的名字");
			form.UserName.focus();
			return false;
		}
		 
		if(form.Password.value=="")
		{
			alert("请填写密码");
			form.Password.focus();
			return false;
		}
		if(form.code_name.value=="")
		{
			alert("请填写验证码");
			form.code_name.focus();
			return false;
		}
		
		
	//正则表达式判断
	     //var re = /^[0-9]+.?[0-9]*$/;//判断字符串是否为数字
		 var re = /^[0-9]+[0-9]*]*$/;//判断字符串是否为正整数 
		 if (!re.test(form.code_name.value))
		 {
			 alert("验证码必须是数字");
			 form.code_name.focus();
			 return false;
		 }
		 
		if(form.code_name.value.length<4)
		{
			alert("验证码必须为4位");
			form.code_name.focus();
			return false;
		}
		
		return true;
	}

</script>
</head>
<body>
<div class="loginbg">
 <form action="LoginServlet" name="NETSJ_Login" onclick="return check()" >
<table width="685" height="200" border="0" align="center" cellpadding="0" cellspacing="0" class="busdata">
  <tr >
    <td width="235">
    	<img alt="" src="Images/gongjiao.png"/>
    </td>
    <td width="450">
    	<table align="center" cellpadding="0" cellspacing="10" class="busdata" >
	     	
            <tr height="40">
              <td width="100" align="right" class="text1">登陆账号</td>
              <td><input name="UserName" type="text" id="UserName" style="border:solid 1px #ccc; width:300px;height:35px; background-color:#FFFFFF"></td>
            </tr>
            <tr height="40">
              <td width="100" align="right" class="text1">登陆密码</td>
              <td><input name="PassWord" type="password" id="Password" style="border:solid 1px #ccc; width:300px;height:35px; background-color:#FFFFFF"></td>
            </tr>
            <tr height="40">
              <td width="100" align="right" class="text1"> 验 证 码 </td>
			  <td><input name="Code" type="text" id="Code" style="border:solid 1px #ccc; width:80px;height:35px; background-color:#FFFFFF" maxlength="4">
			  <img src="image.jsp" width="50" height="24" />
		      </td>
            </tr>
            <tr height="40">
              <td colspan="2" align="center"><input type="submit" name="submit" value=" 登  陆 " class="btn3"> 
			  <input type="reset" name="Submit" value=" 返  回 " onclick="location.href='UserView.jsp'" class="btn3"></td>
            </tr>
          </table>
        </td>
     </tr>
</table>
  </form>
<%
	if(request.getAttribute("err")!=null){
		if(request.getAttribute("err").equals("1")){
%>
 			<script type = "text/javascript">
	 			alert("验证码有误！");
 			</script>
 <%
 		}else if(request.getAttribute("err").equals("2")){
 %>
		<script type = "text/javascript">
		 	alert("用户名或密码有误！");
		</script>
<%
		}
	}
 %>
 </div>
</body>
</html>
