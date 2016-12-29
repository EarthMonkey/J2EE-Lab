package edu.nju.serverlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nju.database.DBScoresTable;
import edu.nju.models.ScoresPO;

/**
 * Servlet implementation class CheckTest
 */
@WebServlet("/CheckTest")
public class CheckTestServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckTestServerlet() {
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

		int totalCount = (int) Context.getAttribute("totalCount");
		totalCount++;
		Context.setAttribute("totalCount", totalCount);

		int visitorCount = (int) Context.getAttribute("visitorCount");
		visitorCount++;
		Context.setAttribute("visitorCount", visitorCount);

		response.sendRedirect("/J2EE-Lab/Pages/CheckTest.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html; charset=UTF-8");
		// request.setCharacterEncoding("UTF-8");

		String userId = request.getParameter("id");

		if (userId.equals("")) {
			response.sendRedirect("/J2EE-Lab/Pages/CheckTest.jsp");
		} else {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(userId);

			if (isNum.matches()) {
				ArrayList<ScoresPO> scorelist = DBScoresTable.checkScore(Integer.parseInt(userId));

				if (scorelist.isEmpty()) {
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "该学号不存在");
				} else {

					ServletContext Context = getServletContext();
					int onlineCount = (int) Context.getAttribute("onlineCount");
					int visitorCount = (int) Context.getAttribute("visitorCount");
					onlineCount++;
					if (visitorCount > 0)
						visitorCount--;
					Context.setAttribute("onlineCount", onlineCount);
					Context.setAttribute("visitorCount", visitorCount);

					ArrayList<ScoresPO> absent = new ArrayList<>();

					PrintWriter out = response.getWriter();
					for (ScoresPO sp : scorelist) {
						if (sp.getScore() > 0) {
							out.print(sp.getCourse() + "&nbsp;得分：");
							out.print(sp.getScore() + "<br>");
						} else {
							absent.add(sp);
						}
					}

					if (!absent.isEmpty()) {
						out.print("<br>注意！您有以下" + absent.size() + "场测验未参加：<br>");
						for (ScoresPO ab : absent) {
							out.print(ab.getCourse() + "<br>");
						}
					}

					out.println("<html>");
					out.println("<head><title>CheckTest</title></head>");
					out.println("<body>");
					out.println("<br><form method='get' action='/J2EE-Lab/Logout'>");
					out.println("<input type='submit' value='注销'></form>");
					out.println("</body></html>");
				}

			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "学号应由阿拉伯数字组成");
			}
		}

	}

}
