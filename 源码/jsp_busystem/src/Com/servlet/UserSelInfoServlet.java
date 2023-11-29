package Com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Com.Model.*;

import Com.Model.BusBeanCl;

public class UserSelInfoServlet extends HttpServlet {

	private static final String String = null;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      
		String flag=request.getParameter("flag");
		
		
		 if (flag.equals("SelBus")) {
	    	   

			ArrayList al;
			String BusName = request.getParameter("BusName");
			
			UserSelBeanCl usbc = new UserSelBeanCl();
			BusName = new Tool().getNewString(BusName);
			al = usbc.getinfoByBusName(BusName);
			
			request.setAttribute("StaName", al);
			request.setAttribute("BusName", BusName);
			request.getRequestDispatcher("UserResultBus.jsp").forward(request, response);	
			
				
					
		}else if(flag.equals("SelSta")){
			ArrayList al;
			String StaName = request.getParameter("StaName");
			
			UserSelBeanCl usbc = new UserSelBeanCl();
			StaName = new Tool().getNewString(StaName);
			al = usbc.getinfoByStaName(StaName);
			
			request.setAttribute("BusName", al);
			request.setAttribute("SName", StaName);
			request.getRequestDispatcher("UserResultSta.jsp").forward(request, response);	
			
			
		}else if(flag.equals("SelLine")){
			ArrayList ComLine;
			ArrayList ComLine2;
			ArrayList<String>  Distance;
			ArrayList<String>  Distance2;
			ArrayList all = new ArrayList();
			ArrayList  al = new ArrayList();
			ArrayList  al2 = new ArrayList();
			ArrayList  MidSta = new ArrayList();
			String MidSta1 ;
			String BestLine;
			String BestLine2;
			int b;
			int a = 0;
			String StartSta = request.getParameter("StartSta");
			String EndSta = request.getParameter("EndSta");
			
			
			UserSelBeanCl usbc = new UserSelBeanCl();
			StartSta = new Tool().getNewString(StartSta);
			EndSta = new Tool().getNewString(EndSta);
			
			

			ComLine = usbc.getLineDirect(StartSta,EndSta);
			System.out.println(ComLine.size());
						if(ComLine.size()==1 && ComLine.get(0).equals("start") ||ComLine.size()==1 && ComLine.get(0).equals("end")){
							request.setAttribute("BestLine", ComLine.get(0));
							request.getRequestDispatcher("UserResultLine.jsp").forward(request, response);
						}else{ 
							if(ComLine.size()==0){
							System.out.println("aaa");
							all = usbc.getLineDirect2(StartSta,EndSta);
							ComLine = (ArrayList) all.get(0);
							MidSta = (ArrayList) all.get(1);
							a = 1;
							}
							if(ComLine.size()>1){
								System.out.println("ccc");
								Distance = usbc.getDistance(ComLine,EndSta);
								al = usbc.getBestLineByDistance(Distance, ComLine);
								BestLine = (String)al.get(0);
								
								if(a==1){
									b = Integer.parseInt((String)al.get(1));
									MidSta1 = (String)MidSta.get(b);
									ComLine2 = usbc.getLineDirect(StartSta,MidSta1);
									Distance2 = usbc.getDistance(ComLine2,MidSta1);
									al2 = usbc.getBestLineByDistance(Distance2, ComLine2);
									BestLine2 = (String)al2.get(0);
									request.setAttribute("BestLine2", BestLine2);
									request.setAttribute("MidSta", MidSta1);
								}
								request.setAttribute("a", ""+a);
								request.setAttribute("StartSta", StartSta);
								request.setAttribute("EndSta", EndSta);
								request.setAttribute("BestLine", BestLine);
							
								request.getRequestDispatcher("UserResultLine.jsp").forward(request, response);
							}
							if(ComLine.size()==1){
								System.out.println("ddd");
								Distance = usbc.getDistance(ComLine,EndSta);
								al = usbc.getBestLineByDistance(Distance, ComLine);
								BestLine = (String)al.get(0);
								
								if(a==1){
									b = Integer.parseInt((String)al.get(1));
									MidSta1 = (String)MidSta.get(b);
									ComLine2 = usbc.getLineDirect(StartSta,MidSta1);
									Distance2 = usbc.getDistance(ComLine2,MidSta1);
									al2 = usbc.getBestLineByDistance(Distance2, ComLine2);
									BestLine2 = (String)al2.get(0);
									request.setAttribute("BestLine2", BestLine2);
									request.setAttribute("MidSta", MidSta1);
									
								}
								request.setAttribute("a", ""+a);
								request.setAttribute("StartSta", StartSta);
								request.setAttribute("EndSta", EndSta);
								request.setAttribute("BestLine", BestLine);
							
								request.getRequestDispatcher("UserResultLine.jsp").forward(request, response);
							}
							if(ComLine.size()==0){
								request.setAttribute("BestLine", EndSta);
								request.getRequestDispatcher("UserResultLine.jsp").forward(request, response);
							}
				
						}
					}
		
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
