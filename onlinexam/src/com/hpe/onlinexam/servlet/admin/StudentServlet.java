package com.hpe.onlinexam.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.StuClass;
import com.hpe.onlinexam.po.Student;
import com.hpe.onlinexam.service.admin.StuClassServiceImpl;
import com.hpe.onlinexam.service.admin.StudentServiceImpl;
@WebServlet("/studentServlet")
public class StudentServlet extends BaseServlet{
	// 注入的学生的业务实现类
	private StudentServiceImpl studentService = new StudentServiceImpl();
	// 注入 班级业务实现类
	private StuClassServiceImpl stuclassService = new StuClassServiceImpl(); 
	
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Student> list = studentService.findAllStudent();
		List<StuClass> stuList = stuclassService.findAll();
		
		req.setAttribute("studentList", list);
		req.setAttribute("stuList", stuList);
		
		req.getRequestDispatcher("/admin/studentlist.jsp").forward(req, resp);
	}
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		Student s = null;
		// id不是null并且 id不是空字符串。修改的流程
		if(id != null && !id.equals("")){
			s = studentService.findStudentById(Integer.valueOf(id));
		}
		// 获取班级信息 
		List stuList = stuclassService.findAll();
		
		req.setAttribute("stuList", stuList);
		req.setAttribute("student", s);
		req.getRequestDispatcher("/admin/studentupdate.jsp").forward(req, resp);

	}
	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String pwd = req.getParameter("pwd");
		String school = req.getParameter("school");
		String deptName = req.getParameter("deptName");
		String sex = req.getParameter("sex");
		String born = req.getParameter("born");
		String classId = req.getParameter("classId");
		String oldId = req.getParameter("oldId");
		
		Student s = new Student();
		s.setId(Integer.valueOf(id));
		s.setName(name);
		s.setPwd(pwd);
		s.setDeptName(deptName);
		s.setSchool(school);
		s.setSex(sex);
		s.setClassId(Integer.valueOf(classId));
		s.setBorn(born);
		// 方式一 ：
		if(oldId == null || oldId.trim().equals("")){
			// 新增流程
		}
		// 方式二：
		// 不为空，并且不是空字符串“”   执行修改。
		if(oldId != null && !oldId.trim().equals("")){
			// 修改
			studentService.updateStudent(s, Integer.valueOf(oldId));
		}else{
			studentService.saveStudent(s);
		}
		
		resp.sendRedirect(req.getContextPath() + "/studentServlet?method=show");
	}
	@Override
	protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		studentService.delStudent(Integer.parseInt(id));
		resp.sendRedirect(req.getContextPath() + "/studentServlet?method=show");
		
	}
}
