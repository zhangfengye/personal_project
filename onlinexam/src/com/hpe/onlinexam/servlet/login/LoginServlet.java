package com.hpe.onlinexam.servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.service.login.LoginServiceImpl;
import com.hpe.onlinexam.util.DBUtil;
import com.hpe.onlinexam.util.ToolUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       LoginServiceImpl lsi=new LoginServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 String username = request.getParameter("username");
	 String password = request.getParameter("password");
	 String role = request.getParameter("role");
	 if(role.equals("admin")){
		 String adminname = lsi.login(username, password);
		
		 if(adminname!=null&&!adminname.equals("")){
			 //管理员登陆成功
			 //把当前登陆的信息保存到session 中 然后转发到/admin/index.jsp页面
			 //获取当前session
			 HttpSession session = request.getSession();
		
			 //把信息保存到session中（page，request,session,application）
			 session.setAttribute(ToolUtil.LOGINUSER,adminname);
			 response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
			 return;
		 }
	 }else if(role.equals("teacher")){
		 Teacher t=new Teacher();
		  t.setName(username);
		  t.setPwd(password);
		  t = lsi.login(t);
		  if(t!=null){
			  //教师登陆成功
			  HttpSession session = request.getSession();
				
				 //把信息保存到session中（page，request,session,application）
			  session.setAttribute(ToolUtil.LOGINUSER,t);
				 response.sendRedirect(request.getContextPath()+"/teacher/index.jsp");
			  return;
		  }
	 }else if(role.equals("student")){
		 Student s=new Student();
		 s.setName(username);
		 s.setPwd(password);
		 s = lsi.login(s); 
		 if(s!=null){
			 HttpSession session = request.getSession();
				
			 //把信息保存到session中（page，request,session,application）
			 session.setAttribute(ToolUtil.LOGINUSER,s);
			 response.sendRedirect(request.getContextPath()+"/student/index.jsp");
			 //学生登陆成功
			 return;
		 }
	 }
	 //登陆失败
	 HttpSession session= request.getSession();
	 session.setAttribute("error","<script>alert('用户名"+username+"密码错误');</script>");
	 response.sendRedirect(request.getContextPath()+"/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
