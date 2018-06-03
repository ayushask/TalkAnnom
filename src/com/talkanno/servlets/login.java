package com.talkanno.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.talkanno.dao.UserDao;
//import com.talkanno.dbhelper.UserDbHelper;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 16757657657L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = (String) request.getParameter("uname");
		String password = (String) request.getParameter("password");
		System.out.println("uname " + uname + " pass " + password);
		UserDao dao = new UserDao();
		if (uname == null || uname.equals("") || password == null || password.equals("")) {
			request.setAttribute("message", "INVALID ENTRIES");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		}
		boolean isexist = dao.isUserExist(uname);
		if (!isexist) {
			request.setAttribute("message", " INVALID USERNAME !!!");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			
			rd.forward(request, response);
			return;
		}

		boolean isSuccess = dao.isCorrectPassword(uname, password);
		if (!isSuccess) {
			request.setAttribute("message", "Password Incorrect!!!");
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("uname", uname);
		RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
		rd.forward(request, response);

	}

}
