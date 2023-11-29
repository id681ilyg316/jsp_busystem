package Com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Com.Model.*;

public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String UserName = (String) request.getParameter("UserName");
		UserName = new String(UserName.getBytes("iso-8859-1"), "utf-8");
		String PassWord = (String) request.getParameter("PassWord");
		PassWord = new String(PassWord.getBytes("iso-8859-1"), "utf-8");
		String Code = (String) request.getParameter("Code");

		HttpSession hs = request.getSession(true);
		String rand = (String) hs.getAttribute("rand");

		if (rand == null) {
			request.setAttribute("err", "2");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (!rand.equals(Code)) {

			request.setAttribute("err", "1");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			AdminBeanCl abc = new AdminBeanCl();
			if (abc.check(UserName, PassWord)) {
				request.getRequestDispatcher("Manager.jsp").forward(request, response);
			} else {
				request.setAttribute("err", "2");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}

		}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doGet(request, response);
	}

}
