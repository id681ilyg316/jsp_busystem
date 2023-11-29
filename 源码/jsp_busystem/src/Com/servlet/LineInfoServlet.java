package Com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Com.Model.*;

public class LineInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String flag = request.getParameter("flag");

		if (flag.equals("fenye")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));

			LineBeanCl lbc = new LineBeanCl();
			ArrayList al = lbc.getLineinfoByPage(pageNow);
			int pageCount = lbc.getPageCount();

			request.setAttribute("pageNow", pageNow + "");
			request.setAttribute("pageCount", pageCount + "");
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("LineManage.jsp").forward(request, response);
		} else if (flag.equals("chaxun")) {

			int pageNow = Integer.parseInt(request.getParameter("pageNow"));

			int pageSize = 10;
			ArrayList al;
			int pageCount;

			LineBeanCl lbc = new LineBeanCl();
			String sql = "select * from Line where ";
			String sql1 = "select count(*) from Line where ";
			String sql2 = "";

			String Bus_Id = request.getParameter("Bus_Id");
			String Sta_Id = request.getParameter("Sta_Id");

			if (Bus_Id != null && !Bus_Id.equals("")) {
				Bus_Id = new Tool().getNewString(Bus_Id);
				Bus_Id = Bus_Id.trim();
				sql1 = sql1 + " Bus_Id like '%" + Bus_Id + "%'  and ";
				sql2 = sql2 + " Bus_Id like '%" + Bus_Id + "%'  and ";
			}
			if (Sta_Id != null && !Sta_Id.equals("")) {
				Sta_Id = new Tool().getNewString(Sta_Id);
				Sta_Id = Sta_Id.trim();
				sql1 = sql1 + " Sta_Id like '%" + Sta_Id + "%'  and ";
				sql2 = sql2 + " Sta_Id like '%" + Sta_Id + "%'  and ";
			}

			if (sql2.equals("")) {

				al = lbc.getLineinfoByPage(pageNow);
				pageCount = lbc.getPageCount();
			} else {
				sql2 = sql2.substring(0, sql2.length() - 5);

				sql1 = sql1.substring(0, sql1.length() - 5);

				sql = sql + sql2 + " and " + sql2 + "limit " + pageSize * (pageNow - 1) + "," + pageSize;

				al = lbc.getLineinfobysql(sql);
				pageCount = lbc.getPageCountBySQL(sql1);

				request.setAttribute("Bus_Id", Bus_Id);
				request.setAttribute("Sta_Id", Sta_Id);

			}

			request.setAttribute("pageNow", "" + pageNow);
			request.setAttribute("pageCount", "" + pageCount);
			request.setAttribute("al", al);
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("LineManage.jsp").forward(request, response);

		} else if (flag.equals("Revise")) {
			String LineId = request.getParameter("LineId");
			String Bus_Id = request.getParameter("Bus_Id");
			String Sta_Id = request.getParameter("Sta_Id");
			String Distance = request.getParameter("Distance");

			Tool t = new Tool();
			Bus_Id = t.getNewString(Bus_Id);
			Sta_Id = t.getNewString(Sta_Id);
			Distance = t.getNewString(Distance);

			LineBeanCl lbc = new LineBeanCl();
			if (lbc.ReviseInfo(LineId, Bus_Id, Sta_Id, Distance)) {

				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = lbc.getLineinfoByPage(pageNow);
				int pageCount = lbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);

				request.getRequestDispatcher("LineManage.jsp").forward(request, response);

			}
		} else if (flag.equals("Add")) {
			String Bus_Id = request.getParameter("Bus_Id");
			String Sta_Id = request.getParameter("Sta_Id");

			String Distance = request.getParameter("Distance");

			Tool t = new Tool();
			Bus_Id = t.getNewString(Bus_Id);
			Sta_Id = t.getNewString(Sta_Id);
			Distance = t.getNewString(Distance);

			LineBeanCl lbc = new LineBeanCl();
			if (lbc.AddInfo(Bus_Id, Sta_Id, Distance)) {

				flag = "fenye";

				ArrayList al = lbc.getLineinfoByPage(1);
				int pageCount = lbc.getPageCount();

				request.setAttribute("pageNow", 1 + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);

				request.getRequestDispatcher("LineManage.jsp").forward(request, response);

			}
		} else if (flag.equals("delAll")) {
			String[] LineId = request.getParameterValues("checkbox");
			LineBeanCl lbc = new LineBeanCl();
			if (lbc.delAllLineInfo(LineId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = lbc.getLineinfoByPage(pageNow);
				int pageCount = lbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc", "1");
				request.getRequestDispatcher("LineManage.jsp").forward(request, response);
			}
		} else if (flag.equals("del")) {
			String LineId = request.getParameter("LineId");
			LineBeanCl lbc = new LineBeanCl();
			if (lbc.delLineInfo(LineId)) {
				flag = "fenye";
				int pageNow = Integer.parseInt(request.getParameter("pageNow"));

				ArrayList al = lbc.getLineinfoByPage(pageNow);
				int pageCount = lbc.getPageCount();

				if (pageNow > pageCount) {
					pageNow = pageCount;
				}
				request.setAttribute("pageNow", pageNow + "");
				request.setAttribute("pageCount", pageCount + "");
				request.setAttribute("al", al);
				request.setAttribute("flag", flag);
				request.setAttribute("suc", "1");
				request.getRequestDispatcher("LineManage.jsp").forward(request, response);
			}
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
