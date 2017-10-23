package com.hpe.onlinexam.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.Course;
import com.hpe.onlinexam.service.admin.CourseServiceImpl;
import com.hpe.onlinexam.service.admin.TeacherServiceImpl;

/**
 * 课程控制类
 * @author Administrator
 *
 */
@WebServlet("/courseServlet")
public class CourseServlet extends BaseServlet{

	// 注入 课程业务类
	CourseServiceImpl courseServcie = new CourseServiceImpl();
	// 注入 教师业务类
	TeacherServiceImpl teacherService = new TeacherServiceImpl();
	
	/**
	 * 首页list展示，请求方法
	 * courseServlet?method=show
	 */
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CourseServlet===sho");
		List<Course> list = courseServcie.findAllCourse();
		req.setAttribute("courseList", list);// EL表达式前端获取
		req.getRequestDispatcher("/admin/courselist.jsp").forward(req, resp);
	}
	
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("courseServlet===showUpdate");
		// 获取课程主键
		String id = req.getParameter("id");
		// 获取课程信息
		Course c = courseServcie.getCourseById(Integer.valueOf(id));
		// request.getAttribute("course") ===== ${course }
		req.setAttribute("course", c);  
		// 转发 WebContext/
		req.getRequestDispatcher("/admin/courseupdate.jsp").forward(req, resp);
	}
	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("courseServlet===udpate");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		// 封装
		Course c = new Course();
		if(id != null && !id.equals("")){
			c.setId(Integer.valueOf(id));
		}
		c.setName(name);
		
		// 判断是 新增操作还是修改操作。
		if(id == null || id.equals("")){// 新增
			courseServcie.saveCourse(c);
		}else{
			courseServcie.updateCourse(c);
		}
		// 重定向 。
		resp.sendRedirect(req.getContextPath()+
				"/courseServlet?method=show");
		
	}
	@Override
	protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		courseServcie.delCourse(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath()+
				"/courseServlet?method=show");
	}	
	
}
