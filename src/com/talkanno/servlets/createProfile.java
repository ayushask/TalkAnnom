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
 * Servlet implementation class CreateProfile
 */
@WebServlet("/createProfile")
public class createProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public createProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub
	 * response.getWriter().append("Served at: ").append(request.getContextPath());
	 * }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uname = (String) request.getParameter("uname");
		String password = (String) request.getParameter("password");
		String email = (String) request.getParameter("email");
		System.out.println("uname " + uname + " pass " + password + "email " + email);

		UserDao userDao = new UserDao();

		PrintWriter out = response.getWriter();
		if (uname == null || uname.trim().equals("") || password == null || password.trim().equals("") || email == null
				|| email.trim().equals("")) {
			request.setAttribute("message", "Invalid Enteries");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
			return;
		}

		boolean isexist = userDao.isUserExist(uname);
		if (isexist) {
			// out.println("<h2> USERNAME ALREADY TAKEN !! try another!!<h2>");
			request.setAttribute("message", "USERNAME ALREADY TAKEN !! try another!!");

			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
			return;
		}

		int isSuccess = userDao.createUser(uname, password, email);
		if (isSuccess == 0) {
			out.println("<h1> INTERNAL SERVER ERROR !!! TRY AGAIN<h1>");
			request.setAttribute("message", "INTERNAL SERVER ERROR !!! TRY AGAIN");

			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.include(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("uname", uname);
		RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
		rd.include(request, response);

	}

}
