package edu.nju.listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CounterListener
 *
 */
@WebListener
public class CounterListener implements ServletContextListener, ServletContextAttributeListener {

	int updateCount;
	String counterFilePath = "/Users/pc/Documents/workspace/J2EE-Lab/WebContent/counter.txt";

	/**
	 * Default constructor.
	 */
	public CounterListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	// 创建
	public void contextInitialized(ServletContextEvent cse) {
		try {
			System.out.println("Reading Start");
			BufferedReader reader = new BufferedReader(new FileReader(counterFilePath));
			updateCount = Integer.parseInt(reader.readLine());
			reader.close();
			System.out.println("Reading " + updateCount);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		ServletContext servletContext = cse.getServletContext();
		servletContext.setAttribute("totalCount", updateCount);
		servletContext.setAttribute("onlineCount", 0);
		servletContext.setAttribute("visitorCount", 0);
		System.out.println("Application initialized");
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("ServletContextattribute added");
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	// 修改属性
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		String attrName = scae.getName();
		System.out.println(attrName + "：ServletContextattribute replaced");
	
		if (attrName.equals("totalCount")) {
			writeCounter(scae);
		}
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		System.out.println("ServletContextattribute removed");
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	// serverletcontext销毁
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Application shut down");
	}

	synchronized void writeCounter(ServletContextAttributeEvent scae) {
		ServletContext servletContext = scae.getServletContext();
		String updateCount = servletContext.getAttribute("totalCount") + "";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(counterFilePath));
			writer.write(updateCount);
			writer.close();
			System.out.println("Writing");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
}
