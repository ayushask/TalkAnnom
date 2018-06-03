package com.talkanno.servlets;

import java.io.IOException;
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
import com.talkanno.pojos.UserPojo;

/**
 * Servlet implementation class Send
 */
@WebServlet("/send")
public class Send extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Send() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("uname"));
		HttpSession session = request.getSession();
//		if ((session.getAttribute("uname") == null) || (((String) session.getAttribute("uname")).equals(""))) {
//			request.setAttribute("message", "You have to login first");
//			response.sendRedirect("login.jsp");
//			return;
//
//		}
		String userid = request.getParameter("bookid");
		System.out.println("USER SERIAL " + userid);
		UserDao dao = new  UserDao();

			UserPojo userPojo = dao.getUserBySerial(Integer.parseInt(userid));
			if (userPojo.getUserName() == null || userPojo.getUserName().equals("")) {
				request.setAttribute("message", "Something Goes Wrong !! We cannot Find requsted user !! try again...");
				RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
				rd.include(request, response);
				return;
			}
			String msg = request.getParameter("msg");
			System.out.println("Message " + msg);

			int res = dao.createMessageEntry(Integer.parseInt(userid), msg);
			if (res == 0) {
				request.setAttribute("message", "Something Goes Wrong !! DB ERROR !! try again...");
				RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
				rd.include(request, response);
				return;
			}
			
			request.setAttribute("message", "Message Successfully sent!!!");
			RequestDispatcher rd = request.getRequestDispatcher("/welcome.jsp");
			rd.forward(request, response);
			
	}

}
