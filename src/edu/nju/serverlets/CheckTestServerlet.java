package edu.nju.serverlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.nju.action.business.ScoreListBean;
import edu.nju.models.ScoresPO;
import edu.nju.service.CheckScoreService;

/**
 * Servlet implementation class CheckTest
 */
@WebServlet("/Login")
public class CheckTestServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static ApplicationContext applicationContext;
	private static CheckScoreService scoreService;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckTestServerlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public  void init() throws ServletException {
		super.init();
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		scoreService = (CheckScoreService) applicationContext.getBean("CheckScoreService");
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

		response.sendRedirect("/J2EE-Lab/Pages/Login.jsp");

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
			response.sendRedirect("/J2EE-Lab/Pages/Login.jsp");
		} else {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(userId);

			if (isNum.matches()) {
				ArrayList<ScoresPO> scorelist = scoreService.checkScore(Integer.parseInt(userId));

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
					
					request.getSession().setAttribute("user", userId);
					
					ArrayList<ScoresPO> score = new ArrayList<>();
					ArrayList<ScoresPO> absent = new ArrayList<>();

					for (ScoresPO sp : scorelist) {
						if (sp.getScore() > 0) {
							score.add(sp);
						} else {
							absent.add(sp);
						}
					}
					
					ScoreListBean listBean = new ScoreListBean();
					listBean.setScoreList(score);
					listBean.setAbsentList(absent);
					
					HttpSession session = request.getSession(true);
					session.setAttribute("listBean", listBean);
					session.setAttribute("user", userId);
					
					response.sendRedirect("/J2EE-Lab/Pages/CheckResult.jsp");
				}

			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "学号应由阿拉伯数字组成");
			}
		}

	}

}
