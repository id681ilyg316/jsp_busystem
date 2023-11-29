package Com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.*;

import Com.Model.StaBeanCl;

public class StaInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String flag = request.getParameter("flag");

		if (flag.equals("fenye")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));
			String StaId = request.getParameter("StaId");
			String StaName = request.getParameter("StaName");

			StaBeanCl sbc = new StaBeanCl();
			ArrayList al = sbc.getStainfoByPage(pageNow);
			int pageCount = sbc.getPageCount();

			request.setAttribute("pageNow", pageNow + "");
			request.setAttribute("pageCount", pageCount + "");
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("StationManage.jsp").forward(request, response);
		} else if (flag.equals("chaxun")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));

			int pageSize = 10;
			ArrayList al;
			int pageCount;

			StaBeanCl sbc = new StaBeanCl();
			String sql = "select * from Station where ";
			String sql1 = "select count(*) from Station where ";
			String sql2 = "";

			String StaId = request.getParameter("StaId");
			String StaName = request.getParameter("StaName");

			if (StaId != null && !StaId.equals("")) {
				StaId = new Tool().getNewString(StaId);
				StaId = StaId.trim();
				sql1 = sql1 + "ID like '%" + StaId + "%'  and";
				sql2 = sql2 + "ID like '%" + StaId + "%'  and";
			}
			if (StaName != null && !StaName.equals("")) {
				StaName = new Tool().getNewString(StaName);
				StaName = StaName.trim();
				sql1 = sql1 + " Name like '%" + StaName + "%'  and";
				sql2 = sql2 + " Name like '%" + StaName + "%'  and";
			}

			if (sql2.equals("")) {

				al = sbc.getStainfoByPage(pageNow);
				pageCount = sbc.getPageCount();
			} else {
				sql2 = sql2.substring(0, sql2.length() - 5);

				sql1 = sql1.substring(0, sql1.length() - 5);

				sql = sql + sql2 + " and " + sql2 + "limit " + pageSize * (pageNow - 1) + "," + pageSize;

				al = sbc.getStainfobysql(sql);
				pageCount = sbc.getPageCountBySQL(sql1);

				request.setAttribute("StaId", StaId);
				request.setAttribute("StaName", StaName);

			}

			request.setAttribute("pageNow", "" + pageNow);
			request.setAttribute("pageCount", "" + pageCount);
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("StationManage.jsp").forward(request, response);

		} else if (flag.equals("Revise")) {
			String StaId = request.getParameter("StaId");
			String StaName = request.getParameter("StaName");

			Tool t = new Tool();
			StaId = t.getNewString(StaId);
			StaName = t.getNewString(StaName);

			StaBeanCl sbc = new StaBeanCl();
			if (sbc.ReviseInfo(StaId, StaName)) {

				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = sbc.getStainfoByPage(pageNow);
				int pageCount = sbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);

				request.getRequestDispatcher("StationManage.jsp").forward(request, response);

			}
		} else if (flag.equals("Add")) {
			String StaId = request.getParameter("StaId");
			String StaName = request.getParameter("StaName");

			Tool t = new Tool();
			StaId = t.getNewString(StaId);
			StaName = t.getNewString(StaName);

			StaBeanCl sbc = new StaBeanCl();
			if (sbc.AddInfo(StaId, StaName)) {

				flag = "fenye";

				ArrayList al = sbc.getStainfoByPage(1);
				int pageCount = sbc.getPageCount();

				request.setAttribute("pageNow", 1 + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);

				request.getRequestDispatcher("StationManage.jsp").forward(request, response);

			}
		} else if (flag.equals("delAll")) {
			String[] StaId = request.getParameterValues("checkbox");
			StaBeanCl sbc = new StaBeanCl();
			if (sbc.delAllStaInfo(StaId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = sbc.getStainfoByPage(pageNow);
				int pageCount = sbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc", "1");
				request.getRequestDispatcher("StationManage.jsp").forward(request, response);
			}
		} else if (flag.equals("del")) {
			String StaId = request.getParameter("StaId");
			StaBeanCl sbc = new StaBeanCl();
			if (sbc.delStaInfo(StaId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = sbc.getStainfoByPage(pageNow);
				int pageCount = sbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc", "1");
				request.getRequestDispatcher("StationManage.jsp").forward(request, response);
			}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
