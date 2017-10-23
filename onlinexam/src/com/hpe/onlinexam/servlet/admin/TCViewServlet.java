package com.hpe.onlinexam.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.service.admin.CourseServiceImpl;
import com.hpe.onlinexam.service.admin.StuClassServiceImpl;
import com.hpe.onlinexam.service.admin.TCViewServiceImpl;
import com.hpe.onlinexam.service.admin.TeacherServiceImpl;
import com.hpe.onlinexam.util.ToolUtil;
import com.hpe.onlinexam.vo.TCView;

@WebServlet("/TCViewServlet")
public class TCViewServlet extends BaseServlet{
	TCViewServiceImpl tcviewService = new TCViewServiceImpl();
	TeacherServiceImpl teacherService = new TeacherServiceImpl();
	CourseServiceImpl courseServcie = new CourseServiceImpl();
	StuClassServiceImpl stuServcie = new StuClassServiceImpl();
	
	@Override // 覆盖或重写
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 第一种，首次访问。第二，点击搜索按钮。
		String teaName = req.getParameter("teaName");
		String courseName = req.getParameter("courseName");
		String className = req.getParameter("className");
		String deptName = req.getParameter("deptName");
		// 从数据库服务器中获取数据，转发到 viewlist.jsp页面
		TCView v = new TCView();
		v.setTeaName(teaName);
		v.setCourseName(courseName);
		v.setClassName(className);
		v.setDeptName(deptName);
		
		List<TCView> tcvList =  tcviewService.searchTCView(v);// v
		req.setAttribute("tcvList", tcvList);
		req.setAttribute("tcview", v);
		
		req.getRequestDispatcher("/admin/viewlist.jsp").forward(req, resp);
	}
	
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取要修改的数据id
		String di = req.getParameter("id");
		// 如果是新增流程,把tcview设置为null
		TCView tcview = null;
		if(di !=null && !di.equals("")){
			tcview = tcviewService.findById(Integer.valueOf(di));
		}
		req.setAttribute("tcview", tcview);
		
		//（可以在执行保存操作时，做判断） 根据教师id判断数据是否重复。
		// 获取业务展示的数据集合 。3个集合
		List teaList = teacherService.findAllTeacher();
		List courseList = courseServcie.findAllCourse();
		List classList = stuServcie.findAll();
		
		req.setAttribute("teaList", teaList);
		req.setAttribute("courseList", courseList);
		req.setAttribute("classList", classList);
		
		// 转发到viewupdate.jsp
		req.getRequestDispatcher("/admin/viewupdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String teaId = req.getParameter("teaId");
		String courseId = req.getParameter("courseId");
		String classId = req.getParameter("classId");
		TCView v = new TCView();
		v.setTeaId(Integer.valueOf(teaId));
		v.setClassId(Integer.valueOf(classId));
		v.setCourseId(Integer.valueOf(courseId));
		
		if(id != null && !id.equals("")){
			v.setId(Integer.valueOf(id));
			tcviewService.updateTCView(v);			
		}else{
			tcviewService.saveTCView(v);
		}
		
		resp.sendRedirect(req.getContextPath()+ "/TCViewServlet?method=show");
		
	}
	@Override
	protected void del(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		String teaName = req.getParameter("teaName");
		String courseName = req.getParameter("courseName");
		String className = req.getParameter("className");
		String deptName = req.getParameter("deptName");
		
		//tcviewService.delTCView(Integer.valueOf(id));
		// 考虑模糊查询需要的参数。重定向 必须通过 url路径传参。
		resp.sendRedirect(req.getContextPath()+ "/TCViewServlet?method=show"
				+ "&teaName="+ToolUtil.encode(teaName)+"&courseName="+ToolUtil.encode(courseName)
				+ "&className="+ToolUtil.encode(className)+"&deptName="+ToolUtil.encode(deptName));
	}
	
	
	
	
	
}
