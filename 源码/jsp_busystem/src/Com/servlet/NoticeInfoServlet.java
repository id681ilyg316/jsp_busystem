package Com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.*;


public class NoticeInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      
		String flag=request.getParameter("flag");
		
		
		if(flag.equals("fenye")){
			
		int pageNow =Integer.parseInt(request.getParameter("pageNow")); 
		
		NoticeBeanCl nbc = new NoticeBeanCl();
		ArrayList al = nbc.getNoticeinfoByPage(pageNow);
		int pageCount = nbc.getPageCount();
		
		request.setAttribute("pageNow", pageNow+"");
		request.setAttribute("pageCount", pageCount+"");
		request.setAttribute("al", al);
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);
		}else if (flag.equals("chaxun")) {
	    	   
		    
		    int pageNow=Integer.parseInt(request.getParameter("pageNow"));
			
		    int pageSize=10;
			ArrayList al;
			int pageCount;
			
			NoticeBeanCl nbc = new NoticeBeanCl();
			String sql="select top "+pageSize+" * from Notice where NoticeID not in (select top "+pageSize*(pageNow-1)+" NoticeID from Notice where";
			String sql1="select count(*) from Notice where";
			String sql2="";
			
			String Title =request.getParameter("Title");
			String Time=request.getParameter("Time");
			
			
			if(Title!=null&&!Title.equals(""))
			{   
				Title= new Tool().getNewString(Title);
				Title =Title.trim();
				sql1=sql1+" Title like '%"+Title+"%'  and";
				sql2=sql2+" Title like '%"+Title+"%'  and";
			}
			if(Time!=null&&!Time.equals(""))
			{
				Time= new Tool().getNewString(Time);
				Time =Time.trim();
				sql1=sql1+" Time like '%"+Time+"%'  and";
				sql2=sql2+" Time like '%"+Time+"%'  and";
			}
		
				
			if(sql2.equals(""))
			{
				
				 al = nbc.getNoticeinfoByPage(pageNow);
				 pageCount = nbc.getPageCount();
			}else 
			{
				sql2 = sql2.substring(0,sql2.length()-5);
				
				sql1 = sql1.substring(0,sql1.length()-5);
				
				sql=sql+sql2 +") and" +sql2;
				
				al = nbc.getNoticeinfobysql(sql);
				pageCount = nbc.getPageCountBySQL(sql1);
				
				request.setAttribute("Title", Title);
				request.setAttribute("Time", Time);

				
			}
			
			request.setAttribute("pageNow", ""+pageNow);
			request.setAttribute("pageCount",""+pageCount);
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);	
			
				
					
		}else if(flag.equals("Revise")){
			NoticeBeanCl nbc=new NoticeBeanCl();
			
			String NoticeId=request.getParameter("NoticeId");
			String Title=request.getParameter("Title");
			String Time=nbc.getSystemTime();
			String Content=request.getParameter("Content");
			
			Tool t=new Tool();
			Title=t.getNewString(Title);
			Time=t.getNewString(Time);
			Content=t.getNewString(Content);
			
			
			if(nbc.ReviseInfo( NoticeId,Title,Content,Time)){
				
				flag="fenye";
				int pageNow =Integer.parseInt(request.getParameter("pageNow"));
		
				ArrayList al = nbc.getNoticeinfoByPage(pageNow);
				int pageCount = nbc.getPageCount();
				
				if(pageNow>pageCount)
				{
					pageNow=pageCount;	
				}
				request.setAttribute("pageNow", pageNow+"");
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				
				request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);
				
				
				
			}
		}else if(flag.equals("Add")){
			NoticeBeanCl nbc=new NoticeBeanCl();
			
			String Title=request.getParameter("Title");
			String Content = request.getParameter("Content");
			String Time=nbc.getSystemTime();
			
			Tool t=new Tool();
			Title=t.getNewString(Title);
			Content=t.getNewString(Content);
			Time =t.getNewString(Time);
			
			
			if(nbc.AddInfo(Title, Content,Time)){
				
				flag="fenye";
				
				ArrayList al = nbc.getNoticeinfoByPage(1);
				int pageCount = nbc.getPageCount();
				
		
				request.setAttribute("pageNow", 1+"");
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				
				request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);
				
				
			}
		}else if(flag.equals("delAll"))
		{
			String[] NoticeId = request.getParameterValues("checkbox");
			NoticeBeanCl nbc=new NoticeBeanCl();
			if(nbc.delAllNoticeInfo(NoticeId))
			{   
				flag="fenye";
				int pageNow =Integer.parseInt(request.getParameter("pageNow"));
		
				ArrayList al = nbc.getNoticeinfoByPage(pageNow);
				int pageCount = nbc.getPageCount();
				
				if(pageNow>pageCount)
				{
					pageNow=pageCount;	
				}
				request.setAttribute("pageNow", pageNow+"");
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc","1");
				request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);
			}
		}else if(flag.equals("del"))
		{
			String NoticeId = request.getParameter("NoticeId");
			NoticeBeanCl nbc=new NoticeBeanCl();
			if(nbc.delNoticeInfo(NoticeId))
			{   
				flag="fenye";
				int pageNow =Integer.parseInt(request.getParameter("pageNow"));
		 
				ArrayList al = nbc.getNoticeinfoByPage(pageNow);
				int pageCount = nbc.getPageCount();
				
				if(pageNow>pageCount)
				{
					pageNow=pageCount;	
				}
				request.setAttribute("pageNow", pageNow+"");
				request.setAttribute("pageCount", pageCount+"");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc","1");
				request.getRequestDispatcher("NoticeManage.jsp").forward(request, response);
			}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
