package com.hpe.onlinexam.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.service.admin.TeacherServiceImpl;

/**
 * 教师管理 控制类
 * @author Administrator
 *
 */
@WebServlet("/teacherServlet")
public class TeacherServlet extends HttpServlet{
	
	//ITeacherService teacherService = new TeacherServiceImpl();
	TeacherServiceImpl teacherService = new TeacherServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 具体的业务处理 ,用户提交的请求中，必须要有一个method参数。
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if(method.equals("show")){
			System.out.println("-----------------show");
			show(req,resp);
		}else if(method.equals("update")){
			System.out.println("-----------------update");
			update(req,resp);
		}else if(method.equals("showUpdate")){
			System.out.println("-----------------showUpdate");
			showUpdate(req,resp);
		}else if(method.equals("del")){
			delete(req,resp);
		}
	}
	private void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException{
		String id=req.getParameter("id");
		teacherService.delTeacher(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath()+
				"/teacherServlet?method=show");
		
	}
	private void show(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		// 通过教师业务类获取所有教师信息。
		List list = teacherService.findAllTeacher();
		// 通过request传递数据
		req.setAttribute("teacherList", list);
		System.out.println("TeacherServlet==========show");
		//转发，request可以传数据；并且绝对路径是从WebContext开始的。
		//  "/admin/stuclasslist.jsp"
		req.getRequestDispatcher("/admin/teacherlist.jsp").forward(req, resp);
	}
	private void update(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		// 获取前端提交的数据
		String oldId = req.getParameter("oldId");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String deptName = req.getParameter("deptName");
		// 封装这个对象
		Teacher t = new Teacher();
		t.setId(Integer.valueOf(id));
		t.setName(name);
		t.setDeptName(deptName);
		t.setPwd(pwd);
		
		if(oldId == null){//新增
			teacherService.saveTeacher(t);
		}else{
			teacherService.updateTeacher(t, Integer.valueOf(oldId));
		}
		// 转向到某个页面 重定向；客户端的行为，跟目录“/项目名/”
		resp.sendRedirect(req.getContextPath()+
				"/teacherServlet?method=show");
		
	}
	private void showUpdate(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		String id = req.getParameter("id");
		// 通过id获取教师信息
		Teacher t = teacherService.findTeacherById(Integer.valueOf(id));
		
		// 转发到 teacherupdate.jsp页面
		req.setAttribute("teacher", t);
		req.getRequestDispatcher("/admin/teacherupdate.jsp").forward(req, resp);		
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
		// 所有的类型（get/post）请假都执行 doGet方法。
	}
	
	
	
	
	
	
	
	
	
	
}
