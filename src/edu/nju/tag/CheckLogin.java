package edu.nju.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CheckLogin extends SimpleTagSupport {

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public void doTag() throws JspException, IOException {

		HttpSession htpSession = req.getSession();
		if (htpSession.getAttribute("user") == null || htpSession.getAttribute("user").equals("null")) {
			resp.sendRedirect("/J2EE-Lab/Pages/Login.jsp");
			return;
		}
	}

	public void setReq(HttpServletRequest req) {
		this.req = req;
	}

	public void setResp(HttpServletResponse resp) {
		this.resp = resp;
	}
}
