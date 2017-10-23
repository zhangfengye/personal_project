package com.hpe.onlinexam.servlet.teacher;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.onlinexam.po.Question;
import com.hpe.onlinexam.service.admin.CourseServiceImpl;
import com.hpe.onlinexam.service.teacher.QuestionServiceImpl;
import com.hpe.onlinexam.servlet.admin.BaseServlet;
import com.hpe.onlinexam.util.Page;
import com.hpe.onlinexam.util.ToolUtil;

/**
 * 题库管理 控制类
 * @author Administrator
 *
 */
@WebServlet("/questionServlet")
public class QuestionServlet extends BaseServlet{
	QuestionServiceImpl queService = new QuestionServiceImpl();
	CourseServiceImpl courseService = new CourseServiceImpl();
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/// 首先获取查询的页数 curpage
		// 如果 curpage为null或为“” ,给curpage默认1
		String curpage = req.getParameter("curpage");
		if(curpage == null || curpage.equals("")){
			curpage = "1";
		}
		// 调用业务类，查询 获取分页对象 page。
		Page page = new Page();
		page.setCurPage(Integer.valueOf(curpage));
		
		page = queService.findPage(page);
		// 通过req转发到questionlist.jsp页面
		req.setAttribute("page", page);
		req.getRequestDispatcher("/teacher/questionlist.jsp").forward(req, resp);
	}
	
	@Override
	protected void showUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取id信息，
		String queId = req.getParameter("id");
		// 获取分页信息，第几页
		String curpage = req.getParameter("curpage");
		// 判断 id是否为null 或者 为“” 执行新增流程，反则 修改流程。
		Question question = null;
		if(ToolUtil.isNotNull(queId)){
			// 修改流程
			question = queService.findQuestionById(Integer.valueOf(queId));
		}
		
		// 转发到questionupdat.jsp页面
		req.setAttribute("question", question);
		//  课程集合 转发到 页面     coureseService业务类
		List courseList = courseService.findAllCourse();
		req.setAttribute("courseList", courseList);
		req.setAttribute("curpage", curpage);
		req.getRequestDispatcher("/teacher/questionupdate.jsp").forward(req, resp);	
		
	}
	
	@Override
	protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String curpage = req.getParameter("curpage");
		
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		req.getParameter("");
		
		Question que = new Question();
		
		// id如果是“” 是一个新增。 否则修改。 
		
		// 重定向 list页面
		resp.sendRedirect(req.getContextPath() +"/questionServlet?method=show&curpage="+curpage);
	}
	
	
	
	
	
}
