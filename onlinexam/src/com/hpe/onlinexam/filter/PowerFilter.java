package com.hpe.onlinexam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.util.ToolUtil;

/**
 * Servlet Filter implementation class PowerFilter
 */
@WebFilter(filterName="powerfilter",urlPatterns={"/*"})
public class PowerFilter implements Filter {
	private String adminPowers = "/loginServlet/login.jsp/courseServlet/js/css/stuClassServlet/studentServlet/TCViewServlet/teacherServlet/img/admin/";
	private String teacherPowers = "/loginServlet/login.jsp/questionServlet/js/css/testServlet/testViewServlet/classEvaluationServlet/img/teacher/";
	private String studentPowers = "/loginServlet/login.jsp/papersServlet/img/js/css/student/";

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		Object o = req.getSession().getAttribute(ToolUtil.LOGINUSER);
		String currentURL = req.getRequestURI(); // 取得根目录所对应的绝对路径:  
		System.out.println("curr     "+currentURL);
		String[] urls = currentURL.split("/");
		String targetURL="/login.jsp";
		if(urls.length>=3){
		 targetURL = "/"+urls[2]+"/";// 截取到当前文件名用于比较 	
		}
	     System.out.println("tar    "+targetURL);
		
		// 如果没有登录，且访问非登录页面（ login.jsp和/loginServlet ）。重定向到登录页面。
		if(o == null && !targetURL.equals("/login.jsp/") && !targetURL.equals("/loginServlet/")&& !targetURL.equals("/img/")){
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			return;
		} 
		if(o != null){	// 如果 已经登录，权限控制。
			System.out.println("["+targetURL+"]");
			if(o instanceof String && adminPowers.contains(targetURL)){// admin
				System.out.println("有管理员权限。"); 
			}else if(o instanceof Teacher && teacherPowers.contains(targetURL)){
				System.out.println("有教师权限。");
			}else if(o instanceof Student && studentPowers.contains(targetURL)){
				System.out.println("有学生权限。");
			}else{
				System.out.println("无权限，拦截。");
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
				return;// 无权限 拦截
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
