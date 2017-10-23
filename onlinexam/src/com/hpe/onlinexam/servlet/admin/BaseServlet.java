package com.hpe.onlinexam.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共的基本控制类
 * @author Administrator
 *
 */
public class BaseServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		String method = req.getParameter("method");
		if(method.equals("show")){
			this.show(req,resp);
		}else if(method.equals("showUpdate")){
			this.showUpdate(req,resp);
		}else if(method.equals("update")){
			this.update(req,resp);
		}else if(method.equals("del")){
			this.del(req,resp);
		}
	}
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("BaseServlet===shwo");
	}
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
