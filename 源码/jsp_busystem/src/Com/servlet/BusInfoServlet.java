package Com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.*;

import Com.Model.BusBeanCl;

public class BusInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String flag = request.getParameter("flag");

		if (flag.equals("fenye")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));

			BusBeanCl bbc = new BusBeanCl();
			ArrayList al = bbc.getBusinfoByPage(pageNow);
			int pageCount = bbc.getPageCount();

			request.setAttribute("pageNow", pageNow + "");
			request.setAttribute("pageCount", pageCount + "");
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("BusManage.jsp").forward(request, response);
		} else if (flag.equals("chaxun")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));

			int pageSize = 10;
			ArrayList al;
			int pageCount;

			BusBeanCl bbc = new BusBeanCl();
			String sql = "select * from Bus where ";
			String sql1 = "select count(*) from Bus where ";
			String sql2 = "";

			String BusName = request.getParameter("BusName");
			String BeginTime = request.getParameter("BeginTime");
			String LastTime = request.getParameter("LastTime");

			if (BusName != null && !BusName.equals("")) {
				BusName = new Tool().getNewString(BusName);
				BusName = BusName.trim();
				sql1 = sql1 + " Name like '%" + BusName + "%'  and ";
				sql2 = sql2 + " Name like '%" + BusName + "%'  and ";
			}
			if (BeginTime != null && !BeginTime.equals("")) {
				BeginTime = new Tool().getNewString(BeginTime);
				BeginTime = BeginTime.trim();
				sql1 = sql1 + " BeginTime='" + BeginTime + "'  and ";
				sql2 = sql2 + " BeginTime='" + BeginTime + "'  and ";
			}
			if (LastTime != null && !LastTime.equals("")) {
				LastTime = new Tool().getNewString(LastTime);
				LastTime = LastTime.trim();
				sql1 = sql1 + " LastTime='" + LastTime + "'  and ";
				sql2 = sql2 + " LastTime='" + LastTime + "'  and ";
			}

			if (sql2.equals("")) {

				al = bbc.getBusinfoByPage(pageNow);
				pageCount = bbc.getPageCount();
			} else {
				sql2 = sql2.substring(0, sql2.length() - 5);

				sql1 = sql1.substring(0, sql1.length() - 5);

				sql = sql + sql2 + " and " + sql2 + "limit " + pageSize * (pageNow - 1) + "," + pageSize;

				al = bbc.getBusinfobysql(sql);
				pageCount = bbc.getPageCountBySQL(sql1);

				request.setAttribute("BusName", BusName);
				request.setAttribute("BeginTime", BeginTime);
				request.setAttribute("LastTime", LastTime);

			}

			request.setAttribute("pageNow", "" + pageNow);
			request.setAttribute("pageCount", "" + pageCount);
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("BusManage.jsp").forward(request, response);

		} else if (flag.equals("Revise")) {
			String BusId = request.getParameter("BusId");
			String BusName = request.getParameter("BusName");
			String Region = request.getParameter("Region");
			String BeginTime = request.getParameter("BeginTime");
			String LastTime = request.getParameter("LastTime");

			Tool t = new Tool();
			BusId = t.getNewString(BusId);
			BusName = t.getNewString(BusName);
			Region = t.getNewString(Region);
			BeginTime = t.getNewString(BeginTime);
			LastTime = t.getNewString(LastTime);

			BusBeanCl bbc = new BusBeanCl();
			if (bbc.ReviseInfo(BusId, BusName, Region, BeginTime, LastTime)) {

				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = bbc.getBusinfoByPage(pageNow);
				int pageCount = bbc.getPageCount();
				String a = "ok";
				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("a", a);
				request.getRequestDispatcher("BusManage.jsp").forward(request, response);

			}
		} else if (flag.equals("Add")) {
			String BusId = request.getParameter("BusId");
			String BusName = request.getParameter("BusName");
			String Region = request.getParameter("Region");
			String BeginTime = request.getParameter("BeginTime");
			String LastTime = request.getParameter("LastTime");

			Tool t = new Tool();
			BusId = t.getNewString(BusId);
			BusName = t.getNewString(BusName);
			Region = t.getNewString(Region);
			BeginTime = t.getNewString(BeginTime);
			LastTime = t.getNewString(LastTime);

			BusBeanCl bbc = new BusBeanCl();
			if (bbc.AddInfo(BusId, BusName, Region, BeginTime, LastTime)) {

				flag = "fenye";

				ArrayList al = bbc.getBusinfoByPage(1);
				int pageCount = bbc.getPageCount();
				String a = "ok";

				request.setAttribute("pageNow", 1 + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("a", a);
				request.getRequestDispatcher("BusManage.jsp").forward(request, response);

			}
		} else if (flag.equals("delAll")) {
			String[] BusId = request.getParameterValues("checkbox");
			BusBeanCl bbc = new BusBeanCl();
			if (bbc.delAllBusInfo(BusId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = bbc.getBusinfoByPage(pageNow);
				int pageCount = bbc.getPageCount();
				String a = "ok";
				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("a", a);
				request.getRequestDispatcher("BusManage.jsp").forward(request, response);
			}
		} else if (flag.equals("del")) {
			String BusId = request.getParameter("BusId");
			BusBeanCl bbc = new BusBeanCl();
			if (bbc.delBusInfo(BusId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = bbc.getBusinfoByPage(pageNow);
				int pageCount = bbc.getPageCount();
				String a = "ok";
				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("a", a);
				request.getRequestDispatcher("BusManage.jsp").forward(request, response);
			}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
