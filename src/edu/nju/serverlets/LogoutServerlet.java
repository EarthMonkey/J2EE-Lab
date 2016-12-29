package edu.nju.serverlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServerlet
 */
@WebServlet("/Logout")
public class LogoutServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletContext Context = getServletContext();
		int onlineCount = (int) Context.getAttribute("onlineCount");
		int visitorCount = (int) Context.getAttribute("visitorCount");
		if (onlineCount > 0)
			onlineCount--;
		visitorCount++;
		Context.setAttribute("onlineCount", onlineCount);
		Context.setAttribute("visitorCount", visitorCount);

		response.sendRedirect("/J2EE-Lab/Pages/CheckTest.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
