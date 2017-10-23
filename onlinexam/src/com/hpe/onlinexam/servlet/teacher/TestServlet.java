package com.hpe.onlinexam.servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.onlinexam.po.Course;
import com.hpe.onlinexam.po.StuClass;
import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.po.Test;
import com.hpe.onlinexam.service.admin.CourseServiceImpl;
import com.hpe.onlinexam.service.admin.StuClassServiceImpl;
import com.hpe.onlinexam.service.teacher.QuestionServiceImpl;
import com.hpe.onlinexam.service.teacher.TestServiceImpl;
import com.hpe.onlinexam.servlet.admin.BaseServlet;
import com.hpe.onlinexam.util.ToolUtil;
/**
 * 试卷发布的控制类。
 * @author Administrator
 *
 */
@WebServlet("/testServlet")
public class TestServlet extends BaseServlet{
	
	// 注入 课程和班级的业务类
	CourseServiceImpl courseService = new CourseServiceImpl();
	StuClassServiceImpl stuclaService = new StuClassServiceImpl();
	TestServiceImpl testService = new TestServiceImpl();
	QuestionServiceImpl questionService = new QuestionServiceImpl();
	
	// /testServlet?method=show
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取 testupdate.jsp需要的数据集合（  课程的集合，班级的集合， ）
		List<Course> courseList = courseService.findAllCourse();
		List<StuClass> stuclaList = stuclaService.findAll();
		
		req.setAttribute("courseList", courseList);
		req.setAttribute("stuclaList", stuclaList);
		// 转发到testupdate.jsp页面
		req.getRequestDispatcher("/teacher/testupdate.jsp").forward(req, resp);
	}
	
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取页面传递的参数。  		
		// 试卷名
		String name = req.getParameter("name");
		// 课程id
		String courseId = req.getParameter("courseId");
		// 截至时间
		String endDate = req.getParameter("endDate");
		// 考试班级的ids
		String[] classIds = req.getParameterValues("classIds");
		// 考试时长 
		String testTime = req.getParameter("testTime");
		// 题目个数
		String num = req.getParameter("num");
		// 分值
		String scores = req.getParameter("scores");
		//
		
		// 根据获取的课程id和题目个数，从题库中随机出题。
		List list = questionService.findQuestion(Integer.parseInt(courseId), Integer.parseInt(num));

		req.setAttribute("name", name);
		req.setAttribute("courseId", courseId);
		req.setAttribute("endDate", endDate);
		// 把classIds数组转化为字符串。或者去数据查询应该班级名。
		req.setAttribute("classIds", ToolUtil.arraytoString(classIds));
		req.setAttribute("testTime", testTime);
		req.setAttribute("num", Integer.valueOf(num));
		req.setAttribute("scores", Double.valueOf(scores));
		req.setAttribute("questionList", list);
		// 转发到 testdetail.jsp页面。
		req.getRequestDispatcher("/teacher/testdetail.jsp").forward(req, resp);
		
	}
	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取 发布的试卷信息
		// 试卷名
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher)session.getAttribute(ToolUtil.LOGINUSER);
		String name = req.getParameter("name");
		// 课程id
		String courseId = req.getParameter("courseId");
		// 截至时间
		String endDate = req.getParameter("endDate");
		// 考试班级的ids
		String classIds = req.getParameter("classIds");
		// 考试时长 
		String testTime = req.getParameter("testTime");
		// 分值
		String scores = req.getParameter("scores");
		//试题主键id集合
		String[] ids = req.getParameterValues("id");
		
		Test test = new Test();
		// 通过当前登录用户session中的数据，得到教师的信息id 2017070401 = 暂时固定
		test.setClassIds(classIds);
		test.setCourseId(Integer.valueOf(courseId));
		test.setEndDate(ToolUtil.getDate(endDate));
		test.setName(name);
		test.setQuestions(ToolUtil.arraytoString(ids));
		test.setScores(Double.valueOf(scores));
		test.setTeacherId(teacher.getId());
		test.setTestTime(Integer.valueOf(testTime));
		
		testService.insertTest(test);
		// 转到近期考试列表页面。testlist.jsp
		resp.getWriter().println("发布成功！请查看考试列表！");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
