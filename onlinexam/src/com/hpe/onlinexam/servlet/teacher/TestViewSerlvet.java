package com.hpe.onlinexam.servlet.teacher;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.onlinexam.po.Teacher;
import com.hpe.onlinexam.service.teacher.TestServiceImpl;
import com.hpe.onlinexam.servlet.admin.BaseServlet;
import com.hpe.onlinexam.util.ToolUtil;

/**
 * 统计查看 试卷控制类
 * @author Administrator
 *
 */
@WebServlet("/testViewServlet")
public class TestViewSerlvet extends BaseServlet{
	TestServiceImpl testService = new TestServiceImpl();
	@Override
	protected void show(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取 需要的结果集
		 HttpSession session = req.getSession();
		 Teacher teacher = (Teacher)session.getAttribute(ToolUtil.LOGINUSER);
		int id = teacher.getId(); // 后续通过session获取。
		List<Map<String,Object>> list = testService.findListByTeaId(id);
		req.setAttribute("testList", list);
		// 转发到testlist.jsp页面
		req.getRequestDispatcher("/teacher/testlist.jsp").forward(req, resp);
	}






}
